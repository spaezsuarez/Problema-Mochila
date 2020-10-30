package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class EntradaArticulo extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField[][] articulos;

    public EntradaArticulo(int numeroArticulos) {
        articulos = new JTextField[2][numeroArticulos];

    }

    public void initInputElements() {
        for (int i = 0; i < articulos.length; i++) {
            for (int j = 0; j < articulos[i].length; j++) {
                articulos[i][j] = new JTextField();
                articulos[i][j].setBounds(j * 75 + 6, i * 75 + 6, 50, 50);
                articulos[i][j].setHorizontalAlignment(JLabel.CENTER);
                add(articulos[i][j]);
            }
        }

        repaint();
    }

    public void removeInputElements() {
        removeAll();
        repaint();
    }

    
}
