package br.com.ps.api.mybooks.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.ps.api.mybooks.model.Livro;
import br.com.ps.api.mybooks.model.Usuario;
import br.com.ps.api.mybooks.repository.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public Livro atualizar(Livro livro) {
        Livro livroVigente = buscarPorId(livro.getId()).get();
        livro.setUsuario(livroVigente.getUsuario());
        livro.setDataAlteracao(new Date());
        BeanUtils.copyProperties(livro, livroVigente);
        return livroRepository.save(livroVigente);
    }

    @Override
    public Optional<Livro> buscarPorId(int id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if(!livro.isPresent()){
            throw new EmptyResultDataAccessException("Livro não encontrado",1);
        }
        return livro;
    }

    @Override
    public Livro inserir(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public List<Livro> todosPorUsuario(Usuario usuario) {
        return livroRepository.findAllByUsuario(usuario);
    }
}