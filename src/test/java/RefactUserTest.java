import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RefactUserTest {

    @Test
    public void aaverifyUserCanBeCreated() {
        User user = new User();
        user.setUsername("Andrii");
        user.setId(2255);
        new UserEndPoint()
                .postCreatUser(user)
                .then()
                .statusCode(200);

    }

    @Test
    public void abCreateUser(){
        User andrii = User.creatUser(44,"Andrii23");
        Response userResponse = new UserEndPoint()
                .postCreatUser(andrii);
        User userFromService = userResponse.body().as(User.class);
        Assert.assertNotNull(userFromService);
        Assert.assertNotNull(userFromService.getId());
    }


    @Test
    public void anverifyUserByName() {
        new UserEndPoint()
                .getUserByName("Andrii")
                .then()
                .assertThat()
                .log().body()
                .body(Matchers.notNullValue());
    }

    @Test
    public void bverifyStatusCodeByName() {
        new UserEndPoint()
                .getUserByName("Andrii")
                .then()
                .log().status()
                .statusCode(200);
    }

    @Test
    public void cverifyIdIsNotEmpty() {
        Response userResponse = new UserEndPoint()
                .getUserByName("Andrii");
        User user = userResponse
                .body()
                .as(User.class);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void dverifyDeleteUser() {
        new UserEndPoint()
                .deleteByName("Andrii")
                .then()
                .log().status()
                .statusCode(200);
    }

    @Test
    public void everifyNotExistingPetReturn404() {
        new UserEndPoint()
                .getUserByName("Andrii")
                .then()
                .log().body()
                .statusCode(404);
    }
//    @BeforeClass
//    public static void cleanUp() {
//        List<User> userList = new UserEndPoint()
//                .getUserByName("Andrii")
//                .body()
//                .jsonPath().getList("$", User.class);
//        for(User user : userList){
//            new UserEndPoint().deleteByName(user.getUsername());
//        }
//
//    }
}
