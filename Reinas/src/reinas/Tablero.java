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
public class Tablero {

    protected int[][] tablero;
    protected double reinasColocadas = 0;
    protected double tablerosIntentados = 0;
    protected double llegada;
    protected double tiempoCola;
    protected boolean atendido;

    private double tiempoInicio;
    private double tiempoFin;

    public double getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(double tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public double getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(double tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public Tablero(int n) {
        tablero = new int[n][n];
        this.inicializarTablero();
        this.tablerosIntentados = 1;
        this.llegada = 0;
        this.tiempoCola = 0;
        this.atendido = false; 
    }

    public Tablero() {
        tablero = new int[8][8];
        this.inicializarTablero();
        this.tablerosIntentados = 1;
        this.llegada = 0;
        this.tiempoCola = 0;
        this.atendido = false; 
    }
    
    public void setAtendido(boolean atendido){
        this.atendido = atendido; 
    }
    
    public boolean getAtendido(){
        return atendido; 
    }

    public void setLlegada(double Llegada) {
        this.llegada = Llegada;
    }
    
    public void incrTiempoCola(double tiempoCola){
        this.tiempoCola += (tiempoCola -this.tiempoCola); 
    }

    public void setTiempoCola(double tiempoCola) {
        this.tiempoCola = tiempoCola;
    }

    public double getLlegada() {
        return llegada;
    }

    public double getTiempoCola() {
        return tiempoCola;
    }

    public String getMetodo() {

        return "";

    }

    private void inicializarTablero() {
        for (int i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = 0;
            }
        }
    }

    public void resolverTablero() {

    }

    public boolean isValid(int i) {

        if (i < tablero[0].length && i >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void llenarDiag45(int i, int j) {

        while (isValid(i) && isValid(j)) {
            if (tablero[i][j] == 0) {
                tablero[i][j] = 3;
            }
            i++;
            j++;

        }
    }

    public void llenarDiag135(int i, int j) {

        while (isValid(i) && isValid(j)) {
            if (tablero[i][j] == 0) {
                tablero[i][j] = 4;
            }

            i++;
            j--;
        }

    }

    public void llenarFila(int i, int j) {

        while (isValid(j)) {
            tablero[i][j] = 2;
            j--;
        }
    }

    public void borrarFila(int i, int j) {
        int col = 0;
        while (isValid(col)) {
            tablero[i][col] = 0;
            j++;
        }
    }

    public void borrarReinaAnterior(int i) {
        int j = 0;
        while (isValid(j)) {
            if (tablero[i][j] == 1) {
                tablero[i][j] = 5;
                this.borrarColumna(i + 1, j);
                this.borrarDiag135(i + 1, j - 1);
                this.borrarDiag45(i + 1, j + 1);

            }
            j++;
        }
    }

    public void borrarColumna(int i, int j) {
        while (isValid(i)) {
            if (tablero[i][j] == 2) {
                tablero[i][j] = 0;
            }
            i++;
        }
    }

    public void borrarDiag45(int i, int j) {
        while (isValid(i) && isValid(j)) {
            if (tablero[i][j] == 3) {
                tablero[i][j] = 0;
            }
            j++;
            i++;
        }
    }

    public void borrarDiag135(int i, int j) {
        while (isValid(i) && isValid(j)) {
            if (tablero[i][j] == 4) {
                tablero[i][j] = 0;
            }
            i++;
            j--;
        }
    }

    public void llenarColumna(int i, int j) {

        while (isValid(i)) {
            if (tablero[i][j] == 0) {
                tablero[i][j] = 2;
            }
            i++;

        }
    }

    public void depurar() {
        for (int i = 0; isValid(i); i++) {
            for (int j = 0; isValid(j); j++) {
                if (tablero[i][j] != 1) {

                    tablero[i][j] = 0;
                }
            }
        }
    }

    //ALEATORIA ENTRE 0 Y #DE DISPONIBLES
    public void maneraProbabilistica3() {
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

    public void borrarIntentos(int fila) {

        int col = 0;
        /*  while(isValid(col) && isValid(fila)){
            if(tablero[fila][col]== 5)
                tablero[fila][col] = 0;
            col++;
            fila++;
        } */
        for (int i = fila; isValid(i); i++) {
            for (int j = 0; isValid(j); j++) {
                if (tablero[i][j] == 5) {
                    tablero[i][j] = 0;
                }
            }
        }
    }

    public void maneraCombinatoria2() {
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

    public boolean sePuedeContinuar(int i) {
        int col = 0;
        for (int fila = i; isValid(fila); fila++) {
            LinkedList<Integer> a = new LinkedList<Integer>();
            for (int j = 0; isValid(j); j++) {
                if (tablero[i][col] == 0) {
                    a.add(tablero[i][col]);
                }
            }

            if (a.isEmpty()) {
                return false;

            }
        }
        return true;
    }

    public double getIntentos() {
        return tablerosIntentados;
    }

    public double getReinas() {
        return reinasColocadas;
    }

    public void mostrarTablero() {
        for (int i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setTiempoCola() {
        
        if(tiempoInicio != 0 )
            this.tiempoCola = tiempoInicio - llegada ;

    }

}
