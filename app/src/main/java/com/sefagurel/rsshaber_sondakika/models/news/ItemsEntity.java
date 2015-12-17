package com.sefagurel.rsshaber_sondakika.models.news;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sefagurel.rsshaber_sondakika.database.DatabaseHelper;

/**
 * Created by Sefa on 12.12.2015.
 */

@DatabaseTable(tableName = "ItemsEntity")
public class ItemsEntity {

	@DatabaseField(generatedId = true) public int			columnId;
	@Expose @DatabaseField public String						id;
	@Expose @DatabaseField public String						originId;
	@Expose @DatabaseField public String						fingerprint;
	@Expose @DatabaseField public String						title;
	@Expose @DatabaseField public long							published;
	@Expose @DatabaseField public long							crawled;
	@Expose @DatabaseField(foreign = true) public OriginEntity	origin;
	@Expose @DatabaseField(foreign = true) public SummaryEntity	summary;
	@Expose @DatabaseField(foreign = true) public VisualEntity	visual;
	@Expose @DatabaseField public boolean						unread;
	@Expose public ArrayList<AlternateEntity2>					alternate;
	@Expose public ArrayList<EnclosureEntity>					enclosure;

	private DatabaseHelper				databaseHelper	= null;
	private Dao<ItemsEntity, Integer>	myDao;

	public ItemsEntity() {

	}

	public void Insert() {
		try {
			databaseHelper = DatabaseHelper.getDbHelper();
			myDao = databaseHelper.getItemsEntityDataHelper();
			ItemsEntity existenceCheck = myDao.queryForId(this.columnId);

			if (existenceCheck != null) {
				myDao.update(this);
			}
			else {
				myDao.create(this);
			}

			for (AlternateEntity2 alternateEntity2 : alternate) {
//				alternateEntity2.columnId = columnId;
				alternateEntity2.Insert();
			}

			for (EnclosureEntity enclosureEntity : enclosure) {
//				enclosureEntity.columnId = columnId;
				enclosureEntity.Insert();
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

		for (AlternateEntity2 alternateEntity2 : alternate) {
			alternateEntity2.Destroy();
		}

		for (EnclosureEntity enclosureEntity : enclosure) {
			enclosureEntity.Destroy();
		}
	}

}