package io.supercharge.mock;

import java.util.List;

/**
 * Created by richardradics on 29/11/15.
 */
public class MockedResponse {

    List<DummyData> data = DataGenerator.dummyDataGenerator(10);
    List<DummyData> data2 = DataGenerator.dummyDataGenerator(10);

}
