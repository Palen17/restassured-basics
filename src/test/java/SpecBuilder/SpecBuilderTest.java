package SpecBuilder;

import Serialization.AddPlace;
import Serialization.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

    public static void main(String[] args) {


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

        ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        RequestSpecification res = given().spec(req).body(addPlace);

        Response response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);

    }
}
