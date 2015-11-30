package io.supercharge.rxsnappy;

import android.content.Context;

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
                RxSnappyLog.d("Database created.");
            } catch (SnappydbException e) {
                RxSnappyLog.e("Failed to open database!", e);
            }
        } else {
            RxSnappyLog.d("Database already initialized.");
        }
    }


    public static void closeDatabase() {
        if (db != null) {
            try {
                db.close();
            } catch (SnappydbException e) {
                RxSnappyLog.e("Failed to close database", e);
            }
        } else {
            RxSnappyLog.d("Database is null. Nothing to do.");
        }
    }

    public static void clearDatabase() {
        if (db != null) {
            try {
                db.destroy();
                db = null;
            } catch (SnappydbException e) {
                RxSnappyLog.e("Failed to destroy database", e);
            }
        } else {
            RxSnappyLog.d("Database is null. Nothing to do.");
        }
    }
}
