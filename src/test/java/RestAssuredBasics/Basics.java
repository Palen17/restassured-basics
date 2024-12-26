package RestAssuredBasics;

import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    public static void main(String[] args) throws IOException {

        //Add place -> Update place -> Get place to validate

        //given - all input details
        //when - Submit the api - resource, http method
        //Then - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //add place
       String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); //for parsing json
        String placeID = js.getString("place_id");

        System.out.println(placeID);

        //update place

        String newAddress = "70 Summer walk, USA";

        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("c://"))))
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //get place
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeID)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath jsGetPlace = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAdress = jsGetPlace.getString("address");
        System.out.println(actualAdress);
        Assert.assertEquals(actualAdress, newAddress);
    }
}
