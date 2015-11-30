package io.supercharge.rxsnappy;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.snappydb.SnappydbException;

import io.supercharge.mock.DataGenerator;
import io.supercharge.mock.DummyData;
import io.supercharge.rxsnappy.exception.CacheExpiredException;

/**
 * Created by richardradics on 27/11/15.
 */
public class TimeBasedCacheTest extends AndroidTestCase {

    RxSnappyClient rxSnappyClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RxSnappy.init(getContext());
        rxSnappyClient = new RxSnappyClient();
    }

    @SmallTest
    public void testDataShouldBeAlwaysCachedOnce() throws SnappydbException {
        String key = "asd";

        DummyData dummyData = DataGenerator.generateNewDummyData();
        DummyData dummyData2 = DataGenerator.generateNewDummyData();

        rxSnappyClient.setObject(key, dummyData)
                .toBlocking().first();

        String[] keys = rxSnappyClient.db.findKeys(key);

        assertEquals(1, keys.length);


        rxSnappyClient.setObject(key, dummyData2)
                .toBlocking().first();

        String[] keys2 = rxSnappyClient.db.findKeys(key);

        assertEquals(1, keys2.length);


        DummyData actual = rxSnappyClient.getObject(keys2[0], DummyData.class)
                .toBlocking().first();


        assertEquals(dummyData2, actual);

    }

    @SmallTest
    public void testDataCacheIsInvalid() throws SnappydbException {
        String key = "asd";
        boolean exceptioThrown = false;

        DummyData dummyData = DataGenerator.generateNewDummyData();

        rxSnappyClient.setObject(key, dummyData)
                .toBlocking().first();

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {

        }

        try {
            rxSnappyClient.getObject(key, 1000L, DummyData.class)
                    .toBlocking().first();
        } catch (CacheExpiredException e) {
            exceptioThrown = true;
        }

        assertEquals(true, exceptioThrown);
    }

    @SmallTest
    public void testDataCacheIsValid() throws SnappydbException {
        String key = "asd";

        DummyData dummyData = DataGenerator.generateNewDummyData();

        rxSnappyClient.setObject(key, dummyData)
                .toBlocking().first();

        try {
            Thread.sleep(2500L);
        } catch (InterruptedException e) {

        }

        DummyData actual = rxSnappyClient.getObject(key, 5000L, DummyData.class)
                .toBlocking().first();

        assertEquals(dummyData, actual);
    }

    @SmallTest
    public void testKeyGenerator() throws Exception {

        String key = RxSnappyUtils.generateKey("123", "14123", DataGenerator.generateNewDummyData(), "adasd", DataGenerator.generateNewDummyData());

        assertNotNull(key);

        DummyData expected = DataGenerator.generateNewDummyData();

        rxSnappyClient.setObject(key, expected)
                .toBlocking().first();

        DummyData actual = rxSnappyClient.getObject(key, DummyData.class)
                .toBlocking().first();

        assertEquals(expected, actual);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        RxSnappy.closeDatabase();
        RxSnappy.destroyDatabase();
    }
}
