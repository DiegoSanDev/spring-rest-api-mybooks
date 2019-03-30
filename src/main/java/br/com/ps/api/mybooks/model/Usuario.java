package br.com.ps.api.mybooks.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.ps.api.mybooks.model.enums.TipoUsuario;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBase {

    private static final long serialVersionUID = 1L;

    private List<Permissao> permissoes;
    private String login;
    private String senha;
    private boolean ativo;
    private TipoUsuario tipo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao", 
               joinColumns = @JoinColumn(name = "id_usaurio"), 
               inverseJoinColumns = @JoinColumn(name = "id_permissao")
    )
    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Column(name = "login", nullable = false, length = 100)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "senha", nullable = false, columnDefinition = "TEXT")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "is_ativo", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 50)
    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

}