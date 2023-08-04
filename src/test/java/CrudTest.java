import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class CrudTest {

    @Test
    public void creatUser() {
        // Given
        User artem = User.creatUser(5555, "Artem");
        new UserEndPoint().postCreatUser(artem);
        String findUserName = artem.getUsername();
        // When
        User userFromService = new UserEndPoint().getUserByName(findUserName).as(User.class);
        // Then
        Assert.assertNotNull(userFromService.getId());


//        SoftAssertions assertions = new SoftAssertions();
//        assertions.assertThat(userFromService.getId()).as("Id").isEqualTo(artem.getId());
//        assertions.assertThat(userFromService.getUsername()).as("Username").isEqualTo(artem.getUsername());
//        assertions.assertAll();

    }

    @Test
    public void updateUser() {
        // Given
        User artem = User.creatUser(5555, "Artem");
        new UserEndPoint().postCreatUser(artem);
        artem.setId(1);
        String findUserName = artem.getUsername();
        // When
        new UserEndPoint().updateUser(artem);
        User userFromService = new UserEndPoint().getUserByName(findUserName).as(User.class);
        // Then
        Assert.assertNotNull(userFromService.getId());
    }

    @Test
    public void deleteUser() {
        // Given
        User artem = User.creatUser(55552, "Artem2");
        new UserEndPoint().postCreatUser(artem);
        String findUserName = artem.getUsername();
        // When
        User userFromService = new UserEndPoint().getUserByName(findUserName).as(User.class);
        // Then
        new UserEndPoint().deleteByName(findUserName);
        Assert.assertNotNull(userFromService.getUsername());
    }
//    @BeforeClass
//    public static void cleanUp() {
//        List<User> userList = new UserEndPoint()
//                .getUserByName("Artem")
//                .body()
//                .jsonPath().getList("$", User.class);
//        for(User user : userList){
//            new UserEndPoint().deleteByName(user.getUsername());
//        }
//
//    }
}
