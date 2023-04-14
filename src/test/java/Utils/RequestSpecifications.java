package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import resources.APIResources;

import java.io.IOException;

public class RequestSpecifications extends Utils {
    public static RequestSpecification req;
    public static RequestSpecification res;
    public static ResponseSpecification resspec;
    public static Response response;

    public static RequestSpecification requestSpecification() throws IOException {
        req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).setContentType(ContentType.JSON).build();
        return req;
    }

    public static Response makeRestCall(RequestSpecification req, String httpMethod, String api) {
        APIResources apiResource = APIResources.valueOf(api);
        switch (httpMethod) {
            case "GET": {
                response = req.when().get(apiResource.getResource());
                return response;
            }
            case "DELETE": {
                response = req.when().delete(apiResource.getResource());
                return response;
            }
            case "POST": {
                response = req.when().post(apiResource.getResource());
                return response;
            }
            case "PUT": {
                response = req.when().put(apiResource.getResource());
                return response;
            }


        }
        return response;
    }
}