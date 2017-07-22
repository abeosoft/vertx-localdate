package localdatetest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.serviceproxy.ProxyHelper;
import localdatetest.services.DataService;


public class App extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {


        JsonObject mysql = config().getJsonObject("mysql");

        JDBCClient jdbcClient = JDBCClient.createShared(vertx, mysql);

        DataService.create(jdbcClient, ready -> {
            if (ready.succeeded()) {

                ProxyHelper.registerService(DataService.class, vertx, ready.result(), "data.service.address");

                vertx.deployVerticle(new HttpVerticle(), event -> {
                   if (event.succeeded()) {

                       startFuture.complete();
                   } else {
                       startFuture.fail(event.cause());
                   }
                });
            } else {
                startFuture.fail(ready.cause());
            }
        });
    }
}
