package controllers;

import javax.swing.JTextField;

public class ArticuloController {

    private int[][] matriz;
    private int[] pesos, costos;

    public ArticuloController(int sizeRow, int sizeColumn) {

        matriz = new int[sizeRow+1][sizeColumn+4];
        pesos = new int[sizeRow];
        costos = new int[sizeRow];
    }

    public void setMatriz() {

        for (int i = 0; i < matriz[0].length; i++) {
            matriz[0][i] = 0;
        }

        for (int j = 0; j < matriz.length; j++) {
            matriz[j][3] = 0;

        }

        for (int j = 1; j < matriz.length; j++) {
            matriz[j][0] = j ;
            matriz[j][1] = pesos[j-1];
            matriz[j][2] = costos[j-1];
        }

    }

    public void setItems(JTextField[][] elements) {
        for (int i = 0; i < elements[0].length; i++) {
            costos[i] = Integer.parseInt(elements[1][i].getText());
            pesos[i] = Integer.parseInt(elements[0][i].getText());
        }

    }

    public void sort() {
        for (int i = 0; i < pesos.length - 1; i++) {
            for (int j = i + 1; j < pesos.length; j++) {
                if (pesos[i] > pesos[j]) {
                    int pesoTemp = pesos[i];
                    pesos[i] = pesos[j];
                    pesos[j] = pesoTemp;

                    int costoTemp = costos[i];
                    costos[i] = costos[j];
                    costos[j] = costoTemp;
                }
            }
        }


    }

    public void start() {
       
       for(int i = 1; i < matriz.length; i++){
           for(int j = 3; j < matriz[0].length;j++){
               if(matriz[i][1] <= j-3){
                   int peso_i = matriz[i][1];
                   int valor_i = matriz[i][2];
                   matriz[i][j] = max(matriz[i-1][j],matriz[i-1][j-peso_i] + valor_i);
               }else{
                   matriz[i][j] = matriz[i-1][j];
               }
           }
       }
        
        
    }

    public int max(int valorUno, int valorDos) {
        if (valorUno > valorDos) {
            return valorUno;
        }

        return valorDos;
    }

    public void imprimirMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("\n");
        }

    }

    public void imprimirItems() {
        for (int i = 0; i < pesos.length; i++) {
            System.out.print("(" + pesos[i] + "," + costos[i] + ") ");
        }
        System.out.println("\n");
    }

}
