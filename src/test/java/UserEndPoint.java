import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndPoint {

    public Response postCreatUser(User user){
        return given()
                .body(user)
                .when()
                .post(UserConfig.CREATE_USER)
                .then().extract().response();
    }

    public Response getUserByName(String name){
        return given()
                .pathParam("username", name)
                .when()
                .get(UserConfig.USER_BY_NAME)
                .then().extract().response();
    }

    public Response updateUser(User user){
        return given()
                .body(user)
                .when()
                .put(UserConfig.CREATE_USER)
                .then().extract().response();
    }

    public Response deleteByName(String name){
        return given()
                .pathParam("username", name)
                .when()
                .delete(UserConfig.USER_BY_NAME)
                .then().extract().response();
    }



    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(UserConfig.USERSTORE_BASE_URL)
                .contentType(ContentType.JSON);
    }
}
