package br.com.dimed.app.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConfigExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus()
    public ResponseEntity<ExceptionMsg> HandlerForIllegalSateException(Exception e, HttpServletRequest req, HttpServletResponse resp){
        ExceptionMsg excepMsg = new ExceptionMsg();
        excepMsg.setNamespace(req.getRequestURI());
        excepMsg.setLanguage("pt-BR");
        excepMsg.erros.add(new ErrosMsg(e, req, resp));
        return ResponseEntity.ok(excepMsg);
        
    }
}
