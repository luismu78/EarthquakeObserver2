package es.cervecitas.earthquakeobserver.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EarthquakeDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "earthquakeobserver";
    public static final int DATABASE_VERSION = 1;

    public EarthquakeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_EARTHQUAKES_TABLE =
                "CREATE TABLE " + EarthquakeContract.EarthquakeEntry.TABLE_NAME + " (" +
                        EarthquakeContract.EarthquakeEntry.COLUMN_DATETIME + " STRING PRIMARY KEY, " +
                        EarthquakeContract.EarthquakeEntry.COLUMN_LOCATION + " STRING NOT NULL, " +
                        EarthquakeContract.EarthquakeEntry.COLUMN_MAGNITUDE + " STRING NOT NULL);";

        db.execSQL(SQL_CREATE_EARTHQUAKES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // first version nothing to do here
    }
}
