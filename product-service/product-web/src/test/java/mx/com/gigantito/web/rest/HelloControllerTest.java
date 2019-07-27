package mx.com.gigantito.web.rest;

import mx.com.gigantito.commons.to.UserTO;
import mx.com.gigantito.web.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class HelloControllerTest extends BaseTest {

    @Test
    public void exampleTest() {

        //this.entityManager.persist(new UserDO("Javier", "Rodriguez", 1));

        List<UserTO> users = this.productFacade.getAllUsers();

        Assert.assertEquals(1, users.size());
    }
}
