package br.com.conteudo.service;

import br.com.conteudo.dto.CadastroConteudoDto;
import br.com.conteudo.dto.ListagemUsuarioDTO;
import br.com.conteudo.entity.Conteudo;
import br.com.conteudo.exception.ConteudoNaoEncontradoException;
import br.com.conteudo.exception.CursoNaoEncontradoException;
import br.com.conteudo.exception.NenhumConteudoCadastradoException;
import br.com.conteudo.mapper.ConteudoMapper;
import br.com.conteudo.repository.ConteudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.sound.midi.MidiChannel;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConteudoService {

    @Autowired
    ConteudoRepository conteudoRepository;

    @Autowired
    ConteudoMapper conteudoMapper;

    @Autowired
    private RestTemplate restTemplate;

    private final WebClient webClient;

    public ConteudoService(WebClient webClient) {
        this.webClient = webClient;
    }



    public CadastroConteudoDto saveConteudo(CadastroConteudoDto dto) {
       boolean verificarCurso = VerificarCurso(dto.idCurso());

        if (verificarCurso == false){
            throw new CursoNaoEncontradoException("Curso com esse ID não existe!");
        }

        var conteudo = new Conteudo();
        conteudo.setPdf(dto.pdf());
        conteudo.setTitulo(dto.titulo());
        conteudo.setVideo(dto.video());
        conteudo.setIdCursos(dto.idCurso());
        conteudoRepository.save(conteudo);

        return conteudoMapper.toDto(conteudo);

    }

    boolean VerificarCurso(UUID cursoId) {
        try {
            webClient.get()
                    .uri("http://localhost:8082/curso-aluno", cursoId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar curso", e);
        }
    }

    public void deletarConteudo(UUID id) {
        if (!conteudoRepository.existsById(id)){
            throw new CursoNaoEncontradoException("Curso com esse ID não existe!");
        }

        conteudoRepository.deleteById(id);

    }

    public CadastroConteudoDto atualizarConteudo(UUID id, CadastroConteudoDto dto){
        var conteudoEncontrado =conteudoRepository.findById(id);

        if (conteudoEncontrado.isEmpty()) {
            throw new ConteudoNaoEncontradoException("Conteúdo com esse ID não existe!");
        }

        var conteudo = conteudoEncontrado.get();
        conteudo.setPdf(dto.pdf());
        conteudo.setTitulo(dto.titulo());
        conteudo.setVideo(dto.video());
        conteudo.setIdCursos(dto.idCurso());
        conteudoRepository.save(conteudo);

        return conteudoMapper.toDto(conteudo);
    }

    public List<CadastroConteudoDto> listar(String authorizationHeader){
        var token = extractToken(authorizationHeader);
        System.out.println(token);

        try {
            ListagemUsuarioDTO usuario = webClient.get()
                    .uri("http://localhost:8080/api/usuario/subject")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .bodyToMono(ListagemUsuarioDTO.class)
                    .block();


            System.out.println(usuario.login());
        } catch (WebClientResponseException.NotFound e) {
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erro ao buscar login do usuário", e);

        }

        var listaConteudo = conteudoRepository.findAll();

        if (listaConteudo.isEmpty()) {
            throw new NenhumConteudoCadastradoException("Nenhum conteúdo cadastrado");
        }

        var listaListagemConteudo = new ArrayList<CadastroConteudoDto>();

        listaConteudo.stream().forEach(
                c ->listaListagemConteudo.add(conteudoMapper.toDto(c))
        );

        return listaListagemConteudo;
    }

    public List<CadastroConteudoDto> listarPorId(String authorizationHeader, UUID idCurso){
        var token = extractToken(authorizationHeader);

        System.out.println(token);
        try {
            Boolean id = webClient.get()
                    .uri("http://localhost:8083/matricula/verificar-matricula/" +  idCurso)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (id == true){
                List<Conteudo> listaConteudo = conteudoRepository.findAllConteudo(idCurso);


                return listaConteudo.stream()
                        .map(conteudoMapper::toDto)
                        .collect(Collectors.toList());
            } else {
                //Necessário criar exception personalizada para 403 com mensagem
                throw new RuntimeException("Acessso negado aos conteúdos!");
            }

        } catch (WebClientResponseException.NotFound e) {
            return null;
        }

    }

    public List<CadastroConteudoDto> listarPorIdProfessor(String authorizationHeader, UUID idCurso){
        var token = extractToken(authorizationHeader);

        List<Conteudo> listaConteudo = conteudoRepository.findAllConteudo(idCurso);

        return listaConteudo.stream()
                .map(conteudoMapper::toDto)
                .collect(Collectors.toList());

    }

    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new RuntimeException("Token inválido ou ausente no cabeçalho Authorization");
        }
    }


}
