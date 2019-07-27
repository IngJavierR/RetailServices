package mx.com.gigantito.services.facade.impl;

import mx.com.gigantito.commons.to.UserTO;
import mx.com.gigantito.services.facade.IOrderFacade;
import mx.com.gigantito.services.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderFacade implements IOrderFacade {

    @Autowired
    private IOrderService orderService;

    public List<UserTO> getAllUsers() {
        return this.orderService.getUsers();
    }
}
