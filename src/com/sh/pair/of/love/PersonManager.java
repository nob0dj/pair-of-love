package com.sh.pair.of.love;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PersonManager {
    Map<String, Person> tempMap = new HashMap<>();
    List<Person> persons;
    List<Pair> pairs = new ArrayList<>();

    public PersonManager(String fileName) {
        StringBuilder src = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String data = null;
            while ((data = br.readLine()) != null)
                src.append(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[[데이터]] \n" + src);

        String[] temp = src.toString().split("\n");
        for (int i = 0; i < temp.length; i++) {
            String[] names = temp[i].split("_");
            Person person = new Person(names[0]);
            tempMap.put(names[0], person);
        }
        setPriorities(src);
    }

    public void setPriorities(StringBuilder src) {
        String[] temp = src.toString().split("\n");
        for (int i = 0; i < temp.length; i++) {
            String[] names = temp[i].split("_");
            Person person = tempMap.get(names[0]);
            for (int j = 1; j < names.length; j++) {
                String name = names[j]; // 우선순위 이름
                person.addPriority(tempMap.get(name));
            }
        }
        this.persons = new ArrayList<>(tempMap.values());
    }

    public void print() {
        System.out.println();
        System.out.println("[매칭결과]");
        System.out.println("[[매칭성공]]");
        pairs.forEach(System.out::println);
        System.out.println("[[매칭실패]]");
        persons.forEach(System.out::println);
    }

    public void proceed() {
        System.out.println("[1순위 매칭을 시작합니다]");
        matchTopPriority();
        System.out.println("[1순위 매칭을 끝났습니다]");
        System.out.println("[잔여인원의 매칭을 계산합니다.]");
        calcMatch();
        System.out.println("[잔여인원의 매칭이 끝났습니다.]");
    }

    private void calcMatch() {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            Person opposite = person.calcMatch();
            if(opposite != null){
                pairs.add(new Pair(person, opposite));
            }
        }

        for (int i = 0; i < pairs.size(); i++) {
            Pair pair = pairs.get(i);
            persons.remove(pair.getPerson1());
            persons.remove(pair.getPerson2());
        }
    }

    /**
     * 0순위 매칭
     */
    private void matchTopPriority() {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            Person opposite = person.findOpposite();
            if(opposite != null){
                pairs.add(new Pair(person, opposite));
            }
        }
    
        // 매칭된 인원 persons에서 제거 - ConcurrentModificationException을 회피하기 위해 조회후 제거함.
        for (int i = 0; i < pairs.size(); i++) {
            Pair pair = pairs.get(i);
            persons.remove(pair.getPerson1());
            persons.remove(pair.getPerson2());
        }
    }

    public void shuffle() {
        Collections.shuffle(persons);
    }
}
