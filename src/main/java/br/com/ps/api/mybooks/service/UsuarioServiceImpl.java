package br.com.ps.api.mybooks.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ps.api.mybooks.model.Permissao;
import br.com.ps.api.mybooks.model.Usuario;
import br.com.ps.api.mybooks.repository.PermissaoRepository;
import br.com.ps.api.mybooks.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Transactional(noRollbackFor = Exception.class)
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

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> buscarPorId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent()) {
            throw new EmptyResultDataAccessException("Usuário não encontrado", 1);
        }
        return usuario;
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscarPorLogin(String login) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        if (!usuario.isPresent()) {
            throw new EmptyResultDataAccessException("Usuário não encontrado", 1);
        }
        return usuario.get();
    }

    @Transactional(noRollbackFor = Exception.class)
    @Override
    public Usuario inserir(Usuario usuario) {
        List<Permissao> permissoes = permissaoRepository.findAll();;
        Date dataCadastro = new Date();
        try {
            usuario.setDataCadastro(dataCadastro);
            usuario.setPermissoes(permissoes);
            usuario.setAtivo(true);
            return usuarioRepository.save(usuario);
        } finally {
            permissoes = null;
            dataCadastro = null;
        }
    }
}