/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Dependente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Elizabeth
 */
public class DependenteDao {
    
    private Connection con;
    
    public DependenteDao(Connection con){
        this.con = con;
    }
    

    public void inserirTitular (Dependente dependente){
        
        String sql = "INSERT INTO skynet_db.dependente(cpf,nome,telefone,login,senha,ativo,cpftitular)\n" + "VALUES (?,?,?,?,?,?,?);";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, dependente.getCpf());
            ps.setString(2, dependente.getNome());
            ps.setString(3, dependente.getTelefone());
            ps.setString(4, dependente.getLogin());
            ps.setString(5, dependente.getSenha());
            ps.setBoolean(6, dependente.isAtivo()); 
            ps.setString(7, dependente.getCpftitular());
            ps.execute();
            System.out.println("Dependenyr inserido com sucesso!");
        }catch(SQLException e){
            System.out.println("***************************");
            System.out.println("Erro ao Inserir Dependente");
            System.out.println("***************************");
        }
        
    }
    
    
    public void excluirDependente (String cpf){
        
        String sql = "UPDATE skynet_db.dependente SET ativo = 0 WHERE cpf = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.executeUpdate();
            System.out.println("Dependente excluido com sucesso");
        }catch(SQLException e){
            System.out.println("***************************");
            System.out.println("Erro ao deletar Dependente");
            System.out.println("***************************");
        }
        
    }
    
    
    public Dependente obterDadosDependente(String login){
        
        Dependente dependente = new Dependente();
        String sql = "SELECT * FROM skynet_db.titular WHERE login = ?";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            rs.first();
            dependente.setCpf(rs.getString("cpf"));
            dependente.setNome(rs.getString("nome"));
            dependente.setTelefone(rs.getString("telefone"));
            dependente.setLogin(rs.getString("login"));
            dependente.setSenha(rs.getString("senha"));
            dependente.setAtivo(rs.getBoolean("ativo"));
            //melhor nao exibir o cpf do titular
                
        } catch (SQLException ex) {
            System.out.println("************************");
            System.out.println("Erro ao consultar dados.");
            System.out.println("************************");
        }     
        return dependente;
        
    }
    
}
