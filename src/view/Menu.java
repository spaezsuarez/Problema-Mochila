package view;

//Elementos Graficos
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
//Elementos Decorativos
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
//Controladores
import controllers.ArticuloController;

public class Menu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final int ancho, alto;

    private final JButton btnStart, btnGrilla,btnLimpiar;
    private final JLabel title, txtLimite, txtNumeroArticulos;
    private final JTextField inputPesoMaximo, inputNumeroArticulos;
    private EntradaArticulo panel;

    public Menu() {
        ancho = 800;
        alto = 600;

        btnStart = new JButton("Iniciar");
        btnGrilla = new JButton("Crear Grilla");
        btnLimpiar = new JButton("Limpiar Grilla");
        title = new JLabel("El problema de la mochila con Programación dinámica");
        txtLimite = new JLabel("Peso limite de la Mochila: ");
        txtNumeroArticulos = new JLabel("Numero de Articulos: ");
        inputPesoMaximo = new JTextField();
        inputNumeroArticulos = new JTextField();
    }

    public void initComponents() {

        btnStart.setSize(new Dimension(200, 40));
        btnStart.setLocation((this.getWidth() - btnStart.getWidth()) / 2, 500);
        btnStart.setFont(new Font("Arial", Font.BOLD, 20));
        btnStart.addActionListener(this);
        add(btnStart);

        title.setSize(new Dimension(800, 40));
        title.setLocation(20, 50);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        add(title);

        txtLimite.setSize(new Dimension(500, 30));
        txtLimite.setLocation(10, 150);
        txtLimite.setFont(new Font("Arial", Font.BOLD, 20));
        add(txtLimite);

        inputPesoMaximo.setSize(new Dimension(30, 30));
        inputPesoMaximo.setLocation(270, 150);
        inputPesoMaximo.setHorizontalAlignment(JLabel.CENTER);
        add(inputPesoMaximo);

        txtNumeroArticulos.setSize(new Dimension(500, 30));
        txtNumeroArticulos.setLocation(10, 200);
        txtNumeroArticulos.setFont(new Font("Arial", Font.BOLD, 20));
        add(txtNumeroArticulos);

        inputNumeroArticulos.setSize(new Dimension(30, 30));
        inputNumeroArticulos.setLocation(270, 200);
        inputNumeroArticulos.setHorizontalAlignment(JLabel.CENTER);
        add(inputNumeroArticulos);

        btnGrilla.setSize(new Dimension(150, 40));
        btnGrilla.setLocation(350, 170);
        btnGrilla.setFont(new Font("Arial", Font.BOLD, 15));
        btnGrilla.setFocusable(false);
        btnGrilla.addActionListener(this);
        add(btnGrilla);

        btnLimpiar.setSize(new Dimension(150, 40));
        btnLimpiar.setLocation(520, 170);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 15));
        btnLimpiar.setFocusable(false);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

    }

    public void initTemplate() {
        setLayout(null);
        setTitle("Menu");
        setSize(new Dimension(ancho, alto));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        int numeroElementos = 0;
        int pesoMaximo = 0;

        if (event.getSource() == btnGrilla) {

            try{

                numeroElementos = Integer.parseInt(inputNumeroArticulos.getText());

                panel = new EntradaArticulo(numeroElementos);
                panel.initInputElements();
                panel.setSize(new Dimension(ancho, 200));
                panel.setLocation(0, 270);
                //panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                add(panel);
                repaint();

            }catch(NumberFormatException  e){
                JOptionPane.showMessageDialog(null, "Ingrese los numeros", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if(event.getSource() == btnLimpiar ){

            try{
                panel.removeInputElements();
            }catch(NullPointerException e){}

        }else if(event.getSource() == btnStart){
            ArticuloController controller = null;

            try{

                numeroElementos = Integer.parseInt(inputNumeroArticulos.getText());
                pesoMaximo = Integer.parseInt(inputPesoMaximo.getText());

                controller = new ArticuloController(numeroElementos,pesoMaximo);
                controller.start(panel.getArticulos());
                
                Resultado ventana = new Resultado(controller);
                ventana.initTemplate();
                
                
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Ingrese solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
            }catch(NullPointerException  a){
                JOptionPane.showMessageDialog(null, "Ingrese primero los articulos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
