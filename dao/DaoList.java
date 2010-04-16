package dao;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Missing;

public class DaoList implements Dao {
	private List<Board> boards;
	private List<Missing> missings;
	
	private static DaoList dao = null;
	
	private DaoList() {
		boards = new ArrayList<Board>();
		missings = new ArrayList<Missing>();	
	}
	
	public static Dao getDao() {
		if (dao == null)
			dao = new DaoList();
		return dao;
	}
	
	public List<Board> getAllBoards() {
		return boards;
	}
	
	public void store(Board board) {
		if (!boards.contains(board))
			boards.add(board);
	}
	
	public void delete(Board board) {
		boards.remove(board);
	}
	
	public List<Missing> getAllMissings() {
		return missings;
	}
	
	public void store(Missing missing) {
		if(!missings.contains(missing))
			missings.add(missing);
	}
	
	public void remove(Missing missing) {
		missings.remove(missing);
	}
	
	public void close() {
		// do nothing here
	}
}
