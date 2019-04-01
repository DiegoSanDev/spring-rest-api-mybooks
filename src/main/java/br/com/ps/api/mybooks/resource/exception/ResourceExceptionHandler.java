package br.com.ps.api.mybooks.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ps.api.mybooks.exception.LivrosEmptyException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Erro> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
            HttpServletRequest request) {
        Erro erro = new Erro();
        erro.setTimestamp(System.currentTimeMillis());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setMessage("Não encontrado");
        erro.setError(ex.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler({ LivrosEmptyException.class })
    public ResponseEntity<Erro> handleLivrosEmptyException(LivrosEmptyException ex,
            HttpServletRequest request) {
        Erro erro = new Erro();
        erro.setTimestamp(System.currentTimeMillis());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setMessage("Lista de livros vazia...");
        erro.setError(ex.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Erro> handleAccessDeniedException(AccessDeniedException ex,
            HttpServletRequest request) {
        Erro erro = new Erro();
        erro.setTimestamp(System.currentTimeMillis());
        erro.setStatus(HttpStatus.UNAUTHORIZED.value());
        erro.setMessage("ACESSO NEGADO. Seu usuário não tem permissão para acessar esse recurso.");
        erro.setError(ex.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }

    private class Erro {
        
        private long timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        public Erro() {
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

    }
}