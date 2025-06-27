package br.com.matricula.service.mapper;

import br.com.matricula.service.dto.AtualizarDto;
import br.com.matricula.service.dto.CadastroDto;
import br.com.matricula.service.entity.Matricula;
import org.springframework.stereotype.Component;

@Component
public class MatriculaMapper {

    public Matricula cadadastrarDtoToEntity(CadastroDto dto) {
        Matricula matricula = new Matricula();
        matricula.setIdUsuario(dto.idUsuario());
        matricula.setIdCurso(dto.idCurso());
        return matricula;
    }
    public Matricula atualizarDtoToEntity(AtualizarDto dto) {
        Matricula matricula = new Matricula();
        matricula.setIdUsuario(dto.idUsuario());
        matricula.setIdCurso(dto.idCurso());
        return matricula;
    }
}