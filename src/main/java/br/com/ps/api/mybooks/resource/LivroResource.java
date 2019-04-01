package br.com.ps.api.mybooks.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ps.api.mybooks.exception.LivrosEmptyException;
import br.com.ps.api.mybooks.model.Livro;
import br.com.ps.api.mybooks.model.Usuario;
import br.com.ps.api.mybooks.service.LivroServiceImpl;
import br.com.ps.api.mybooks.service.UsuarioService;

@RestController
@RequestMapping("/api/livros")
public class LivroResource {

    @Autowired
    private LivroServiceImpl livroService;
    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/atualizar")
    public ResponseEntity<Livro> atualizar(@Valid @RequestBody Livro livro) {
        Livro livroSalvo = livroService.atualizar(livro);
        return livroSalvo == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(livroSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable int id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        return livro.isPresent() ? ResponseEntity.ok(livro.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/inserir")
    public ResponseEntity<Livro> inserir(@Valid @RequestBody Livro livro) {
        Livro livroSalvo = livroService.inserir(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    @GetMapping("/listar/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Livro>> listarPorIdUsuario(@PathVariable int id) throws LivrosEmptyException {
        Usuario usuario = usuarioService.buscarPorId(id).get();
        List<Livro> livros = livroService.todosPorUsuario(usuario);
        return livros.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(livros);
    }
}