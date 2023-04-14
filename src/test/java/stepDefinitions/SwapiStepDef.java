package stepDefinitions;

import Utils.RequestSpecifications;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;

public class SwapiStepDef extends RequestSpecifications {

    ResponseSpecification resspec;
    RequestSpecification res;
    Response response;
    RequestSpecification req;
    String basePath = System.getProperty("user.dir") + "/src/test/java";

    @Given("StarWars API")
    public void star_wars_api() throws IOException {
        res = given().log().all().spec(requestSpecification());
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    }

    @Given("queryparam {string} is {string}")
    public void queryparam_is(String queryparam, String value) throws IOException {
        res = given().log().all().queryParam(queryparam, value).spec(requestSpecification());
    }

    @When("User makes {string} call to {string}")
    public void user_makes_call_to(String httpMethod, String api) {
//      For Logging Responses
//      response = makeRestCall(res, httpMethod, api).then().log().all().spec(resspec).extract().response();
//      response = res.when().get(apiResource.getResource()).then().log().all().spec(resspec).extract().response();
        response = makeRestCall(res, httpMethod, api);

    }

    @Then("{int} status code should be returned")
    public void status_code_should_be_returned(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @Then("{string} in response should be equal to {string}")
    public void in_response_should_be_equal_to(String key, String value) {
        JsonPath js = new JsonPath(response.asString());
        if (key.equalsIgnoreCase("films")) {
            String[] expectedFilmsArray = value.split(",");
            List<String> expectedFilmsNames = Arrays.asList(expectedFilmsArray);
            ArrayList<String> actualFilmsLinks = js.get(key);
            ArrayList<String> actualFilmsNames = new ArrayList<>();

            for (int i = 0; i < actualFilmsLinks.size(); i++) {
                String filmResponse = given().log().all().header("Content-Type", "application/json").
                        when().get(actualFilmsLinks.get(i)).
                        then().log().all().assertThat().statusCode(200).extract().response().asString();
                JsonPath js1 = new JsonPath(filmResponse);
                actualFilmsNames.add(js1.get("title"));
            }
            Assert.assertEquals(actualFilmsNames, expectedFilmsNames);
        } else if (key.equalsIgnoreCase("homeworld")) {
            String homeworldLink = js.get(key);
            String planetResponse = given().log().all().header("Content-Type", "application/json").
                    when().get(homeworldLink).
                    then().log().all().assertThat().statusCode(200).extract().response().asString();
            JsonPath js1 = new JsonPath(planetResponse);
            Assert.assertEquals(js1.get("name"), value);
        } else {
            Assert.assertEquals(js.get(key), value);
        }

    }

    @Then("response in {string} format should match {string}")
    public void response_in_format_should_match(String string, String jsonFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> type = new TypeReference<HashMap<String, Object>>() {
        };
        Map<String, Object> leftMap = mapper.readValue(new File(basePath + "/resources/" + jsonFilePath), type);
        Map<String, Object> rightMap = mapper.readValue(response.asString(), type);
        try {
            Assert.assertEquals(leftMap, rightMap);
        } catch (AssertionError e) {
            MapDifference<String, Object> difference = Maps.difference(leftMap, rightMap);
            System.out.println("Keys not matching: " + difference.entriesDiffering().keySet());
            Assert.assertTrue(false);
        }
    }
}
