package br.com.curso.service.service;

import br.com.curso.service.dto.*;
import br.com.curso.service.entity.Avaliacao;
import br.com.curso.service.entity.Comentario;
import br.com.curso.service.entity.Curso;
import br.com.curso.service.exception.NaoEncontradoException;
import br.com.curso.service.exception.NoContentException;
import br.com.curso.service.mapper.AvaliacaoMapper;
import br.com.curso.service.mapper.ComentarioMapper;
import br.com.curso.service.mapper.CursoMapper;
import br.com.curso.service.repository.AvaliacaoRepository;
import br.com.curso.service.repository.ComentarioRepository;
import br.com.curso.service.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    CursoMapper cursoMapper;

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioMapper comentarioMapper;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;


    @Autowired
    private RestTemplate restTemplate;

    private final WebClient webClient;

    public CursoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public CadastroCursoDTO criar(CadastroCursoDTO dto) {
        Curso curso1 = cursoMapper.toEntity(dto);
        Curso cursoSalvo = cursoRepository.save(curso1);
        return cursoMapper.toDto(cursoSalvo);
    }

    public void deleteCurso(UUID id) {
        var cursoEncontrado = cursoRepository.findById(id);

        if (cursoEncontrado.isEmpty()) {
            throw new NaoEncontradoException("Não há curso cadastrado para o id " + id);
        }
        cursoRepository.deleteById(id);
    }

    public void atualizarCurso(UUID id, CadastroCursoDTO dto) {
        var cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isEmpty()) {
            throw new NaoEncontradoException("Não foi possível atualizar. Curso com id " + id + " não encontrado.");
        }

        var curso = cursoOptional.get();
        curso.setTitulo(dto.titulo());
        curso.setDescricao(dto.descricao());
        curso.setPreco(dto.preco());
        curso.setDisponivel(dto.disponivel());
        curso.setIdUsuario(dto.idUsuario());
        curso.setTipoCurso(dto.tipoCurso());

        cursoRepository.save(curso);
    }

    public void atualizarDisponibilidade(UUID id, Boolean disponivel) {
        var cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isEmpty()) {
            throw new NaoEncontradoException("Curso com id " + id + " não encontrado.");
        }

        var curso = cursoOptional.get();
        curso.setDisponivel(disponivel);
        cursoRepository.save(curso);
    }

    public void atualizarProfessor(UUID id, UUID idUsuario) {
        var cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isEmpty()) {
            throw new NaoEncontradoException("Curso com id " + id + " não encontrado.");
        }

        var curso = cursoOptional.get();
        curso.setIdUsuario(idUsuario);
        cursoRepository.save(curso);
    }

    public List<Curso> getCursosIndisponiveis() {
        return cursoRepository.findByDisponivel(false);
    }

    public List<NomeCursoDTO> getNomesDosCursos() {
        return cursoRepository.buscarNomesDosCursos();
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso listarUmCurso(UUID id) {
        var endereco = cursoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Curso com ID não encontrado!"));

        return endereco;
    }

    public List<ListagemCursoDTO> getCursosDisponiveis() {
        return cursoRepository.listarCursosDisponiveis();
    }

    public ListagemComentarioDTO saveComentario(CadastroComentarioDTO dto, String authorizationHeader) {
        var token = extractToken(authorizationHeader);

        var listagemEmailUsuario = getEmail(token);

        Object uuid = getId(listagemEmailUsuario.login(), String.valueOf(token.replace("Bearer ", "")));
        Comentario comentario = comentarioMapper.toEntity(dto);

        UUID uuidFinal = formatUUID(uuid.toString());
        comentario.setIdUsuario(uuidFinal);

        comentarioRepository.save(comentario);

        return comentarioMapper.toDto(comentario);
    }

    public List<ListagemComentarioDTO> getAllComment() {
        var commentList = comentarioRepository.findAll();

        if (commentList.isEmpty()) {
            throw new NoContentException("Nenhum comentário encontrado");
        }

        var commentListDTO = commentList.stream()
                .map(c -> new ListagemComentarioDTO(c.getComentario()))
                .toList();

        return commentListDTO;
    }

    public ListagemComentarioDTO getOneComment(UUID id) {

        var comment = comentarioRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Comentário não encontrado!"));

        return comentarioMapper.toDto(comment);
    }


    public ListagemComentarioDTO updateComentario(UUID id, AtualizarComentarioDTO dto) {

        var comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Comentário não encontrado!"));

        comentario.setComentario(dto.comentario());
        comentarioRepository.save(comentario);

        return comentarioMapper.toDto(comentario);
    }

    public void deleteComment(UUID id) {

        if (!comentarioRepository.existsById(id)) {
            throw new NaoEncontradoException("Comment with this id don't exist");
        }

        comentarioRepository.deleteById(id);
    }

    public ListagemAvaliacaoDTO saveAvaliacao(CadastroAvaliacaoDTO dto, String authorizationHeader) {
        var token = extractToken(authorizationHeader);

        var listagemEmailUsuario = getEmail(token);

        Object uuid = getId(listagemEmailUsuario.login(), String.valueOf(token.replace("Bearer ", "")));

        Avaliacao avaliacao = avaliacaoMapper.toEntity(dto);
        UUID uuidFinal = formatUUID(uuid.toString());
        avaliacao.setIdUsuario(uuidFinal);

        avaliacaoRepository.save(avaliacao);

        return avaliacaoMapper.toDto(avaliacao);
    }

    public List<ListagemAvaliacaoDTO> getAllAvaliacao(){
        var avaliacoesList = avaliacaoRepository.findAll();

        if (avaliacoesList.isEmpty()) {
            throw new NoContentException("Nenhuma avaliação encontrado");
        }

        var avaliacoesListDTO = avaliacoesList.stream()
                .map(a -> new ListagemAvaliacaoDTO(a.getAvaliacao()))
                .toList();

        return avaliacoesListDTO;
    }

    public ListagemAvaliacaoDTO getOneAvaliacao(UUID id) {

        var avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Avaliação não encontrada!"));

        return avaliacaoMapper.toDto(avaliacao);
    }

    public ListagemAvaliacaoDTO updateAvaliacao(UUID id, AtualizarAvaliacaoDTO dto) {

        var avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Avaliação não encontrada!"));

        avaliacao.setAvaliacao(dto.avaliacao());
        avaliacaoRepository.save(avaliacao);

        return avaliacaoMapper.toDto(avaliacao);
    }

    public void deleteAvaliacao(UUID id) {

        if (!avaliacaoRepository.existsById(id)) {
            throw new NaoEncontradoException("Avaliacao with this id don't exist");
        }

        avaliacaoRepository.deleteById(id);
    }

    private Object getId(String email, String token) {
        Map<String, String> requestBody = Map.of("login", email);
        try {
            return webClient.post()
                    .uri("http://localhost:8080/api/usuario/loginByEmail")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            throw new NaoEncontradoException("ID não encontrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erro ao ID do Usuário", e);
        }
    }

    private ListagemUsuarioDTO getEmail(String token) {
        try {
            ListagemUsuarioDTO listagemEmailUsuario = webClient.get()
                    .uri("http://localhost:8081/usuario/subject")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(ListagemUsuarioDTO.class)
                    .block();
            return listagemEmailUsuario;
        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erro ao capturar informações do usuário", e);
        }
    }

    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new RuntimeException("Token inválido ou ausente no cabeçalho Authorization");
        }
    }

    public UUID formatUUID(String uuid) {
        String uuidLimpo = uuid.toString().replace("{id=", "");
        String uuidFinal = uuidLimpo.replace("}", "");
        return UUID.fromString(uuidFinal);
    }
}
