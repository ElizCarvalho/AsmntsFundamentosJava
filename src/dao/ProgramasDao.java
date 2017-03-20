/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Programas;
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
public class ProgramasDao {
    
    private Connection con;
    
    public ProgramasDao(Connection con){
        this.con = con;
    }
       
    
    public List<Programas> obterDadosProgramas(int idcanal){
         
        List <Programas> listPrograma = new ArrayList<>();
        //String sql = "SELECT * FROM skynet_db.programa WHERE idcanal = "+idcanal;
        String sql = "SELECT * FROM skynet_db.programa WHERE idcanal = ?";
        
        try {
            //ResultSet rs = con.prepareStatement(sql).executeQuery();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idcanal);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Programas programa = new Programas();
                programa.setId(rs.getInt("idprograma"));
                programa.setNome(rs.getString("nome"));
                programa.setDescricao(rs.getString("descricao"));
                programa.setClassificao(rs.getString("classificacao"));
                programa.setDuracao(rs.getInt("duracao"));
                programa.setHoraInicial(rs.getString("hora_inicial"));
                listPrograma.add(programa);
            }
                
        } catch (SQLException ex) {
            System.out.println("*******************************");
            System.out.println("Erro ao consultar os programas.");
            System.out.println("*******************************");
        }     
        return listPrograma;
        
    }
    
    
}
