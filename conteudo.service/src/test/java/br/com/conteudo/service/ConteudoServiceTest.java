package br.com.conteudo.service;
import br.com.conteudo.dto.CadastroConteudoDto;
import br.com.conteudo.dto.ListagemUsuarioDTO;
import br.com.conteudo.entity.Conteudo;
import br.com.conteudo.exception.ConteudoNaoEncontradoException;
import br.com.conteudo.exception.CursoNaoEncontradoException;
import br.com.conteudo.exception.NenhumConteudoCadastradoException;
import br.com.conteudo.mapper.ConteudoMapper;
import br.com.conteudo.repository.ConteudoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConteudoServiceTest {

    @Mock
    private ConteudoRepository conteudoRepository;

    @Mock
    private ConteudoMapper conteudoMapper;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private ConteudoService conteudoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        conteudoService = new ConteudoService(webClient);
        conteudoService.conteudoRepository = conteudoRepository;
        conteudoService.conteudoMapper = conteudoMapper;
    }


    @Test
    void saveConteudo_DeveSalvarConteudoComSucesso() {
        UUID cursoId = UUID.randomUUID();
        CadastroConteudoDto dto = new CadastroConteudoDto("Título", "pdf.pdf", "video.mp4", cursoId);
        Conteudo conteudo = new Conteudo();
        conteudo.setIdCursos(cursoId);


        ConteudoService spyService = Mockito.spy(conteudoService);
        doReturn(true).when(spyService).VerificarCurso(cursoId);

        when(conteudoRepository.save(any())).thenReturn(conteudo);
        when(conteudoMapper.toDto(any())).thenReturn(dto);

        CadastroConteudoDto resultado = spyService.saveConteudo(dto);

        assertNotNull(resultado);
        assertEquals("Título", resultado.titulo());
        verify(conteudoRepository).save(any());
    }


    @Test
    void saveConteudo_DeveLancarExcecaoQuandoCursoNaoExiste() {
        UUID cursoId = UUID.randomUUID();
        CadastroConteudoDto dto = new CadastroConteudoDto("Título", "pdf.pdf", "video.mp4", cursoId);

        ConteudoService spyService = Mockito.spy(conteudoService);
        doReturn(false).when(spyService).VerificarCurso(cursoId);

        assertThrows(CursoNaoEncontradoException.class, () -> spyService.saveConteudo(dto));
    }


    @Test
    void deletarConteudo_DeveDeletarComSucesso() {
        UUID id = UUID.randomUUID();

        when(conteudoRepository.existsById(id)).thenReturn(true);

        conteudoService.deletarConteudo(id);

        verify(conteudoRepository).deleteById(id);
    }

    @Test
    void deletarConteudo_DeveLancarExcecaoQuandoNaoExiste() {
        UUID id = UUID.randomUUID();

        when(conteudoRepository.existsById(id)).thenReturn(false);

        assertThrows(CursoNaoEncontradoException.class, () -> conteudoService.deletarConteudo(id));
    }


    @Test
    void atualizarConteudo_DeveAtualizarComSucesso() {
        UUID id = UUID.randomUUID();
        CadastroConteudoDto dto = new CadastroConteudoDto("Título", "pdf.pdf", "video.mp4", UUID.randomUUID());
        Conteudo conteudo = new Conteudo();
        conteudo.setId(id);

        when(conteudoRepository.findById(id)).thenReturn(Optional.of(conteudo));
        when(conteudoRepository.save(any())).thenReturn(conteudo);
        when(conteudoMapper.toDto(any())).thenReturn(dto);

        CadastroConteudoDto resultado = conteudoService.atualizarConteudo(id, dto);

        assertNotNull(resultado);
        assertEquals("Título", resultado.titulo());
    }


    @Test
    void atualizarConteudo_DeveLancarExcecaoQuandoNaoExiste() {
        UUID id = UUID.randomUUID();
        CadastroConteudoDto dto = new CadastroConteudoDto("Título", "pdf.pdf", "video.mp4", UUID.randomUUID());

        when(conteudoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ConteudoNaoEncontradoException.class, () -> conteudoService.atualizarConteudo(id, dto));
    }


    @Test
    void listar_DeveLancarExcecaoQuandoNenhumConteudo() {
        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(ListagemUsuarioDTO.class))
                .thenReturn(Mono.just(new ListagemUsuarioDTO("usuario@gmail.com")));

        when(conteudoRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NenhumConteudoCadastradoException.class, () -> conteudoService.listar("Bearer token"));
    }


    @Test
    void extractToken_DeveExtrairTokenCorretamente() {
        String token = conteudoService.extractToken("Bearer abcdef123456");
        assertEquals("abcdef123456", token);
    }

    @Test
    void extractToken_DeveLancarExcecaoQuandoInvalido() {
        assertThrows(RuntimeException.class, () -> conteudoService.extractToken("TokenInvalido"));
    }
}