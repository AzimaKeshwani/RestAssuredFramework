package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static api.endpoints.Routes.post_url;
import static io.restassured.RestAssured.given;

//Created to perform CRUD operations - Create, Read, Update & Delete request to user API
public class UserEndPointsProp {

    //Load properties file - method created to get url from properties file
    static ResourceBundle getURL() {
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    /* Because its static it can be directly accessed.
    All validations are done as part of test case
    User payload is POJO class
    This is only implementation of test case
    User = name of payload that needs to be created and imported
    */
    public static Response createUser(User payload) {
        String post_url = getURL().getString("post_url");
        System.out.println(post_url);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);

        return response;
    }

    /* username/path parameter = required parameter that we are receiving from test case */
    public static Response readUser(String username) {
        String get_url = getURL().getString("get_url");
        Response response = given()
                .pathParams("username", username)
                .when()
                .get(get_url);
        return response;
    }

    /* 2 parameters are needed - Which user and data we are updating */
    public static Response updateUser(String username, User payload) {
        String update_url = getURL().getString("update_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", username)
                .body(payload)
                .when()
                .put(update_url);
        return response;

    }

    public static Response deleteUser(String username) {
        String delete_url = getURL().getString("delete_url");
        Response response = given()
                .pathParams("username", username)
                .when()
                .delete(delete_url);
        return response;
    }
}
