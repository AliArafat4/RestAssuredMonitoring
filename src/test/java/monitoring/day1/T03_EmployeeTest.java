package monitoring.day1;

import base_urls.EmployeeUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class T03_EmployeeTest extends EmployeeUrl {
    /*
    Given
 https://dummy.restapiexample.com/api/v1/employees
 When
 User sends GET request
 Then
 Status code is 200
 And
 There are 24 employees
 And
 "Tiger Nixon" and "Garrett Winters" are among them
 And
 Highest age = 66
 And
 Youngest = "Tatyana Fitzpatrick"
 And
 Total salary = 6,644,770
     */

    @Test
    public void EmployeeTest() {
        spec.pathParams("first", "api").pathParams("second", "v1").pathParams("third", "employees");
        Response response = given(spec).get("/{first}/{second}/{third}");
        response.then().statusCode(200);
//        response.prettyPrint();
        //T1
        int numbOfEmployees = response.jsonPath().getInt("data.findAll{it.id}.size()");
        System.out.printf("number of employees %S\n", numbOfEmployees);
        Assert.assertEquals(numbOfEmployees, 24);
        
        //T2
        boolean tiger = response.jsonPath().getBoolean("data.any{it.employee_name == 'Tiger Nixon'}");
        boolean garrett = response.jsonPath().getBoolean("data.any{it.employee_name == 'Garrett Winters'}");
        Assert.assertTrue(tiger);
        Assert.assertTrue(garrett);

        //T3
        int highestAge = response.jsonPath().getInt("data.max{it.employee_age}.employee_age");
        System.out.println(highestAge);
        Assert.assertEquals(highestAge, 66);

        //T4
        String youngestName = response.jsonPath().getString("data.min{it.employee_age}.employee_name");
        System.out.println(youngestName);
        Assert.assertEquals(youngestName, "Tatyana Fitzpatrick");

        //T5
        List<String> allSalaries = response.jsonPath().getList("data.findAll{it.employee_salary}.employee_salary");

        int totalSalary = 0;
        for (String salary : allSalaries) {
            totalSalary += Integer.parseInt(salary);
        }
        System.out.println("" + totalSalary);
        Assert.assertEquals(totalSalary, 6644770);

    }
}