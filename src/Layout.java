import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Layout {
	LayoutHeaderFile layoutHeaderFile;
	LayoutHeaderLote layoutHeaderLote;
	ArrayList<LayoutDetailSegmentP> arrDetails;
	ArrayList<Register> arrRegisters;
	RegisterConnection con;
	
	public Layout() {
		this.layoutHeaderFile = new LayoutHeaderFile();
		this.layoutHeaderLote = new LayoutHeaderLote();

		try {
			this.con = new RegisterConnection();
			ResultSet rs = this.con.getRegisters();
			
			if (rs.next() == false) {
				JOptionPane.showMessageDialog(null, "Não há boletos para gerar arquivo de remessa.");
				System.exit(0);
			}
			
			this.arrDetails = new ArrayList<LayoutDetailSegmentP>();
			this.arrRegisters = new ArrayList<Register>();
			int i = 1;
			do {
				Register register = new Register();
				register.setPosition(i);
				register.setNumeroTitulo(rs.getString("nTit"));
				register.setNumeroDocumento(rs.getString("numeroDocumento"));
				register.setVencimento(rs.getDate("vencimento"));
				register.setValor(rs.getFloat("valor"));
				register.setDataEmissao(rs.getDate("dataEmissao"));
				register.setValorMora(rs.getInt("valorMora"));
				register.setNumeroInscricao(rs.getString("numeroInscricao"));
				register.setNome(rs.getString("nome"));
				register.setEndereco(rs.getString("endereco"));
				register.setBairro(rs.getString("bairro"));
				register.setCep(rs.getString("cep"));
				register.setCidade(rs.getString("cidade"));
				register.setUf(rs.getString("uf"));
				
				this.arrDetails.add(new LayoutDetailSegmentP(register));
				this.arrRegisters.add(register);
				i++;
			} while(rs.next());
			rs.close();
			
			this.layoutHeaderLote.setTrailerLote(arrRegisters.size(), this.getValorTotal(arrRegisters));
			
			int quantidadeLotesFile = 1;
			this.layoutHeaderFile.setTrailerFile(quantidadeLotesFile, arrRegisters.size());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private float getValorTotal(ArrayList<Register> arrRegisters) {
		float valorTotal = 0F;
		
		for (Register register : arrRegisters) {
			valorTotal += register.getValor();
		}
			
		return valorTotal;
	}
	
	private String mountLayout() {
		String fileContent = this.layoutHeaderFile.getHeaderFile() + System.lineSeparator();
		fileContent += this.layoutHeaderLote.getHeaderLote() + System.lineSeparator();
		
		for (LayoutDetailSegmentP detail : this.arrDetails) {
			fileContent += detail.getDetailSegmentP() + System.lineSeparator();
			fileContent += detail.getDetailSegmentQ() + System.lineSeparator();
		}
		
		fileContent += this.layoutHeaderLote.getTrailerLote() + System.lineSeparator();
		fileContent += this.layoutHeaderFile.getTrailerFile();		
System.out.println(fileContent);
		return fileContent;
	}
	
	private void log(String file) {
		for (Register register : this.arrRegisters) {
			this.con.updateRegister(register.getNumeroTitulo(), file);
		}
	}
	
	public void print(String path) {
		String fileContent = this.mountLayout();
		
		char buffer[] = new char[fileContent.length()];
		fileContent.getChars(0, fileContent.length(), buffer, 0);

		try {
			FileWriter file = new FileWriter(path);
			file.write(buffer);
			file.close();
			
			this.log(path);
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
}