package com.example.task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


class ProductManager {
    public List<String> getProducts() {

        return new ArrayList<>(List.of("Товар 1", "Товар 2", "Товар 3"));
    }
}


class CartManager {
    private List<String> cartItems;

    public CartManager() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(String product) {

        cartItems.add(product);
    }

    public List<String> getCartItems() {
        return new ArrayList<>(cartItems);
    }
}


class OrderManager {
    public void placeOrder(List<String> items) {
        // Логика оформления заказа
        System.out.println("Заказ оформлен с товарами: " + items);
    }
}


class OrderFacade {
    private ProductManager productManager;
    private CartManager cartManager;
    private OrderManager orderManager;

    public OrderFacade(ProductManager productManager, CartManager cartManager, OrderManager orderManager) {
        this.productManager = productManager;
        this.cartManager = cartManager;
        this.orderManager = orderManager;
    }

    public List<String> получитьДоступныеТовары() {
        return productManager.getProducts();
    }

    public void добавитьВКорзину(String товар) {
        cartManager.addToCart(товар);
    }

    public List<String> просмотретьКорзину() {
        return cartManager.getCartItems();
    }

    public void оформитьЗаказ() {
        List<String> товары = cartManager.getCartItems();
        orderManager.placeOrder(товары);
    }
}

// Пример использования
public class OnlineStoreApp {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        CartManager cartManager = new CartManager();
        OrderManager orderManager = new OrderManager();

        OrderFacade orderFacade = new OrderFacade(productManager, cartManager, orderManager);

        // Создаем простой пользовательский интерфейс Swing
        JFrame frame = new JFrame("Интернет-магазин");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton просмотрТоваровКнопка = new JButton("Просмотр Товаров");
        JButton добавитьВКорзинуКнопка = new JButton("Добавить в Корзину");
        JButton просмотретьКорзинуКнопка = new JButton("Просмотреть Корзину");
        JButton оформитьЗаказКнопка = new JButton("Оформить Заказ");

        просмотрТоваровКнопка.addActionListener(e -> {
            List<String> товары = orderFacade.получитьДоступныеТовары();
            JOptionPane.showMessageDialog(frame, "Доступные товары: " + товары);
        });

        добавитьВКорзинуКнопка.addActionListener(e -> {
            String выбранныйТовар = JOptionPane.showInputDialog(frame, "Введите товар для добавления в корзину:");
            orderFacade.добавитьВКорзину(выбранныйТовар);
            JOptionPane.showMessageDialog(frame, выбранныйТовар + " добавлен в корзину.");
        });

        просмотретьКорзинуКнопка.addActionListener(e -> {
            List<String> товарыВКорзине = orderFacade.просмотретьКорзину();
            JOptionPane.showMessageDialog(frame, "Товары в корзине: " + товарыВКорзине);
        });

        оформитьЗаказКнопка.addActionListener(e -> {
            orderFacade.оформитьЗаказ();
            JOptionPane.showMessageDialog(frame, "Заказ успешно оформлен.");
        });

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(просмотрТоваровКнопка);
        frame.add(добавитьВКорзинуКнопка);
        frame.add(просмотретьКорзинуКнопка);
        frame.add(оформитьЗаказКнопка);

        frame.setVisible(true);
    }
}
