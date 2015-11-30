package io.supercharge.rxsnappy;

/**
 * Created by richardradics on 24/11/15.
 */
public final class RxSnappyLog {

    static boolean debug = true;
    private static final String TAG = "RxSnappy";

    private RxSnappyLog() {
    }

    public static void enableLog(boolean isEnabled) {
        debug = isEnabled;
    }


    public static void v(String msg) {
        if (debug) {
            android.util.Log.v(TAG, msg);
        }
    }

    public static void v(String msg, Throwable tr) {
        if (debug) {
            android.util.Log.v(TAG, msg, tr);
        }
    }

    public static void d(String msg) {
        if (debug) {
            android.util.Log.d(TAG, msg);
        }
    }

    public static void d(String msg, Throwable tr) {
        if (debug) {
            android.util.Log.d(TAG, msg, tr);
        }
    }

    public static void i(String msg) {
        if (debug) {
            android.util.Log.i(TAG, msg);
        }
    }

    public static void i(String msg, Throwable tr) {
        if (debug) {
            android.util.Log.i(TAG, msg, tr);
        }
    }

    public static void w(String msg) {
        if (debug) {
            android.util.Log.w(TAG, msg);
        }
    }

    public static void w(String msg, Throwable tr) {
        if (debug) {
            android.util.Log.w(TAG, msg, tr);
        }
    }

    public static void w(Throwable tr) {
        if (debug) {
            android.util.Log.w(TAG, tr);
        }
    }

    public static void e(String msg) {
        if (debug) {
            android.util.Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (debug) {
            android.util.Log.e(TAG, msg, tr);
        }
    }

    public static void e(Throwable tr) {
        if (debug) {
            android.util.Log.e(TAG, "", tr);
        }
    }

}
