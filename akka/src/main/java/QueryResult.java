/**
 * Created by ospen on 3/15/2018.
 */
public class QueryResult {
    private final String result;

    private final String url;

    public QueryResult(String result, String url) {
        this.result = result;
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public String getUrl() {
        return url;
    }
}
