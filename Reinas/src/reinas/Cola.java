/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reinas;

import java.util.ArrayList;


/**
 *
 * @author daniel
 */
public class Cola {
    
    private double tiempo; 
    private ArrayList<Tablero> cola; 
    private double colaMax; 
    private boolean libre; 
    
    public Cola(){
        tiempo= 0; 
        cola = new ArrayList<Tablero>(); 
        colaMax= 0; 
    }
    
    public void addCola(Tablero t){
        cola.add(t);
        System.out.println("Tamaño Cola "+ cola.size());
        System.out.println("Agregado con llegada = "+ t.getLlegada());
        
        
        colaMax = Math.max(colaMax, cola.size());
        
    }
    
    public double getColaMax(){
        return colaMax;
    }
    
    public void removeCola(Tablero t){
        
    }
    
    public void incrTiempo(){
        tiempo++;
    }
    
    public void setLibre(boolean libre){
        this.libre = libre; 
    }
    
    public void cambiarEstado(){
        this.libre = !libre; 
    }
    
    public void actualizarEstado(){
        if(cola.size() == 0){
            libre = true; 
        }
        else{ 
            libre = false; 
        }
    }
    
    public void registrarLlegada(Tablero t){
        
        if(libre)
            t.resolverTablero();
        else
            cola.add(t); 
    }
    
  
    
    public boolean getLibre() {
        return libre; 
    }
    
    public double getTiempo(){
        return tiempo;
    }
    
    public ArrayList<Tablero> getCola()
    {
        return cola; 
    }
    
    
    
    /*
    public void run(int n){
        
        for (int i=0; i <n ; i++){
            Tablero  t = new Tablero(8); 
            t.setLlegada(tiempo +this.generarLlegada()); 
            
            System.out.println("Tablero "+ i+1);
            System.out.println("Tiempo de Llegada: "+ t.getLlegada());
            
            
        }
        
        
    }
    */
}
