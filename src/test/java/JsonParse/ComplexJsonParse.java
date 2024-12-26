package JsonParse;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath js = new JsonPath(Payload.CoursePrice());

        int count = js.getInt("courses.size()");
        System.out.println(count);

        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        String titleFirstCourse = js.getString("courses[0].title");
        System.out.println(titleFirstCourse);

        String titleSecondCourse = js.getString("courses[1].title");
        System.out.println(titleSecondCourse);

        for (int i = 0; i < count; i++){
            String courseTitles = js.getString("courses["+i+"].title");
            System.out.println(courseTitles);
            System.out.println(js.getInt("courses["+i+"].price"));
        }

        for (int i = 0; i < count; i++){
            String courseTitles = js.getString("courses["+i+"].title");
            if (courseTitles.equalsIgnoreCase("RPA")){
                System.out.println(js.getInt("courses["+i+"].copies"));
                break;
            }
        }

    }
}
