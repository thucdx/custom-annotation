package me.thucdx;

public class Person {
    private int age;
    private String name;
    private String maritalStatus;

    public int getHandsomeness() {
        return handsomeness;
    }

    @BuilderProperty
    public void setHandsomeness(int handsomeness) {
        this.handsomeness = handsomeness;
    }

    private int handsomeness;

    public String getMaritalStatus() {
        return maritalStatus;
    }

    @BuilderProperty
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getAge() {
        return age;
    }

    @BuilderProperty
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @BuilderProperty
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                '}';
    }
}
