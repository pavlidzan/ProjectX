package com.projects.pavlovic.vladimir.projectx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by vladimir.pavlovic on 4/25/2017.
 */

public class RandomNumbers implements Serializable{

    List<Integer> numbers;

    public RandomNumbers() {
        numbers = new ArrayList<>(30);
        Random r = new Random();
        for (int i = 0; i < 30; i++) {
            int number = r.nextInt(100);
            numbers.add(number);
        }
    }

    public String showNumbers() {
        StringBuilder sb = new StringBuilder();
        String helper = "";
        for (int number : numbers) {
            sb.append(helper);
            helper = ", ";
            sb.append(number);
        }
        return sb.toString();
    }

    public void sortAscending() {
        Collections.sort(numbers);
    }

    public void sortDescending() {
        Collections.sort(numbers, Collections.<Integer>reverseOrder());
    }

}
