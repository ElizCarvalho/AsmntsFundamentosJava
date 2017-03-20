/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Elizabeth
 */
public class ConnectionFactory {
    
    public static String status = "Não conectou...";
 
    //Método Construtor da Classe//
    public ConnectionFactory(){} 
    
    //Método de Conexão//
    public static Connection getConexaoMySQL() {
 
        Connection connection = null;//atributo do tipo Connection

        try {
 
            // Carregando o JDBC Driver padrão
            String driverName = "com.mysql.jdbc.Driver";                        
            Class.forName(driverName);
 
            // Configurando conexão com um banco de dados
            String serverName = "localhost"; //caminho do servidor do BD
            String mydatabase = "skynet_db"; //nome banco de dado
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root";        //nome do usuário de seu BD      
            String password = "root";      //senha de acesso
            connection = DriverManager.getConnection(url, username, password); 
  
            //Testando conexão//  
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possível realizar conexão");
            }
            return connection;
 
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver expecificado não foi encontrado.");
            return null;
        } catch (SQLException e) { //Não conseguindo se conectar ao banco
            System.out.println("Não foi possível conectar ao Banco de Dados.");
            return null;
        }
    }
 
    
    //Método que retorna o status da sua conexão//
    public static String statusConection() {
 
        return status;
 
    }
 
 
   //Método que fecha sua conexão//
    public static boolean FecharConexao() {
        try {
            ConnectionFactory.getConexaoMySQL().close();
            System.out.println("Conexão com o Banco de Dados encerrada com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar conexão com o Banco de Dados.");
            return false;
        }
        
    }
 
   
    //Método que reinicia sua conexão//
    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        System.out.println("Conexão com o Banco de Dados reiniciada com sucesso.");
        return ConnectionFactory.getConexaoMySQL();
 
    }
            
       
}
