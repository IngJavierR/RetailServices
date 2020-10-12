package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.UserTO;
import mx.com.axity.services.facade.IProductFacade;
import mx.com.axity.services.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductFacade implements IProductFacade {

    @Autowired
    private IProductService productService;

    public List<UserTO> getAllUsers() {
        return this.productService.getUsers();
    }
}
