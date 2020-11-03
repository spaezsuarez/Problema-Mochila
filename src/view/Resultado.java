package view;

import controllers.ArticuloController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Resultado extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private final int ancho, alto;
    private final int[][] data;
    private final String[][] composiciones;

    private JLabel[][] result;
    private JPanel panel;
    private JButton btnItems;
    private ArrayList<ArrayList<Integer>> results;

    public Resultado(ArticuloController controller) {
        ancho = 1300;
        alto = 600;
        panel = new JPanel();
        btnItems = new JButton("Resultado");
        data = controller.getMatriz();
        result = new JLabel[data.length][data[0].length];
        results = controller.getResults();
        composiciones = controller.getMatrizComposicion();

    }

    public void initComponents() {

        Border border = LineBorder.createGrayLineBorder();
        JScrollPane jsp = new JScrollPane(panel);
        
        //panel.setSize(new Dimension(ancho,alto-100));
        panel.setPreferredSize(new Dimension(ancho,alto-100));
        panel.setLayout(new GridLayout(result.length,result[0].length));
 
        jsp.setBounds(0, 0,ancho,alto-100);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        int contador = 0;
      
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                
                if(i == 0 && j > 2){
                    result[i][j] = new JLabel(String.valueOf(contador));
                    contador++;
                }else if(i > 0 && j > 2 ){
                    result[i][j] = new JLabel("<html><head><></head><body><p> "+ data[i][j] +"</p><br>" + "<p style='color:green;'>"+composiciones[i][j] + " </p></body></html>");
                }else{
                    result[i][j] = new JLabel("<html><body><p> "+ data[i][j] +" </p></body></html>");
                }
                
                //result[i][j].setBounds(j * 60 + 6, i * 60 + 6, 60, 60);
                result[i][j].setPreferredSize(new Dimension(60,45));
                result[i][j].setHorizontalAlignment(JLabel.CENTER);
                result[i][j].setFont(new Font("Arial", Font.BOLD, 15));
                result[i][j].setBorder(border);
                panel.add(result[i][j]);
            }
        }
        
        result[0][0].setText("A");
        result[0][1].setText("P");
        result[0][2].setText("V");

        add(jsp);
        
        btnItems.setSize(new Dimension(150,40));
        btnItems.setLocation((this.getWidth()-btnItems.getWidth())/2, 520);
        btnItems.addActionListener(this);
        btnItems.setFont(new Font("Arial", Font.BOLD, 20));
        btnItems.setFocusable(false);
        add(btnItems);

    }

    public void initTemplate() {
        setLayout(null);
        setTitle("Resultado");
        setSize(new Dimension(ancho, alto));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String resultado = "Articulo   Peso   Valor";
        
        for(int i = 0; i < results.size()-1; i++){
            resultado += "\n"+results.get(i).get(0) + "                  " + results.get(i).get(1) + "         " +results.get(i).get(2);
        }
        
        resultado += "\n\nBeneficio Maximo:  " + results.get(results.size()-1).get(1);
        resultado += "\nPeso Maximo: "+results.get(results.size()-1).get(0);
        
        JOptionPane.showMessageDialog(null, resultado, "Items", JOptionPane.DEFAULT_OPTION);
    }

}