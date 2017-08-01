import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class LayoutHeaderFile {
	private String header, trailer;
	
	public LayoutHeaderFile() {
		this.setHeaderFile();
	}
	
	private void setControleFile() {
		this.header += "001"; //Código do Banco na Compensação
		this.header += "0000"; //Lote de Serviço
		this.header += "0"; //Tipo de Registro
	}
	
	private void setCNABFile() {
		this.header += "         "; //Uso Exclusivo FEBRABAN / CNAB
	}
	
	private void setEmpresaFile() {
		this.header += "2"; //Tipo de Inscrição da Empresa
		this.header += "51370575000137"; //Número de Inscrição da Empresa
		
		//Código do Convênio no Banco
		this.header += StringUtils.leftPad("2963846", 9, '0'); //Número do convênio de cobrança BB
		this.header += "0014"; //Cobrança Cedente BB
		this.header += "17"; //Número da carteira de cobrança BB
		this.header += "035"; //Número da variação da carteira de cobrança BB
		this.header += "  "; //Campo reservado BB
		
		this.header += "04770"; //Agência Mantenedora da Conta
		this.header += "8"; //Dígito Verificador da Agência
		this.header += "000000022000"; //Número da Conta Corrente
		this.header += "0"; //Dígito Verificador da Conta
		this.header += "0"; //Dígito Verificador da Ag/Conta
		this.header += "PROGRESSO E DESENVOLVIMENTO DE"; //Nome da Empresa
	}
	
	private void setNomeDoBancoFile() {
		this.header += "BANCO DO BRASIL S.A.          ";
	}
	
	private void setCNAB2File() {
		this.header += "          "; //Uso Exclusivo FEBRABAN / CNAB
	}
	
	private void setArquivoFile() {
		this.header += "1"; //Código Remessa / Retorno
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		String day = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("yyyy");
		String year = simpleDateFormat.format(date).toUpperCase();		
		this.header += day + monthy + year; //Data de Geração do Arquivo

		simpleDateFormat = new SimpleDateFormat("HH");
		String hour = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("mm");
		String minute = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("ss");
		String second = simpleDateFormat.format(date).toUpperCase();
		this.header += hour + minute + second; //Data de Geração do Arquivo

		this.header += "000000"; //Número Seqüencial do Arquivo
		this.header += "083"; //No da Versão do Layout do Arquivo
		this.header += "00000"; //Densidade de Gravação do Arquivo
	}
	
	private void setReservadoBancoFile() {
		this.header += String.format("%20s", ""); //Para Uso Reservado do Banco
	}
	
	private void setReservadoEmpresa() {
		this.header += String.format("%20s", ""); //Para Uso Reservado da Empresa
	}
	
	private void setCNAB3File() {
		this.header += String.format("%29s", ""); //Uso Exclusivo FEBRABAN / CNAB
	}
	
	public void setHeaderFile() {
		this.header = "";
		this.setControleFile();
		this.setCNABFile();
		this.setEmpresaFile();
		this.setNomeDoBancoFile();
		this.setCNAB2File();
		this.setArquivoFile();
		this.setReservadoBancoFile();
		this.setReservadoEmpresa();
		this.setCNAB3File();
	}
	
	public String getHeaderFile() {
		return this.header;
	}
	
	private void setControleTrailerFile() {
		this.trailer += "001"; //Código do Banco na Compensação
		this.trailer += "9999"; //Lote de Serviço
		this.trailer += "9"; //Tipo de Registro
	}
	
	private void setCNABTrailerFile() {
		this.trailer += "         "; //Uso Exclusivo FEBRABAN / CNAB	
	}
	
	private void setTrailerTotais(int quantidadeLotesFile, int quantidadeRegistrosArquivo) {
		this.trailer += StringUtils.leftPad(""+quantidadeLotesFile, 6, '0'); //Quantidade de Lotes do Arquivo
		this.trailer += StringUtils.leftPad(""+(quantidadeRegistrosArquivo * 2 + 4), 6, '0'); //Quantidade de Registros do Arquivo
		this.trailer += StringUtils.leftPad("0", 6, '0'); //Qtde de Contas p/ Conc. (Lotes)
	}
	
	private void setCNAB2TrailerFile() {
		this.trailer += String.format("%205s", ""); //Uso Exclusivo FEBRABAN / CNAB
	}
	
	public void setTrailerFile(int quantidadeLotesFile, int quantidadeRegistrosArquivo) {
		this.trailer = "";
		this.setControleTrailerFile();
		this.setCNABTrailerFile();
		this.setTrailerTotais(quantidadeLotesFile, quantidadeRegistrosArquivo);
		this.setCNAB2TrailerFile();
	}
	
	public String getTrailerFile() {
		return this.trailer;
	}
}
