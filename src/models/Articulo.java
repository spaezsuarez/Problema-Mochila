package models;

public class Articulo {

    private int numeroArticulo,peso,costo;
    private String composicion;

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

    public String getComposicion(){
        return composicion;
    }

    public void setComposicion(String composicion){
        this.composicion = composicion;
    }

    public Articulo clone(){

        Articulo clone = new Articulo();
        clone.setCosto(this.costo);
        clone.setNumeroArticulo(this.numeroArticulo);
        clone.setPeso(this.peso);
        return clone;

    }
    
}
