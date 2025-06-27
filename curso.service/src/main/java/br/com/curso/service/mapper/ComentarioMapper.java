package br.com.curso.service.mapper;

import br.com.curso.service.dto.CadastroComentarioDTO;
import br.com.curso.service.dto.ListagemComentarioDTO;
import br.com.curso.service.entity.Comentario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    public Comentario toEntity(CadastroComentarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Comentario comentario = new Comentario();

        comentario.setComentario(dto.comentario());
        comentario.setIdCurso(dto.idCurso());

        return comentario;
    }

    public ListagemComentarioDTO toDto(Comentario comentario) {
        if (comentario == null) {
            return null;
        }

        return new ListagemComentarioDTO(comentario.getComentario());
    }
}
