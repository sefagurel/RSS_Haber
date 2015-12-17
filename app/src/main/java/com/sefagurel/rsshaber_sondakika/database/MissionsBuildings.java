package com.sefagurel.rsshaber_sondakika.database;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.DatabaseTable;
import com.sefagurel.rsshaber_sondakika.tools.Tools;

/**
 * Created by Sefa on 17.12.2015.
 */
@DatabaseTable(tableName = "missionsbuildings")
public class MissionsBuildings {

	@DatabaseField(id = true) private int						UserDailyMissionId;
	@DatabaseField private int									StreetId;
	@DatabaseField private String								Name;
	@DatabaseField private String								BuildingNumber;
	@DatabaseField private int									UserDailyMissionTypeId;
	@DatabaseField private Boolean								BuildingNumber_IsOdd;
	@DatabaseField private String								AddressText;
	@DatabaseField private int									OrderIndex;
	@DatabaseField private int									StreetTypeId;
	@DatabaseField private int									IndependentSectionCount;
	@DatabaseField(format = "yyyy-MM-dd HH:mm:ss") private Date	ModifiedDate;
	@DatabaseField private Boolean								IsDeleted;
	@DatabaseField private Boolean								IsCompleted;
	@DatabaseField private String								PersonNameSurname;
	@DatabaseField(format = "yyyy-MM-dd HH:mm:ss") private Date	LastOperationDate;
	@DatabaseField private int									UserId;
	@DatabaseField private String								IndependentSectionType;
	@DatabaseField private int									DistrictId;
	@DatabaseField private Boolean								IsForcedUrbanStreet;

	public void Insert() {
		try {
			Dao<MissionsBuildings, Integer> Missionsinsert = (DatabaseHelper.getDbHelper()).getMissionsBuildingsDataHelper();
			MissionsBuildings existenceCheck = Missionsinsert.queryForId(this.UserDailyMissionId);
			if (IsCompleted == null) {
				IsCompleted = false;
			}
			if (existenceCheck != null) {
				Missionsinsert.update(this);
			}
			else {
				Missionsinsert.create(this);
			}

		}
		catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}

	public void Update() {
		try {
			Dao<MissionsBuildings, Integer> Missionsinsert = (DatabaseHelper.getDbHelper()).getMissionsBuildingsDataHelper();
			Missionsinsert.update(this);
		}
		catch (SQLException e) {
			Tools.saveErrors(e);

		}
	}

	public List<MissionsBuildings> GetAllData() {

		List<MissionsBuildings> data = new ArrayList<MissionsBuildings>();

		try {

			Dao<MissionsBuildings, Integer> dao = DatabaseHelper.getDbHelper().getMissionsBuildingsDataHelper();
			QueryBuilder<MissionsBuildings, Integer> qBuilder = dao.queryBuilder();
			qBuilder.where().eq("IsDeleted", false).and().not().eq("IsCompleted", true);
			PreparedQuery<MissionsBuildings> pQuery = qBuilder.prepare();
			data = dao.query(pQuery);

		}
		catch (SQLException e) {
			Tools.saveErrors(e);
		}

		return data;
	}

	public List<MissionsBuildings> GetBuildingsByStreetId(int streetId) {

		List<MissionsBuildings> data = new ArrayList<MissionsBuildings>();

		try {

			Dao<MissionsBuildings, Integer> dao = DatabaseHelper.getDbHelper().getMissionsBuildingsDataHelper();
			QueryBuilder<MissionsBuildings, Integer> qBuilder = dao.queryBuilder();
			qBuilder.where().eq("IsDeleted", false).and().eq("IsCompleted", false).and().eq("StreetId", streetId);
			PreparedQuery<MissionsBuildings> pQuery = qBuilder.prepare();
			data = dao.query(pQuery);

		}
		catch (SQLException e) {
			Tools.saveErrors(e);
		}

		return data;
	}

	public int GetRowCount() {
		int count = 0;

		try {
			Dao<MissionsBuildings, Integer> dao = DatabaseHelper.getDbHelper().getMissionsBuildingsDataHelper();
			count = (int) dao.countOf();
		}
		catch (Exception e) {
			Tools.saveErrors(e);
		}

		return count;
	}

	public void DeleteRow(int deleteId) {
		try {

			Dao<MissionsBuildings, Integer> dao = DatabaseHelper.getDbHelper().getMissionsBuildingsDataHelper();
			DeleteBuilder<MissionsBuildings, Integer> deleteBuilder = dao.deleteBuilder();
			deleteBuilder.where().eq("UserDailyMissionId", deleteId);
			deleteBuilder.delete();
		}
		catch (Exception e) {
			Tools.saveErrors(e);

		}
	}

	public List<MissionsBuildings> getColumn(String ColumnName) throws SQLException {
		Dao<MissionsBuildings, Integer> dao = DatabaseHelper.getDbHelper().getMissionsBuildingsDataHelper();
		List<MissionsBuildings> results = dao.queryBuilder().distinct().selectColumns(ColumnName).query();
		return results;
	}

	public MissionsBuildings getRow(int id) {

		MissionsBuildings dmfb = null;

		try {
			Dao<MissionsBuildings, Integer> dao = DatabaseHelper.getDbHelper().getMissionsBuildingsDataHelper();
			dmfb = dao.queryForAll().get(id);
		}
		catch (Exception e) {
			Tools.saveErrors(e);

		}

		return dmfb;
	}

}