package br.edu.ifpr.pgua.eic.tads.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

public class Cadastro {
    private List<Pessoa> pessoas;

    public Cadastro() {
        pessoas = new ArrayList<>();
    }

    public Resultado<Pessoa> add(Pessoa p) {

        try (Connection con = FabricaConexao.getInstance().getConnection()){

            System.out.println("Conectado!!");
            String sql = "INSERT INTO oo_contatos(nome,email,telefone) VALUES (?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, p.getNome());
            pstm.setString(2, p.getEmail());
            pstm.setString(3, p.getTelefone());

            int res = pstm.executeUpdate();
            if (res == 1) {
                this.pessoas.add(p);
                return Resultado.sucesso("Pessoa inserida!",p);
            } else {
                return Resultado.erro("Problema no Banco de Dados");
            }

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

        /* this.pessoas.add(p); */
    }

    public List<Pessoa> getPessoas() {

        this.pessoas.clear();

        try(Connection con = DriverManager.getConnection("jdbc:mysql://URL/BANCO", "USUARIO", "SENHA")){
            
            String sql = "SELECT * FROM oo_contatos";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                Pessoa p = new Pessoa(nome, email, telefone);
                pessoas.add(p);
            }

        } 
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return this.pessoas;
    }
}
