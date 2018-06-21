/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reinas;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author daniel
 */
public class Maestro {

    private double tiempo;
    private final Cola master;
    private final Tablero[] tableros;
    private int entreLlegadas ; 
    private int desviacion;

    public Maestro(int n) {

        tableros = new Tablero[n];
        master = new Cola();
        tiempo = 0;
        this.crearTableros();
    }
    
    public void setEntreLlegadas(int entreLlegadas){
        this.entreLlegadas = entreLlegadas;
    }
    
    public void setDesviacion(int desviacion){
        this.desviacion = desviacion; 
    }

    public void crearMaster() {
        for (int i = 0; i < 6; i++) {
            Tablero t = new Tablero(8);
            //master.getCola().add(t);
        }
    }

    public void actualizarCola(int i) {

        for (int j = i; j < tableros.length; j++) {

            if (tiempo > tableros[j].getLlegada() && !tableros[j].getAtendido()) {

                if (!master.getCola().contains(tableros[j])) {
                    master.addCola(tableros[j]);
                    System.out.println("add");
                    
                    
                } else if (tableros[j].getAtendido()) {
                    master.getCola().remove(tableros[j]);
                    System.out.println("Eliminado con llegada: " + tableros[j].getLlegada());
                } else {
                   
                }
            }

        }
         System.out.println("Llegadas en Cola " + master.getCola().size());
        for (Tablero t : master.getCola()){
           
            System.out.print(t.getLlegada() + " ");
        }
        System.out.println();
    }

    private void crearTableros() {
        /*        for (Tablero a : tableros) {
            a = new Tablero(8);
         //   master.getCola().add(a);
        }
         */
        for (int i = 0; i < tableros.length; i++) {
            Random rand = new Random();
            if (rand.nextInt(10) > 5) {
                tableros[i] = new Vegas(8);
            } else {
                tableros[i] = new Deterministico(8);
            }
        }

    }

    public void setLlegadasTableros() {
        for (int i = 1; i < tableros.length; i++) {

            tableros[i].setLlegada(tiempo + generarLlegada());
        }
    }

    public void run() {
        //master.getCola().get(0).setLlegada(0);

        tableros[0].setLlegada(0);
        for (int i = 1; i <= tableros.length; i++) {

            if (tableros[i - 1].getLlegada() > tiempo) {
                tiempo = tableros[i - 1].getLlegada();
            }

            if (i < tableros.length) {
                tableros[i].setLlegada(tiempo + generarLlegada());
            }

            tableros[i - 1].setTiempoInicio(tiempo);

            tableros[i - 1].resolverTablero();
            tiempo += tableros[i - 1].getReinas();

            tableros[i - 1].setTiempoFin(tiempo);
            tableros[i - 1].setTiempoCola(tiempo);

            System.out.println("Tiempo para tablero " + (i - 1) + ": " + tableros[i - 1].getReinas());
            // this.mostrarTiempo();

            // this.encolarTableros(i);

            /*
            master.getCola().get(i + 1).setLlegada(tiempo + master.generarLlegada());
            master.getCola().get(i).maneraCombinatoria2();
            
            tiempo += master.getCola().get(i).getReinas();
            this.mostrarTiempo(); 
             */
        }
        System.out.println("Encolados: " + master.getCola().size());

    }

    public void run2() {

        tableros[0].setLlegada(0);
        this.asignarLlegadas();

        for (int i = 1; i <= tableros.length; i++) {

            if (tableros[i - 1].getLlegada() > tiempo) {
                tiempo = tableros[i - 1].getLlegada();
            }

            // if(i < tableros.length)
            //     tableros[i].setLlegada(tiempo + generarLlegada());
            tableros[i - 1].setTiempoInicio(tiempo);

            tableros[i - 1].setAtendido(true);
            tableros[i - 1].resolverTablero();

            tiempo += tableros[i - 1].getReinas();
            this.actualizarCola(i-1);
            tableros[i-1].setTiempoCola();
            tableros[i - 1].setTiempoFin(tiempo);
            
            if(i < tableros.length)
                if(tableros[i].getLlegada() < tableros[i-1].getTiempoFin())
                    master.getCola().remove(tableros[i-1]);

            //System.out.println("Tiempo para tablero " + (i - 1) + ": " + tableros[i - 1].getReinas());
            // this.mostrarTiempo();
            //this.encolarTableros(i);
            /*
            master.getCola().get(i + 1).setLlegada(tiempo + master.generarLlegada());
            master.getCola().get(i).maneraCombinatoria2();
            
            tiempo += master.getCola().get(i).getReinas();
            this.mostrarTiempo(); 
             */
        }
        System.out.println("Encolados: " + master.getCola().size());
    }

