package Presentation;


import java.awt.*;

import javax.swing.*;

public class ProductView extends Component {

    private JFrame frame;
    private JTextField nume;
    private JTextField cantitate;
    private JTextField pret;
    private JTextField ID;
    private JTable table;
    private JButton butonInsert;
    private JButton butonUpdate;
    private JButton butonFindAll;
    private JButton btnNewButton;
    private JButton butonFindByID;

    public ProductView() {
        initialize();
        this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel labelNume = new JLabel("Nume:");
        labelNume.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelNume.setBounds(20, 20, 50, 20);
        frame.getContentPane().add(labelNume);

        JLabel labelCantitate = new JLabel("Cantitate:");
        labelCantitate.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelCantitate.setBounds(20, 60, 61, 20);
        frame.getContentPane().add(labelCantitate);

        JLabel labelPret = new JLabel("Pret:");
        labelPret.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelPret.setBounds(20, 100, 50, 20);
        frame.getContentPane().add(labelPret);

        nume = new JTextField();
        nume.setBounds(85, 20, 100, 20);
        frame.getContentPane().add(nume);
        nume.setColumns(10);

        cantitate = new JTextField();
        cantitate.setBounds(85, 60, 100, 20);
        frame.getContentPane().add(cantitate);
        cantitate.setColumns(10);

        pret = new JTextField();
        pret.setBounds(85, 100, 100, 20);
        frame.getContentPane().add(pret);
        pret.setColumns(10);

        butonInsert = new JButton("Insert");
        butonInsert.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonInsert.setBounds(260, 20, 80, 20);
        frame.getContentPane().add(butonInsert);

        butonUpdate = new JButton("Update");
        butonUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonUpdate.setBounds(260, 60, 80, 20);
        frame.getContentPane().add(butonUpdate);

        butonFindAll = new JButton("FindAll");
        butonFindAll.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonFindAll.setBounds(260, 100, 80, 20);
        frame.getContentPane().add(butonFindAll);

        btnNewButton = new JButton("Delete");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton.setBounds(600, 40, 80, 20);
        frame.getContentPane().add(btnNewButton);

        butonFindByID = new JButton("Find");
        butonFindByID.setFont(new Font("Tahoma", Font.BOLD, 12));
        butonFindByID.setBounds(600, 80, 80, 20);
        frame.getContentPane().add(butonFindByID);

        JLabel labelID = new JLabel("ID:");
        labelID.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelID.setBounds(500, 60, 30, 20);
        frame.getContentPane().add(labelID);

        ID = new JTextField();
        ID.setBounds(530, 60, 50, 20);
        frame.getContentPane().add(ID);
        ID.setColumns(10);

        table = new JTable();
        table.setBounds(20, 150, 950, 400);
        frame.getContentPane().add(table);
    }


    public JTextField getNume() {
        return nume;
    }

    public void setNume(JTextField nume) {
        this.nume = nume;
    }

    public JTextField getCantitate() {
        return cantitate;
    }

    public void setCantitate(JTextField cantitate) {
        this.cantitate = cantitate;
    }

    public JTextField getPret() {
        return pret;
    }

    public void setPret(JTextField pret) {
        this.pret = pret;
    }

    public JTextField getID() {
        return ID;
    }

    public void setID(JTextField ID) {
        this.ID = ID;
    }

    public JButton getButonInsert() {
        return butonInsert;
    }

    public void setButonInsert(JButton butonInsert) {
        this.butonInsert = butonInsert;
    }

    public JButton getButonUpdate() {
        return butonUpdate;
    }

    public void setButonUpdate(JButton butonUpdate) {
        this.butonUpdate = butonUpdate;
    }

    public JButton getButonFindAll() {
        return butonFindAll;
    }

    public void setButonFindAll(JButton butonFindAll) {
        this.butonFindAll = butonFindAll;
    }

    public JButton getBtnNewButton() {
        return btnNewButton;
    }

    public void setBtnNewButton(JButton btnNewButton) {
        this.btnNewButton = btnNewButton;
    }

    public JButton getButonFindByID() {
        return butonFindByID;
    }

    public void setButonFindByID(JButton butonFindByID) {
        this.butonFindByID = butonFindByID;
    }

    public void displayErrorMessage(Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            UIManager UI = new UIManager();
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayInformationMessage(String s) {
        if (!s.isEmpty()) {
            UIManager UI = new UIManager();
            JOptionPane.showMessageDialog(this, s, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void addTable(JTable clientsTable) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(90, 200, 800, 170);

        clientsTable.setVisible(true);
        clientsTable.setEnabled(true);
        scrollPane.setViewportView(clientsTable);
        frame.add(scrollPane);
    }
}

