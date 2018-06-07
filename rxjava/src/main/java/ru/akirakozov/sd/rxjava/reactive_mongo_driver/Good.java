package ru.akirakozov.sd.rxjava.reactive_mongo_driver;

import org.bson.Document;

public class Good {
    public final int id;
    public final String name;
    public final String rub;
    public final String eur;
    public final String dol;

    public Good(Document doc) {
        this(doc.getInteger("id"), doc.getString("name"), doc.getString("rub"), doc.getString("eur"), doc.getString("dol"));
    }

    public Good(int id, String name, String rub, String eur, String dol) {
        this.id = id;
        this.name = name;
        this.rub = rub;
        this.eur = eur;
        this.dol = dol;
    }

    public Document getDocument() {
        return new Document("id", id).append("name", name).append("rub", rub).append("dol", dol).append("eur", eur);
    }

    public String toString(String currency) {
        String price = "";
        switch (currency) {
            case "rub":
                price = "rub= '" + rub;
                break;
            case "eur":
                price = "eur= '" + eur;
                break;
            case "dol":
                price = "dol= '" + dol;
                break;
        }

        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", " + price + '\'' +
                '}';
    }
}
