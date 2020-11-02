package view;

import controllers.ArticuloController;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.*;
//import javax.swing.JScrollPane;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Resultado extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private final int ancho, alto;
    private final int[][] data;

    private JLabel[][] result;
    private JPanel panel;
    private JButton btnItems;
    private ArrayList<ArrayList<Integer>> results;

    public Resultado(ArticuloController controller) {
        ancho = 800;
        alto = 600;
        panel = new JPanel();
        btnItems = new JButton("Resultado");
        data = controller.getMatriz();
        result = new JLabel[data.length][data[0].length];
        results = controller.getResults();

    }

    public void initComponents() {

        Border border = LineBorder.createGrayLineBorder();
        //JScrollPane jsp = new JScrollPane(panel);
        
        panel.setSize(new Dimension(ancho,alto-100));
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setLocation(0, 0);
        
        int contador = 0;
      
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                
                if(i == 0 && j > 2){
                    result[i][j] = new JLabel(String.valueOf(contador));
                    contador++;
                }else if(i > 0 && j > 2 ){
                    result[i][j] = new JLabel("<html><body><p> "+ data[i][j] +"<br>_______<br>" + "0:0" + " </p></body></html>");
                }else{
                    result[i][j] = new JLabel("<html><body><p> "+ data[i][j] +" </p></body></html>");
                }
                
                result[i][j].setBounds(j * 60 + 6, i * 60 + 6, 60, 60);
                result[i][j].setHorizontalAlignment(JLabel.CENTER);
                result[i][j].setFont(new Font("Arial", Font.BOLD, 15));
                result[i][j].setBorder(border);
                panel.add(result[i][j]);
            }
        }
        
        result[0][0].setText("A");
        result[0][1].setText("Peso");
        result[0][2].setText("Valor");

        add(panel);
        
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
        setResizable(false);
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
        
        resultado += "\n\nBeneficio Maximo:  " + results.get(results.size()-1).get(0);
        
        JOptionPane.showMessageDialog(null, resultado, "Items", JOptionPane.DEFAULT_OPTION);
    }

}
