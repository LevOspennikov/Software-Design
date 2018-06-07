package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.htmlHelper.WebPageCreator;
import ru.akirakozov.sd.refactoring.sqlModule.Database;
import ru.akirakozov.sd.refactoring.sqlModule.DatabaseSingleton;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        Database database = DatabaseSingleton.getInstance();
        String res;
        switch (command) {
            case "max":
                res = "<h1>Product with max price: </h1>\n";
                res += database.getProductPriceMax().encode();
                break;

            case "min":
                res = "<h1>Product with min price: </h1>\n";
                res += database.getProductPriceMin().encode();
                break;

            case "sum":
                res = "Summary price: \n";
                res += database.getProductPriceSum().toString() + "\n";
                break;

            case "count":
                res = "Number of products:\n";
                res += database.getCount();
                break;

            default:
                res = "Unknown command: " + command;
                break;
        }

        response.getWriter().print(WebPageCreator.createWebPage(res));

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}