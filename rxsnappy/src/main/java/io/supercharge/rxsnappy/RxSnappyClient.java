package io.supercharge.rxsnappy;

import com.snappydb.DB;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by richardradics on 24/11/15.
 */
public final class RxSnappyClient extends BaseSnappyClient {

    public RxSnappyClient(DB db) {
        super(db);
    }

    public RxSnappyClient() {
        super(RxSnappy.db);
    }


    public Observable<Boolean> isCached(final String key) {
        return Observable.defer(new Func0<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call() {
                try {
                    return Observable.just(isInCache(key));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<Boolean> exists(final String key) {
        return Observable.defer(new Func0<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call() {
                try {
                    return Observable.just(isExists(key));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<Boolean> getBoolean(String key) {
        return getBoolean(key, null);
    }

    public Observable<Boolean> getBoolean(final String key, final Long cacheTime) {

        return Observable.defer(new Func0<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call() {
                try {
                    return Observable.just(getBooleanValue(key, cacheTime));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<Boolean> setBoolean(String key, Boolean value) {
        return setBoolean(key, value, false);
    }


    public Observable<Boolean> setBoolean(final String key, final Boolean value, final boolean ignoreCache) {

        return Observable.defer(new Func0<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call() {
                try {
                    setValue(key, value, ignoreCache);
                    return Observable.just(value);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<String> getString(String key) {
        return getString(key, null);
    }

    public Observable<String> getString(final String key, final Long cacheTime) {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    return Observable.just(getStringValue(key, cacheTime));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<String> setString(final String key, final String value) {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    setValue(key, value, false);
                    return Observable.just(value);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<Long> setLong(final String key, final Long value) {
        return setLong(key, value, false);
    }

    public Observable<Long> setLong(final String key, final Long value, final boolean ignoreCache) {
        return Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                try {
                    setValue(key, value, ignoreCache);
                    return Observable.just(value);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<Long> getLong(String key) {
        return getLong(key, null);
    }

    public Observable<Long> getLong(final String key, final Long cacheTime) {
        return Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                try {
                    return Observable.just(getLongValue(key, cacheTime));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }


    public Observable<Integer> setInteger(final String key, final Integer value) {
        return setInteger(key, value, false);
    }

    public Observable<Integer> setInteger(final String key, final Integer value, final boolean ignoreCache) {
        return Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                try {
                    setValue(key, value, ignoreCache);
                    return Observable.just(value);
                } catch (Exception e) {
                    return Observable.error(e);
                }

            }
        });
    }

    public Observable<Integer> getInteger(String key) {
        return getInteger(key, null);
    }

    public Observable<Integer> getInteger(final String key, final Long cacheTime) {
        return Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                try {
                    return Observable.just(getIntegerValue(key, cacheTime));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<List<String>> setStringList(final String key, final List<String> value) {
        return setStringList(key, value, false);
    }

    public Observable<List<String>> setStringList(final String key, final List<String> value, final boolean ignoreCache) {
        return Observable.defer(new Func0<Observable<List<String>>>() {
            @Override
            public Observable<List<String>> call() {
                try {
                    setStringListValue(key, value, ignoreCache);
                    return Observable.just(value);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }


    public Observable<List<String>> getStringList(String key) {
        return getStringList(key, null);
    }

    public Observable<List<String>> getStringList(final String key, final Long cacheTime) {
        return Observable.defer(new Func0<Observable<List<String>>>() {
            @Override
            public Observable<List<String>> call() {
                try {
                    return Observable.just(getStringListValue(key, cacheTime));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }


    public Observable<HashMap<Object , Object>> setHashMapList(final String key, final HashMap<Object , Object> value) {
        return Observable.defer(new Func0<Observable<HashMap<Object , Object>>>() {
            @Override
            public Observable<HashMap<Object , Object>> call() {
                try {
                    setHashMap(key, value , false);
                    return Observable.just(value);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<HashMap<Object , Object>> getHashMapList(final String key, final Long cacheTime) {
        return Observable.defer(new Func0<Observable<HashMap<Object , Object>>>() {
            @Override
            public Observable<HashMap<Object , Object>> call() {
                try {
                    return Observable.just(getHashMap(key, cacheTime));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }






    public Observable<List> setList(final String key, final List value) {
        return setList(key, value, false);
    }

    public Observable<List> setList(final String key, final List value, final boolean ignoreCache) {
        return Observable.defer(new Func0<Observable<List>>() {
            @Override
            public Observable<List> call() {
                try {
                    setValue(key, value, ignoreCache);
                    return Observable.just(value);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public <T> Observable<List<T>> getList(String key, Class<T> selectedClass) {
        return getList(key, null, selectedClass);
    }

    public <T> Observable<List<T>> getList(final String key, final Long cacheTime, final Class<T> selectedClass) {
        return Observable.defer(new Func0<Observable<List<T>>>() {
            @Override
            public Observable<List<T>> call() {
                try {
                    return Observable.just(getObjectList(key, cacheTime, selectedClass));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public <T> Observable<T> setObject(final String key, final T value) {
        return setObject(key, value, false);
    }

    public <T> Observable<T> setObject(final String key, final T value, final boolean ignoreCache) {
        return Observable.defer(new Func0<Observable<T>>() {
            @Override
            public Observable<T> call() {
                try {
                    setValue(key, value, ignoreCache);
                    return Observable.just(value);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }



    public <T> Observable<T> getObject(String key, Class<T> selectedClass) {
        return getObject(key, null, selectedClass);
    }

    public <T> Observable<T> getObject(final String key, final Long cacheTime, final Class<T> selectedClass) {
        return Observable.defer(new Func0<Observable<T>>() {
            @Override
            public Observable<T> call() {
                try {
                    return Observable.just(getObjectValue(key, cacheTime, selectedClass));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<Boolean> deleteCache(final String key) {
        return Observable.defer(new Func0<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call() {
                try {
                    removeCacheForKey(key);
                    return Observable.just(true);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<String[]> findKeys(final String key) {
        return Observable.defer(new Func0<Observable<String[]>>() {
            @Override
            public Observable<String[]> call() {
                try {
                    return Observable.just(fndKeys(key));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<Integer> countKeys(final String key) {
        return Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                try {
                    return Observable.just(cntKeys(key));
                } catch (Exception e) {
                    return Observable.error(e);
                }

            }
        });
    }

}
