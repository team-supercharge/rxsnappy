package io.supercharge.mock;

/**
 * Created by richardradics on 27/11/15.
 */
public class DummyData {

    private static long INSTANCES = 0;
    private String title;
    private Long id;
    private NestedData nestedData;

    public DummyData() {
        this.id = ++INSTANCES;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NestedData getNestedData() {
        return nestedData;
    }

    public void setNestedData(NestedData nestedData) {
        this.nestedData = nestedData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DummyData dummyData = (DummyData) o;

        if (title != null ? !title.equals(dummyData.title) : dummyData.title != null) return false;
        if (id != null ? !id.equals(dummyData.id) : dummyData.id != null) return false;
        return !(nestedData != null ? !nestedData.equals(dummyData.nestedData) : dummyData.nestedData != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (nestedData != null ? nestedData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DummyData{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", nestedData=" + nestedData +
                '}';
    }
}
