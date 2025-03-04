package util;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;

public class PDFGenerator {
    public static void generarPDF(String contenido) {
        String filePath = "reporte.pdf";
        try {
            PdfWriter writer = new PdfWriter(new File(filePath));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(contenido));

            document.close();
            System.out.println("PDF generado correctamente en: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
