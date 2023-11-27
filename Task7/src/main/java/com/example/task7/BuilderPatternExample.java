package com.example.task7;

// Интерфейс строителя
interface BurgerBuilder {
    void buildBun();
    void buildPatty();
    void buildCheese();
    void buildSauce();
    void buildSize();
    Burger getResult();
}


class Burger {
    private String bun;
    private String patty;
    private String cheese;
    private String sauce;
    private String size;

    public void setBun(String bun) {
        this.bun = bun;
    }

    public void setPatty(String patty) {
        this.patty = patty;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Бургер {" +
                "булочка='" + bun + '\'' +
                ", котлета='" + patty + '\'' +
                ", сыр='" + cheese + '\'' +
                ", соус='" + sauce + '\'' +
                ", размер='" + size + '\'' +
                '}';
    }
}


class VeggieBurgerBuilder implements BurgerBuilder {
    private Burger burger;

    public VeggieBurgerBuilder() {
        this.burger = new Burger();
    }

    @Override
    public void buildBun() {
        burger.setBun("Булочка из цельного зерна");
    }

    @Override
    public void buildPatty() {
        burger.setPatty("Овощная котлета");
    }

    @Override
    public void buildCheese() {
        burger.setCheese("Сыр Швейцария");
    }

    @Override
    public void buildSauce() {
        burger.setSauce("Майонез");
    }

    @Override
    public void buildSize() {
        burger.setSize("Обычный");
    }

    @Override
    public Burger getResult() {
        return burger;
    }
}


class Waiter {
    private BurgerBuilder burgerBuilder;

    public void setBurgerBuilder(BurgerBuilder burgerBuilder) {
        this.burgerBuilder = burgerBuilder;
    }

    public Burger getBurger() {
        return burgerBuilder.getResult();
    }

    public void constructBurger() {
        burgerBuilder.buildBun();
        burgerBuilder.buildPatty();
        burgerBuilder.buildCheese();
        burgerBuilder.buildSauce();
        burgerBuilder.buildSize();
    }
}


public class BuilderPatternExample {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();

        BurgerBuilder veggieBurgerBuilder = new VeggieBurgerBuilder();

        waiter.setBurgerBuilder(veggieBurgerBuilder);
        waiter.constructBurger();

        Burger veggieBurger = waiter.getBurger();
        System.out.println("Вегетарианский бургер: " + veggieBurger);
    }
}

