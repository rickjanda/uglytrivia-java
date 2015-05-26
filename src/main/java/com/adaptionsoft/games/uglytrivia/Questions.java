package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Questions {

    private Map<Category, LinkedList<String>> questions = new HashMap<>();

    public Questions() {

        for (Category category : Category.values()) {
            questions.put(category, new LinkedList<String>());

            for (int i = 0; i < 50; i++) {
                questions.get(category).add(category + " Question " + i);
            }
        }
    }

    public String pullNext(Category category) {
        return questions.get(category).removeFirst();
    }

    public enum Category {
        Pop,Science,Sports,Rock;

        static Category byPlace(int place) {
            //duplicate lines
            if ((place % 4) == 0) return Pop;
            if ((place % 4) == 1) return Science;
            if ((place % 4) == 2) return Sports;
            return Rock;
        }
    }
}
