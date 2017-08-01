import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class LayoutPrint {
	private static String pathFileOutput = "\\\\fileserver\\proguaru\\docs_gf\\Contas a Receber\\BBRemessa\\REM_CNAB240_BOLETO_";
	
	public static void main(String[] args) throws Exception {
		String fileName = "";
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD");
		String day = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("MM");
		String monthy = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("YYYY");
		String year = simpleDateFormat.format(date).toUpperCase();		
		fileName += day + monthy + year;
		
		simpleDateFormat = new SimpleDateFormat("HH");
		String hour = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("mm");
		String minute = simpleDateFormat.format(date).toUpperCase();
		simpleDateFormat = new SimpleDateFormat("ss");
		String second = simpleDateFormat.format(date).toUpperCase();
		fileName += "_" + hour + minute + second;
		
		Layout l = new Layout();
		l.print(LayoutPrint.pathFileOutput + fileName + ".txt");
		
		JOptionPane.showMessageDialog(null, "O arquivo de remessa foi criado em " + 
				LayoutPrint.pathFileOutput + fileName + ".txt");
		System.exit(0);
	}
}