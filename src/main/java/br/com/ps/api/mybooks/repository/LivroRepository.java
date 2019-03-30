package br.com.ps.api.mybooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ps.api.mybooks.model.Livro;
import br.com.ps.api.mybooks.model.Usuario;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findAllByUsuario(Usuario usuario);
    
}