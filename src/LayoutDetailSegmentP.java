import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class LayoutDetailSegmentP {
	private String detail;
	private LayoutDetailSegmentQ layoutDetailSegmentQ; 
	
	public LayoutDetailSegmentP(Register reg) {
		this.detail = "";
		this.setDetailSegmentP(reg);
		
		this.layoutDetailSegmentQ = new LayoutDetailSegmentQ(reg);
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
		this.detail += StringUtils.leftPad(""+position, 5, '0') ; //N� Sequencial do Registro no Lote
		this.detail += "P"; //C�d. Segmento do Registro Detalhe
		this.detail += " "; //Uso Exclusivo FEBRABAN/CNAB
		this.detail += "01"; //C�digo de Movimento Remessa
	}
	
	private void setCCDetail() {
		this.detail += "04770"; //Ag�ncia Mantenedora da Conta
		this.detail += "8"; //D�gito Verificador da Ag�ncia
		this.detail += "000000022000"; //N�mero da Conta Corrente
		this.detail += "0"; //D�gito Verificador da Conta
		this.detail += "0"; //D�gito Verificador da Ag/Conta
	}
	
	private void setNossoNumero(String numeroTitulo) {
		//Identifica��o do T�tulo no Banco
		this.detail += "2963846"; //n�mero do conv�nio
		this.detail += StringUtils.leftPad(numeroTitulo, 10, '0'); //n�mero sequencial
		this.detail += "   ";
	}
	
	private void setCaracteristicaCobranca() {
		this.detail += "7"; //C�digo da Carteira
		this.detail += "1"; //Forma de Cadastr. do T�tulo no Banco
		this.detail += "1"; //Tipo de Documento
		this.detail += "2"; //Identifica��o da Emiss�o do Bloqueto
		this.detail += "1"; //Identifica��o da Distribui��o
	}
	
	private void setNumeroDocumento(String numeroDocumento) {
		this.detail += StringUtils.leftPad(numeroDocumento, 15, '0') ;//N�mero do Documento de Cobran�a
	}
	
	private void setVencimento(Date vencimento) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		String day = simpleDateFormat.format(vencimento).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(vencimento).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("yyyy");
		String year = simpleDateFormat.format(vencimento).toUpperCase();		
		this.detail += day + monthy + year; //Data de Vencimento do T�tulo
	}
	
	private void setValorTitulo(float valor) {
		String valorFormatted = String.format(java.util.Locale.US, "%.2f", valor);
		valorFormatted  = valorFormatted .replace(".", "");
		this.detail += StringUtils.leftPad(valorFormatted, 15, '0'); //Valor Nominal do T�tulo
	}
	
	private void setAgenciaCobradora() {
		this.detail += "00000"; //Ag�ncia Encarregada da Cobran�a
	}
	
	private void setDigitoVerificadorAgenciaCobradora() {
		this.detail += " "; //D�gito Verificador da Ag�ncia
	}
	
	private void setEspecieTitulo() {
		this.detail += "01"; //Esp�cie do T�tulo
	}
	
	private void setAceite() {
		this.detail += "A"; //Identific. de T�tulo Aceito/N�o Aceito
	}
	
	private void setDataEmissaoTitulo(Date dataEmissaoTitulo) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		String day = simpleDateFormat.format(dataEmissaoTitulo).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(dataEmissaoTitulo).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("yyyy");
		String year = simpleDateFormat.format(dataEmissaoTitulo).toUpperCase();		
		this.detail += day + monthy + year; //Data da Emiss�o do T�tulo
	}
	
	private void setJuros(int valorMoraDia) {
		this.detail += "1"; //C�digo do Juros de Mora
		this.detail += "00000000"; //Data do Juros de Mora
		
		float valorMora = ((float) valorMoraDia) / 100F;
		String valorMoraFormatted = String.format(java.util.Locale.US, "%.2f", valorMora);
		valorMoraFormatted = valorMoraFormatted.replace(".", "");
		this.detail += StringUtils.leftPad(valorMoraFormatted, 15, '0'); //Juros de Mora por Dia/Taxa
	}
	
	private void setDesconto1() {
		this.detail += "0"; //C�digo do Desconto 1
		this.detail += "00000000"; //Data do Desconto 1
		this.detail += "000000000000000"; //Valor/Percentual a ser Concedido
	}
	
	private void setValorIOF() {
		this.detail += "000000000000000"; //Valor do IOF a ser Recolhido
	}
	
	private void setValorAbatimento() {
		this.detail += "000000000000000"; //Valor do Abatimento
	}
	
	private void setUsoEmpresaCedente() {
		this.detail += String.format("%25s", ""); //Identifica��o do T�tulo na Empresa		
	}
	
	private void setCodigoProtesto() {
		this.detail += "3"; //C�digo para Protesto
	}
	
	private void setPrazoProtesto() {
		this.detail += "00"; //N�mero de Dias para Protesto
	}
	
	private void setCodigoBaixaDevolucao() {
		this.detail += "0"; //C�digo para Baixa/Devolu��o
	}
	
	private void setPrazoBaixaDevolucao() {
		this.detail += "000"; //N�mero de Dias para Baixa/Devolu��o
	}
	
	private void setCodigoMoeda() {
		this.detail += "09"; //C�digo da Moeda
	}
	
	private void setNumeroContrato() {
		this.detail += "0000000000"; //N� do Contrato da Opera��o de Cr�d.
	}
	
	private void setUsoLivreBancoEmpresa() {
		this.detail += " "; //Uso livre banco/empresa ou autoriza��o de pagamento parcial
	}
	
	private void setDetailSegmentP(Register reg) {
		this.setControleDetail();
		this.setServicoDetail(reg.getPosition());
		this.setCCDetail();
		this.setNossoNumero(reg.getNumeroTitulo());
		this.setCaracteristicaCobranca();
		this.setNumeroDocumento(reg.getNumeroDocumento());
		this.setVencimento(reg.getVencimento());
		this.setValorTitulo(reg.getValor());
		this.setAgenciaCobradora();
		this.setDigitoVerificadorAgenciaCobradora();
		this.setEspecieTitulo();
		this.setAceite();
		this.setDataEmissaoTitulo(reg.getDataEmissao());
		this.setJuros(reg.getValorMora());
		this.setDesconto1();
		this.setValorIOF();
		this.setValorAbatimento();
		this.setUsoEmpresaCedente();
		this.setCodigoProtesto();
		this.setPrazoProtesto();
		this.setCodigoBaixaDevolucao();
		this.setPrazoBaixaDevolucao();
		this.setCodigoMoeda();
		this.setNumeroContrato();
		this.setUsoLivreBancoEmpresa();
	}
	
	public String getDetailSegmentP() {
		return this.detail;
	}
	
	public String getDetailSegmentQ() {
		return this.layoutDetailSegmentQ.getDetail();
	}
}