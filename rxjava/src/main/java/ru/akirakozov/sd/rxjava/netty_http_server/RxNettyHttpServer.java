package ru.akirakozov.sd.rxjava.netty_http_server;

import com.mongodb.rx.client.Success;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServer;
import ru.akirakozov.sd.rxjava.reactive_mongo_driver.Good;
import ru.akirakozov.sd.rxjava.reactive_mongo_driver.ReactiveMongoDriver;
import ru.akirakozov.sd.rxjava.reactive_mongo_driver.User;
import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ospennikov
 */
public class RxNettyHttpServer {

    public static void main(final String[] args) {
        HttpServer
                .newServer(8080)
                .start((req, resp) -> {
                    Observable<String> response;
                    String name = req.getDecodedPath().substring(1);
                    Map<String, List<String>> queryParam = req.getQueryParameters();
                    switch (name) {
                        case "addUser":
                            response = addUser(queryParam);
                            resp.setStatus(HttpResponseStatus.OK);
                            break;
                        case "addGood":
                            response = addGood(queryParam);
                            resp.setStatus(HttpResponseStatus.OK);
                            break;
                        case "getGoods":
                            response = getGoods(queryParam);
                            resp.setStatus(HttpResponseStatus.OK);
                            break;
                        default:
                            response = Observable.just("Wrong command");
                            resp.setStatus(HttpResponseStatus.BAD_REQUEST);
                    }

                    return resp.writeString(response);
                })
                .awaitShutdown();
    }

    public static Observable<String> addGood(Map<String, List<String>> queryParam) {
        ArrayList<String> needValues = new ArrayList<>(Arrays.asList("id", "name", "eur", "dol", "rub"));
        String error = checkAndFormError(queryParam, needValues);
        if (!error.equals("")) {
            return Observable.just(error);
        }
        Integer id = new Integer(queryParam.get("id").get(0));
        ;
        String name = queryParam.get("name").get(0);

        String eur = queryParam.get("eur").get(0);
        String rub = queryParam.get("rub").get(0);
        String dol = queryParam.get("dol").get(0);

        if (ReactiveMongoDriver.addGoods(new Good(id, name, rub, eur, dol)) == Success.SUCCESS) {
            return Observable.just("SUCCESS");
        } else {
            return Observable.just("Can't add to db");
        }
    }

    private static String checkAndFormError(Map<String, List<String>> queryParam, List<String> needValues) {
        String result = "";
        for (String value : needValues) {
            if (!queryParam.containsKey(value)) {
                if (!"".equals(result)) {
                    result += ", " + value;
                } else {
                    result += "Can't find" + value;
                }
            }
        }
        return result;
    }

    public static Observable<String> addUser(Map<String, List<String>> queryParam) {
        ArrayList<String> needValues = new ArrayList<>(Arrays.asList("id", "currency", "name"));
        String error = checkAndFormError(queryParam, needValues);
        if (!error.equals("")) {
            return Observable.just(error);
        }
        Integer id = new Integer(queryParam.get("id").get(0));
        
        String name = queryParam.get("name").get(0);
        String currency = queryParam.get("currency").get(0);
        if (ReactiveMongoDriver.createUser(new User(id, name, currency)) == Success.SUCCESS) {
            return Observable.just("SUCCESS");
        } else {
            return Observable.just("Can't add to db");
        }
    }

    public static Observable<String> getGoods(Map<String, List<String>> queryParam) {
        ArrayList<String> needValues = new ArrayList<>(Arrays.asList("id"));
        String error = checkAndFormError(queryParam, needValues);
        if (!error.equals("")) {
            return Observable.just(error);
        }
        Integer id = new Integer(queryParam.get("id").get(0));
        return toJson(ReactiveMongoDriver.getAllGoods(id), id);
    }

    public static Observable<String> toJson(Observable<String> goods, Integer id) {
        return Observable.just("{ id = " + id + ", goods = [").concatWith(goods).concatWith(Observable.just("]}"));
    }
}
