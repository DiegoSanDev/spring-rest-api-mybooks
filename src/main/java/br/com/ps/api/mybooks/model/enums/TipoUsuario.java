package br.com.ps.api.mybooks.model.enums;

public enum TipoUsuario {

    ADMIN(1, "Administrador"), USER(2, "Usuario");

    private int codigo;
    private String descricao;

    private TipoUsuario(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}