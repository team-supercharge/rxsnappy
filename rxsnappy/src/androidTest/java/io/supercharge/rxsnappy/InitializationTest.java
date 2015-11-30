package io.supercharge.rxsnappy;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.snappydb.SnappydbException;

import io.supercharge.rxsnappy.exception.RxSnappyException;

/**
 * Created by richardradics on 27/11/15.
 */
public class InitializationTest extends AndroidTestCase {

    @SmallTest
    public void testDBInitAndDestroy() throws SnappydbException {

        RxSnappy.init(getContext());

        assertNotNull(RxSnappy.db);

        RxSnappy.closeDatabase();
        RxSnappy.destroyDatabase();

        assertNull(RxSnappy.db);

    }


    @SmallTest
    public void testClientCreateWithoutInit() {
        boolean exceptionThrown = false;

        try {
            RxSnappyClient rxSnappyClient = new RxSnappyClient();
        } catch (RxSnappyException rxSnappyException) {
            exceptionThrown = true;
        }

        assertEquals(true, exceptionThrown);

    }


}
