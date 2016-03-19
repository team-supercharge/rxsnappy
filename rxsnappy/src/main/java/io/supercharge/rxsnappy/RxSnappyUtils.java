package io.supercharge.rxsnappy;

import android.util.Base64;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by richardradics on 28/11/15.
 */
public class RxSnappyUtils {

    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private static Gson gson = new Gson();


    public static SimpleDateFormat getDateFormatter() {
        return sdf;
    }

    public static String printDateFromLong(long dateInMilliSeconds) {
        Date date = new Date(dateInMilliSeconds);
        return sdf.format(date);
    }

    public static Date getDate(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String generateKey(String key, int position) {
        StringBuilder sb = new StringBuilder();

        sb.append(key);
        sb.append(":");
        sb.append(position);

        return sb.toString();
    }

    public static String generateKey(Object... objects) {

        Object[] args = objects;

        if (args == null) {
            throw new NullPointerException("Cannot generate key with no params!");
        }

        StringBuilder stringBuilder = new StringBuilder();


        for (Object o : args) {
            if (o != null) {
                if (o instanceof String) {
                    stringBuilder.append(o);
                    stringBuilder.append("_");
                } else {
                    String json = gson.toJson(o);
                    stringBuilder.append(json);
                    stringBuilder.append("_");
                }
            }
        }

        return Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.DEFAULT);
    }


}
