import java.util.List;

/**
 * Created by ospen on 3/15/2018.
 */
abstract public class AbstractSearch {
    private String name;

    AbstractSearch(String name) {
        this.name = name;
    }

    abstract public List<QueryResult> search();

    abstract public String getName();
}
