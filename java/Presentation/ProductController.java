package Presentation;

import Bussiness_logic.ProductBll;
import Model.Product;
import exception.Failed;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class ProductController {
    private ProductView view;
    private ProductBll product;

    public ProductController() {
        view = new ProductView();
        product = new ProductBll();
        initializeListeners();
    }

    private void initializeListeners() {

        view.getButonInsert().addActionListener(e-> {
            try {
                Product p = new Product(view.getNume().getText(), Integer.parseInt(view.getCantitate().getText()), Integer.parseInt(view.getPret().getText()));
                List<Product> products = product.findAll();
                Product lastProduct = products.get(products.size() - 1);
                p.setID(lastProduct.getID() + 1);
                product.insertProduct(p);
                view.displayInformationMessage("Produsul a fost adaugat in baza de date.");
            }catch (Failed message) {
                view.displayErrorMessage(message);
            }
        });

        view.getButonUpdate().addActionListener((e -> {
            try {
                int ID = Integer.parseInt(view.getID().getText());
                Product p = new Product(view.getNume().getText(), Integer.parseInt(view.getCantitate().getText()), Integer.parseInt(view.getPret().getText()));
                product.updateProduct(p, ID);
                view.displayInformationMessage("Datele despre produs au fost schimbate");
            }catch(NumberFormatException exception) {
                view.displayInformationMessage("Cantitatea trebuie sa fie un numar!!");
            }
        }));

        view.getButonFindAll().addActionListener(e->{
            List<Product> products = product.getProduct().findAll();
            JTable productsTable = product.getProduct().createTable(products);
            view.addTable(productsTable);
        });

        view.getBtnNewButton().addActionListener(e->{
            try {
                int ID = Integer.parseInt(view.getID().getText());
                product.deleteProduct(ID);
                view.displayInformationMessage("Produsul a fost sters din baza de date");
            }catch (NumberFormatException exception){
                view.displayInformationMessage("ID trebuie sa fie un numar!!");
            }
        });

        view.getButonFindByID().addActionListener(e->{
            int id = Integer.parseInt(view.getID().getText());
            List<Product> products = new ArrayList<>();
            Product p = product.getProduct().findById(id);
            products.add(p);
            JTable productsTable = product.getProduct().createTable(products);
            view.addTable(productsTable);
        });
    }
}
