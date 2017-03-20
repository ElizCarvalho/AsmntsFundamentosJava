/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Canal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elizabeth
 */
public class CanalDao {
    
    private Connection con;
    
    public CanalDao(Connection con){
        this.con = con;
    }
    

    public void inserirCanal (Canal canal){
        
        String sql = "INSERT INTO skynet_db.canal(nome,numero,preco,idcategoria) VALUES (?,?,?,?)";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, canal.getNome());
            ps.setInt(2, canal.getNumero());
            ps.setDouble(3, canal.getPreco());
            ps.setInt(4, canal.getIdcategoria());         
            ps.execute();
            System.out.println("Canal inserido com sucesso!");
        }catch(SQLException e){
            System.out.println("Erro ao inserir canal");
        }
        
    }
       
    
    public Canal obterDadosDeUmCanal(int numero){
        
        Canal canal = new Canal();
        //String sql = "SELECT * FROM skynet_db.canal WHERE numero = '"+numero+"'";
        String sql = "SELECT * FROM skynet_db.canal WHERE numero = ?";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();
            rs.first();
            
            canal.setId(rs.getInt("idcanal"));
            canal.setNome(rs.getString("nome"));
            canal.setNumero(rs.getInt("numero"));
            canal.setPreco(rs.getDouble("preco"));
            canal.setIdcategoria(rs.getInt("idcategoria"));
                            
        } catch (SQLException ex) {
            System.out.println("***************************");
            System.out.println("ERRO: Canal não encontrado.");
            System.out.println("***************************");
        }     
        return canal;
        
    }
    
    
    public List<Canal> obterCanaisDeUmPacote(int idpacote){
        
       List <Canal> listCanaisPacote = new ArrayList<>();
        //String sql = "SELECT * FROM skynet_db.programa WHERE idcanal = "+idcanal;
        String sql = "SELECT * FROM skynet_db.canal t1 INNER JOIN skynet_db.canal_pacote t2 ON (t1.idcanal = t2.idcanal) WHERE t2.idpacote = ?";
        
        try {
            //ResultSet rs = con.prepareStatement(sql).executeQuery();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idpacote);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Canal canal = new Canal();
                canal.setId(rs.getInt("idcanal"));
                canal.setNome(rs.getString("nome"));
                canal.setNumero(rs.getInt("numero"));
                canal.setPreco(rs.getDouble("preco"));
                canal.setIdcategoria(rs.getInt("idcategoria"));
                listCanaisPacote.add(canal);
            }
                
        } catch (SQLException ex) {
            System.out.println("*********************");
            System.out.println("Pacote não encontrado.");
            System.out.println("**********************");
        }     
        return listCanaisPacote; 
    } 
}
