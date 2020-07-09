package StepDefinitions.API;

import API.pojo.PetPojo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class PetStoreSteps {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Given("request type headers set to {string}")
    public void request_type_headers_set_to(String requestHeader) {
        requestSpecification = given().header("Accept", requestHeader);
    }

    @When("user executes {string} request")
    public void user_executes_GET_request(String requestType) {
        response = requestSpecification.request(requestType, "https://petstore.swagger.io/v2/pet/113");
    }

    @Then("the status code is {int}")
    public void the_status_code_is(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("content type header is {string}")
    public void content_type_header_is(String responseHeader) {
        validatableResponse = response.then().contentType(responseHeader);
    }

    @Then("user verifies {int}, {string}, {int} size")
    public void user_verifies_doggie_size(Integer id, String name, Integer tagSize) {
        PetPojo petPojo = validatableResponse.extract().as(PetPojo.class);
        Assert.assertEquals((int)id, petPojo.getId());
        Assert.assertEquals(name, petPojo.getName());
        Assert.assertEquals((int)tagSize, petPojo.getTags().size());
    }
}
