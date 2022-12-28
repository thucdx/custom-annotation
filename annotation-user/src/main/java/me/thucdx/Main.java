package me.thucdx;

public class Main {
    public static void main(String[] args) {
        Person person = (new PersonBuilder().setAge(25)
                .setName("John")
                .setHandsomeness(9999)
                .build());
        System.out.println(person.toString());
    }
}
