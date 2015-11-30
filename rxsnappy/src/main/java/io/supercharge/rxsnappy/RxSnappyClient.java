package io.supercharge.rxsnappy;

import com.snappydb.DB;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

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

    public Observable<Boolean> getBoolean(String key) {
        return getBoolean(key, null);
    }

    public Observable<Boolean> getBoolean(final String key, final Long cacheTime) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    subscriber.onNext(getBooleanValue(key, cacheTime));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<Boolean> setBoolean(final String key, final Boolean value) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    setValue(key, value);
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<String> getString(String key) {
        return getString(key, null);
    }

    public Observable<String> getString(final String key, final Long cacheTime) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(getStringValue(key, cacheTime));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<String> setString(final String key, final String value) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    setValue(key, value);
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<Long> setLong(final String key, final Long value) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    setValue(key, value);
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<Long> getLong(String key) {
        return getLong(key, null);
    }

    public Observable<Long> getLong(final String key, final Long cacheTime) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(getLongValue(key, cacheTime));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }


    public Observable<Integer> setInteger(final String key, final Integer value) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    setValue(key, value);
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<Integer> getInteger(String key) {
        return getInteger(key, null);
    }

    public Observable<Integer> getInteger(final String key, final Long cacheTime) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    subscriber.onNext(getIntegerValue(key, cacheTime));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }


    public Observable<List<String>> setStringList(final String key, final List<String> value) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                try {
                    setStringListValue(key, value);
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }


    public Observable<List<String>> getStringList(String key) {
        return getStringList(key, null);
    }

    public Observable<List<String>> getStringList(final String key, final Long cacheTime) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                try {
                    subscriber.onNext(getStringListValue(key, cacheTime));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<List> setList(final String key, final List value) {
        return Observable.create(new Observable.OnSubscribe<List>() {
            @Override
            public void call(Subscriber<? super List> subscriber) {
                try {
                    setValue(key, value);
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public <T> Observable<List<T>> getList(String key, Class<T> selectedClass) {
        return getList(key, null, selectedClass);
    }

    public <T> Observable<List<T>> getList(final String key, final Long cacheTime, final Class<T> selectedClass) {
        return Observable.create(new Observable.OnSubscribe<List<T>>() {
            @Override
            public void call(Subscriber<? super List<T>> subscriber) {
                try {
                    subscriber.onNext(getObjectList(key, cacheTime, selectedClass));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }


    public <T> Observable<T> setObject(final String key, final T value) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    setValue(key, value);
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public <T> Observable<T> getObject(String key, Class<T> selectedClass) {
        return getObject(key, null, selectedClass);
    }

    public <T> Observable<T> getObject(final String key, final Long cacheTime, final Class<T> selectedClass) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(getObjectValue(key, cacheTime, selectedClass));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

}
