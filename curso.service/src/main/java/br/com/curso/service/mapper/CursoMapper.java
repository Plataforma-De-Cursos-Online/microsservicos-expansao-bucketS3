package br.com.curso.service.mapper;

import br.com.curso.service.dto.CadastroCursoDTO;
import br.com.curso.service.entity.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso toEntity(CadastroCursoDTO dto) {
        if (dto == null) {
            return null;
        }

        Curso curso = new Curso();

        curso.setTitulo(dto.titulo());
        curso.setDescricao(dto.descricao());
        curso.setPreco(dto.preco());
        curso.setDisponivel(dto.disponivel());
        curso.setIdUsuario(dto.idUsuario());
        curso.setTipoCurso(dto.tipoCurso());

        return curso;
    }

    // Opcional: converte entidade para DTO (se precisar)
    public CadastroCursoDTO toDto(Curso curso) {
        if (curso == null) {
            return null;
        }

        return new CadastroCursoDTO(
                curso.getTitulo(),
                curso.getDescricao(),
                curso.getPreco(),
                curso.getDisponivel(),
                curso.getIdUsuario(),
                curso.getTipoCurso()
        );
    }

}
