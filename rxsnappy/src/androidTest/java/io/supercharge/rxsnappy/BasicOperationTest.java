package io.supercharge.rxsnappy;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.snappydb.SnappydbException;

import java.util.List;

import io.supercharge.mock.DataGenerator;
import io.supercharge.mock.DummyData;
import io.supercharge.mock.MockedResponse;
import io.supercharge.rxsnappy.exception.RxSnappyException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class BasicOperationTest extends AndroidTestCase {

    private static final String test_key = "test-asd";


    RxSnappyClient rxSnappyClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RxSnappy.init(getContext());
        rxSnappyClient = new RxSnappyClient();
    }

    @SmallTest
    public void testResetDatabase() throws SnappydbException {
        String key = "asd";

        DummyData dummyData = DataGenerator.generateNewDummyData();

        rxSnappyClient.setObject(key, dummyData)
                .toBlocking().first();


        String[] keys = rxSnappyClient.db.findKeys(key);

        assertEquals(1, keys.length);


        RxSnappy.resetDatabase(getContext());

        keys = rxSnappyClient.db.findKeys(key);

        assertEquals(0, keys.length);

    }


    @SmallTest
    public void testBooleanValue() throws Exception {
        Boolean expected = Boolean.TRUE;


        rxSnappyClient.setBoolean(test_key, expected)
                .toBlocking().first();

        Boolean actual = rxSnappyClient.getBoolean(test_key)
                .toBlocking().first();

        assertEquals(expected, actual);
    }

    @SmallTest
    public void testSaveNullBoolean() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setBoolean(test_key, null)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testSaveNullKeyBoolean() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setBoolean(null, true)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testBooleanMissingFromCache() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.getBoolean("test123")
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testStringValue() throws Exception {
        String expected = "test string";


        rxSnappyClient.setString(test_key, expected)
                .toBlocking().first();

        String actual = rxSnappyClient.getString(test_key)
                .toBlocking().first();

        assertEquals(expected, actual);

    }

    @SmallTest
    public void testSaveNullString() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setString(test_key, null)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testSaveNullKeyString() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setString(null, "asd")
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testStringMissingFromCache() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.getString("test123")
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testLongValue() throws Exception {
        Long expected = 12314124L;

        rxSnappyClient.setLong(test_key, expected)
                .toBlocking().first();

        Long actual = rxSnappyClient.getLong(test_key)
                .toBlocking().first();

        assertEquals(expected, actual);

    }

    @SmallTest
    public void testSaveNullKeyLong() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setLong(null, 1231L)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testSaveNullLong() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setLong(test_key, null)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testLongMissingFromCache() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.getLong("test123")
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testIntegerValue() throws Exception {
        Integer expected = 12314;


        rxSnappyClient.setInteger(test_key, expected)
                .toBlocking().first();

        Integer actual = rxSnappyClient.getInteger(test_key)
                .toBlocking().first();

        assertEquals(expected, actual);


    }


    @SmallTest
    public void testSaveNullKeyInteger() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setInteger(null, 123)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testSaveNullInteger() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setInteger(test_key, null)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testIntegerMissingFromCache() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.getInteger("test123")
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testStringListValue() throws Exception {
        List<String> expected = DataGenerator.stringListGenerator(10);


        rxSnappyClient.setStringList(test_key, expected)
                .toBlocking().first();

        List<String> actual = rxSnappyClient.getStringList(test_key)
                .toBlocking().first();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);


    }

    @SmallTest
    public void testSaveNullKeyStringList() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setStringList(null, DataGenerator.stringListGenerator(10))
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testSaveNullStringList() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setStringList(test_key, null)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testStringListMissingFromCache() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.getStringList("test123")
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testListValue() throws Exception {
        List<DummyData> expected = DataGenerator.dummyDataGenerator(10);


        rxSnappyClient.setList(test_key, expected)
                .toBlocking().first();

        List<DummyData> actual = rxSnappyClient.getList(test_key, DummyData.class)
                .toBlocking().first();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);


    }

    @SmallTest
    public void testSaveNullKeyObjectList() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setList(null, DataGenerator.dummyDataGenerator(3))
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testSaveNullObjectList() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setList(test_key, null)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testObjectListMissingFromCache() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.getList("test123", DummyData.class)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testObjectValue() throws Exception {
        DummyData expected = DataGenerator.generateNewDummyData();


        rxSnappyClient.setObject(test_key, expected)
                .toBlocking().first();

        DummyData actual = rxSnappyClient.getObject(test_key, DummyData.class)
                .toBlocking().first();

        assertEquals(expected, actual);

    }

    @SmallTest
    public void testComplesObjectSave() throws Exception {
        MockedResponse mockedResponse = new MockedResponse();

        MockedResponse actual = rxSnappyClient.setObject(test_key, mockedResponse)
                .toBlocking().first();

        assertEquals(mockedResponse, actual);

    }


    @SmallTest
    public void testSaveNullObject() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setObject(test_key, null)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testSaveNullKeyObject() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.setObject(null, DataGenerator.generateNewDummyData())
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testObjectMissingFromCache() throws Exception {
        boolean isExceptionThrown = false;

        try {
            rxSnappyClient.getObject("test123", DummyData.class)
                    .toBlocking().first();
        } catch (RxSnappyException ex) {
            isExceptionThrown = true;
        }
        assertEquals(true, isExceptionThrown);
    }

    @SmallTest
    public void testCountFindExistsRemoveCache() {
        String key = "asdasd";

        rxSnappyClient.setObject(key, "asd").toBlocking().first();

        boolean exists = rxSnappyClient.isCached(key).toBlocking().first();
        boolean notexists = rxSnappyClient.isCached("testnot").toBlocking().first();

        assertTrue(exists);
        assertFalse(notexists);

        int cnt = rxSnappyClient.countKeys(key).toBlocking().first();
        assertTrue(cnt == 1);

        rxSnappyClient.deleteCache(key).toBlocking().first();

        String[] cnt1 = rxSnappyClient.findKeys(key).toBlocking().first();

        assertTrue(cnt1.length == 0);

    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        RxSnappy.closeDatabase();
        RxSnappy.destroyDatabase();


    }
}