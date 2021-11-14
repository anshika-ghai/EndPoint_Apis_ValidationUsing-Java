package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {
	private static final String BASE_URL = "https://5f99522350d84900163b8737.mockapi.io/tech-test/articles";

	private static Response response;
	private static String jsonString;

	@Given("Api Endpoint 1 sends 200 status code")
	public void apiResponse() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		response = request.get(BASE_URL);
		Assert.assertEquals(200, response.getStatusCode());

	}

	@When("Api header")
	public void responseHeaders() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		// request.header("Content-Type", "application/xml");
		response = request.get(BASE_URL);
		System.out.println("headers are" + response.headers());
	}

	@When("I validate size of response body of api endpoint")
	public void responseSize() {
		jsonString = response.asString();
		System.out.println("This is json string" + JsonPath.from(jsonString).get());
		List<Map<String, String>> x = JsonPath.from(jsonString).get();
		System.out.println("ID is " + x.size());
		Assert.assertTrue(x.size() > 1);

	}

	@And("I validate content of response body")
	public void responseBody() {
		jsonString = response.asString();
		Assert.assertEquals(jsonString.contains("id"), true);

	}


}