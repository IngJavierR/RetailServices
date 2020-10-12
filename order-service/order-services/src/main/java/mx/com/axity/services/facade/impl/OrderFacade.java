package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.UserTO;
import mx.com.axity.services.facade.IOrderFacade;
import mx.com.axity.services.service.IOrderService;
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
