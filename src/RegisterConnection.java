import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterConnection {
	private Connection con;
	private Statement stm;
	
	public RegisterConnection() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	    	this.con = DriverManager.getConnection("jdbc:ucanaccess://\\\\proaxdtb\\sig\\CNAB240CobrancaTitulos.mdb");
	        this.stm = con.createStatement();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public ResultSet getRegisters() {
		try {
			return this.stm.executeQuery("SELECT * FROM CNAB240_COBRANCA_TITULOS WHERE gerado = 0");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public boolean updateRegister(String nTit, String file) {
		String query = "UPDATE CNAB240_COBRANCA_TITULOS SET nTit = ?, gerado = ?, path = ? WHERE nTit = ? AND gerado = 0";
		
		try {
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setString(1, nTit);
			pst.setBoolean(2, true);
			pst.setString(3, file);
			pst.setString(4, nTit);
			
			pst.executeUpdate();
			pst.close();
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		
		return true;
	}
}
