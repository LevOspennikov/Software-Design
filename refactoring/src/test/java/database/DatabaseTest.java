
package database;

import org.eclipse.jetty.util.ArrayTernaryTrie;
import org.junit.*;
import ru.akirakozov.sd.refactoring.sqlModule.Database;
import ru.akirakozov.sd.refactoring.sqlModule.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by ospen on 11/16/2017.
 */
public class DatabaseTest {

    public static String DB_NAME = "testTest.db";
    public Database db;
    public static int MAX_SIZE = 100;


    @Before
    public void deleteDb() {
        try {
            Files.deleteIfExists(Paths.get(DB_NAME));
        } catch (IOException exeption) {
            exeption.printStackTrace();
        }
        db = new Database(DB_NAME);
    }

    @Test
    public void testInsert() {
        for (int i = 1; i <= MAX_SIZE; i++) {
            db.insert(new Product("name" + i, i));
            assert db.getCount() == i;
        }
    }

    @Test
    public void testQuery() {
        for (int i = 1; i <= MAX_SIZE; i++) {
            db.insert(new Product("name" + i, i));
        }
        assert db.getProductPriceMax().getPrice() == MAX_SIZE;
        assert db.getProductPriceMin().getPrice() == 1;
        assert db.getProductPriceSum() == (MAX_SIZE + 1) * MAX_SIZE / 2;
    }

    @Test
    public void testGetProduct() {
        Set<String> products = new HashSet<>();
        for (int i = 1; i <= MAX_SIZE; i++) {
            Product product = new Product("name" + i, i);
            db.insert(product);
            products.add(product.encode());
        }
        List<Product> productsFromDb = db.getProducts();
        assert productsFromDb.size() == products.size();
        productsFromDb.forEach((product) -> {
            assert products.contains(product.encode());
        });

    }

}
