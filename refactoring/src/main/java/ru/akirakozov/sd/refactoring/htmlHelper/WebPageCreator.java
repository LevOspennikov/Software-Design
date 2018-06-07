package ru.akirakozov.sd.refactoring.htmlHelper;

import java.util.List;

/**
 * Created by ospen on 11/2/2017.
 */
public class WebPageCreator {
    public static <T extends Encodable> String encode(List<T> list) {
        String result = "";
        for (T element: list) {
            result += element.encode();
        }
        return createWebPage(result);
    }

    public static String createWebPage(String s) {
        return "<html><body>" + s + "</body></html>";
    }
}
