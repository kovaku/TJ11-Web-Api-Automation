package com.github.kovaku;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.Test;

import com.github.kovaku.domain.CreateEmployeeRequest;
import com.github.kovaku.domain.CreateEmployeeResponse;
import com.github.kovaku.domain.GetEmployeeResponse;
import com.github.kovaku.domain.UpdateEmployeeRequest;
import com.github.kovaku.domain.UpdateEmployeeResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

public class DummyRestApiExampleComTest {

    private static final String API_HOST = "http://dummy.restapiexample.com";
    private static final String API_BASE_PATH = "/api/v1";
    private static final String API_GET_ALL_PATH = "/employees";
    private static final String API_GET_EMPLOYEE_PATH = "/employee/{employeeId}";
    private static final String API_CREATE_EMPLOYEE_PATH = "/create";
    private static final String API_UPDATE_EMPLOYEE_PATH = "/update/{employeeId}";
    private static final String API_DELETE_EMPLOYEE_PATH = "/delete/{employeeId}";
    private static final String EMPLOYEE_ID_PARAM = "employeeId";

    static {
        RestAssured.registerParser("text/html", Parser.JSON);
    }


    @Test
    public void getAllTheEmployees() {
        getCommonRequestSpecifications()
        .when()
            .get(API_GET_ALL_PATH)
        .then()
            .log()
            .all()
        .assertThat()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("get-all-employees-response-schema.json"));
    }

    @Test
    public void getSpecificEmployee() {
        GetEmployeeResponse expectedEmployee = new GetEmployeeResponse(47234, "Zsolt Kovacs", 999L, 18, "");

        GetEmployeeResponse actualEmployee = getCommonRequestSpecifications()
            .when()
                .pathParam(EMPLOYEE_ID_PARAM, expectedEmployee.getId())
                .get(API_GET_EMPLOYEE_PATH)
            .then()
                .log()
                .all()
            .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("get-employee-response-schema.json"))
                .extract()
                .body()
                .as(GetEmployeeResponse.class);

        assertThat(actualEmployee, equalTo(expectedEmployee));
    }

    @Test
    public void createUpdateDeleteFlow() {
        //Create
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest("Test Account", 0L, 18);

        CreateEmployeeResponse createEmployeeResponse = given(getCommonRequestSpecifications())
                .body(createEmployeeRequest)
            .when()
                .post(API_CREATE_EMPLOYEE_PATH)
            .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateEmployeeResponse.class);

        //Update
        Integer employeeId = createEmployeeResponse.getId();

        UpdateEmployeeRequest updateEmployeeRequest = new UpdateEmployeeRequest(createEmployeeResponse.getName(), 100L, 19);
        UpdateEmployeeResponse updateEmployeeResponse = given(getCommonRequestSpecifications())
            .body(updateEmployeeRequest)
            .when()
                .pathParam(EMPLOYEE_ID_PARAM, employeeId)
                .put(API_UPDATE_EMPLOYEE_PATH)
            .then()
                .log()
                .all()
            .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UpdateEmployeeResponse.class);

        //Delete
        getCommonRequestSpecifications()
        .when()
            .pathParam(EMPLOYEE_ID_PARAM, employeeId)
            .delete(API_DELETE_EMPLOYEE_PATH)
        .then()
            .log()
            .all()
        .assertThat()
            .statusCode(200)
            .body(containsString("successfully"));
    }

    public RequestSpecification getCommonRequestSpecifications() {
        return given()
            .baseUri(API_HOST)
            .basePath(API_BASE_PATH)
            .log()
            .all()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON);
    }
}
