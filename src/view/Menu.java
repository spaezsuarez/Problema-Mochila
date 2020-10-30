package view;

//Elementos Graficos
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
//Elementos Decorativos
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final int ancho,alto;
    private JButton btnStart;
    private JLabel title;

    public Menu(){
        ancho = 800;
        alto = 600;

        btnStart = new JButton("Iniciar");
        title = new JLabel("El problema de la mochila con Programación dinámica");
    }

    public void initTemplate(){
        setLayout(null);
        setSize(new Dimension(ancho,alto));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initComponents(){
        btnStart.setSize(new Dimension(200,40));
        btnStart.setLocation((this.getWidth()-btnStart.getWidth())/2, 500);
        btnStart.setFont(new Font("Arial",Font.BOLD,20));
        btnStart.addActionListener(this);
        add(btnStart);

        title.setSize(new Dimension(800,40));
        title.setLocation(30,50);
        title.setFont(new Font("Arial",Font.BOLD,25));
        add(title);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == btnStart){

        }

    }
    
}
