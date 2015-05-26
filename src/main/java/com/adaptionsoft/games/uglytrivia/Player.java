package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.Questions.Category;

public class Player {

    public Player(String name) {
        this.name = name;
    }

    private final String name;

    private int place = 0;
    private int purse = 0;
    private boolean inPenaltyBox = false;
    private boolean isGettingOutOfPenaltyBox;


    public String getName() {
        return name;
    }

    void moveForwardBy(int roll) {
        place = (place + roll) % 12;
    }

    void increasePurse() {
        purse++;
    }

    void putInPenaltyBox() {
        inPenaltyBox = true;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public boolean isWinner() {
        return purse == 6;
    }

    public Category getCurrentQuestionCategory() {
        return Category.byPlace(place);
    }

    public boolean isStuckInPenaltyBox() {
        return inPenaltyBox && !isGettingOutOfPenaltyBox;
    }

    public void setIsGettingOutOfPenaltyBoxByRoll(int roll) {
        if (roll % 2 == 0) {
            isGettingOutOfPenaltyBox = false;
        } else {
            isGettingOutOfPenaltyBox = true;
        }
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }
}
