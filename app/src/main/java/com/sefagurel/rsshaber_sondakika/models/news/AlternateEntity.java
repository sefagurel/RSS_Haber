package com.sefagurel.rsshaber_sondakika.models.news;

import java.sql.SQLException;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sefagurel.rsshaber_sondakika.database.DatabaseHelper;

/**
 * Created by Sefa on 12.12.2015.
 */

@DatabaseTable(tableName = "AlternateEntity")
public class AlternateEntity {

	@Expose @DatabaseField(id = true) public String	href;
	@DatabaseField public String					parentId;
	@Expose @DatabaseField public String			type;

	private DatabaseHelper					databaseHelper	= null;
	private Dao<AlternateEntity, String>	myDao;

	public AlternateEntity() {
		try {
			databaseHelper = DatabaseHelper.getDbHelper();
			myDao = databaseHelper.getAlternateEntityDataHelper();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Insert() {
		try {
			AlternateEntity existenceCheck = myDao.queryForId(this.href);

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
