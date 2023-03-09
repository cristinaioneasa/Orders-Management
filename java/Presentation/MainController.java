package Presentation;

public class MainController {

    private MainView view;

    public MainController(){
        view = new MainView();
        initializeListeners();

    }

    public void initializeListeners(){
        view.getButonClienti().addActionListener(e -> new ClientController());
        view.getButonProduse().addActionListener(e -> new ProductController());
        view.getButonComenzi().addActionListener(e -> new OrderController());
    }


}
