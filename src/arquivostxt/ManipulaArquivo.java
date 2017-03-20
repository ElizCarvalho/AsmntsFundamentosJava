/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivostxt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Elizabeth
 */
public class ManipulaArquivo {
    
    public static void lerArquivo(String path){
        Scanner scan = null;
        try {
            scan = new Scanner(new FileReader(path));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado!");
        }
    }
    
}
