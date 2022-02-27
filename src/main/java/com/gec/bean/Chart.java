package com.gec.bean;

import java.util.ArrayList;
import java.util.List;

public class Chart {
    private String name;
    private ArrayList<Double> data;

    public Chart() {
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getDate() {
        return data;
    }

    public void setDate(ArrayList<Double> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
