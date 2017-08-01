import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class LayoutHeaderLote {
	private String header, trailer;

	public LayoutHeaderLote() {
		this.setHeaderLote();
	}
	
	private void setControleLote() {
		this.header += "001"; //Código do Banco na Compensação
		this.header += "0001"; //Lote de Serviço
		this.header += "1"; //Tipo de Registro
	}
	
	private void setServicoLote() {
		this.header += "R"; //?(C ou R) Tipo de Operação;
		this.header += "01"; //Tipo de Serviço
		this.header += "  "; //Uso Exclusivo FEBRABAN/CNAB
		this.header += "042"; //Nº da Versão do Layout do Lote
	}
	
	private void setCNABLote() {
		this.header += " "; //Uso Exclusivo FEBRABAN / CNAB
	}
	
	private void setEmpresaLote() {
		this.header += "2"; //Tipo de Inscrição da Empresa
		this.header += "051370575000137"; //Número de Inscrição da Empresa
		
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
	
	private void setInformacao1Lote() {
		this.header += String.format("%40s", ""); //Mensagem 1
	}
	
	private void setInformacao2Lote() {
		this.header += String.format("%40s", ""); //Mensagem 2
	}
	
	private void setControleCobrancaLote() {
		this.header += "00000000"; //Número Remessa/Retorno
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		String day = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("yyyy");
		String year = simpleDateFormat.format(date).toUpperCase();		
		this.header += day + monthy + year; //Data de Gravação Remessa/Retorno
	}
	
	private void setDataCredito() {
		this.header += "00000000"; //Data do Crédito
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
		this.trailer += "001"; //Código do Banco na Compensação
		this.trailer += "0001"; //Lote de Serviço
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
		this.trailer += String.format("%8s", ""); //Número do Aviso de Lançamento 
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
