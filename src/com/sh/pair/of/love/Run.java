package com.sh.pair.of.love;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Run {

    /*


     */
    public static void main(String[] args) {
        PersonManager pm = new PersonManager("kh352_230220.txt");

        // 순서가 바뀌어도 잘 작동하는지 테스트
        pm.shuffle();

        // 매칭
        pm.proceed();

        // 결과확인
        pm.print();

    }

}
