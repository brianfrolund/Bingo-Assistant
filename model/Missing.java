package model;

import java.util.ArrayList;

public class Missing {
	private ArrayList<Integer> missingNumbers = new ArrayList<Integer>();
	private Board board;
	
	public Missing(ArrayList<Integer> missingNumbers, Board board) {
		this.missingNumbers = missingNumbers;
		this.board = board;
	}

	public void setMissingNumbers(ArrayList<Integer> missingNumbers) {
		this.missingNumbers = missingNumbers;
	}

	public ArrayList<Integer> getMissingNumbers() {
		return missingNumbers;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}
	
	public String toString() {
		return missingNumbers.toString();
	}
}
