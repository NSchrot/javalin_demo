package br.edu.ifpr.pgua.eic.tads.controllers;



import io.javalin.http.Context;
import io.javalin.http.Handler;

public class IndexController {
    
    public Handler get = (Context ctx)->{
        ctx.render("index.html");
    };

    public Handler ola = (Context ctx)->{
        ctx.render("ola.html");
    };

}
