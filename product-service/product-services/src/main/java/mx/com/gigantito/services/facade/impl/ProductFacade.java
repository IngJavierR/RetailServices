package mx.com.gigantito.services.facade.impl;

import mx.com.gigantito.commons.to.UserTO;
import mx.com.gigantito.services.facade.IProductFacade;
import mx.com.gigantito.services.service.IProductService;
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
