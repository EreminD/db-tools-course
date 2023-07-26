package ru.inno;

import java.util.Iterator;
import java.util.Scanner;

public class MyIterator implements Iterator<String> {
    private final String[] str;
    private int index;

    public MyIterator() {
        String s = new Scanner(System.in).nextLine();
        str = s.split("");
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index == str.length) {
            return false;
        }

        if (str[index].isBlank()) {
            return false;
        }

        return true;
    }

    @Override
    public String next() {

        for (String s : str) {

        }

        return str[index++];
    }
}
