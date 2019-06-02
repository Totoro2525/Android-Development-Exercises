package com.example.totoro.entity;

import java.io.Serializable;

/**
 * Created by Totoro on 2019/3/29.
 */

public class Book implements Serializable {
    private String book_name;
    private double book_price;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public Book(String book_name, double book_price) {
        super();
        this.book_name = book_name;
        this.book_price = book_price;
    }

    public String tostring() {
        return "" + book_name + "," + book_price + "]";
    }
}
