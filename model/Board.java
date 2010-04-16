package model;

import java.util.ArrayList;

public class Board {
	private String controleNumber;
	private ArrayList<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> remainingNumbers = new ArrayList<ArrayList<Integer>>();	
	
	public Board(String controlenumber, ArrayList<ArrayList<Integer>> numbers) {
		this.controleNumber = controlenumber;
		this.numbers = numbers;
		this.remainingNumbers = numbers;
	}

	public String getControlenumber() {
		return controleNumber;
	}

	public void setControlenumber(String controleNumber) {
		this.controleNumber = controleNumber;
	}

	public ArrayList<ArrayList<Integer>> getNumbers() {
		return numbers;
	}

	public void setNumbers(ArrayList<ArrayList<Integer>> numbers) {
		this.numbers = numbers;
	}

	public void setRemainingNumbers(ArrayList<ArrayList<Integer>> remainingNumbers) {
		this.remainingNumbers = remainingNumbers;
	}

	public ArrayList<ArrayList<Integer>> getRemainingNumbers() {
		return remainingNumbers;
	}
	
	public String toString() {
		return String.valueOf(controleNumber);
	}

}
