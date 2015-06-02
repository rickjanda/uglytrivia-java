package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class QuestionPool {
	public LinkedList<String> popQuestions;
	public LinkedList<String> scienceQuestions;
	public LinkedList<String> sportsQuestions;
	public LinkedList<String> rockQuestions;

	public QuestionPool() {
		this.popQuestions = new LinkedList<>();
		this.scienceQuestions = new LinkedList<>();
		this.sportsQuestions = new LinkedList<>();
		this.rockQuestions = new LinkedList<>();
		
		for (int i = 0; i < 50; i++) {
			// duplicate
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast("Rock Question " + i);
		}
	}

	String getQuestion(QuestionCategory currentCategory) {
		String removeFirst = null;
		if (currentCategory == QuestionCategory.POP) {
			 removeFirst = popQuestions.removeFirst();
		}
		if (currentCategory == QuestionCategory.SCIENCE) {
			 removeFirst = scienceQuestions.removeFirst();
		}
		if (currentCategory == QuestionCategory.SPORTS) {
			 removeFirst = sportsQuestions.removeFirst();
		}
		if (currentCategory == QuestionCategory.ROCK) {
			 removeFirst = rockQuestions.removeFirst();
		}
		return removeFirst;
	}
}