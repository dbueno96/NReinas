/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reinas;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author invitado
 */
public class Reinas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 

        ArrayList<Integer> filas = new ArrayList<Integer>();
        ArrayList<Integer> columnas = new ArrayList<Integer>();
        ArrayList<Integer> diag45 = new ArrayList<Integer>();
        ArrayList<Integer> diag135 = new ArrayList<Integer>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        int[][] tablero = new int[7][7];

        for (int i = 0; i < tablero[0].length; i++) {
            indices.add(i);
        }

        for (int i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = 0;
            }
        }

        for (int i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {

            }
        }

        int i = 0;

        while (i < tablero[0].length) {
            

            System.out.println("Valor de i = " + i);

            Random rand = new Random();
            int randCol = indices.get(rand.nextInt(tablero[0].length));
            if (!diag45.contains(i + randCol) && !diag135.contains(i - randCol) && !columnas.contains(randCol)) {

                tablero[i][randCol] = 1;
                diag45.add(i + randCol);
                diag135.add(i - randCol);
                columnas.add(randCol);
                i++;
            } else {
                for (i = 0; i < tablero[0].length; i++) {
                    for (int j = 0; j < tablero[0].length; j++) {
                        System.out.print(tablero[i][j] + " ");
                    }
                    System.out.println();
                }

                System.out.println();
                System.out.println();
                for (i = 0; i < tablero[0].length; i++) {
                    for (int j = 0; j < tablero[0].length; j++) {

                        tablero[i][j] = 0;
                    }
                }
                columnas.clear();
                diag45.clear();
                diag135.clear();
                i = 0;
            }
            
        }
        for (i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }

    }
}
