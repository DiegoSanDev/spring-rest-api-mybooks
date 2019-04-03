package br.com.ps.api.mybooks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ps.api.mybooks.model.Permissao;
import br.com.ps.api.mybooks.model.enums.TipoPermissao;

public interface PermissaoRepository extends JpaRepository<Permissao,Integer>{    
    
    Optional<Permissao> findByTipo(TipoPermissao tipo);

}