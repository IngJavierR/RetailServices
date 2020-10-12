package mx.com.axity.services.service;

import mx.com.axity.commons.to.UserTO;
import mx.com.axity.services.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class OrderServiceTest extends BaseTest {

    @Test
    public void exampleTest() {

        List<UserTO> users = this.orderService.getUsers();

        Assert.assertEquals(1, users.size());
    }
}
