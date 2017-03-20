/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assesmentsemc;

import static arquivostxt.ManipulaArquivo.lerArquivo;
import bean.Canal;
import bean.Dependente;
import bean.Programas;
import bean.Titular;
import dao.CanalDao;
import dao.DependenteDao;
import dao.ProgramasDao;
import dao.TitularDao;
import exceptions.TipoDeEntradaInvalida;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Elizabeth
 */
public class AssesmentsEMC {    
    
    static Scanner scan = new Scanner(System.in);
    
    
    public static void main(String[] args) {
        
        Connection con = ConnectionFactory.getConexaoMySQL();
        int opcao=0; //variavel controle do menu
        boolean continua = true; //variavel de controle para repeticao do menu
      
        String pathMenu = "C:\\Users\\DesenvolvedorJava\\Documents\\NetBeansProjects\\AssesmentsEMC\\src\\arquivostxt\\menu.txt";
        String pathPromocao = "C:\\Users\\DesenvolvedorJava\\Documents\\NetBeansProjects\\AssesmentsEMC\\src\\arquivostxt\\promocao.txt";
        
        //Mensagem de Boas Vindas/Promocao
        lerArquivo(pathPromocao);
        System.out.println("");
        
        
        //metodo de login .. se logar aparece o menu... senao aparece mensagem erro de login
        System.out.println("Informe seu login: ");
        String login = scan.next();
        
        Titular titular = new Titular();
        TitularDao titulardao = new TitularDao(con);
        titular = titulardao.obterDadosTitular(login);
        
        Dependente dependente = new Dependente();
        DependenteDao dependentedao = new DependenteDao(con);
        dependente = dependentedao.obterDadosDependente(login);
        
        //ve se o cara é titular
        if (titular.getLogin().equals(login)){
            
            System.out.println("Informe sua senha");
            String senha = scan.next();
            
            if (titular.getSenha().equals(senha)){
                
                //aqui seria o codigo do programa completo
                
            }
            else{
                System.out.println("Senha inválida");
                System.exit(0);
            }
            
        }
        //ve o cara é dependente
        else if (dependente.getLogin().equals(login)){
            
            System.out.println("Informe sua senha");
            String senha = scan.next();
            
            if (dependente.getSenha().equals(senha)){
                
                //aqui seria o codigo do programa sem opcao de aquisicao
                
            }
            else{
                System.out.println("Senha inválida");
                System.exit(0);
            }
            
        }
        else{
            System.out.println("Usuario não encontrado");
            System.exit(0);
        }
        
        
        
        
        //Subindo o menu com try/catch/finaly
        do {
            try {
               //Menu de Opcoes
                lerArquivo(pathMenu);
                opcao = scan.nextInt();
                continua = false;
                if (!(opcao >=1 && opcao <= 5)) {
                    throw new TipoDeEntradaInvalida("ERRO 5: Entre com numero de 1 a 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("****************************************");
                System.out.println("ERRO 2: Somente numeros são permitidos.");
                System.out.println("****************************************");
                scan = new Scanner(System.in);
            } catch (Exception e ){
                System.out.println("****************************************");
                System.out.println(e.getMessage());
                System.out.println("****************************************");
            }
            finally{
                System.out.println(""); //essa parte executa independente de erro 
            }
        } while (continua);
        
        
         //enquanto não escolher a opção 5 que é para sair da aplicação vai executar os codigos em loop
        while(opcao!=5){
            
            //avalia qual a opcao foi escolhida
            switch (opcao){
                
                //Consultar dados do cliente
                case 1:
        
                break;
                
                //Consultar canais do plano contratado.
                case 2:
                                     
                    try {
                        System.out.println("Informe a opcao do pacote que deseja consultar os canais: ");
                        System.out.println("1 - Pacote Light ");
                        System.out.println("2 - Pacote Master ");
                        System.out.println("3 - Pacote Premiun ");
                        int pacote = scan.nextInt();
                        if (!(pacote >=1 && pacote <= 3)) {
                            throw new TipoDeEntradaInvalida("ERRO 5: Somente números de 1 a 3");
                        }
                        else{
                            System.out.println("");
                            CanalDao dao = new CanalDao(con);
                            List <Canal> listCanalPacote = dao.obterCanaisDeUmPacote(pacote);
                            System.out.println("*** LISTA DE CANAIS ***");
                            for (Canal canalpacote: listCanalPacote){
                                System.out.println("NOME: " + canalpacote.getNome() + " | NUMERO: " + canalpacote.getNumero());
                            }
                            System.out.println("");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("****************************************");
                        System.out.println("ERRO 2: Somente numeros são permitidos.");
                        System.out.println("****************************************");
                        scan = new Scanner(System.in);
                    } catch (Exception e ){
                        System.out.println("****************************************");
                        System.out.println(e.getMessage());
                        System.out.println("****************************************");
                    }
                    
                
                    /*
                    try{
                        
                        System.out.println("");
                        CanalDao dao = new CanalDao(con);
                        List <Canal> listCanalPacote = dao.obterCanaisDeUmPacote(pacote);
                        System.out.println("*** LISTA DE CANAIS ***");
                        for (Canal canalpacote: listCanalPacote){
                            System.out.println("NOME: " + canalpacote.getNome() + " | NUMERO: " + canalpacote.getNumero());
                        }
                        System.out.println("");
                        
                    } 
                    catch (InputMismatchException e) {
                        System.out.println("****************************************");
                        System.out.println("ERRO 2: Somente numeros são permitidos.");
                        System.out.println("****************************************");
                        scan = new Scanner(System.in);
                    }*/
                    
                break;
                
                //Consultar programas de um canal.
                case 3:
                    
                    System.out.println("Informe o numero do canal que deseja pesquisar: ");
                    try {
                        int numeroCanal = scan.nextInt();
                        
                        CanalDao dao2 = new CanalDao(con);
                        Canal canal = dao2.obterDadosDeUmCanal(numeroCanal);
                        String nomeCanal = canal.getNome();
                    
                        if (nomeCanal != null){
                            System.out.println("");
                            System.out.println("*** PROGRAMAÇÃO DO CANAL: " + canal.getNome() + " | NÚMERO: " + canal.getNumero() + " ***");

                            System.out.println("");
                            ProgramasDao dao = new ProgramasDao(con);
                            List <Programas> listPrograma = dao.obterDadosProgramas(canal.getId());
        
                            for (Programas programa: listPrograma){
                                System.out.println("NOME: " + programa.getNome() + " | DESCRIÇÃO: " + programa.getDescricao()+ " | CLASSIFICAÇÃO: " 
                                    + programa.getClassificao()+ " | DURAÇÃO: " + programa.getDuracao()+"min"+ 
                                    " | HORA INICIAL: " + programa.getHoraInicial()+"hs");
                            }
                            System.out.println("");
                        }
                        
                    } catch (InputMismatchException e) {
                        System.out.println("****************************************");
                        System.out.println("ERRO 2: Somente numeros são permitidos.");
                        System.out.println("****************************************");
                        scan = new Scanner(System.in);
                    }
                    
                    break;
                
                //Adquirir canal.
                case 4:
                    
                break;
                
                //Sair
                case 5:
                    System.exit(0);
                break;
                
            } 
            
            //loop do menu
            do {
                try {
                    lerArquivo(pathMenu);
                    opcao = scan.nextInt();
                    continua = false;
                } catch (InputMismatchException e) {
                    System.out.println("****************************************");
                    System.out.println("ERRO 2: Somente numeros são permitidos.");
                    System.out.println("****************************************");
                    scan = new Scanner(System.in);
                }
            } while (continua);
        }
        
        
        /*Titular titular = new Titular();
        
        titular.setCpf("96385274145");
        titular.setNome("Teste de Insercao");
        titular.setTelefone("88889999");
        titular.setEmail("teste@ola.com");
        titular.setEndereco("Rua 89");
        titular.setLogin("teste");
        titular.setSenha("teste");
        titular.setAtivo(true);
        */
        
        
        
        //titular = dao.obterDadosTitular("jaguiar");
        //dao.excluirTitular("44555677890");
        
        //System.out.println(titular.getNome());
        
        
        
    }
   
}
