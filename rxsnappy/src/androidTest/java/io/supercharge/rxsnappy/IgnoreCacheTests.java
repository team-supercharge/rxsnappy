package io.supercharge.rxsnappy;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.List;

import io.supercharge.mock.DataGenerator;
import io.supercharge.mock.DummyData;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by richardradics on 19/03/16.
 */
public class IgnoreCacheTests extends AndroidTestCase {

    private static final String test_key = "test-asd";


    RxSnappyClient rxSnappyClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RxSnappy.init(getContext());
        rxSnappyClient = new RxSnappyClient();
    }


    @SmallTest
    public void testDataHandlingWithoutCacheSupport() {
        String key = "asdkey";
        String key1 = RxSnappyUtils.generateKey(key, 1);
        String key2 = RxSnappyUtils.generateKey(key, 2);


        DummyData dummyData = DataGenerator.generateNewDummyData();
        DummyData dummyData1 = DataGenerator.generateNewDummyData();

        rxSnappyClient.setObject(key1, dummyData, true).toBlocking().first();
        int cnt = rxSnappyClient.countKeys(key).toBlocking().first();
        assertTrue(cnt == 1);
        rxSnappyClient.setObject(key1, dummyData1, true).toBlocking().first();

        DummyData s = rxSnappyClient.getObject(key1, DummyData.class).toBlocking().first();
        assertTrue(s.equals(dummyData1));

        cnt = rxSnappyClient.countKeys(key).toBlocking().first();
        assertTrue(cnt == 1);

        boolean exists = rxSnappyClient.exists(key1).toBlocking().first();
        boolean notExists = rxSnappyClient.exists(key2).toBlocking().first();

        assertTrue(exists);
        assertFalse(notExists);

        rxSnappyClient.setObject(key2, dummyData, true).toBlocking().first();
        cnt = rxSnappyClient.countKeys(key).toBlocking().first();
        assertTrue(cnt == 2);

        s = rxSnappyClient.getObject(key2, DummyData.class).toBlocking().first();
        assertTrue(s.equals(dummyData));

        List<DummyData> datas =
                rxSnappyClient.findKeys(key).flatMap(new Func1<String[], Observable<String>>() {
                    @Override
                    public Observable<String> call(String[] strings) {
                        return Observable.from(strings);
                    }
                }).flatMap(new Func1<String, Observable<DummyData>>() {
                    @Override
                    public Observable<DummyData> call(String s) {
                        return rxSnappyClient.getObject(s, DummyData.class);
                    }
                }).toList().toBlocking().first();

        assertTrue(datas.size() == 2);

        rxSnappyClient.deleteCache(key).toBlocking().first();

        cnt = rxSnappyClient.countKeys(key).toBlocking().first();
        assertTrue(cnt == 0);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        RxSnappy.closeDatabase();
        RxSnappy.destroyDatabase();


    }

}
