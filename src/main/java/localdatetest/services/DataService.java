package localdatetest.services;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.jdbc.JDBCClient;
import localdatetest.domain.Person;

@ProxyGen
public interface DataService {

    static DataService createProxy(Vertx vertx, String address) {
        return new DataServiceVertxEBProxy(vertx, address);
    }

    static DataService create(JDBCClient jdbcClient, Handler<AsyncResult<DataService>> resultHandler) {
        return new DataServiceImpl(jdbcClient, resultHandler);
    }

    @Fluent
    DataService persist(Person person, Handler<AsyncResult<Person>> resultHandler);
}
