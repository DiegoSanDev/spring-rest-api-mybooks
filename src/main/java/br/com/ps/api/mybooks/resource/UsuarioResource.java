package br.com.ps.api.mybooks.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ps.api.mybooks.model.Usuario;
import br.com.ps.api.mybooks.service.UsuarioServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PutMapping(path = "/atualizar", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.atualizar(usuario);
        return usuarioSalvo == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(usuarioSalvo);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=UTF-8")
    @PreAuthorize(value = "hasAuthority('ROLE_PESQUISAR_USUARIO')")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/inserir", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Usuario> inserir(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.inserir(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }
}