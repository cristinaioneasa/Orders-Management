package Presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class MainView {

    private JFrame frame;
    private JButton butonClienti;
    private JButton butonProduse;
    private JButton butonComenzi;

    /**
     * Launch the application.

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainView window = new MainView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainView() {
        initialize();
        this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(null);

        butonClienti = new JButton("Clienti");
        butonClienti.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonClienti.setBounds(20, 90, 100, 25);
        frame.getContentPane().add(butonClienti);

        butonProduse = new JButton("Produse");
        butonProduse.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonProduse.setBounds(150, 90, 100, 25);
        frame.getContentPane().add(butonProduse);

        butonComenzi = new JButton("Comenzi");
        butonComenzi.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonComenzi.setBounds(280, 90, 100, 25);
        frame.getContentPane().add(butonComenzi);

        JLabel JPanel = new JLabel("Alegeti tabelul pe care doriti sa il modificati:");
        JPanel.setFont(new Font("Tahoma", Font.BOLD, 15));
        JPanel.setBounds(30, 40, 400, 30);
        frame.getContentPane().add(JPanel);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton getButonClienti() {
        return butonClienti;
    }

    public void setButonClienti(JButton butonClienti) {
        this.butonClienti = butonClienti;
    }

    public JButton getButonProduse() {
        return butonProduse;
    }

    public void setButonProduse(JButton butonProduse) {
        this.butonProduse = butonProduse;
    }

    public JButton getButonComenzi() {
        return butonComenzi;
    }

    public void setButonComenzi(JButton butonComenzi) {
        this.butonComenzi = butonComenzi;
    }
}
