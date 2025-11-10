package com.example;

/**
Key points:
    - Default and static methods allow adding functionality to interfaces without breaking existing implementations.
    - Static methods cannot be overridden by implementing classes.
    - Default methods can be overridden if desired.
 */
// Interface with default and static methods
interface Vehicle {

    // Abstract method (must be implemented)
    void start();

    // Default method (optional to override)
    default void honk() {
        System.out.println("Vehicle is honking: Beep Beep!");
    }

    // Static method (can be called on interface)
    static void service() {
        System.out.println("Vehicle is being serviced!");
    }
}

// Concrete class implementing Vehicle
class Car implements Vehicle {

    private String name;

    Car(String name) {
        this.name = name;
    }

    // Must implement abstract method
    @Override
    public void start() {
        System.out.println(name + " is starting.");
    }

    // Optional: Override default method
    @Override
    public void honk() {
        System.out.println(name + " honks: Honk Honk!");
    }
}

public class InterfaceDefaultStaticDemo {

    public static void main(String[] args) {

        // Create a car object
        Car car = new Car("Toyota");

        // Call abstract method
        car.start(); // Output: Toyota is starting.

        // Call default method (overridden version)
        car.honk(); // Output: Toyota honks: Honk Honk!

        // Call static method (on interface, not instance)
        Vehicle.service(); // Output: Vehicle is being serviced!

        // Optional: Use interface reference
        Vehicle myVehicle = new Car("Honda");
        myVehicle.start(); // Output: Honda is starting.
        myVehicle.honk();  // Output: Honda honks: Honk Honk!
    }
}
