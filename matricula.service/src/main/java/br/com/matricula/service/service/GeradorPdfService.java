package br.com.matricula.service.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class GeradorPdfService {

    public String gerarCertificado(String nome, String curso, String data) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Texto do título
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 26);
            contentStream.newLineAtOffset(150, 750);
            contentStream.showText("Certificado de Conclusão");
            contentStream.endText();

            // Nome do aluno
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.newLineAtOffset(100, 650);
            contentStream.showText("Certificamos que " + nome);
            contentStream.endText();

            // Curso
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 16);
            contentStream.newLineAtOffset(100, 600);
            contentStream.showText("Concluiu o curso de: " + curso);
            contentStream.endText();

            // Data
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 14);
            contentStream.newLineAtOffset(100, 550);
            contentStream.showText("Em: " + data);
            contentStream.endText();

            // Assinatura
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 12);
            contentStream.newLineAtOffset(100, 450);
            contentStream.showText("_____________________________");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 12);
            contentStream.newLineAtOffset(100, 430);
            contentStream.showText("Mony Courses");
            contentStream.endText();

        }

        // Salvar o PDF
        String caminho = "certificado-" + nome.replace(" ", "_") + ".pdf";
        document.save(new File(caminho));
        document.close();

        return caminho;
    }
}
