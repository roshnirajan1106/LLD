package chapter1.duck.inheritance;

public class RubberDuck extends Duck{

    @Override
    public void quack(){
        System.out.println("no quacking");
    }

    public void fly(){
        System.out.println("no flying");
    }

    @Override
    public void display(){
        System.out.println("display the rubberduck");
    }
}
