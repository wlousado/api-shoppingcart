package br.com.dimed.app.config;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionMsg {
    private String namespace;
    private String language;
    List<ErrosMsg> erros = new ArrayList<ErrosMsg>();
}