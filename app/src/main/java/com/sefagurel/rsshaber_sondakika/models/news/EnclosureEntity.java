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

@DatabaseTable(tableName = "EnclosureEntity")
public class EnclosureEntity {
	@DatabaseField(generatedId = true) public int	columnId;
	@Expose @DatabaseField public String				href;
	@Expose @DatabaseField public String				type;
	@Expose @DatabaseField public int				length;

	private DatabaseHelper					databaseHelper	= null;
	private Dao<EnclosureEntity, Integer>	myDao;

	public EnclosureEntity() {
		try {
			databaseHelper = DatabaseHelper.getDbHelper();
			myDao = databaseHelper.getEnclosureEntityDataHelper();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Insert() {
		try {
			EnclosureEntity existenceCheck = myDao.queryForId(this.columnId);

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