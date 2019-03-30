package br.com.ps.api.mybooks.service;

import br.com.ps.api.mybooks.model.Usuario;

public interface UsuarioService extends Service<Usuario>{
    Usuario buscarPorLogin(String login);
}