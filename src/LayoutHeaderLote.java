import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class LayoutHeaderLote {
	private String header, trailer;

	public LayoutHeaderLote() {
		this.setHeaderLote();
	}
	
	private void setControleLote() {
		this.header += "001"; //C�digo do Banco na Compensa��o
		this.header += "0001"; //Lote de Servi�o
		this.header += "1"; //Tipo de Registro
	}
	
	private void setServicoLote() {
		this.header += "R"; //?(C ou R) Tipo de Opera��o;
		this.header += "01"; //Tipo de Servi�o
		this.header += "  "; //Uso Exclusivo FEBRABAN/CNAB
		this.header += "042"; //N� da Vers�o do Layout do Lote
	}
	
	private void setCNABLote() {
		this.header += " "; //Uso Exclusivo FEBRABAN / CNAB
	}
	
	private void setEmpresaLote() {
		this.header += "2"; //Tipo de Inscri��o da Empresa
		this.header += "051370575000137"; //N�mero de Inscri��o da Empresa
		
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
	
	private void setInformacao1Lote() {
		this.header += String.format("%40s", ""); //Mensagem 1
	}
	
	private void setInformacao2Lote() {
		this.header += String.format("%40s", ""); //Mensagem 2
	}
	
	private void setControleCobrancaLote() {
		this.header += "00000000"; //N�mero Remessa/Retorno
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		String day = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("yyyy");
		String year = simpleDateFormat.format(date).toUpperCase();		
		this.header += day + monthy + year; //Data de Grava��o Remessa/Retorno
	}
	
	private void setDataCredito() {
		this.header += "00000000"; //Data do Cr�dito
	}

	private void setCNAB2Lote() {
		this.header += String.format("%33s", ""); //Uso Exclusivo FEBRABAN / CNAB
	}
	
	public void setHeaderLote() {
		this.header = "";
		this.setControleLote();
		this.setServicoLote();
		this.setCNABLote();
		this.setEmpresaLote();
		this.setInformacao1Lote();
		this.setInformacao2Lote();
		this.setControleCobrancaLote();
		this.setDataCredito();
		this.setCNAB2Lote();
	}
	
	public String getHeaderLote() {
		return this.header;
	}
	
	private void setControleTrailerLote() {
		this.trailer += "001"; //C�digo do Banco na Compensa��o
		this.trailer += "0001"; //Lote de Servi�o
		this.trailer += "5"; //Tipo de Registro
	}
	
	private void setCNABTrailerLote() {
		this.trailer += "         "; //Uso Exclusivo FEBRABAN / CNAB
	}
	
	private void setQtdeRegistrosTrailerLote(int quantidadeRegistros) {
		this.trailer += StringUtils.leftPad(""+(quantidadeRegistros * 2 + 2), 6, '0');
	}
	
	/*private void setTrailerLoteTotalizacaoCobrancaSimples(int quantidadeLotesFile, float valorTotalTitulosCarteira) {
		this.trailer += StringUtils.leftPad(""+quantidadeLotesFile, 6, '0');
		
		String vTTCFormatted = String.format(java.util.Locale.US, "%.2f", valorTotalTitulosCarteira);
		vTTCFormatted = vTTCFormatted.replace(".", "");
		this.trailer += StringUtils.leftPad(vTTCFormatted, 17, '0');
	}
	
	private void setTrailerLoteTotalizacaoCobrancaVinculada(int quantidadeTitulosCobranca, float valorTotalTitulosCarteira) {
		this.trailer += StringUtils.leftPad(""+quantidadeTitulosCobranca, 6, '0');
		
		String vTTCFormatted = String.format(java.util.Locale.US, "%.2f", valorTotalTitulosCarteira);
		vTTCFormatted = vTTCFormatted.replace(".", "");
		this.trailer += StringUtils.leftPad(""+vTTCFormatted, 17, '0');
	}
	
	private void setTrailerLoteTotalizacaoCobrancaCaucionada(int quantidadeTitulosCobranca, float valorTotalTitulosCarteira) {
		this.trailer += StringUtils.leftPad(""+quantidadeTitulosCobranca, 6, '0');
		
		String vTTCFormatted = String.format(java.util.Locale.US, "%.2f", valorTotalTitulosCarteira);
		vTTCFormatted = vTTCFormatted.replace(".", "");
		this.trailer += StringUtils.leftPad(""+vTTCFormatted, 17, '0');
	}
	
	private void setTrailerLoteTotalizacaoCobrancaDescontada(int quantidadeTitulosCobranca, float valorTotalTitulosCarteira) {
		this.trailer += StringUtils.leftPad(""+quantidadeTitulosCobranca, 6, '0');
		
		String vTTCFormatted = String.format(java.util.Locale.US, "%.2f", valorTotalTitulosCarteira);
		vTTCFormatted = vTTCFormatted.replace(".", "");
		this.trailer += StringUtils.leftPad(vTTCFormatted, 17, '0');
	}
	
	private void setNumeroAvisoTrailerLote() {
		this.trailer += String.format("%8s", ""); //N�mero do Aviso de Lan�amento 
	}
	
	private void setCNAB2TrailerLote() {
		this.trailer += String.format("%117s", ""); //Uso Exclusivo FEBRABAN / CNAB
	}*/
	
	private void setCNAB2TrailerLote() {
		this.trailer += String.format("%217s", ""); //Uso Exclusivo FEBRABAN / CNAB
	}
	
	public void setTrailerLote(int quantidadeRegistrosLote, float valorTotal) {
		this.trailer = "";
		this.setControleTrailerLote();
		this.setCNABTrailerLote();
		this.setQtdeRegistrosTrailerLote(quantidadeRegistrosLote);
		/*this.setTrailerLoteTotalizacaoCobrancaSimples(quantidadeRegistrosLote, valorTotal);
		this.setTrailerLoteTotalizacaoCobrancaVinculada(quantidadeRegistrosLote, valorTotal);
		this.setTrailerLoteTotalizacaoCobrancaCaucionada(quantidadeRegistrosLote, valorTotal);
		this.setTrailerLoteTotalizacaoCobrancaDescontada(quantidadeRegistrosLote, valorTotal);
		this.setNumeroAvisoTrailerLote();
		this.setCNAB2TrailerLote();*/
		this.setCNAB2TrailerLote();
	}
	
	public String getTrailerLote() {
		return this.trailer;
	}
}
