package br.com.usuario.mapper;

import br.com.usuario.dto.CadastroUsuarioDTO;
import br.com.usuario.dto.ListagemUsuarioDTO;
import br.com.usuario.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(CadastroUsuarioDTO dto);

    ListagemUsuarioDTO toListagemUsuarioDTO(Usuario usuario);
}
