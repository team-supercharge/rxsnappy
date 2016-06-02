package io.supercharge.rxsnappy;

import android.content.Context;
import android.util.Log;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

/**
 * Created by richardradics on 24/11/15.
 */
public final class RxSnappy {

    static DB db;

    private RxSnappy() {
    }

    public static void init(Context context) {
        if (db == null) {
            try {
                db = DBFactory.open(context);
            } catch (SnappydbException e) {
                Log.e("RxSnappy", "Failed to open database", e);
            }
        }
    }


    public static void closeDatabase() {
        if (db != null) {
            try {
                db.close();
            } catch (SnappydbException e) {
                Log.e("RxSnappy", "Failed to close database", e);
            }
        }
    }

    public static void destroyDatabase() {
        if (db != null) {
            try {
                db.destroy();
                db = null;
            } catch (SnappydbException e) {
                Log.e("RxSnappy", "Failed to destroy database", e);
            }
        }
    }

    public static void resetDatabase(Context context) {
        if (db != null) {
            try {
                db.destroy();
                db = null;

                init(context);

            } catch (SnappydbException e) {
                Log.e("RxSnappy", "Failed to destroy database", e);
            }
        } else {
            Log.d("RxSnappy", "Database is null. Nothing to do.");
        }
    }

}
