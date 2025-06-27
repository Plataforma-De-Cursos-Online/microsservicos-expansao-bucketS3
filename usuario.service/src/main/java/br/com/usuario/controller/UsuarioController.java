package br.com.usuario.controller;

import br.com.usuario.dto.*;
import br.com.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.cadastrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.login(dto));
    }

    @GetMapping
    public ResponseEntity<List<ListagemUsuarioDTO>> getAllUsers() {
        return ResponseEntity.ok(usuarioService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemUsuarioDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.getUserById(id));
    }

    @GetMapping("/usuario-email-corpo/{id}")
    public ResponseEntity<DadosBasicoUsuarioEmailDto> getDadosBasicosParaEmail(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.getDadosBasicosParaEmail(id));
    }

    @PostMapping("/loginByEmail")
    public ResponseEntity<Object> getUserByLogin(@RequestBody ListagemUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.getUserByLogin(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListagemUsuarioDTO> updateUser(@PathVariable UUID id, @RequestBody @Valid CadastroUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.updateUser(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ListagemUsuarioDTO> updateUserName(@PathVariable UUID id, @RequestBody @Valid UpdateUserNameDTO dto) {
        return ResponseEntity.ok(usuarioService.updateUserName(id, dto));
    }

    @PatchMapping("/login/{id}")
    public ResponseEntity<ListagemUsuarioDTO> updateUserLogin(@PathVariable UUID id, @RequestBody @Valid UpdateUserLogin dto) {
        return ResponseEntity.ok(usuarioService.updateUserLogin(id, dto));
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<ListagemUsuarioDTO> updateUserPassword(@PathVariable UUID id, @RequestBody @Valid UpdateUserPassword dto) {
        return ResponseEntity.ok(usuarioService.updateUserPassword(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        usuarioService.deleteUserById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/subject")
    public ResponseEntity<ListagemUsuarioDTO> getSubjectByToken(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(usuarioService.getSubjectByToken(authorizationHeader));
    }

}
