package br.com.ps.api.mybooks.exception;

@SuppressWarnings("serial")
public class LivrosEmptyException extends Exception {

    public LivrosEmptyException(String msg) {
        super(msg);
    }

    public LivrosEmptyException(String msg, Throwable e) {
        super(msg, e);
    }
}