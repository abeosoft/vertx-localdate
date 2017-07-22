package localdatetest.services;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.UpdateResult;
import localdatetest.domain.Person;

public class DataServiceImpl implements DataService {

    private JDBCClient jdbcClient;

    public DataServiceImpl(JDBCClient jdbcClient, Handler<AsyncResult<DataService>> resultHandler) {

        this.jdbcClient = jdbcClient;

        resultHandler.handle(Future.succeededFuture(this));
    }


    @Override
    public DataService persist(Person person, Handler<AsyncResult<Person>> resultHandler) {

        String insertPerson = "INSERT INTO person (name, dob) VALUES (?, ?);";

        JsonArray params = new JsonArray()
                .add(person.getName())
                .add(person.getDob());

        jdbcClient.getConnection(connectionAsyncResult -> {
            if(connectionAsyncResult.succeeded()) {
                SQLConnection connection = connectionAsyncResult.result();

                connection.updateWithParams(insertPerson, params, result -> {
                    if (result.succeeded()) {
                        UpdateResult updateResult = result.result();
                        JsonArray keys = updateResult.getKeys();

                        Integer personId = keys.getInteger(0);
                        person.setPersonId(personId);

                        resultHandler.handle(Future.succeededFuture(person));
                    }
                });
            } else {
                // handle errors
            }
        });

        return this;
    }
}
