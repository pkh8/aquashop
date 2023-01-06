package com.shop.aqua.constant;

public enum Sorter {

    PRICE("가격순"), LASTER("최신순");

    private String sorter;
    Sorter(String sorter) {
        this.sorter = sorter;
    }

}
