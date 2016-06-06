package com.gjeanmart.mybankadvisor.core.webservice.impl;

import com.gjeanmart.mybankadvisor.core.webservice.UserRestService;
import com.gjeanmart.mybankadvisor.core.model.security.User;
import com.gjeanmart.mybankadvisor.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestServiceImpl extends AbstractRestServiceImpl<User, Long> implements UserRestService {

    @Autowired
    public UserRestServiceImpl(UserService userService) {
        super(userService);
    }
}
