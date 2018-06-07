package ru.akirakozov.sd.refactoring.sqlModule;

/**
 * Created by ospen on 11/16/2017.
 */
public class DatabaseSingleton {
    public static Database instance;
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database("test.db");
        }
        return instance;
    }
}
