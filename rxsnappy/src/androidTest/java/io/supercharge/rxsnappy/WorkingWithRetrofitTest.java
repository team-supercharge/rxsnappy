package io.supercharge.rxsnappy;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import io.supercharge.mock.DataGenerator;
import io.supercharge.mock.DummyData;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by richardradics on 28/11/15.
 */
public class WorkingWithRetrofitTest extends AndroidTestCase {

    MockWebServer mockWebServer;
    RxSnappyClient rxSnappyClient;
    TestRestAdapter testRestAdapter;

    private interface TestRestAdapter {

        @POST("/{brand}")
        Observable<DummyData> getDummyData(@Header("Auth") String token, @Body DummyData requestData);

    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RxSnappy.init(getContext());
        mockWebServer = new MockWebServer();
        mockWebServer.start(9812);

        OkHttpClient okHttpClient = new OkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(mockWebServer.getUrl("/").toString())
                .build();

        testRestAdapter = restAdapter.create(TestRestAdapter.class);

        rxSnappyClient = new RxSnappyClient();
    }

    @SmallTest
    public void testDataIsMissingThanDownload() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\":11231,\"nestedData\":{\"id\":1,\"name\":\"NesteData 1\"},\"title\":\"DummyData 1\"}"));

        DummyData requestObj = DataGenerator.generateNewDummyData();

        final String key = RxSnappyUtils.generateKey("test", requestObj);


        rxSnappyClient.getObject(key, 3000L, DummyData.class)
                .onErrorResumeNext(testRestAdapter.getDummyData("test", requestObj)
                        .doOnNext(new Action1<DummyData>() {
                            @Override
                            public void call(DummyData dummyData) {
                                rxSnappyClient.setObject(key, dummyData)
                                        .toBlocking().first();
                            }
                        })).toBlocking().first();


        DummyData dummyData2 = rxSnappyClient.getObject(key, 10000L, DummyData.class)
                .onErrorResumeNext(testRestAdapter.getDummyData("test", requestObj))
                .doOnNext(new Action1<DummyData>() {
                    @Override
                    public void call(DummyData dummyData) {
                        rxSnappyClient.setObject(key, dummyData);
                    }
                }).toBlocking().first();

        assertEquals(11231L, dummyData2.getId().longValue());
        assertEquals(mockWebServer.getRequestCount(), 1);
    }

    @SmallTest
    public void testDataIsMissingThanDownloadAndCacheInvalid() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\":11231,\"nestedData\":{\"id\":1,\"name\":\"NesteData 1\"},\"title\":\"DummyData 1\"}"));
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\":123,\"nestedData\":{\"id\":1,\"name\":\"NesteData 1\"},\"title\":\"DummyData 1\"}"));

        DummyData requestObj = DataGenerator.generateNewDummyData();

        final String key = RxSnappyUtils.generateKey("test", requestObj);


        rxSnappyClient.getObject(key, 3000L, DummyData.class)
                .onErrorResumeNext(testRestAdapter.getDummyData("test", requestObj))
                .doOnNext(new Action1<DummyData>() {
                    @Override
                    public void call(DummyData dummyData) {
                        rxSnappyClient.setObject(key, dummyData)
                                .toBlocking().first();
                    }
                }).toBlocking().first();


        Thread.sleep(3000L);

        DummyData dummyData2 = rxSnappyClient.getObject(key, 1000L, DummyData.class)
                .onErrorResumeNext(testRestAdapter.getDummyData("test", requestObj))
                .doOnNext(new Action1<DummyData>() {
                    @Override
                    public void call(DummyData dummyData) {
                        rxSnappyClient.setObject(key, dummyData);
                    }
                }).toBlocking().first();

        assertEquals(123L, dummyData2.getId().longValue());
        assertEquals(mockWebServer.getRequestCount(), 2);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mockWebServer.shutdown();
        RxSnappy.closeDatabase();
        RxSnappy.clearDatabase();
    }
}
