package es.cervecitas.earthquakeobserver.data.cache;

import android.content.Context;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import es.cervecitas.earthquakeobserver.BuildConfig;

//https://stackoverflow.com/questions/12746269/how-to-implement-caching-in-android-app-for-rest-api-results
public class CacheManager {

    Cache<String, String> earthquakeListCache;
    private DiskLruCache diskLruCache;
    private final Context context;

    public CacheManager(Context context) throws IOException {
        this.context = context;
        setUp();
        this.earthquakeListCache = DiskCache.getInstanceUsingDoubleLocking(diskLruCache);
    }

    public void setUp() throws IOException {
        File cacheInFiles = context.getFilesDir();
        int version = BuildConfig.VERSION_CODE;

        int KB = 1024;
        int MB = 1024 * KB;
        int cacheSize = 5 * MB;

        diskLruCache = DiskLruCache.open(cacheInFiles, version, 1, cacheSize);
    }

    public Cache<String, String> getCache() {
        return earthquakeListCache;
    }

    public static String getMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String md5 = number.toString(16);

            while (md5.length() < 32)
                md5 = "0" + md5;

            return md5;
        } catch (NoSuchAlgorithmException e) {
            Log.e("MD5", e.getLocalizedMessage());
            return null;
        }
    }

    public static class DiskCache implements Cache<String, String> {

        private static DiskLruCache mDiskLruCache;
        private static DiskCache instance = null;

        public static DiskCache getInstanceUsingDoubleLocking(DiskLruCache lruCache) {
            mDiskLruCache = lruCache;
            if (instance == null) {
                synchronized (DiskCache.class) {
                    if (instance == null) {
                        instance = new DiskCache();
                    }
                }
            }
            return instance;
        }

        @Override
        public synchronized void put(String key, String value) {
            try {
                if (mDiskLruCache != null) {
                    DiskLruCache.Editor edit = mDiskLruCache.edit(getMd5Hash(key));
                    if (edit != null) {
                        edit.set(0, value);
                        edit.commit();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public synchronized String get(String key) {
            try {
                if (mDiskLruCache != null) {
                    DiskLruCache.Snapshot snapshot = mDiskLruCache.get(getMd5Hash(key));

                    if (snapshot == null) {
                        // if there is a cache miss simply return null;
                        return null;
                    }

                    return snapshot.getString(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // in case of error in reading return null;
            return null;
        }

        @Override
        public String remove(String key) {
            try {
                if (mDiskLruCache != null) {
                    DiskLruCache.Snapshot snapshot = mDiskLruCache.get(getMd5Hash(key));

                    if (snapshot == null) {
                        // if there is a cache miss simply return null;
                        return null;
                    }

                    String listToReturn = snapshot.getString(0);

                    mDiskLruCache.remove(getMd5Hash(key));

                    return listToReturn;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // in case of error in reading return null;
            return null;
        }

        @Override
        public void clear() {
            try {
                mDiskLruCache.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
