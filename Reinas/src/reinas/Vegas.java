/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reinas;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author daniel
 */
public class Vegas extends Tablero{
    
    private final static String metodo = "Vegas";   
    public Vegas(int n){
        super(n);
    }
    
    
    @Override
    public String getMetodo(){
        return metodo;
    }
    
    @Override
    public void resolverTablero() {
        int i = 0;
        LinkedList<Integer> a = new LinkedList<Integer>();

        while (i < tablero[0].length) {

            a.clear();
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 0) {
                    a.add(j);
                }
            }
            if (!a.isEmpty()) {
                Random rand = new Random();

                int randCol = a.get(rand.nextInt(a.size()));

                tablero[i][randCol] = 1;
                reinasColocadas++;
                this.llenarDiag45(i + 1, 1 + randCol);
                this.llenarDiag135(i + 1, randCol - 1);
                this.llenarColumna(i + 1, randCol);
                i++;

            } else {
                for (i = 0; i < tablero[0].length; i++) {
                    for (int j = 0; j < tablero[0].length; j++) {

                        tablero[i][j] = 0;
                    }
                }
                i = 0;
                tablerosIntentados++;
            }
        }

        this.depurar();
    }
}
