package br.com.conteudo.mapper;

import br.com.conteudo.dto.CadastroConteudoDto;
import br.com.conteudo.entity.Conteudo;
import org.springframework.stereotype.Component;

@Component
public class ConteudoMapper {

    public CadastroConteudoDto toDto(Conteudo conteudo){
        return new CadastroConteudoDto(
                conteudo.getTitulo(),
                conteudo.getVideo(),
                conteudo.getPdf(),
                conteudo.getIdCursos()
        );
    }

    public Conteudo toEntity(CadastroConteudoDto dto){
        Conteudo conteudo = new Conteudo();
        conteudo.setTitulo(dto.titulo());
        conteudo.setVideo(dto.video());
        conteudo.setPdf(dto.pdf());
        conteudo.setIdCursos(dto.idCurso());

        return conteudo;
    }

    }
