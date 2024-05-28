class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    // Static polymorphism (method overloading)
    public void makeSound() {
        System.out.println("Dog barks");
    }

    // Dynamic polymorphism (method overriding)
    public void move() {
        System.out.println("Dog moves");
    }
}

public class Prog30 {
    public static void main(String[] args) {
        // Static polymorphism
        Animal animal = new Animal();
        animal.makeSound(); // Calls Animal's makeSound method

        Dog dog = new Dog();
        dog.makeSound(); // Calls Dog's makeSound method

        // Dynamic polymorphism
        Animal anotherDog = new Dog();
        anotherDog.makeSound(); // Calls Dog's makeSound method (dynamic binding)
        
        // Uncommenting the following line will result in a compilation error because Animal does not have a move method
        // anotherDog.move();
    }
}
