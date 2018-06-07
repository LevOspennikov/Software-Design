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
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String htmlResponce = WebPageCreator.encode(DatabaseSingleton.getInstance().getProducts());
        response.getWriter().append(htmlResponce);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}