import org.apache.commons.lang.StringUtils;

public class LayoutDetailSegmentQ {
	private String detail;
	
	public LayoutDetailSegmentQ(Register reg) {
		this.detail = "";
		this.setDetailSegmentQ(reg);
	}
	
	public String getDetail() {
		return this.detail;
	}
	
	private void setControleDetail() {
		this.detail += "001"; //Banco
		this.detail += "0001"; //Lote
		this.detail += "3"; //Tipo Registro
	}
	
	private void setServicoDetail(int position) {
		this.detail += StringUtils.leftPad(""+(position + 1), 5, '0') ; //Nº Sequencial do Registro no Lote
		this.detail += "Q"; //Cód. Segmento do Registro Detalhe
		this.detail += " "; //Uso Exclusivo FEBRABAN/CNAB
		this.detail += "01"; //Código de Movimento Remessa
	}
	
	private void setDadosPagador(Register register) {
		this.detail += "1"; //Tipo de Inscrição
		this.detail += StringUtils.leftPad(register.getNumeroInscricao(), 15, '0'); //Número de Inscrição
		
		String nome = (register.getNome().length() > 40) ? register.getNome().substring(0, 40) : register.getNome(); 
		this.detail += StringUtils.rightPad(nome, 40, ' '); //Nome
		
		String endereco = (register.getEndereco().length() > 40) ? register.getEndereco().substring(0, 40) : register.getEndereco();
		this.detail += StringUtils.rightPad(endereco, 40, ' '); //Endereço
		
		String bairro = (register.getBairro().length() > 15) ? register.getBairro().substring(0, 15) : register.getBairro();
		this.detail += StringUtils.rightPad(bairro, 15, ' '); //Bairro
		
		this.detail += register.getCep(); //CEP + Sufixo do CEP
		
		String cidade = (register.getCidade().length() > 15) ? register.getCidade().substring(0, 15) : register.getCidade();
		this.detail += StringUtils.rightPad(cidade, 15, ' '); //Cidade
		
		this.detail += register.getUf(); //Unidade da Federação
	}
	
	private void setSacadorAvalista(Register register) {
		this.detail += "0"; //Tipo de Inscrição
		this.detail += StringUtils.leftPad("", 15, '0'); //Número de Inscrição
		this.detail += StringUtils.rightPad("", 40, ' '); //Nome do Sacador/Avalista
	}
	
	private void setBancoCorrespondente() {
		this.detail += "000"; //Cód. Bco. Corresp. na Compensação
	}
	
	private void setNossoNumeroBancoCorrepondente() {
		this.detail += String.format("%20s", ""); //Nosso Nº no Banco Correspondente 
	}
	
	private void setCNAB() {
		this.detail += "        ";
	}
	
	private void setDetailSegmentQ(Register reg) {
		this.setControleDetail();
		this.setServicoDetail(reg.getPosition());
		this.setDadosPagador(reg);
		this.setSacadorAvalista(reg);
		this.setBancoCorrespondente();
		this.setNossoNumeroBancoCorrepondente();
		this.setCNAB();
	}
}