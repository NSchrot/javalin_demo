package br.edu.ifpr.pgua.eic.tads.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao{

    private static int MAX_CONNECTIONS = 5;
    private static FabricaConexao instance;

    private static String URL = "";
    private static String DB = "";
    private static String USER = "";
    private static String PASSWORD = "";

    private Connection[] conexoes;

    private FabricaConexao(){

        conexoes = new Connection[MAX_CONNECTIONS];

    }

    public static FabricaConexao getInstance(){
        if(instance == null){
            instance = new FabricaConexao();
        }
        
        return instance;
    
    }


    public Connection getConnection() throws SQLException{
        for(int i=0; i<MAX_CONNECTIONS; i++){
            if(conexoes[i] == null || conexoes[i].isClosed()){
                conexoes[i] = DriverManager.getConnection(URL+DB, USER, PASSWORD);
                return conexoes[i];
            }
        }
        throw new SQLException("Máximo de conexões abertas!");
    }

}