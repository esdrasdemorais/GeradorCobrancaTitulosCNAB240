import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class LayoutHeaderFile {
	private String header, trailer;
	
	public LayoutHeaderFile() {
		this.setHeaderFile();
	}
	
	private void setControleFile() {
		this.header += "001"; //C�digo do Banco na Compensa��o
		this.header += "0000"; //Lote de Servi�o
		this.header += "0"; //Tipo de Registro
	}
	
	private void setCNABFile() {
		this.header += "         "; //Uso Exclusivo FEBRABAN / CNAB
	}
	
	private void setEmpresaFile() {
		this.header += "2"; //Tipo de Inscri��o da Empresa
		this.header += "51370575000137"; //N�mero de Inscri��o da Empresa
		
		//C�digo do Conv�nio no Banco
		this.header += StringUtils.leftPad("2963846", 9, '0'); //N�mero do conv�nio de cobran�a BB
		this.header += "0014"; //Cobran�a Cedente BB
		this.header += "17"; //N�mero da carteira de cobran�a BB
		this.header += "035"; //N�mero da varia��o da carteira de cobran�a BB
		this.header += "  "; //Campo reservado BB
		
		this.header += "04770"; //Ag�ncia Mantenedora da Conta
		this.header += "8"; //D�gito Verificador da Ag�ncia
		this.header += "000000022000"; //N�mero da Conta Corrente
		this.header += "0"; //D�gito Verificador da Conta
		this.header += "0"; //D�gito Verificador da Ag/Conta
		this.header += "PROGRESSO E DESENVOLVIMENTO DE"; //Nome da Empresa
	}
	
	private void setNomeDoBancoFile() {
		this.header += "BANCO DO BRASIL S.A.          ";
	}
	
	private void setCNAB2File() {
		this.header += "          "; //Uso Exclusivo FEBRABAN / CNAB
	}
	
	private void setArquivoFile() {
		this.header += "1"; //C�digo Remessa / Retorno
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		String day = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("yyyy");
		String year = simpleDateFormat.format(date).toUpperCase();		
		this.header += day + monthy + year; //Data de Gera��o do Arquivo

		simpleDateFormat = new SimpleDateFormat("HH");
		String hour = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("mm");
		String minute = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("ss");
		String second = simpleDateFormat.format(date).toUpperCase();
		this.header += hour + minute + second; //Data de Gera��o do Arquivo

		this.header += "000000"; //N�mero Seq�encial do Arquivo
		this.header += "083"; //No da Vers�o do Layout do Arquivo
		this.header += "00000"; //Densidade de Grava��o do Arquivo
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
		this.trailer += "001"; //C�digo do Banco na Compensa��o
		this.trailer += "9999"; //Lote de Servi�o
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
