package es.cervecitas.earthquakeobserver.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class EarthquakeProvider extends ContentProvider{

    private static final String TAG = EarthquakeProvider.class.getSimpleName();

    public static final int EARTHQUAKES = 100;
    public static final int EARTHQUAKE_ID = 101;

    public static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(EarthquakeContract.CONTENT_AUTHORITY, EarthquakeContract.PATH_EARTHQUAKES, EARTHQUAKES);
    }

    private EarthquakeDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new EarthquakeDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor;
        final int match = MATCHER.match(uri);

        switch (match) {
            case EARTHQUAKES:
                cursor = db.query(
                        EarthquakeContract.EarthquakeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = MATCHER.match(uri);

        switch (match) {
            case EARTHQUAKES:
                return insertEarthquake(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertEarthquake(Uri uri, ContentValues values) {
        if (values.containsKey(EarthquakeContract.EarthquakeEntry.COLUMN_DATETIME)) {
            String dateTime = values.getAsString(EarthquakeContract.EarthquakeEntry.COLUMN_DATETIME);
            if (dateTime == null || dateTime.isEmpty()) {
                throw new IllegalArgumentException("Item requires a Date and Time");
            }
        }

        if (values.containsKey(EarthquakeContract.EarthquakeEntry.COLUMN_LOCATION)) {
            String location = values.getAsString(EarthquakeContract.EarthquakeEntry.COLUMN_LOCATION);
            if (location == null || location.isEmpty()) {
                throw new IllegalArgumentException("Item requires a Location");
            }
        }

        if (values.containsKey(EarthquakeContract.EarthquakeEntry.COLUMN_MAGNITUDE)) {
            String magnitude = values.getAsString(EarthquakeContract.EarthquakeEntry.COLUMN_MAGNITUDE);
            if (magnitude == null || magnitude.isEmpty()) {
                throw new IllegalArgumentException("Item requires a Magnitude");
            }
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long id = db.insert(EarthquakeContract.EarthquakeEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = MATCHER.match(uri);

        switch (match) {
            case EARTHQUAKES:
                return update(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = MATCHER.match(uri);
        switch (match) {
            case EARTHQUAKES:
                rowsDeleted = db.delete(EarthquakeContract.EarthquakeEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }



    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = MATCHER.match(uri);

        switch (match) {
            case EARTHQUAKES:
                return EarthquakeContract.EarthquakeEntry.CONTENT_LIST_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri + " with match " + match);
        }
    }
}
