package com.benjamin.wte.objects;

import java.util.Date;

/**
 * @Auther Benjamin(Jingyi Li) Li
 * @Email jili@student.unimelb.edu.au
 * @ID 961543
 * @Date 2019-04-10 21:25
 */
public class Item {
    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    private String Item;
    private Integer Amount;
    private String Unit;
    private String Date;

}
