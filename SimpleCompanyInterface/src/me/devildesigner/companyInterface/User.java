/*
 * Today is 4/26/2020
 * Session by: *DevilDesigner (Dev.)
 * Create Time: 4:53 AM
 * This Class: Controller
 */

package me.devildesigner.companyInterface;

public class User {

    public User(String name, int age, int salary, boolean married) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.married = married;
    }


    public void changeSalary(int x) {
        salary = (salary + x <= 0)? 0: salary + x;
    }

    public String toString() {
        return name;
    }

    public String getAge() {
        return String.valueOf(age);
    }

    public String getSalary() {
        return String.valueOf(salary);
    }
    public String getMarried() {
        return (married)? ("Married"):("Single");
    }

    String name;
    int age;
    int salary;
    boolean married;
}