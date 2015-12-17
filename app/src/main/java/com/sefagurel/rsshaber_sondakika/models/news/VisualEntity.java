package com.sefagurel.rsshaber_sondakika.models.news;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sefagurel.rsshaber_sondakika.database.DatabaseHelper;

import java.sql.SQLException;

/**
 * Created by Sefa on 12.12.2015.
 */

@DatabaseTable(tableName = "VisualEntity")
public class VisualEntity {

	@DatabaseField(generatedId = true) public int	columnId;
	@Expose @DatabaseField public String			url;
	@Expose @DatabaseField public long				expirationDate;
	@Expose @DatabaseField public int				width;
	@Expose @DatabaseField public int				height;
	@Expose @DatabaseField public String			edgeCacheUrl;
	@Expose @DatabaseField public String			processor;
	@Expose @DatabaseField public String			contentType;

	private DatabaseHelper				databaseHelper	= null;
	private Dao<VisualEntity, Integer>	myDao;

	public VisualEntity() {
		try {
			databaseHelper = DatabaseHelper.getDbHelper();
			myDao = databaseHelper.getVisualEntityDataHelper();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Insert() {
		try {
			VisualEntity existenceCheck = myDao.queryForId(this.columnId);

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

	public void Destroy() {
		if (databaseHelper != null) {
			databaseHelper.close();
			databaseHelper = null;
		}
	}
}
