package com.insurance_system.utilities;

import com.insurance_system.model.Insurance;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PDFGenerator {

    public void generateInfo(Insurance insurance, String filePath) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();
            document.add(generateTable(insurance));
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }


    }

    private PdfPTable generateTable(Insurance insurance) {
       PdfPTable table = new PdfPTable(2);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Your Insurance"));
        cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Insurance Details:"));
        cell.setColspan(2);
        table.addCell(cell);

        table.addCell("Insurance ID:");
        table.addCell(insurance.getId().toString());

        table.addCell("Insurance Name:");
        table.addCell(insurance.getPolicyNumber());

        table.addCell("Created Date:");
        table.addCell(insurance.getRegisterDate().toString());


       return table;
    }

}
