package dao;

import java.util.List;

import model.Board;
import model.Missing;

public interface Dao {
	public  List<Board> getAllBoards();
	public void store(Board board);
	public  void delete(Board board);
	public List<Missing> getAllMissings();
	public void store(Missing missing);
	public void remove(Missing missing);
	public void close();
}