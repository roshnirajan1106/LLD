package chapter1.duck;


import chapter1.duck.inheritance.Duck;
import chapter1.duck.inheritance.RubberDuck;

public class DuckMain {
    public static void main(String[] args){
        System.out.println("Hi finally");
        Duck d = new RubberDuck();
        d.swim();
    }
}
