/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Titular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Elizabeth
 */
public class TitularDao {
    
    private Connection con;
    
    public TitularDao(Connection con){
        this.con = con;
    }
    

    public void inserirTitular (Titular titular){
        
        String sql = "INSERT INTO skynet_db.titular(cpf,nome,telefone,email,endereco,login,senha,ativo)\n" + "VALUES (?,?,?,?,?,?,?,?);";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, titular.getCpf());
            ps.setString(2, titular.getNome());
            ps.setString(3, titular.getTelefone());
            ps.setString(4, titular.getEmail());
            ps.setString(5, titular.getEndereco());
            ps.setString(6, titular.getLogin());
            ps.setString(7, titular.getSenha());
            ps.setBoolean(8, titular.isAtivo());          
            ps.execute();
            System.out.println("Titular inserido com sucesso!");
        }catch(SQLException e){
            System.out.println("************************");
            System.out.println("Erro ao Inserir Titular");
            System.out.println("************************");
        }
        
    }
    
    
    public void excluirTitular (String cpf){
        
        String sql = "UPDATE skynet_db.titular SET ativo = 0 WHERE cpf = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.executeUpdate();
            System.out.println("Titular excluido com sucesso");
        }catch(SQLException e){
            System.out.println("************************");
            System.out.println("Erro ao deletar Titular");
            System.out.println("*************************");
        }
        
    }
    
    
    public Titular obterDadosTitular(String login){
        
        Titular titular = new Titular();
        //String sql = "SELECT * FROM skynet_db.titular WHERE login = '"+login+"'";
        String sql = "SELECT * FROM skynet_db.titular WHERE login = ?";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            rs.first();
            titular.setCpf(rs.getString("cpf"));
            titular.setNome(rs.getString("nome"));
            titular.setTelefone(rs.getString("telefone"));
            titular.setEmail(rs.getString("email"));
            titular.setEndereco(rs.getString("endereco"));
            titular.setLogin(rs.getString("login"));
            titular.setSenha(rs.getString("senha"));
            titular.setAtivo(rs.getBoolean("ativo"));
                
        } catch (SQLException ex) {
            System.out.println("************************");
            ex.printStackTrace();
            System.out.println("Erro ao consultar dados.");
            System.out.println("************************");
        }     
        return titular;
        
    }
    
}
