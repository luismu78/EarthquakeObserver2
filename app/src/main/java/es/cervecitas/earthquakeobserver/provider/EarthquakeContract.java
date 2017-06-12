package es.cervecitas.earthquakeobserver.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class EarthquakeContract {

    public static final String CONTENT_AUTHORITY = "es.cervecitas.earthquakeobserver.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // PATHS
    public static final String PATH_EARTHQUAKES = "earthqueakes";

    public static final class EarthquakeEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EARTHQUAKES);

        // MIME TYPES
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_EARTHQUAKES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_EARTHQUAKES;

        // TABLE NAME
        public static final String TABLE_NAME = "earthquakes";

        // COLUMNS NAMES
        public static final String COLUMN_MAGNITUDE = "magnitude";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_DATETIME = "datetime";
    }
}
