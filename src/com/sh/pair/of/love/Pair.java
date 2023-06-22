package com.sh.pair.of.love;

public class Pair {
    private Person person1;
    private Person person2;

    public Pair(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;

        // matched 처리 책임
        person1.setMatched(true);
        person2.setMatched(true);
    }

    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "person1=" + person1 +
                ", person2=" + person2 +
                '}';
    }
}
