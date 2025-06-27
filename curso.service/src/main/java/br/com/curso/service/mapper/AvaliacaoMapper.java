package br.com.curso.service.mapper;

import br.com.curso.service.dto.CadastroAvaliacaoDTO;
import br.com.curso.service.dto.CadastroComentarioDTO;
import br.com.curso.service.dto.ListagemAvaliacaoDTO;
import br.com.curso.service.dto.ListagemComentarioDTO;
import br.com.curso.service.entity.Avaliacao;
import br.com.curso.service.entity.Comentario;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {

    public Avaliacao toEntity(CadastroAvaliacaoDTO dto) {
        if (dto == null) {
            return null;
        }

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setAvaliacao(dto.avaliacao());
        avaliacao.setIdCurso(dto.idCurso());

        return avaliacao;
    }

    public ListagemAvaliacaoDTO toDto(Avaliacao avaliacao) {
        if (avaliacao == null) {
            return null;
        }

        return new ListagemAvaliacaoDTO(avaliacao.getAvaliacao());
    }

}
