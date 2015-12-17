package com.sefagurel.rsshaber_sondakika.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sefagurel.rsshaber_sondakika.MainActivity;
import com.sefagurel.rsshaber_sondakika.models.news.AlternateEntity;
import com.sefagurel.rsshaber_sondakika.models.news.AlternateEntity2;
import com.sefagurel.rsshaber_sondakika.models.news.EnclosureEntity;
import com.sefagurel.rsshaber_sondakika.models.news.ItemsEntity;
import com.sefagurel.rsshaber_sondakika.models.news.NewsModel;
import com.sefagurel.rsshaber_sondakika.models.news.OriginEntity;
import com.sefagurel.rsshaber_sondakika.models.news.SummaryEntity;
import com.sefagurel.rsshaber_sondakika.models.news.VisualEntity;
import com.sefagurel.rsshaber_sondakika.tools.Info;
import com.sefagurel.rsshaber_sondakika.tools.Tools;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final int		DATABASE_VERSION	= Info.DATABASE_VERSION;
	private static final String		DATABASE_NAME		= Info.DATABASE_NAME;
	private static final String		MAP_DATABASE_NAME	= Info.MAP_DBNAME;
	private final Context			myContext;
	private static DatabaseHelper	dbHelper			= null;
	private static final String		MAP_DATABASE_PATH	= "/data/data/com.sefagurel.rsshaber_sondakika/databases/";
	private static Object			syncObject			= new Object();
	private Boolean					isHaveDB			= false;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.myContext = context;
		// this.getReadableDatabase();
		// this.getWritableDatabase();
	}

	public static synchronized DatabaseHelper getDbHelper() {
		if (dbHelper == null) {
			dbHelper = new DatabaseHelper(MainActivity.context);
		}
		return dbHelper;
	}

	private Dao<MissionsBuildings, Integer> MissionsBuildingsDataHelper = null;

	private Dao<AlternateEntity, Integer>	AlternateEntityDataHelper	= null;
	private Dao<AlternateEntity2, Integer>	AlternateEntity2DataHelper	= null;
	private Dao<EnclosureEntity, Integer>	EnclosureEntityDataHelper	= null;
	private Dao<ItemsEntity, Integer>		ItemsEntityDataHelper		= null;
	private Dao<NewsModel, Integer>			NewsModelDataHelper			= null;
	private Dao<OriginEntity, Integer>		OriginEntityDataHelper		= null;
	private Dao<SummaryEntity, Integer>		SummaryEntityDataHelper		= null;
	private Dao<VisualEntity, Integer>		VisualEntityDataHelper		= null;

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {

			TableUtils.createTable(connectionSource, MissionsBuildings.class);
			TableUtils.createTable(connectionSource, AlternateEntity.class);
			TableUtils.createTable(connectionSource, AlternateEntity2.class);
			TableUtils.createTable(connectionSource, EnclosureEntity.class);
			TableUtils.createTable(connectionSource, ItemsEntity.class);
			TableUtils.createTable(connectionSource, NewsModel.class);
			TableUtils.createTable(connectionSource, OriginEntity.class);
			TableUtils.createTable(connectionSource, SummaryEntity.class);
			TableUtils.createTable(connectionSource, VisualEntity.class);
		}
		catch (java.sql.SQLException e) {
			Tools.saveErrors(e);
		}
	}

	public void clearDatabase() {
		ConnectionSource connectionSource = getConnectionSource();
		try {
			TableUtils.clearTable(connectionSource, MissionsBuildings.class);
			TableUtils.clearTable(connectionSource, AlternateEntity.class);
			TableUtils.clearTable(connectionSource, AlternateEntity2.class);
			TableUtils.clearTable(connectionSource, EnclosureEntity.class);
			TableUtils.clearTable(connectionSource, ItemsEntity.class);
			TableUtils.clearTable(connectionSource, NewsModel.class);
			TableUtils.clearTable(connectionSource, OriginEntity.class);
			TableUtils.clearTable(connectionSource, SummaryEntity.class);
			TableUtils.clearTable(connectionSource, VisualEntity.class);
		}
		catch (SQLException e) {
			Tools.saveErrors(e);
		}
	}

	public void deleteDatabase() {
		ConnectionSource connectionSource = getConnectionSource();
		try {
			TableUtils.dropTable(connectionSource, MissionsBuildings.class, true);
			TableUtils.dropTable(connectionSource, AlternateEntity.class, true);
			TableUtils.dropTable(connectionSource, AlternateEntity2.class, true);
			TableUtils.dropTable(connectionSource, EnclosureEntity.class, true);
			TableUtils.dropTable(connectionSource, ItemsEntity.class, true);
			TableUtils.dropTable(connectionSource, NewsModel.class, true);
			TableUtils.dropTable(connectionSource, OriginEntity.class, true);
			TableUtils.dropTable(connectionSource, SummaryEntity.class, true);
			TableUtils.dropTable(connectionSource, VisualEntity.class, true);
		}
		catch (SQLException e) {
			Tools.saveErrors(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");

			TableUtils.dropTable(connectionSource, MissionsBuildings.class, true);
			TableUtils.dropTable(connectionSource, AlternateEntity.class, true);
			TableUtils.dropTable(connectionSource, AlternateEntity2.class, true);
			TableUtils.dropTable(connectionSource, EnclosureEntity.class, true);
			TableUtils.dropTable(connectionSource, ItemsEntity.class, true);
			TableUtils.dropTable(connectionSource, NewsModel.class, true);
			TableUtils.dropTable(connectionSource, OriginEntity.class, true);
			TableUtils.dropTable(connectionSource, SummaryEntity.class, true);
			TableUtils.dropTable(connectionSource, VisualEntity.class, true);

			onCreate(db, connectionSource);
		}
		catch (java.sql.SQLException e) {
			Tools.saveErrors(e);

		}
	}

	public void CreateDatabase(Context context) {
		try {
			boolean dbExist = checkDataBase();
			isHaveDB = dbExist;

			if (!dbExist) {
				// By calling this method and empty database will be created into the default system path
				// of your application so we are gonna be able to overwrite that database with our database.
				this.getReadableDatabase();

				try {
					copyDataBase(context);
				}
				catch (IOException e) {
					Tools.saveErrors(e);

				}
			}

		}
		catch (Exception e) {
			Tools.saveErrors(e);
		}

	}

	public void ReCreateDatabase(Context context) {
		try {
			this.getReadableDatabase();

			try {
				copyDataBase(context);
			}
			catch (IOException e) {
				Tools.saveErrors(e);
			}
		}
		catch (Exception e) {
			Tools.saveErrors(e);
		}

	}

	public Boolean getDbStatus() {
		return isHaveDB;
	}

	private void copyDataBase(Context context) throws IOException {

		// String path = Environment.getExternalStorageDirectory() + "/KMLTest/database";

		// Path to the just created empty db
		// String inputFileName = path + "/" + "silivri";

		// InputStream myInput = new FileInputStream(inputFileName);

		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(MAP_DATABASE_NAME);

		// Path to the just created empty db
		String outFileName = MAP_DATABASE_PATH + MAP_DATABASE_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = MAP_DATABASE_PATH + MAP_DATABASE_NAME;
			File file = new File(myPath);
			if (file.exists() && !file.isDirectory())
				checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}
		catch (SQLiteException e) {
			Tools.saveErrors(e);
		}

		if (checkDB != null && checkDB.isOpen()) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	public Dao<MissionsBuildings, Integer> getMissionsBuildingsDataHelper() throws SQLException {
		if (MissionsBuildingsDataHelper == null) {
			MissionsBuildingsDataHelper = getDao(MissionsBuildings.class);
		}
		return MissionsBuildingsDataHelper;
	}

	public Dao<AlternateEntity, Integer> getAlternateEntityDataHelper() throws SQLException {
		if (AlternateEntityDataHelper == null) {
			AlternateEntityDataHelper = getDao(AlternateEntity.class);
		}
		return AlternateEntityDataHelper;
	}

	public Dao<AlternateEntity2, Integer> getAlternateEntity2DataHelper() throws SQLException {
		if (AlternateEntity2DataHelper == null) {
			AlternateEntity2DataHelper = getDao(AlternateEntity2.class);
		}
		return AlternateEntity2DataHelper;
	}

	public Dao<EnclosureEntity, Integer> getEnclosureEntityDataHelper() throws SQLException {
		if (EnclosureEntityDataHelper == null) {
			EnclosureEntityDataHelper = getDao(EnclosureEntity.class);
		}
		return EnclosureEntityDataHelper;
	}

	public Dao<ItemsEntity, Integer> getItemsEntityDataHelper() throws SQLException {
		if (ItemsEntityDataHelper == null) {
			ItemsEntityDataHelper = getDao(ItemsEntity.class);
		}
		return ItemsEntityDataHelper;
	}

	public Dao<NewsModel, Integer> getNewsModelDataHelper() throws SQLException {
		if (NewsModelDataHelper == null) {
			NewsModelDataHelper = getDao(NewsModel.class);
		}
		return NewsModelDataHelper;
	}

	public Dao<OriginEntity, Integer> getOriginEntityDataHelper() throws SQLException {
		if (OriginEntityDataHelper == null) {
			OriginEntityDataHelper = getDao(OriginEntity.class);
		}
		return OriginEntityDataHelper;
	}

	public Dao<SummaryEntity, Integer> getSummaryEntityDataHelper() throws SQLException {
		if (SummaryEntityDataHelper == null) {
			SummaryEntityDataHelper = getDao(SummaryEntity.class);
		}
		return SummaryEntityDataHelper;
	}

	public Dao<VisualEntity, Integer> getVisualEntityDataHelper() throws SQLException {
		if (VisualEntityDataHelper == null) {
			VisualEntityDataHelper = getDao(VisualEntity.class);
		}
		return VisualEntityDataHelper;
	}

}
