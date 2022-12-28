package me.thucdx;

public class Main {
    public static void main(String[] args) {
        Person person = (new PersonBuilder().setAge(25).setName("John").build());
        System.out.println(person.toString());
    }
}
