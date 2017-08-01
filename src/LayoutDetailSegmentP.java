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
		this.detail += StringUtils.leftPad(""+position, 5, '0') ; //Nº Sequencial do Registro no Lote
		this.detail += "P"; //Cód. Segmento do Registro Detalhe
		this.detail += " "; //Uso Exclusivo FEBRABAN/CNAB
		this.detail += "01"; //Código de Movimento Remessa
	}
	
	private void setCCDetail() {
		this.detail += "04770"; //Agência Mantenedora da Conta
		this.detail += "8"; //Dígito Verificador da Agência
		this.detail += "000000022000"; //Número da Conta Corrente
		this.detail += "0"; //Dígito Verificador da Conta
		this.detail += "0"; //Dígito Verificador da Ag/Conta
	}
	
	private void setNossoNumero(String numeroTitulo) {
		//Identificação do Título no Banco
		this.detail += "2963846"; //número do convênio
		this.detail += StringUtils.leftPad(numeroTitulo, 10, '0'); //número sequencial
		this.detail += "   ";
	}
	
	private void setCaracteristicaCobranca() {
		this.detail += "7"; //Código da Carteira
		this.detail += "1"; //Forma de Cadastr. do Título no Banco
		this.detail += "1"; //Tipo de Documento
		this.detail += "2"; //Identificação da Emissão do Bloqueto
		this.detail += "1"; //Identificação da Distribuição
	}
	
	private void setNumeroDocumento(String numeroDocumento) {
		this.detail += StringUtils.leftPad(numeroDocumento, 15, '0') ;//Número do Documento de Cobrança
	}
	
	private void setVencimento(Date vencimento) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		String day = simpleDateFormat.format(vencimento).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(vencimento).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("yyyy");
		String year = simpleDateFormat.format(vencimento).toUpperCase();		
		this.detail += day + monthy + year; //Data de Vencimento do Título
	}
	
	private void setValorTitulo(float valor) {
		String valorFormatted = String.format(java.util.Locale.US, "%.2f", valor);
		valorFormatted  = valorFormatted .replace(".", "");
		this.detail += StringUtils.leftPad(valorFormatted, 15, '0'); //Valor Nominal do Título
	}
	
	private void setAgenciaCobradora() {
		this.detail += "00000"; //Agência Encarregada da Cobrança
	}
	
	private void setDigitoVerificadorAgenciaCobradora() {
		this.detail += " "; //Dígito Verificador da Agência
	}
	
	private void setEspecieTitulo() {
		this.detail += "01"; //Espécie do Título
	}
	
	private void setAceite() {
		this.detail += "A"; //Identific. de Título Aceito/Não Aceito
	}
	
	private void setDataEmissaoTitulo(Date dataEmissaoTitulo) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		String day = simpleDateFormat.format(dataEmissaoTitulo).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(dataEmissaoTitulo).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("yyyy");
		String year = simpleDateFormat.format(dataEmissaoTitulo).toUpperCase();		
		this.detail += day + monthy + year; //Data da Emissão do Título
	}
	
	private void setJuros(int valorMoraDia) {
		this.detail += "1"; //Código do Juros de Mora
		this.detail += "00000000"; //Data do Juros de Mora
		
		float valorMora = ((float) valorMoraDia) / 100F;
		String valorMoraFormatted = String.format(java.util.Locale.US, "%.2f", valorMora);
		valorMoraFormatted = valorMoraFormatted.replace(".", "");
		this.detail += StringUtils.leftPad(valorMoraFormatted, 15, '0'); //Juros de Mora por Dia/Taxa
	}
	
	private void setDesconto1() {
		this.detail += "0"; //Código do Desconto 1
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
		this.detail += String.format("%25s", ""); //Identificação do Título na Empresa		
	}
	
	private void setCodigoProtesto() {
		this.detail += "3"; //Código para Protesto
	}
	
	private void setPrazoProtesto() {
		this.detail += "00"; //Número de Dias para Protesto
	}
	
	private void setCodigoBaixaDevolucao() {
		this.detail += "0"; //Código para Baixa/Devolução
	}
	
	private void setPrazoBaixaDevolucao() {
		this.detail += "000"; //Número de Dias para Baixa/Devolução
	}
	
	private void setCodigoMoeda() {
		this.detail += "09"; //Código da Moeda
	}
	
	private void setNumeroContrato() {
		this.detail += "0000000000"; //Nº do Contrato da Operação de Créd.
	}
	
	private void setUsoLivreBancoEmpresa() {
		this.detail += " "; //Uso livre banco/empresa ou autorização de pagamento parcial
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