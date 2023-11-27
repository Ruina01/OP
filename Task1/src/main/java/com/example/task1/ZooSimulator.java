package com.example.task1;

import java.util.ArrayList;
import java.util.List;


interface AnimalFactory {
    Animal createAnimal();
}


interface Animal {
    void makeSound();
}


class LionFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Lion();
    }
}


class MonkeyFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Monkey();
    }
}


class ElephantFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Elephant();
    }
}


class Lion implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Лев издает рык");
    }
}

class Monkey implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Обезьяна издает визги");
    }
}

class Elephant implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Слон издает трубный звук");
    }
}


class Zoo {
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(AnimalFactory animalFactory) {
        Animal animal = animalFactory.createAnimal();
        animals.add(animal);
    }

    public void makeAllSounds() {
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }
}


public class ZooSimulator {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();


        zoo.addAnimal(new LionFactory());


        zoo.addAnimal(new MonkeyFactory());


        zoo.addAnimal(new ElephantFactory());


        zoo.makeAllSounds();
    }
}
