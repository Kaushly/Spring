package ru.geekbrains.spring;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
    Basket basket = context.getBean("basket", Basket.class);

    @FXML
    TableView<Product> shop;

    @FXML
    private TableColumn<Product, Long> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Integer> priceColumn;

    @FXML
    private TableColumn<Product, Integer> countColumn;

    @FXML
    TableView<Product> basketTable;
    @FXML
    private TableColumn<Product, Long> idBuyColumn;

    @FXML
    private TableColumn<Product, String> nameBuyColumn;

    @FXML
    private TableColumn<Product, Integer> priceBuyColumn;


    @FXML
    TextField choice;

    @PostConstruct
    public void initialize(URL location, ResourceBundle resources) {
        productRepository.init();
        basket.getBuys();

        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("sale"));
        countColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("count"));

        shop.getItems().addAll(productRepository.getProducts());

        idBuyColumn.setCellValueFactory(new PropertyValueFactory<Product, Long>("id"));
        nameBuyColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceBuyColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("sale"));

    }
    public void addProduct(){
        shop.refresh();
        basketTable.getItems().add(basket.getBuys().get(basket.getBuys().size() - 1));
    }

    public void deleteProduct(){
        shop.refresh();
        basketTable.getItems().removeAll(basketTable.getItems());
        basketTable.getItems().addAll(basket.getBuys());
    }

    public void btnAddAction(ActionEvent actionEvent) {
        long idBuy = Long.parseLong(choice.getText());
        for (Product p : productRepository.getProducts()) {
            if (idBuy == p.getId()) {
                basket.addBasket(p);
                p.setCount(p.getCount() - 1);
                addProduct();
            }
        }
        choice.clear();
    }

    public void btnDeleteAction(ActionEvent actionEvent) {
        long idBuy = Long.parseLong(choice.getText());
        if(basket.getBuys().size() != 0) {
            for (Product p : basket.getBuys()) {
                if (idBuy == p.getId()) {
                    basket.deleteBasket(p);
                    p.setCount(p.getCount() + 1);
                    deleteProduct();
                }
            }
        }
        choice.clear();
    }

    public void btnExitAction(ActionEvent actionEvent) {
        context.close();
        Platform.exit();
    }
}
