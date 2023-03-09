package Presentation;

import Bussiness_logic.OrderBll;
import Model.Order;
import exception.Failed;

import javax.swing.*;
import java.util.List;

public class OrderController {
    private OrderView view;
    private OrderBll order;

    public OrderController(){
        view = new OrderView();
        order = new OrderBll();
        initializeListeners();
    }

    private void initializeListeners() {

        view.getButonFindAll().addActionListener(e->{
            List<Order> orders = order.getOrder().findAll();
            JTable ordersTable = order.getOrder().createTable(orders);
            view.addTable(ordersTable);
        });

        view.getButonInsert().addActionListener(e->{
            try{
                int cantitate = Integer.parseInt(view.getCantitate().getText());
                Order o = new Order(Integer.parseInt(view.getComboBoxClient().getSelectedItem().toString()),
                        Integer.parseInt(view.getComboBoxProdus().getSelectedItem().toString()),
                        cantitate);
                order.insertOrder(o);
                view.displayInformationMessage("Comanda a fost creata");
            }catch (NumberFormatException exp){
                view.displayInformationMessage("Cantitatea trebuie sa fie un numar");
            }catch (Failed exception){
                view.displayErrorMessage(exception);
            }
        });

        view.getButonUpdate().addActionListener(e->{
            try {
                int id = Integer.parseInt(view.getID().getText());
                int cantitate = Integer.parseInt(view.getCantitate().getText());
                Order o = new Order(Integer.parseInt(view.getComboBoxClient().getSelectedItem().toString()),
                        Integer.parseInt(view.getComboBoxProdus().getSelectedItem().toString()),
                        cantitate);
                order.updateOrder(o, id);

                view.displayInformationMessage("Comanda a fost schimbata");
            } catch (NumberFormatException ex) {
                view.displayInformationMessage("Cantitatea trebuie sa fie un numar.");
            } catch (Failed ex2) {
                view.displayErrorMessage(ex2);
            }
        });
        view.getButonDelete().addActionListener(e->{
            try{
                int id = Integer.parseInt(view.getID().getText());
                order.deleteOrder(id);
                view.displayInformationMessage("Comanda a fost stearsa");
            }catch (NumberFormatException exp){
                view.displayInformationMessage("ID trebuie sa fie un numar");
            }catch (Failed exception){
                view.displayErrorMessage(exception);
            }
        });
    }
}
