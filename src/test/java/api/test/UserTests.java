package api.test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/* only for user model */
public class UserTests {

    Faker faker;
    User userPayload;
    String id;

    /* data is created using faker library which is passed to POJO class and then post request
    userPayload is to pass data to pojo class and then user object is created.
    hashcode - randomly generates number */

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPhone(faker.phoneNumber().phoneNumber());

    }

    /* We have already used routes and user so that is not needed.
    We can directly call createUser directly since it is static.
     We are going to use same data we created, and it will be stored in response.
     Once stored as response you can do all validations.
     */

    @Test(priority = 1)
    public void testPostUser() {
        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
        id = response.jsonPath().get("message");
    }

    /* we are sending same data that was generated earlier.
    We are referring payload using this and getting username */

    @Test(priority = 2)
    public void testGetUserByName() {
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        String responseID = response.jsonPath().get("id").toString();
        String firstName = response.jsonPath().get("firstName").toString();
        String lastName = response.jsonPath().get("lastName").toString();
        String email = response.jsonPath().get("email").toString();
        Assert.assertEquals(id, responseID);
        Assert.assertEquals(this.userPayload.getFirstName(), firstName);
        Assert.assertEquals(this.userPayload.getLastName(), lastName);
        Assert.assertEquals(this.userPayload.getEmail(), email);

    }

    @Test(priority = 4)
    public void deleteUser() {
        //Verify user exist
        testGetUserByName();

        //Delete
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        String usernameDeleted = response.jsonPath().get("message").toString();
        Assert.assertEquals(usernameDeleted, userPayload.getUsername());
    }

    @Test(priority = 3)
    public void updateUser() {
        //Update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
        String responseId = response.jsonPath().get("message").toString();
        Assert.assertEquals(id, responseId);

        //Checking data after update
        testGetUserByName();
    }
}

