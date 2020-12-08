package ru.geekbrains.spring;

import com.sun.deploy.ref.AppModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;
import java.util.Scanner;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        primaryStage.setTitle("Shop");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.setX(500);
        primaryStage.show();

    }


    public static void main(String[] args) {

        // консольный вариант. Для просмотра корзины вводим 0000
//        AnnotationConfigApplicationContext context = console();

        launch(args);
//        context.close();
    }

    private static AnnotationConfigApplicationContext console() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
        Basket basket = context.getBean("basket", Basket.class);
        Basket basket1 = context.getBean("basket", Basket.class);
        System.out.println(productRepository.getProducts());

        while(true) {
            System.out.println("Выбери id товара");
            Scanner sc = new Scanner(System.in);
            long id = sc.nextLong();
            if (id != 0000) {
                for (Product p : productRepository.getProducts()) {
                    if (id == p.getId()) {
                        basket.addBasket(p);
                    }
                }
            } else {
                System.out.println(basket.getBuys());
                System.out.println("____________________"); // проверка что корзина уникальная.
                System.out.println(basket1.getBuys());
                break;
            }
        }
        return context;
    }
}
