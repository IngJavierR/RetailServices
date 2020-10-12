package mx.com.axity.services.facade;

import mx.com.axity.commons.to.UserTO;
import mx.com.axity.services.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class ProductFacadeTest extends BaseTest {

    @Test
    public void ShouldReturnAllUsers() {

        List<UserTO> users = this.productFacade.getAllUsers();

        Assert.assertEquals(1, users.size());
    }
}