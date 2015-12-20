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

@DatabaseTable(tableName = "OriginEntity")
public class OriginEntity {

    @DatabaseField(id = true) public String	parentId;
    @Expose @DatabaseField public String	streamId;
	@Expose @DatabaseField public String	title;
	@Expose @DatabaseField public String	htmlUrl;

	private DatabaseHelper				databaseHelper	= null;
	private Dao<OriginEntity, String>	myDao;

	public OriginEntity() {
		try {
			databaseHelper = DatabaseHelper.getDbHelper();
			myDao = databaseHelper.getOriginEntityDataHelper();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Insert() {
		try {
			OriginEntity existenceCheck = myDao.queryForId(this.streamId);

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
