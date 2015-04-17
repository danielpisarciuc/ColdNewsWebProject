package eu.unicredit.fiipractic.integration;

import java.util.List;

import eu.unicredit.fiipractic.model.News;

public interface NewsDAO extends Dao<News, Long> {

	/**
	 * Retrieves all news data from the database ordered by date descending.
	 * 
	 * @return list of ordered news
	 */
	public List<News> loadAllSortedByDateDesc();
}
