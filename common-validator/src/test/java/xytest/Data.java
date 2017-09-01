package xytest;

/**
 * @author JianLong
 * @date 2016/3/9 10:47
 */
public class Data {
    private int id;
    private String value;

    public Data(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
