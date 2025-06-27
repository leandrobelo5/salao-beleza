/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Leandro
 */
public class ExportadorPDF {
    public static void exportarTextoParaPDF(String texto, String caminho, String titulo) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new java.io.FileOutputStream(caminho));
        document.open();
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLUE);
        Font fontTexto = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        document.add(new Paragraph(titulo, fontTitulo));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph(texto, fontTexto));
        document.close();
    }
}
