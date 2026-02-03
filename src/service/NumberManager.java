package service;
import model.NumberItem;

import java.util.ArrayList;
import java.util.List;

public class NumberManager {

    private List<NumberItem> list = new ArrayList<>();

    public void add(NumberItem item) {
        list.add(item);
    }

    public double getSum() {
        double sum = 0;
        for (NumberItem item : list) {
            sum += item.getValue();
        }
        return sum;
    }

    public NumberItem getMax() {
        if (list.isEmpty()) {
            return null;
        }
        NumberItem max = list.get(0);
        for (NumberItem item : list) {
            if (item.getValue() > max.getValue()) {
                max = item;
            }
        }
        return max;
    }

    public void display() {
        if (list.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        IO.println("Danh sách dữ liệu: ");
        for (NumberItem item : list) {
            System.out.println("ID: " + item.getId() + " | Value: " + item.getValue());
        }
    }

    public List<NumberItem> getList() {
        return list;
    }

    public void setList(List<NumberItem> list) {
        this.list = list;
    }
}
