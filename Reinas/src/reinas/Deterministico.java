/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reinas;

import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class Deterministico extends Tablero{
    
    private final static String metodo = "Determinista";  
    
    public Deterministico(int n )
    {
        super(n);
    }
    
    @Override
    public String getMetodo(){
        return metodo;
    }
    
    
    @Override
    public void resolverTablero() {
        int i = 0;
        int cont = 0;
        while (isValid(i)) {

            LinkedList<Integer> a = new LinkedList<Integer>();
            for (int j = 0; isValid(j); j++) {
                if (tablero[i][j] == 0) {
                    a.add(j);
                } 
            }

            if (!a.isEmpty()) {
                int col = a.pop();
                this.borrarIntentos(i + 1);
                tablero[i][col] = 1;
                this.llenarDiag45(i + 1, 1 + col);
                this.llenarDiag135(i + 1, col - 1);
                this.llenarColumna(i + 1, col);
                //this.llenarFila(i,col-1);
                reinasColocadas++;
                i++;

            } else {
                i--;
                this.borrarReinaAnterior(i);
            }

            //this.depurar();
        } 
        this.depurar();
    }
}
