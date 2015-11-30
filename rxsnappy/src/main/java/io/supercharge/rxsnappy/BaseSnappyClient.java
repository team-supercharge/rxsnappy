package io.supercharge.rxsnappy;

import com.snappydb.DB;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.supercharge.rxsnappy.exception.CacheExpiredException;
import io.supercharge.rxsnappy.exception.KeyIsNullException;
import io.supercharge.rxsnappy.exception.MissingDataException;
import io.supercharge.rxsnappy.exception.RxSnappyException;
import io.supercharge.rxsnappy.exception.ValueIsNullException;

/**
 * Created by richardradics on 25/11/15.
 */
public abstract class BaseSnappyClient {


    protected DB db;


    public BaseSnappyClient(DB db) {
        this.db = db;
        if (this.db == null) {
            throw new RxSnappyException("Database is null!");
        }
    }


    private boolean checkIsCacheValid(String key, Long cacheTime) {
        if (cacheTime == null) {
            return true;
        } else {

            String[] splitted = key.split(":");
            Date cacheDate = new Date(Long.valueOf(splitted[1]));
            Date current = new Date();
            long diff = current.getTime() - cacheDate.getTime();

            if (diff <= cacheTime) {
                return true;
            } else {
                throw new CacheExpiredException();
            }
        }
    }

    private String generateKey(String key) {
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append(":");
        sb.append(date.getTime());

        String res = sb.toString();
        return res;
    }

    private void removePreviousCachedElement(String key) throws SnappydbException {
        synchronized (db) {
            if (db.countKeys(key) > 1) {
                String[] s = db.findKeys(key);
                db.del(s[0]);
            }
        }
    }

    private String findTimeBasedKey(String key) throws SnappydbException {
        synchronized (db) {
            String[] s = db.findKeys(key);

            String res = s[0];
            String dateLog = "";
            if (RxSnappyLog.debug) {
                String[] tmp = res.split(":");
                Date date = new Date(Long.valueOf(tmp[1]));
                dateLog = RxSnappyUtils.printDateFromLong(date.getTime());
            }
            RxSnappyLog.d("Key found: " + res + " " + dateLog);
            return res;
        }
    }

    protected boolean isInCache(String key, Long cacheTime) throws SnappydbException {
        synchronized (db) {
            String[] keys = db.findKeys(key);

            if (keys.length > 0) {
                if (checkIsCacheValid(keys[0], cacheTime)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    protected void setValue(String key, Boolean value) throws SnappydbException, RxSnappyException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }

            if (value == null) {
                throw new ValueIsNullException();
            }

            db.put(generateKey(key), value);

            removePreviousCachedElement(key);
        }
    }

    protected Boolean getBooleanValue(String key, Long cacheTime) throws SnappydbException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (isInCache(key, cacheTime)) {
                return db.getBoolean(findTimeBasedKey(key));
            } else {
                throw new MissingDataException();
            }
        }
    }

    protected void setValue(String key, Integer value) throws SnappydbException, RxSnappyException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (value == null) {
                throw new ValueIsNullException();
            }
            db.put(generateKey(key), value);

            removePreviousCachedElement(key);
        }
    }

    protected Integer getIntegerValue(String key, Long cacheTime) throws SnappydbException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (isInCache(key, cacheTime)) {
                return db.getObject(findTimeBasedKey(key), Integer.class);
            } else {
                throw new MissingDataException();
            }
        }
    }

    protected void setValue(String key, Long value) throws SnappydbException, RxSnappyException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (value == null) {
                throw new ValueIsNullException();
            }
            db.put(generateKey(key), value);
            removePreviousCachedElement(key);
        }
    }

    protected Long getLongValue(String key, Long cacheTime) throws SnappydbException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (isInCache(key, cacheTime)) {
                return db.getObject(findTimeBasedKey(key), Long.class);
            } else {
                throw new MissingDataException();
            }
        }
    }


    protected void setValue(String key, String value) throws SnappydbException, RxSnappyException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (value == null) {
                throw new ValueIsNullException();
            }
            db.put(generateKey(key), value);

            removePreviousCachedElement(key);
        }
    }

    protected String getStringValue(String key, Long cacheTime) throws SnappydbException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (isInCache(key, cacheTime)) {
                return db.get(findTimeBasedKey(key));
            } else {
                throw new MissingDataException();
            }
        }
    }

    protected void setStringListValue(String key, List<String> value) throws SnappydbException, RxSnappyException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (value == null) {
                throw new ValueIsNullException();
            }
            db.put(generateKey(key), value.toArray(new String[value.size()]));

            removePreviousCachedElement(key);
        }
    }


    protected List<String> getStringListValue(String key, Long cacheTime) throws SnappydbException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            List<String> values = new ArrayList<String>();
            if (isInCache(key, cacheTime)) {
                values.addAll(Arrays.asList(db.getArray(findTimeBasedKey(key), String.class)));
            } else {
                throw new MissingDataException();
            }
            return values;
        }
    }


    protected <T> List<T> getObjectList(String key, Long cacheTime, Class<T> resultClass) throws SnappydbException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            List<T> resultList = new ArrayList<T>();
            if (isInCache(key, cacheTime)) {
                resultList.addAll(Arrays.asList(db.getObjectArray(findTimeBasedKey(key), resultClass)));
            } else {
                throw new MissingDataException();
            }
            return resultList;
        }
    }

    protected void setValue(String key, List object) throws SnappydbException, RxSnappyException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (object == null) {
                throw new ValueIsNullException();
            }
            db.put(generateKey(key), object.toArray());

            removePreviousCachedElement(key);
        }
    }

    protected <T> T setValue(String key, T o) throws SnappydbException, RxSnappyException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (o == null) {
                throw new ValueIsNullException();
            }
            db.put(generateKey(key), o);

            removePreviousCachedElement(key);

            return o;
        }
    }

    protected <T> T getObjectValue(String key, Long cacheTime, Class<T> resultClass) throws SnappydbException {
        synchronized (db) {
            if (key == null) {
                throw new KeyIsNullException();
            }
            if (isInCache(key, cacheTime)) {
                return db.getObject(findTimeBasedKey(key), resultClass);
            } else {
                throw new MissingDataException();
            }
        }
    }


}