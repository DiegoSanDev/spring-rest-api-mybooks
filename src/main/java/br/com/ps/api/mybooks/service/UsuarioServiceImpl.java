package br.com.ps.api.mybooks.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ps.api.mybooks.model.Usuario;
import br.com.ps.api.mybooks.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario atualizar(Usuario usuario) {
        Date dataAlteracao = new Date();
        try {
            Usuario usuarioVigente = buscarPorId(usuario.getId()).get();
            usuario.getPermissoes().clear();
            usuarioVigente.getPermissoes().addAll(usuario.getPermissoes());
            usuarioVigente.setDataAlteracao(dataAlteracao);
            return usuarioRepository.save(usuarioVigente);
        } finally {
            dataAlteracao = null;
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent()) {
            throw new EmptyResultDataAccessException("Usuário não encontrado", 1);
        }
        return usuario;
    }

    @Override
    public Usuario buscarPorLogin(String login) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        if (!usuario.isPresent()) {
            throw new EmptyResultDataAccessException("Usuário não encontrado", 1);
        }
        return usuario.get();
    }

    @Override
    public Usuario inserir(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}