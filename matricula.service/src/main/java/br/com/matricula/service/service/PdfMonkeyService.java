package br.com.matricula.service.service;

import br.com.matricula.service.dto.CertificadoDto;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;
import java.util.UUID;

@Service
public class PdfMonkeyService {

    private final WebClient webClient;

    @Value("${pdfmonkey.api.key}")
    private String apiKey;

    @Value("${pdfmonkey.template.id}")
    private String templateId;

    public PdfMonkeyService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.pdfmonkey.io/api/v1").build();
    }

    public String gerarCertificado(CertificadoDto certificado) {
        Map<String, Object> payload = Map.of(
                "nome", certificado.nome(),
                "curso", certificado.curso(),
                "data_certificado", certificado.data(),
                "_filename", certificado.filename()
        );

        Map<String, Object> request = Map.of(
                "document", Map.of(
                        "document_template_id", templateId,
                        "payload", payload
                )
        );

        try {
            Map response = webClient.post()
                    .uri("/documents")
                    .header("Authorization", "Bearer " + apiKey)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response == null || response.containsKey("errors")) {
                throw new RuntimeException("Erro na geração do PDF: " + response);
            }

            Map document = (Map) response.get("document");

            if (document == null) {
                throw new RuntimeException("Documento não retornado.");
            }

            String previewUrl = (String) document.get("preview_url");

            if (previewUrl == null) {
                throw new RuntimeException("Preview URL não disponível.");
            }

            return previewUrl;

        } catch (WebClientResponseException e) {
            System.err.println("Erro PDFMonkey: " + e.getResponseBodyAsString());
            throw new RuntimeException("Erro ao gerar PDF: " + e.getResponseBodyAsString(), e);
        }
    }

//    private byte[] baixarPdf(String previewUrl) {
//        try {
//            return webClient.get()
//                    .uri(previewUrl)
//                    .retrieve()
//                    .bodyToMono(byte[].class)
//                    .block();
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao baixar PDF do preview", e);
//        }
//    }
}