package eu.unicredit.fiipractic.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eu.unicredit.fiipractic.model.News;

public class NewsDAOImpl implements NewsDAO {

	private final Connection connection;

	private final PreparedStatement loadStatement;
	private final PreparedStatement insertStatement;
	private final PreparedStatement deleteStatement;
	private final PreparedStatement updateStatement;

	public NewsDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;

		loadStatement = connection
				.prepareStatement("SELECT * FROM `fii_practic`.`news` WHERE id = ?");

		deleteStatement = connection
				.prepareStatement("DELETE FROM `fii_practic`.`news` WHERE id = ?");

		insertStatement = connection
				.prepareStatement("INSERT INTO `fii_practic`.`news` ( `title`, `shortText`, `longText`, `date`, `author`) VALUES ( ?, ?, ?, ?, ?);");

		updateStatement = connection
				.prepareStatement("UPDATE `fii_practic`.`news` SET  `title` = ?, `shortText` = ?, `longText` = ?, `date` = ?, `author` = ? WHERE `id` = ? ");

	}

	@Override
	public List<News> loadAll() {
		List<News> newsResult = new ArrayList<News>();
		Statement createStatement;
		ResultSet result;

		try {
			createStatement = connection.createStatement();
			result = createStatement
					.executeQuery("SELECT * FROM `fii_practic`.`news`");

			mapNews(newsResult, result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newsResult;
	}

	@Override
	public List<News> loadAllSortedByDateDesc() {
		List<News> newsResult = new ArrayList<News>();
		Statement createStatement;
		ResultSet result;

		try {
			createStatement = connection.createStatement();
			result = createStatement
					.executeQuery("SELECT * FROM `fii_practic`.`news` order by date desc");

			mapNews(newsResult, result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newsResult;
	}

	@Override
	public News load(Long id) {

		try {
			loadStatement.setLong(1, id);

			ResultSet resultSet = loadStatement.executeQuery();

			if (resultSet.next()) {
				News receivedObject = mapNewsObject(resultSet);
				return receivedObject;

			} else {
				// We don't have the record
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error while retrieving News with ID: "
					+ id, e);
		}

	}

	@Override
	public void save(News news) {
		try {
			insertStatement.setString(1, news.getTitle());
			insertStatement.setString(2, news.getShortText());
			insertStatement.setString(3, news.getLongText());
			insertStatement.setDate(4, new java.sql.Date(news.getDate()
					.getTime()));
			insertStatement.setString(5, news.getAuthor());

			insertStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(News news) {
		try {
			updateStatement.setLong(6, news.getId());
			updateStatement.setString(1, news.getTitle());
			updateStatement.setString(2, news.getShortText());
			updateStatement.setString(3, news.getLongText());
			updateStatement.setDate(4, new java.sql.Date(news.getDate()
					.getTime()));
			updateStatement.setString(5, news.getAuthor());

			updateStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		try {
			deleteStatement.setLong(1, id);
			deleteStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void mapNews(List<News> newsResult, ResultSet result)
			throws SQLException {
		while (result.next()) {
			News newObject = mapNewsObject(result);
			newsResult.add(newObject);
		}
	}

	private News mapNewsObject(ResultSet result) throws SQLException {
		News newObject = new News();
		newObject.setId(result.getInt(1));
		newObject.setTitle(result.getString(2));
		newObject.setShortText(result.getString(3));
		newObject.setLongText(result.getString(4));
		newObject.setDate(result.getDate(5));
		newObject.setAuthor(result.getString(6));
		return newObject;
	}

}
