package br.com.ps.api.mybooks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ps.api.mybooks.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Optional<Usuario> findByLogin(String loign);

}