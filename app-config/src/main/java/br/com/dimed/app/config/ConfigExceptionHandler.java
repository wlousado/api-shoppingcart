package br.com.dimed.app.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConfigExceptionHandler {
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionMsg> HandlerForIllegalSateException(Exception e, HttpServletRequest req, HttpServletResponse resp){
        ExceptionMsg excepMsg = new ExceptionMsg();
        excepMsg.setNamespace(req.getRequestURI());
        excepMsg.setLanguage("pt-BR");
        excepMsg.erros.add(new ErrosMsg(e, req, resp, HttpStatus.INTERNAL_SERVER_ERROR));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(excepMsg);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, MethodArgumentNotValidException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionMsg> HandlerForIllegalArgumentAndStateException(Exception e, HttpServletRequest req, HttpServletResponse resp){
        ExceptionMsg excepMsg = new ExceptionMsg();
        
        excepMsg.setNamespace(req.getRequestURI());
        excepMsg.setLanguage("pt-BR");
        excepMsg.erros.add(new ErrosMsg(e, req, resp, HttpStatus.BAD_REQUEST));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(excepMsg);
    }
}