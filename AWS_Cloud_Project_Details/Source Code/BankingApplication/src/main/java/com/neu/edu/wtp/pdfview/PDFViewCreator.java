package com.neu.edu.wtp.pdfview;

import java.util.ArrayList;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.neu.edu.wtp.pojo.Transaction;

public class PDFViewCreator extends AbstractITextPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		ArrayList<Transaction> transactionList = (ArrayList<Transaction>) model.get("transactionList");
		Long customerAccountNumber=(Long)model.get("customerAccountNumber");
        
		document.add(new Paragraph("Account Statement"));
         
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {1.0f, 1.0f, 1.0f, 1.0f, 2.5f,2.5f,1.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
         
        // write table header
        cell.setPhrase(new Phrase("Transaction Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("From", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("To", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Transaction Type", font));
        table.addCell(cell);
         
        // write table row data
        for (Transaction transaction : transactionList) {
            table.addCell(Long.toString(transaction.getTransactionId()));
            table.addCell(Long.toString(transaction.getFromAccount()));
            table.addCell(Long.toString(transaction.getToAccount()));
            table.addCell(Float.toString(transaction.getAmount()));
            table.addCell(transaction.getShortDescription());
            table.addCell(transaction.getTransactionDate().toString());
            if(transaction.getFromAccount()==customerAccountNumber){
            	table.addCell("Debit");
            }else{
            	table.addCell("Credit");
            }
        }
         
        document.add(table);
		
	}
	
	

}
