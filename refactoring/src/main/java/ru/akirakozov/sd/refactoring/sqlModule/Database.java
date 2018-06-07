package ru.akirakozov.sd.refactoring.sqlModule;

/**
 * Created by ospen on 11/2/2017.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static ru.akirakozov.sd.refactoring.sqlModule.Product.NAME;
import static ru.akirakozov.sd.refactoring.sqlModule.Product.PRICE;

/**
 * Created by ospen on 11/2/2017.
 */
public class Database {


    private static String DATABASE_CREATION_SQL = "CREATE TABLE IF NOT EXISTS PRODUCT" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " PRICE          INT     NOT NULL)";



    private static String GET_PRODUCTS_SQL  = "SELECT * FROM PRODUCT";
    private static String GET_SUM_SQL = "SELECT SUM(price) FROM PRODUCT";
    private static String GET_MAX_SQL = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
    private static String GET_MIN_SQL = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
    private static String GET_COUNT_SQL = "SELECT COUNT(*) FROM PRODUCT";
    private String databaseName;

    public Database(String dbName, boolean needDrop) {
        databaseName = "jdbc:sqlite:" + dbName;
        try (Connection c = DriverManager.getConnection(databaseName)) {

            try (Statement stmt = c.createStatement()) {
                stmt.executeUpdate(DATABASE_CREATION_SQL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Database(String databaseName) {
        this(databaseName, false);
    }

    private <R> R doRequest(String request, Function<ResultSet, R> function) {
        try (Connection c = DriverManager.getConnection(databaseName);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(request)) {

            return function.apply(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> readProducts(ResultSet rs) {
        try {
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                long price = rs.getLong("price");
                Product product = new Product(name, price);

                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProducts() {
        return doRequest(GET_PRODUCTS_SQL, this::readProducts);
    }

    private Long sumProducts(ResultSet rs) {
        try {
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new RuntimeException("next fail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Long getProductPriceSum() {
        return doRequest(GET_SUM_SQL, this::sumProducts);
    }

    private Product takeFirstProduct(ResultSet rs) {
        try {
            if (rs.next()) {
                String name = rs.getString("name");
                long price = rs.getLong("price");

                return new Product(name, price);
            } else {
                throw new RuntimeException("first product");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Product getProductPriceMax() {
        return doRequest(GET_MAX_SQL, this::takeFirstProduct);
    }

    public Product getProductPriceMin() {
        return doRequest(GET_MIN_SQL, this::takeFirstProduct);
    }

    private Long countProducts(ResultSet rs) {
        try {
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new RuntimeException("can't get count of products");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Long getCount() {
        return doRequest(GET_COUNT_SQL, this::countProducts);
    }



    public void insert(Product product) {
        try (Connection c = DriverManager.getConnection(databaseName);
             Statement stmt = c.createStatement()) {

            String sql = "INSERT INTO PRODUCT " +
                    "(" + NAME + ", " + PRICE + ") VALUES " +
                    "(\"" + product.name + "\", " + product.price + ")";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}