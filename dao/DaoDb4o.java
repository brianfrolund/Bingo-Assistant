package dao;


import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;


import model.Board;
import model.Missing;

/**
 * @author mlch
 */
public class DaoDb4o implements Dao {
	
	private ObjectContainer db;
	private static DaoDb4o dao = null;

	private DaoDb4o(){
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		configuration.common().activationDepth(6);
		configuration.common().updateDepth(6);
	
		db = Db4oEmbedded.openFile(configuration,"db.db4o");
		
	}
	
	public static Dao getDao(){
		if (dao == null)
			dao = new DaoDb4o();
		return dao;
	}

	/**
	 * Returns a list of all stored companies.
	 */
	public  List<Board> getAllBoards() {
		return db.query(Board.class);
	}

	/**
	 * Stores the Board.<br />
	 * Requires: The Board is not stored.
	 */
	public void store(Board Board) {
		db.store(Board);
		db.commit();
	}

	/**
	 * Deletes the stored Board.<br />
	 * Requires: The Board is stored.
	 */
	public  void delete(Board Board) {
		db.delete(Board);
		db.commit();
	}

	/**
	 * Returns a list of all employees in the store.
	 */
	public  List<Missing> getAllMissings() {
		return db.query(Missing.class);
	}

	/**
	 * Stores the Missing.<br />
	 * Requires: The Missing is not stored.
	 */
	public void store(Missing Missing) {
		db.store(Missing);
		db.commit();
	}

	/**
	 * Removes the stored Missing.<br />
	 * Requires: The Missing is stored.
	 */
	public void remove(Missing Missing) {
		db.delete(Missing);
		db.commit();
	}
	public void close(){
		db.close();
	}

}
