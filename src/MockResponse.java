import java.util.List;

import org.testng.Assert;

import java.util.*;

import files.PayLoad;
import io.restassured.path.json.JsonPath;

public class MockResponse {

	public static void main(String[] args)
	{
/*
 1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount
 */
		
		JsonPath jp = new JsonPath(PayLoad.course_payload());
		
		int courses_count = jp.getInt("courses.size()");
		
		List<Map<String,String>> s= jp.get("courses");
		
		for(Map<String, String> m : s)
		{
			System.out.println(m);
		}
		
		
		System.out.println(courses_count);
		
		int purchase_amount = jp.getInt("dashboard.purchaseAmount");
		
		System.out.println(purchase_amount);
		
		String first_course = jp.get("courses[0].title");
		System.out.println("********"+jp.get("courses[0].title").toString()+"***********");
		
		
		System.out.println(first_course);
		
		for(int i=0;i<courses_count;i++)
		{
			String course = jp.get("courses["+i+"].title");
			int price = jp.get("courses["+i+"].price");
			System.out.println(course+" "+price);
		}
		
		for(int i=0;i<courses_count;i++)
		{
			String course = jp.get("courses["+i+"].title");
			if(course.equalsIgnoreCase("RPA")) {
				System.out.println(jp.get("courses["+i+"].copies").toString());
				break;
				}
		}
		
		int total = 0;
		for(int i=0;i<courses_count;i++)
		{
			int price = jp.get("courses["+i+"].price");
			int copies = jp.get("courses["+i+"].copies");
			total += price * copies; 

		}
		System.out.println(total);
		Assert.assertEquals(purchase_amount, total);
	}
}
