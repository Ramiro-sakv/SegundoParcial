package com.ramiro.automation.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@DisplayName("Pruebas API de Restful Booker")
class RestfulBookerApiTests {

    @BeforeAll
    static void configureApi() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    @DisplayName("API-01 Crear token con credenciales validas")
    void createAuthenticationToken() {
        Map<String, String> credentials = Map.of(
                "username", "admin",
                "password", "password123");

        given()
            .contentType(ContentType.JSON)
            .header("Accept", "application/json")
            .body(credentials)
        .when()
            .post("/auth")
        .then()
            .statusCode(200)
            .body("token", not(blankOrNullString()));
    }

    @Test
    @DisplayName("API-02 Obtener lista de identificadores de reservas")
    void getBookingIds() {
        given()
            .header("Accept", "application/json")
        .when()
            .get("/booking")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    @DisplayName("API-03 Crear reserva con datos validos")
    void createBookingWithPost() {
        Map<String, Object> booking = bookingBody("POST");

        given()
            .contentType(ContentType.JSON)
            .header("Accept", "application/json")
            .body(booking)
        .when()
            .post("/booking")
        .then()
            .statusCode(200)
            .body("bookingid", greaterThan(0))
            .body("booking.firstname", equalTo("Ramiro"))
            .body("booking.lastname", equalTo("POST"));
    }

    @Test
    @DisplayName("API-04 Consultar la reserva creada")
    void createAndGetBookingById() {
        int bookingId = createBooking();

        given()
            .header("Accept", "application/json")
        .when()
            .get("/booking/{id}", bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Ramiro"))
            .body("lastname", equalTo("ConsultaID"))
            .body("totalprice", equalTo(150));
    }

    @Test
    @DisplayName("API-05 Consultar una reserva inexistente")
    void getNonexistentBooking() {
        given()
            .header("Accept", "application/json")
        .when()
            .get("/booking/{id}", Integer.MAX_VALUE)
        .then()
            .statusCode(404);
    }

    private int createBooking() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(bookingBody("ConsultaID"))
            .when()
                .post("/booking");

        response.then().statusCode(200);
        return response.jsonPath().getInt("bookingid");
    }

    private Map<String, Object> bookingBody(String lastName) {
        Map<String, String> dates = Map.of(
                "checkin", LocalDate.now().plusDays(10).toString(),
                "checkout", LocalDate.now().plusDays(15).toString());

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", "Ramiro");
        booking.put("lastname", lastName);
        booking.put("totalprice", 150);
        booking.put("depositpaid", true);
        booking.put("bookingdates", dates);
        booking.put("additionalneeds", "Breakfast");
        return booking;
    }
}
