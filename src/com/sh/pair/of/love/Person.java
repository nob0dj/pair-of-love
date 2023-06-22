package com.sh.pair.of.love;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<Person> priorities = new ArrayList<>();
    private boolean matched;
    private boolean isChecking;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Person> getPriorities() {
        return priorities;
    }

    public boolean isMatched() {
        return matched;
    }

    public boolean isChecking() {
        return isChecking;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriorities(List<Person> priorities) {
        this.priorities = priorities;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public void setChecking(boolean checking) {
        isChecking = checking;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + '\'' +
                ", priorities=" + printPriorities() +
                ", matched=" + matched +
                ", isChecking=" + isChecking +
                '}';
    }

    private String printPriorities() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        priorities.forEach(person -> {
            s.append(person.getName() + ", ");
        });
        s.append("]");
        return s.toString();
    }


    public void addPriority(Person person) {
        priorities.add(person);
    }

    public Person findOpposite(){
        System.out.println("> " + name);
        // 커플로 이미 등록된 경우
        if(matched) {
            return null;
        }
        Person opposite = priorities.get(0);
        if(matchedAsTopPriority(opposite)){
            System.out.println(">> " + opposite.name);
            return opposite;
        }
        return null;
    }
    public boolean matchedAsTopPriority(Person other) {
        // 커플로 이미 등록된 경우
        if(other.matched)
            return false;

        if(other.priorities.get(0) == this){
            return true;
        }
        return false;
    }

    public Person calcMatch() {
        System.out.println("> " + name);
        if(matched) {
            return null;
        }
        for (int i = 0; i < priorities.size(); i++) {
            Person opposite = priorities.get(i);
            System.out.println(">> " + i + " " + opposite.name + " -> " + calcMatch(opposite));
            if(calcMatch(opposite)){
                return opposite;
            }
        }
        return null;
    }

    private boolean calcMatch(Person other) {
        if(other.matched)
            return false;
        for (int i = 0; i < other.priorities.size(); i++) {
            if(other.priorities.get(i) == this){
                return true;
            }
        }
        return false;
    }
}
