package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

//Created to perform CRUD operations - Create, Read, Update & Delete request to user API
public class UserEndPoints {

    /* Because its static it can be directly accessed.
    All validations are done as part of test case
    User payload is POJO class
    This is only implementation of test case
    User = name of payload that needs to be created and imported
    */
    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        return response;
    }

    /* username/path parameter = required parameter that we are receiving from test case */
    public static Response readUser(String username) {
        Response response = given()
                .pathParams("username", username)
                .when()
                .get(Routes.get_url);
        return response;
    }

    /* 2 parameters are needed - Which user and data we are updating */
    public static Response updateUser(String username, User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", username)
                .body(payload)
                .when()
                .put(Routes.update_url);
        return response;

    }

    public static Response deleteUser(String username) {
        Response response = given()
                .pathParams("username", username)
                .when()
                .delete(Routes.delete_url);
        return response;
    }
}
