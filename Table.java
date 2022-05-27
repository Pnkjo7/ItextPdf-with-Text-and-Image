import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;



public class Table {
	static String a="Pankaj";
	public static void main(String[] args) throws DocumentException, IOException {
		//String file_name = "F:/generate_pdf/sample3.pdf";
		Document document = new Document();
//		try {
//			File file = new File(file_name);
//			Desktop desktop = Desktop.getDesktop();
//			// if(file.exists()) desktop.open(file);
//
//			// let's try to open PDF file
//			// file = new File("/Users/pankaj/java.pdf");
//			if (file.exists())
//				desktop.open(file);
//
//		} catch (Exception fe) {
//			System.out.println(" Unable to Open File");
//		}
		PdfPTable table = new PdfPTable(new float[] { 0.5f, 1.8f, 1.8f, 1.7f, 1.4f, 1.3f, 0.9f, 0.6f});
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell("S.no");
		table.addCell("Cr no.");
		table.addCell("Name");
		table.addCell("Age/Gender");
		table.addCell("Test");
		table.addCell("Sample Number");
		table.addCell("Date");
		table.addCell("QR Code");
		
		table.setHeaderRows(1);

		ByteArrayOutputStream out = QRCode.from(a).to(ImageType.PNG).withSize(5, 5).stream();
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		FileOutputStream fos = new FileOutputStream("barcode.png");
		fos.write(out.toByteArray());
		fos.flush();
		fos.close();
		Image img1 = Image.getInstance("barcode.png");
		
		
		PdfPCell[] cells = table.getRow(0).getCells();
		for (int j = 0; j < cells.length; j++) {
			cells[j].setBackgroundColor(BaseColor.GRAY);
		}
		for (int i = 1; i < 5; i++) {
			table.addCell("i");
			table.addCell("123456789012345:" + i);
			table.addCell("Pankaj Chauhan:" + i);
			table.addCell("23 Male:" + i);
			table.addCell("Urine test:" + i);
			table.addCell("1234567890:" + i);
			table.addCell("2022/05/25:" + i);
			table.addCell(new PdfPCell(img1));
		}
		PdfWriter.getInstance(document, new FileOutputStream("F:/generate_pdf/sample3.pdf"));
		document.open();
		document.add(table);
		document.close();
		System.out.println("Done");
		  
	}
	

}