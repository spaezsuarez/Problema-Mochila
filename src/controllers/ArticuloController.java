package controllers;

import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Arrays;

public class ArticuloController {

    private final int[][] matriz;
    private final int[] pesos, costos;
    private ArrayList<ArrayList<Integer>> results;

    public ArticuloController(int sizeRow, int sizeColumn) {

        matriz = new int[sizeRow + 1][sizeColumn + 4];
        pesos = new int[sizeRow];
        costos = new int[sizeRow];
        results = new ArrayList<>();
        

    }

    public void initMatriz() {

        for (int i = 0; i < matriz[0].length; i++) {
            matriz[0][i] = 0;
        }

        for (int j = 0; j < matriz.length; j++) {
            matriz[j][3] = 0;

        }

        for (int j = 1; j < matriz.length; j++) {
            matriz[j][0] = j;
            matriz[j][1] = pesos[j - 1];
            matriz[j][2] = costos[j - 1];
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

    public void start(JTextField[][] elements) {

        setItems(elements);
        sort();
        initMatriz();

        for (int i = 1; i < matriz.length; i++) {
            for (int j = 3; j < matriz[0].length; j++) {
                if (matriz[i][1] <= j - 3) {
                    int peso_i = matriz[i][1];
                    int valor_i = matriz[i][2];
                    matriz[i][j] = max(matriz[i - 1][j], matriz[i - 1][j - peso_i] + valor_i);
                } else {
                    matriz[i][j] = matriz[i - 1][j];
                }
            }
        }
        
        initResults(matriz[0].length-1, matriz.length-1);

    }

    public int max(int valorUno, int valorDos) {
        if (valorUno > valorDos) {
            return valorUno;
        }

        return valorDos;
    }
    
    public int[][] getMatriz(){
        return this.matriz;
    }
    
    public void initResults(int PesoMaximo,int numeroElementos){
        int i = numeroElementos,k = PesoMaximo;
        
        while (i > 0 && k > 0) {
            if(matriz[i][k] != matriz[i-1][k]){
                int peso_i = matriz[i][1];
                int valor_i = matriz[i][2];
                int articulo_i = matriz[i][0];
                
                ArrayList<Integer> articulo = new ArrayList<>();
                articulo.add(articulo_i);
                articulo.add(peso_i);
                articulo.add(valor_i);
                
                results.add(articulo);

                i -= 1;
                k-= peso_i;
            }else{
                i -= 1;
            }
        }
        
        int suma = 0;
        
        for(int z = 0; z < results.size(); z++){
            suma += results.get(z).get(2);
        }
        
        results.add(new ArrayList<>(Arrays.asList(suma)));
    }
    
    public ArrayList<ArrayList<Integer>> getResults(){
        return results;
    }

    

}
