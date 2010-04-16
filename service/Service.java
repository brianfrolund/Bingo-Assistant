package service;

import gui.MainFrame;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.Dao;
import dao.DaoDb4o;
import dao.DaoList;

import model.Board;
import model.Missing;

public class Service {
	private static Service service = null;
	private Dao dao = null;
	private Service(){
		dao = DaoList.getDao();
	}
	public static Service getService(){
		if (service == null) {
			service = new Service();
		}
		return service;
	}
	
	private ArrayList<Integer> numbers = new ArrayList<Integer>();

	public List<Board> getAllBoards() {
		return dao.getAllBoards();
	}

	public Board createBoard(String controleNumber, ArrayList<ArrayList<Integer>> numbers) {
		Board board = new Board(controleNumber, numbers);
		dao.store(board);
		return board;
	}

	public void deleteBoard(Board board) {
		dao.delete(board);
	}

	public List<Missing> getAllMissings() {
		return dao.getAllMissings();
	}

	public Missing createMissing(ArrayList<Integer> missingNumbers, Board board) {
		Missing missing = new Missing(missingNumbers, board);
		dao.store(missing);
		return missing;
	}

	public void deleteMissing(Missing missing) {
		dao.remove(missing);
	}

	public void addNumber(int number) {
		numbers.add(number);
	}

	public ArrayList<Integer> getNumbers() {
		return numbers;
	}
	
	public void deleteNumber(int number) {
		numbers.remove(numbers.indexOf(number));
		System.out.println("såå deet");
		for(Board board : getAllBoards()) {
			for(ArrayList<Integer> row : board.getNumbers()) {
				if(row.contains(number)) {
					board.getRemainingNumbers().get(board.getNumbers().indexOf(row)).add(number);
				}
			}
		}
	}
	
	public Board checkFor(int checkFor) {
		for(Board board : getAllBoards()) {
			ArrayList<ArrayList<Integer>> tempNumbers = new ArrayList<ArrayList<Integer>>();
			for(ArrayList<Integer> row : board.getRemainingNumbers()) {
				ArrayList<Integer> tempRow = new ArrayList<Integer>();
				for(int number : row) {
					if(!numbers.contains(number)) {
						tempRow.add(number);						
					}
				}
				tempNumbers.add(tempRow);
			}
			board.setRemainingNumbers(tempNumbers);

			if(checkFor==0) {
				for(ArrayList<Integer> row : board.getRemainingNumbers()) {
					if(row.size()==0) {
						return board;
					}
				}
			}
			else if(checkFor==1) {
				if(board.getRemainingNumbers().get(0).size()==0 && board.getRemainingNumbers().get(1).size()==0) {
					return board;
				}
				else if(board.getRemainingNumbers().get(0).size()==0 && board.getRemainingNumbers().get(2).size()==0) {
					return board;
				}
				else if(board.getRemainingNumbers().get(1).size()==0 && board.getRemainingNumbers().get(2).size()==0) {
					return board;
				}
			}
			else if(checkFor==2) {
				if(board.getRemainingNumbers().get(0).size()==0 && board.getRemainingNumbers().get(1).size()==0 && board.getRemainingNumbers().get(2).size()==0) {
					return board;
				}
			}
		}
		return null;
	}

	public ArrayList<Missing> oneToGo(int checkFor) {
		ArrayList<Missing> missings = new ArrayList<Missing>();
		int wantedSize=1;
		while(missings.size()==0 && wantedSize<5) {
			System.out.println(wantedSize);
			for(Board board : getAllBoards()) {
				
				if(checkFor==0) {
					for(ArrayList<Integer> row : board.getRemainingNumbers()) {
						if(row.size()==wantedSize) {
							missings.add(new Missing(row,board));
						}
					}
				}
				
				else if(checkFor==1) {
					if(board.getRemainingNumbers().get(0).size()+board.getRemainingNumbers().get(1).size()==wantedSize) {
						ArrayList<Integer>  tempNumbers = new ArrayList<Integer>();
						tempNumbers.addAll(board.getRemainingNumbers().get(0));
						tempNumbers.addAll(board.getRemainingNumbers().get(1));
						missings.add(new Missing(tempNumbers,board));
					}
					else if(board.getRemainingNumbers().get(0).size()+board.getRemainingNumbers().get(2).size()==wantedSize) {
						ArrayList<Integer>  tempNumbers = new ArrayList<Integer>();
						tempNumbers.addAll(board.getRemainingNumbers().get(0));
						tempNumbers.addAll(board.getRemainingNumbers().get(2));
						missings.add(new Missing(tempNumbers,board));
					}
					else if(board.getRemainingNumbers().get(1).size()+board.getRemainingNumbers().get(2).size()==wantedSize) {
						ArrayList<Integer>  tempNumbers = new ArrayList<Integer>();
						tempNumbers.addAll(board.getRemainingNumbers().get(1));
						tempNumbers.addAll(board.getRemainingNumbers().get(2));
						missings.add(new Missing(tempNumbers,board));
					}
				}
				else if(checkFor==2) {
					if(board.getRemainingNumbers().get(0).size()+board.getRemainingNumbers().get(1).size()+board.getRemainingNumbers().get(2).size()==wantedSize) {
						ArrayList<Integer>  tempNumbers = new ArrayList<Integer>();
						for(ArrayList<Integer> row : board.getRemainingNumbers()) {
								tempNumbers.addAll(row);
						}
						missings.add(new Missing(tempNumbers,board));
//						if(board.getRemainingNumbers().get(0).size()>0)
//							missings.add(new Missing(board.getRemainingNumbers().get(0),board));
//						else if(board.getRemainingNumbers().get(1).size()>0)
//							missings.add(new Missing(board.getRemainingNumbers().get(1),board));
//						else
//							missings.add(new Missing(board.getRemainingNumbers().get(2),board));
					}
				}
			}
			wantedSize++;
		}
		return missings;
	}

	public void importCSV(ArrayList<String> buff) {
		ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		String controleNumber = "";
		int line;

		for(int i=0;i<buff.size();i++) {
			line = i % 3;

			String[] splitBuf = buff.get(i).split(";");
			int start = 0;

			if(line==0) { // new board
				board  = new ArrayList<ArrayList<Integer>>();
				controleNumber = splitBuf[0];
				start = 1;
			}

			row  = new ArrayList<Integer>();
			// fill row from the buffer fields...
			for(int b = start, last = start+9; b < last; b++) {
				if(b >= splitBuf.length || splitBuf[b].length() == 0)
					row.add(0);
				else
					row.add(Integer.valueOf(splitBuf[b]));
			}
			board.add(row);

			if(line==2) {
				createBoard(controleNumber, board);
			}
		}		
	}



	public ArrayList<String> readFile(File file) {
		FileInputStream fileInputStream = null;
		ArrayList<String> buff = new ArrayList<String>();
		String line;

		try {
			fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
			while ((line = bufferedReader.readLine())!= null) {
				buff.add(line);
			}
		} 
		catch (IOException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		} 
		finally {
			try {
				fileInputStream.close();
			} 
			catch (IOException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return buff;
	}
}
