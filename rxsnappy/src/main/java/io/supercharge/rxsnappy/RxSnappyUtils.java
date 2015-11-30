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

    public static String generateKey(Object... objects) {

        StringBuilder stringBuilder = new StringBuilder();

        Object[] args = objects;

        if (args == null) {
            throw new NullPointerException("Cannot generate key with no params!");
        }

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

        String result = stringBuilder.toString();
        RxSnappyLog.d("Key generated from objects: " + result);

        return Base64.encodeToString(result.getBytes(), Base64.DEFAULT);
    }


}
