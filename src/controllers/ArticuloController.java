package controllers;

import models.Articulo;
import javax.swing.JTextField;

public class ArticuloController {
    
    private Articulo[][] matriz;
    private Articulo[] items;

    public ArticuloController(int sizeRow,int sizeColumn){

        matriz = new Articulo[sizeRow][sizeColumn];
        items = new Articulo[sizeColumn-1];
        setMatriz();
    }

    public void setMatriz(){

        for(int i = 0; i < matriz[0].length; i++){
            matriz[0][i] = new Articulo();
            matriz[0][i].setCosto(0);
            matriz[0][i].setPeso(0);
            matriz[0][i].setNumeroArticulo(0);
        }

        for(int j = 0; j < matriz.length; j++){
            matriz[j][0] = new Articulo();
            matriz[j][0].setCosto(0);
            matriz[j][0].setPeso(0);
            matriz[j][0].setNumeroArticulo(0);
            matriz[j][0].setComposicion("0:0");
        }

    }

    public void setItems(JTextField[][] elements){
        for(int i = 0; i < matriz.length-1; i++){
            items[i] = new Articulo();
            items[i].setPeso(Integer.parseInt(elements[0][i].getText()));
            items[i].setCosto(Integer.parseInt(elements[1][i].getText()));
        }

    }

    public void start(){
        int pesoMaximo = matriz[0].length;
        for(int i = 1; i < matriz.length; i++){
            for(int j = 1; j < matriz[i].length; j++){
                if( items[i-1].getPeso() < pesoMaximo){
                    matriz[i][j] = max(items[i-1].getCosto(),matriz[i-1][pesoMaximo-items[i-1].getPeso()],matriz[i-1][j]);
                }else{
                    matriz[i][j] = matriz[i-1][j];
                }
                
                //System.out.println("Valor objeto: " + items[i-1].getCosto());
            }
        }
    }

    public Articulo max(int valor,Articulo ob1,Articulo ob2){

        int valorUno = valor + ob1.getCosto();
        int valorDos = ob2.getCosto();

        if(valorUno > valorDos){
            return ob1.clone(valor);
        }
        return ob2.clone(valor);
    }

    public Articulo[][] getMatriz(){
        return matriz;
    }

    public Articulo[] getItems(){
        return items;
    }

    public void imprimirMatriz(){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[i].length; j++){
               System.out.print(matriz[i][j].getCosto() + " ");
            }
            System.out.println("\n");
        }

    }

}
