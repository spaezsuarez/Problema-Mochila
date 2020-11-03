package controllers;

import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Arrays;

public class ArticuloController {

    private final int[][] matriz;
    private String[][] matrizComposicion;
    private final int[] pesos, costos,numeroArticulos;
    private ArrayList<ArrayList<Integer>> results;

    public ArticuloController(int sizeRow, int sizeColumn) {

        matriz = new int[sizeRow + 1][sizeColumn + 4];
        matrizComposicion = new String[sizeRow + 1][sizeColumn + 4];
        pesos = new int[sizeRow];
        costos = new int[sizeRow];
        numeroArticulos = new int[sizeRow];
        results = new ArrayList<>();

    }

    public int[][] getMatriz() {
        return this.matriz;
    }

    public String[][] getMatrizComposicion() {
        return matrizComposicion;
    }

    public ArrayList<ArrayList<Integer>> getResults() {
        return results;
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

    public void initMatrizComposicion() {
        for (int i = 4; i < matrizComposicion[0].length; i++) {
            matrizComposicion[1][i] = costos[0] + ":" +numeroArticulos[0];
        }

        for (int j = 0; j < matrizComposicion.length; j++) {
            matrizComposicion[j][3] = "0:0";
        }

        for (int j = 0; j < matrizComposicion.length; j++) {
            matrizComposicion[j][0] = "";
            matrizComposicion[j][1] = "";
            matrizComposicion[j][2] = "";
        }

        for (int i = 2; i < matrizComposicion.length; i++) {
            for (int j = 4; j < matrizComposicion[0].length; j++) {
                CalcularValorCelda(i,j);
            }
        }

    }

    public void CalcularValorCelda(int i, int j) {
        int primerNumero, segundoNumero;

        primerNumero = (matriz[i - 1][j]);

        try {
            segundoNumero = matriz[i][j - pesos[i-1]] + costos[i-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            segundoNumero = 0;
        }

        if (primerNumero > segundoNumero) {
            matrizComposicion[i][j] = (matrizComposicion[i - 1][j]);
        } else if(matriz[i][j] == 0){
            matrizComposicion[i][j] = "0:0";
        }else {
            try{
                matrizComposicion[i][j] = matrizComposicion[i-1][j-pesos[i-1]]+"+"+costos[i-1]+":"+matriz[i][0];
            }catch(IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void setItems(JTextField[][] elements) {
        for (int i = 0; i < elements[0].length; i++) {
            costos[i] = Integer.parseInt(elements[1][i].getText());
            pesos[i] = Integer.parseInt(elements[0][i].getText());
            numeroArticulos[i] = i+1;
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
        initResults(matriz[0].length - 1, matriz.length - 1);
        initMatrizComposicion();
    }

    public int max(int valorUno, int valorDos) {
        if (valorUno > valorDos) {
            return valorUno;
        }
        return valorDos;
    }

    public void initResults(int PesoMaximo, int numeroElementos) {
        int i = numeroElementos, k = PesoMaximo;

        while (i > 0 && k > 0) {
            if (matriz[i][k] != matriz[i - 1][k]) {
                int peso_i = matriz[i][1];
                int valor_i = matriz[i][2];
                int articulo_i = matriz[i][0];

                ArrayList<Integer> articulo = new ArrayList<>();
                articulo.add(articulo_i);
                articulo.add(peso_i);
                articulo.add(valor_i);

                results.add(articulo);

                i -= 1;
                k -= peso_i;
            } else {
                i -= 1;
            }
        }

        int sumaValores = 0, sumPeso = 0;

        for (int z = 0; z < results.size(); z++) {
            sumaValores += results.get(z).get(2);
            sumPeso += results.get(z).get(1);
        }

        results.add(new ArrayList<>(Arrays.asList(sumPeso, sumaValores)));
    }

}
