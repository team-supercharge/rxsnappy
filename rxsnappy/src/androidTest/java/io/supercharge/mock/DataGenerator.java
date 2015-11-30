package io.supercharge.mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richardradics on 27/11/15.
 */
public class DataGenerator {

    public static List<String> stringListGenerator(int datacount) {
        List<String> dummyDatas = new ArrayList<>();
        for (int i = 0; i < datacount; i++) {
            dummyDatas.add("String: " + i);
        }
        return dummyDatas;
    }


    public static List<DummyData> dummyDataGenerator(int datacount) {
        List<DummyData> dummyDatas = new ArrayList<>();
        for (int i = 0; i < datacount; i++) {
            dummyDatas.add(generateNewDummyData());
        }
        return dummyDatas;
    }

    public static DummyData generateNewDummyData() {
        NestedData nestedData = new NestedData();
        nestedData.setName("NesteData " + nestedData.getId());
        nestedData.setLatitude(1231.12D);
        nestedData.setLongitude(46.1231D);
        DummyData dummyData = new DummyData();
        dummyData.setTitle("DummyData " + dummyData.getId());
        dummyData.setNestedData(nestedData);
        return dummyData;
    }
}
