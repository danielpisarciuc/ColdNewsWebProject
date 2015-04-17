package eu.unicredit.fiipractic.integration;

import java.sql.SQLException;
import java.util.List;

import eu.unicredit.fiipractic.model.News;

/**
 * 
 * Add methods that represent the basic CRUD operations that all DAOs in my
 * application will have
 */
public interface Dao<T, ID> {

	/**
	 * Saves object into database.
	 * 
	 * @param object
	 */
	public void save(T object);

	/**
	 * Retrieves all news data from the database.
	 * 
	 * @return list of news
	 * @throws SQLException
	 */
	public List<News> loadAll() throws SQLException;

	/**
	 * Retrieve news from database identified by id.
	 * 
	 * @param id
	 * @return retrieved news object by id
	 */
	public T load(ID id);

	/**
	 * Update database row with the received object. The id objected must be set
	 * in order to update properly.
	 * 
	 * @param the
	 *            new object that we want to update
	 */
	public void update(T object);

	/**
	 * Delete database row based on given id.
	 * 
	 * @param id
	 */
	public void delete(ID id);

}
