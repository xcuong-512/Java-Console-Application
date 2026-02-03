package model;

public class NumberItem {
    private  double value;
    private int id;

    public NumberItem(int id, double value) {
        this.id = id;
        this.value = value;
    }
    public int getId() {
        return id;
    }
    public double getValue() {
        return value;
    }

}
