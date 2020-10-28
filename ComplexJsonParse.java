package files;

import java.util.Arrays;
import java.util.List;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {
		JsonPath js = new JsonPath(Payload1.parseJson());
		
		int count = js.getInt("courses.size()");
		int purchasePrice = js.get("dashboard.purchaseAmount");
		String courseTitle = js.get("courses[0].title");
		int noOfCopiesRPA = js.getInt("courses[2].copies");
		List<Integer> listOfCoursePrices = js.getList("courses.price");
		List<Integer> listOfCourseCopies = js.getList("courses.copies");
		
		
		
		for (int i = 0; i < count; i++) {
			String allCourses = js.get("courses["+i+"].title");
			System.out.println(allCourses);
			int coursePrices = js.get("courses["+i+"].price");
			System.out.println(coursePrices);
			
		}
		
		
		System.out.println("The # of courses = "+ count);
		System.out.println("The purchase price is :" + purchasePrice);
		System.out.println("The first course title is :" + courseTitle);
		System.out.println("Cypress book has sold #"+ noOfCopiesRPA + " copies" );
		System.out.println(listOfCoursePrices);
		System.out.println(listOfCourseCopies);
		
		
		
		 
		
	}
	
		
}
	
