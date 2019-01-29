package A.FunctionalInterfaceExample;

import java.util.function.Predicate;

public class FindMatchingAnimals {
    public static void print(Animal animal, Predicate<Animal> animalPredicate) {
        if(animalPredicate.test(animal)) {
            System.out.println("Animal: " + animal);
        }
    }
    public static void main(String[] args) {
        print(new Animal("fish", false, true), a -> a.canSwim());
        print(new Animal("kangaroo", true, false), a -> a.canHop());
        print(new Animal("test", true, false), (String) -> { return  true; });
    }
}
