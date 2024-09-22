package api.test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userName, String firstName, String lastName, String email, String password, String phoneNumber) {
        User userPayload = new User();
        Faker faker = new Faker();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPhone(phoneNumber);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        

    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUserByName(String userName) {
        Response response = UserEndPoints.readUser(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) {
        // First, verify that the user exists
        Response getResponse = UserEndPoints.readUser(userName);
        Assert.assertEquals(getResponse.getStatusCode(), 200, "User should exist before deleting");

        // Then, attempt to delete the user
        Response deleteResponse = UserEndPoints.deleteUser(userName);
        deleteResponse.then().log().all();
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Expected status for deletion");
    }

}
