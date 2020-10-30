package models;

public class Articulo {

    private int numeroArticulo,peso,costo;

    public void setCosto(int costo){
        this.costo = costo;
    }

    public void setPeso(int peso){
        this.peso = peso;
    }

    public void setNumeroArticulo(int numeroArticulo){
        this.numeroArticulo = numeroArticulo;
    }

    public int getNumeroArticulo(){
        return numeroArticulo;
    }

    public int getPeso(){
        return peso;
    }

    public int getCosto(){
        return costo;
    }
    
}
