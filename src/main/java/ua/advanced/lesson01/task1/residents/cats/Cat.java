package ua.advanced.lesson01.task1.residents.cats;

public class Cat {

    protected String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat " + name;
    }
}
