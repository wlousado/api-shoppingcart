package br.com.dimed.app.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrosMsg {
    private String namespace;
    private String name;
    private String message;
    List<Integer> httpStatusCodes = new ArrayList<Integer>();
    List<Issue> issues = new ArrayList<Issue>();
    List<String> suggestedApplicationActions = new ArrayList<String>();
    List<String> suggestedUserActions = new ArrayList<String>();

    public ErrosMsg(Exception e, HttpServletRequest req, HttpServletResponse resp, HttpStatus status) {
        namespace = req.getRequestURI();   
        name = status.name();
        message = e.getMessage();
        httpStatusCodes.add(status.value());
        
        issues.add(new Issue());
        
        suggestedApplicationActions.add("Entre em contato com o nosso suporte através do e-mail desenvservices@dimed.com.br");
        suggestedUserActions.add("Por favor, caso o problema persista entre em contato com o nosso suporte");
    }
}