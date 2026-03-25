public class Unit1Q08MethodOverridingDemo {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();

        animal.speak();
        dog.speak();
        System.out.println("done by Krish Devkota");
    }

    static class Animal {
        void speak() {
            System.out.println("Animal speaks");
        }
    }

    static class Dog extends Animal {
        @Override
        void speak() {
            System.out.println("Dog barks");
        }
    }
}

