package br.com.ps.api.mybooks.service;

import java.util.List;

import br.com.ps.api.mybooks.model.Livro;
import br.com.ps.api.mybooks.model.Usuario;

public interface LivroService extends Service<Livro> {

    List<Livro> todosPorUsuario(Usuario usuario);

}