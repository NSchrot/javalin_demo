package br.edu.ifpr.pgua.eic.tads.controllers;

import java.util.HashMap;
import java.util.Map;

import com.github.hugoperlin.results.Resultado;

import br.edu.ifpr.pgua.eic.tads.models.Cadastro;
import br.edu.ifpr.pgua.eic.tads.models.Pessoa;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CadastroController {

    private Cadastro cadastro;

    public CadastroController(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public Handler get = (Context ctx) -> {
        ctx.render("cadastro.html");
    };

    public Handler post = (Context ctx) -> {
        String nome = ctx.formParam("nome");
        String email = ctx.formParam("email");
        String telefone = ctx.formParam("telefone");

        Resultado<Pessoa> res = cadastro.add(new Pessoa(nome, email, telefone));

        Map<String, Object> dados = new HashMap<>();

        if(res.foiSucesso()){
        dados.put("mensagem", "Cadastro Realizado!");
        }else{
            dados.put("mensagemErro",res.getMsg());
        }

        ctx.render("cadastro.html", dados);

    };

}
