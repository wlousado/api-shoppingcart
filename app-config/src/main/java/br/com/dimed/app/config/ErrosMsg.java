package br.com.dimed.app.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public ErrosMsg(Exception e, HttpServletRequest req, HttpServletResponse resp) {
        namespace = req.getRequestURI();   
        name = resp.getContentType();
        message = e.getMessage();
        httpStatusCodes.add(resp.getStatus());
        
        issues.add(new Issue());
    }
}