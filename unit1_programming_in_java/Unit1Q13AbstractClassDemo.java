public class Unit1Q13AbstractClassDemo {
    public static void main(String[] args) {
        Vehicle bike = new Bike();
        Vehicle car = new Car();

        bike.start();
        car.start();
        System.out.println("done by Krish Devkota");
    }

    abstract static class Vehicle {
        abstract void start();
    }

    static class Bike extends Vehicle {
        @Override
        void start() {
            System.out.println("Bike starts with kick");
        }
    }

    static class Car extends Vehicle {
        @Override
        void start() {
            System.out.println("Car starts with key");
        }
    }
}

