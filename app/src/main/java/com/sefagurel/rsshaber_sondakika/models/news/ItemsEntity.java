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

	@Expose @DatabaseField(id = true) public String	id;
	@DatabaseField public String					parentId;
	@Expose @DatabaseField public String			originId;
	@Expose @DatabaseField public String			fingerprint;
	@Expose @DatabaseField public String			title;
	@Expose @DatabaseField public long				published;
	@Expose @DatabaseField public long				crawled;
	@Expose public OriginEntity						origin;
	@Expose public SummaryEntity					summary;
	@Expose public VisualEntity						visual;
	@Expose @DatabaseField public boolean			unread;
	@Expose public ArrayList<AlternateEntity>		alternate;
	@Expose public ArrayList<EnclosureEntity>		enclosure;

	// public String recrawled;
	// public String author;
	// public String engagement;
	// public String engagementRate;
	// public List<String> keywords;
	// public List<ThumbnailEntity> thumbnail;
	// public List<CanonicalEntity> canonical;

	private DatabaseHelper				databaseHelper	= null;
	private Dao<ItemsEntity, String>	myDao;

	public ItemsEntity() {

		try {
			databaseHelper = DatabaseHelper.getDbHelper();
			myDao = databaseHelper.getItemsEntityDataHelper();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Insert() {
		try {

			ItemsEntity existenceCheck = myDao.queryForId(this.id);

			if (existenceCheck != null) {
				myDao.update(this);
			}
			else {
				myDao.create(this);
			}

			if (alternate != null && alternate.size() > 0) {
				for (AlternateEntity alternateEntity : alternate) {
					alternateEntity.parentId = id;
					alternateEntity.Insert();
				}
			}

			if (enclosure != null && enclosure.size() > 0) {
				for (EnclosureEntity enclosureEntity : enclosure) {
					enclosureEntity.parentId = id;
					enclosureEntity.Insert();
				}
			}

			if (origin != null) {
				origin.parentId = id;
				origin.Insert();
			}

			if (summary != null) {
				summary.parentId = id;
				summary.Insert();
			}

			if (visual != null) {
				visual.parentId = id;
				visual.Insert();
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

		for (EnclosureEntity enclosureEntity : enclosure) {
			enclosureEntity.Destroy();
		}
	}

}