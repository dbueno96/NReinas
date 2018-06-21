/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reinas;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author invitado
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 
        int cantidadTableros; 
        int entreLlegadas; 
        int desviacion;
        do{
        cantidadTableros = Integer.valueOf(JOptionPane.showInputDialog(null, "Indique la cantidad de retadores"));
      //  System.out.println("Entrada: "+ cantidadTableros);
      
        entreLlegadas = Integer.valueOf(JOptionPane.showInputDialog(null, "Indique el tiempo entre llegada de los retadores" ));
        desviacion = Integer.valueOf(JOptionPane.showInputDialog(null, "Indique la desviacion del tiempo entre llegadas"));
        }
        while(cantidadTableros == 0 || entreLlegadas == 0 || desviacion == 0);
        
    
        Maestro master = new Maestro(cantidadTableros);
        master.setEntreLlegadas(entreLlegadas);
        master.setDesviacion(desviacion);

        master.run2(); 
        master.mostrarSoluciones();
        
        master.mostrarReporte();
      
       
    
       
     
    }
}
