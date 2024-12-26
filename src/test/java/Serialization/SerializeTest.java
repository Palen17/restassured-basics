package Serialization;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SerializeTest {

    public static void main(String[] args) {


        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace addPlace = new AddPlace();

        addPlace.setAccuracy(50);
        addPlace.setAddress("29, side layout");
        addPlace.setLanguage("es");
        addPlace.setPhone_number("1234567");
        addPlace.setWebsite("https://examplewebsite.com");
        addPlace.setName("House");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        addPlace.setTypes(myList);

        Location l = new Location();
        l.setLat(-33.456789);
        l.setLng(36.452654);

        addPlace.setLocation(l);

        Response response = given().log().all()
                .queryParam("key", "qaclick123")
                .body(addPlace)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();

        String responseString = response.toString();
        System.out.println(responseString);

    }
}
