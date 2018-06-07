package ru.akirakozov.sd.refactoring.sqlModule;

import ru.akirakozov.sd.refactoring.htmlHelper.Encodable;

/**
 * Created by ospen on 11/2/2017.
 */
public class Product implements Encodable {
    public static final String NAME = "NAME";
    public static final String PRICE = "PRICE";

    String name;
    long price;

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String encode() {
        return name + "\t" + price + "</br>";
    }
}
