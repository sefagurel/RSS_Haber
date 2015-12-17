package com.sefagurel.rsshaber_sondakika.models.news;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sefagurel.rsshaber_sondakika.database.DatabaseHelper;
import com.sefagurel.rsshaber_sondakika.database.MissionsBuildings;

/**
 * Created by Sefa on 12.12.2015.
 */

@DatabaseTable(tableName = "NewsModel")
public class NewsModel {

	@DatabaseField(generatedId = true) public int	columnId;
	@Expose @DatabaseField public String			id;
	@Expose @DatabaseField public String			direction;
	@Expose @DatabaseField public long				updated;
	@Expose @DatabaseField public String			title;
	@Expose @DatabaseField public String			continuation;
	@Expose public ArrayList<AlternateEntity>		alternate;
	@Expose public ArrayList<ItemsEntity>			items;

	private DatabaseHelper			databaseHelper	= null;
	private Dao<NewsModel, Integer>	myDao;

	public NewsModel() {
		try {
			databaseHelper = DatabaseHelper.getDbHelper();
			myDao = databaseHelper.getNewsModelDataHelper();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void Insert() {
		try {
			NewsModel existenceCheck = myDao.queryForId(this.columnId);

			if (existenceCheck != null) {
				myDao.update(this);
			}
			else {
				myDao.create(this);
			}

			for (AlternateEntity alternateEntity : alternate) {
				// alternateEntity.columnId = columnId;
				alternateEntity.Insert();
			}
			for (ItemsEntity itemsEntity : items) {
				// itemsEntity.columnId = columnId;
				itemsEntity.Insert();
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

		for (AlternateEntity alternateEntity : alternate) {
			alternateEntity.Destroy();
		}

		for (ItemsEntity itemsEntity : items) {
			itemsEntity.Destroy();
		}

	}

}
