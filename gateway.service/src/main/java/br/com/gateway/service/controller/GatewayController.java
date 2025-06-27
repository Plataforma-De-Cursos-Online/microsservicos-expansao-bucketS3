package br.com.gateway.service.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class GatewayController {

    private final WebClient webClient;

    public GatewayController() {
        this.webClient = WebClient.builder().build();
    }

    // Mapeia qualquer rota que comece com /api/{service}, inclusive só /api/{service}
    @RequestMapping("/{service}/**")
    public Mono<ResponseEntity<String>> proxy(
            @PathVariable String service,
            @RequestHeader HttpHeaders headers,
            @RequestParam(required = false) MultiValueMap<String, String> queryParams,
            @RequestBody(required = false) Mono<String> body,
            ServerHttpRequest request
    ) {
        String url = switch (service) {
            case "usuario" -> "http://localhost:8081";
            case "curso-professor" -> "http://localhost:8082";
            case "curso-aluno" -> "http://localhost:8082";
            case "matricula" -> "http://localhost:8083";
            case "conteudo-professor" -> "http://localhost:8084";
            case "conteudo-aluno" -> "http://localhost:8084";
            case "conta" -> "http://localhost:8085";
            case "cartao" -> "http://localhost:8085";
            case "transacao" -> "http://localhost:8086";
            default -> null;
        };

        if (url == null) {
            return Mono.just(ResponseEntity.status(400).build());
        }

        // Remove "/api/{service}" do path para formar a URL destino
        String originalPath = request.getURI().getRawPath();
        String prefixToStrip = "/api/" + service;
        String path = originalPath.substring(prefixToStrip.length());

        String uri = url + "/" + service + path;

        if (queryParams != null && !queryParams.isEmpty()) {
            uri += "?" + queryParams.toSingleValueMap().entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .reduce((a, b) -> a + "&" + b)
                    .orElse("");
        }

        return webClient.method(request.getMethod())
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(body == null ? Mono.empty() : body, String.class)
                .retrieve()
                .toEntity(String.class);
    }
}