    public static double[] sortArray(double[] nonSortedArray) {
        double[] sortedArray = new double[nonSortedArray.length];
        double temp;
        for (int j = 0; j < nonSortedArray.length - 1; j++) {// added this for loop, think about logic why do we have to add this to make it work

            for (int i = 0; i < nonSortedArray.length - 1; i++) {
                if (nonSortedArray[i] > nonSortedArray[i + 1]) {
                    temp = nonSortedArray[i];
                    nonSortedArray[i] = nonSortedArray[i + 1];
                    nonSortedArray[i + 1] = temp;
                    sortedArray = nonSortedArray;

                }
            }
        }
        return sortedArray;
    }

    public void asignarLlegadas() {
        double[] llegadas = new double[tableros.length];
        llegadas[0] = 0;
        for (int i = 1; i < tableros.length; i++) {
            Random rand = new Random();
            llegadas[i] =   entreLlegadas   ;
            if(rand.nextInt(10) > 5)
                llegadas[i] += rand.nextInt(desviacion);
            else 
                llegadas[i] -= rand.nextInt(desviacion);
        }

        this.sortArray(llegadas);
        
        
        for (int i = 0 ; i <llegadas.length ; i++){
            System.out.print(llegadas[i]+ " ");
        }
        System.out.println();  

        for (int i = 1; i < tableros.length; i++) {

            tableros[i].setLlegada(llegadas[i]+ tableros[i-1].getLlegada() );

        }

    }

    public void encolarTableros(int i) {

        // for (int j = i; j < tableros.length ; j++){
        if (i < tableros.length && tiempo > tableros[i].getLlegada()) {
            master.addCola(tableros[i]);
            tableros[i].setTiempoCola(tiempo - tableros[i].getLlegada());
        }

        // }
    }

    public int generarLlegada() {
        Random rand = new Random();
        return rand.nextInt(10) + 10;
    }

    public void mostrarLlegadas() {
        int i = 0;
        for (Tablero a : tableros) {

            System.out.println("Tablero " + (i + 1));
            System.out.println("Llegada: " + a.getLlegada());
            i++;

        }
    }

    public void mostrarTiempo() {
        System.out.println("Tiempo Actual: " + tiempo);
    }

    public void mostrarTiempoEncolado() {
        int i = 1;
        for (Tablero a : master.getCola()) {
            System.out.println("Tiempo en cola " + (i) + "= " + a.getTiempoCola());
            i++;
        }
    }

    public int[] metodoUsado() {
        int[] metodos = new int[2];
        metodos[0] = 0;
        metodos[1] = 0;
        for (Tablero tab : tableros) {
            if (tab.getMetodo().equals("Vegas")) {
                metodos[0]++;
            } else {
                metodos[1]++;
            }
        }
        return metodos;
    }

    public double esperaPromedio() {
        int suma = 0;
        for (int i = 1; i < tableros.length; i++) {
            suma += tableros[i].getTiempoCola();
        }
        return suma / (tableros.length - 1);

    }

    public double[] maxMinEspera() {
        double[] maxMin = new double[2];
        maxMin[0] = Double.NEGATIVE_INFINITY;
        maxMin[1] = Double.POSITIVE_INFINITY;
        for (int i = 1; i < tableros.length; i++) {
            if (tableros[i].getTiempoCola() > maxMin[0]) {
                maxMin[0] = tableros[i].getTiempoCola();
            }
            if (tableros[i].getTiempoCola() < maxMin[1]) {
                maxMin[1] = tableros[i].getTiempoCola();
            }
        }
        return maxMin;
    }

    public void mostrarReporte() {
        System.out.println();
        for (int i = 0; i < tableros.length; i++) {
            System.out.println("Retador " + (i + 1) + ": "
                    + "\tLlegada= " + tableros[i].getLlegada()
                    + "\tInicio= " + tableros[i].getTiempoInicio()
                    + "\tFin= " + tableros[i].getTiempoFin()
                    + "\tTiempo= " + tableros[i].getReinas()
                    + "\tEn Cola= " + tableros[i].getTiempoCola()
                    //+ "\tTableros en Cola= " + master.getCola().size()
                    + "\tMetodo= " + tableros[i].getMetodo());

        }

        System.out.println("Retadores Vegas= " + metodoUsado()[0]);
        System.out.println("Retadores deterministicos= " + metodoUsado()[1]);
        System.out.println("Cola Máxima= " + (master.getColaMax() -1) + " ");
        System.out.println("Max Tiempo en Cola= " + maxMinEspera()[0]);
        System.out.println("Min Tiempo en Cola= " + maxMinEspera()[1]);
        System.out.println("Tiempo Promedio de Espera= " + esperaPromedio());
    }

    public void mostrarSoluciones() {
        int i= 1;
        for (Tablero t : tableros)
        {
            System.out.println("Solución para Retador "+ i);
            t.mostrarTablero();
            i++;
        }
    }
}
