package localdatetest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import localdatetest.domain.Person;
import localdatetest.services.DataService;

public class HttpVerticle  extends AbstractVerticle {
    private DataService dataService;

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.requestHandler();
        Router restAPI = Router.router(vertx);

        // Create Body handler - thus we could use JSON in POST/PUT requests
        restAPI.route().handler(BodyHandler.create());

        restAPI.route(HttpMethod.POST, "/person")
                .handler(this::persistPerson);

        dataService = DataService.createProxy(vertx, "data.service.address");


        server.requestHandler(restAPI::accept).listen(4001);
    }

    private void persistPerson(RoutingContext routingContext) {

        Person person = routingContext.getBodyAsJson().mapTo(Person.class);
        dataService.persist(person, event -> {
            if (event.succeeded()) {
                routingContext.response().end(Json.encode(person));
            } else {
                // handle errors
            }
        });
    }
}
