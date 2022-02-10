package ua.advanced.lesson01.task1.house;

import ua.advanced.lesson01.task1.residents.dogs.Puppy;
import ua.advanced.lesson01.task1.residents.cats.Cat;
import ua.advanced.lesson01.task1.residents.cats.Kitten;
import ua.advanced.lesson01.task1.residents.dogs.Dog;

public class Main {

    public static void main(String[] args) {
        Dog rex = new Dog("Rax");
        Puppy randy = new Puppy("Randy");
        Cat barbos = new Cat("Barbos");
        Kitten murzik = new Kitten("Murzik");

        House<Dog> dogHouse = new House<>();
        dogHouse.enter(rex);
        dogHouse.enter(randy);
        //dogHouse.enter(barbos); //This must fail on compilation stage if you parameterize the dogHouse. Delete the line when solution is ready
        System.out.println(dogHouse);

        House<Cat> catHouse = new House<>();
        catHouse.enter(barbos);
        catHouse.enter(murzik);
        //catHouse.enter(rex); //This must fail on compilation stage if you parameterize the catHouse. Delete the line when solution is ready
        System.out.println(catHouse);
    }
}
