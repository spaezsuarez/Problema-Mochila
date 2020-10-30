package controllers;

import models.Articulo;

public class ArticuloController {
    
    private Articulo[][] matriz;
    private int sizeRow,sizeColumn;

    public ArticuloController(int sizeRow,int sizeColumn){
        this.sizeRow = sizeRow;
        this.sizeColumn = sizeColumn;
        setMatriz();
    }

    public void setMatriz(){
        matriz = new Articulo[sizeRow][sizeColumn];
    }

    public Articulo[][] getMatriz(){
        return matriz;
    }

    public void setDimensiones(int sizeRow,int sizeColumn){
        this.sizeRow = sizeRow;
        this.sizeColumn = sizeColumn;
    }

    public int getSizeRow(){
        return sizeRow;
    }

    public int getSizeColumn(){
        return sizeColumn;
    }

    public Articulo max(Articulo ob1,Articulo ob2){
        if(ob1.getCosto() > ob2.getCosto()){
            return ob1.clone();
        }
        return ob2.clone();
    }

}
