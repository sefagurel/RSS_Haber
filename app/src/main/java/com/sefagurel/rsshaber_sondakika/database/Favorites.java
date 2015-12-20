package com.sefagurel.rsshaber_sondakika.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.table.DatabaseTable;
import com.sefagurel.rsshaber_sondakika.tools.Tools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sefa on 20.12.2015.
 */

@DatabaseTable(tableName = "Favorites")
public class Favorites {

	@DatabaseField(id = true) public String	id;
	@DatabaseField public String			folderName;

	private DatabaseHelper			databaseHelper	= null;
	private Dao<Favorites, String>	myDao;

	public Favorites() {
		try {
			databaseHelper = DatabaseHelper.getDbHelper();
			myDao = databaseHelper.getFavoritesDataHelper();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Insert() {
		try {
			Favorites existenceCheck = myDao.queryForId(this.id);

			if (existenceCheck != null) {
				myDao.update(this);
			}
			else {
				myDao.create(this);
			}

		}
		catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}

	public void DeleteRow(String feedId) {
		try {

			boolean isExist = myDao.idExists(feedId);

			if (isExist) {
				DeleteBuilder<Favorites, String> deleteBuilder = myDao.deleteBuilder();
				deleteBuilder.where().eq("id", feedId);
				deleteBuilder.delete();
			}

		}
		catch (Exception e) {
			Tools.saveErrors(e);
		}
	}

	public List<Favorites> getAllRows() {

		List<Favorites> dmfb = null;

		try {
			dmfb = myDao.queryForAll();
		}
		catch (Exception e) {
			Tools.saveErrors(e);
		}

		return dmfb;
	}

	public void Destroy() {
		if (databaseHelper != null) {
			databaseHelper.close();
			databaseHelper = null;
		}
	}
}