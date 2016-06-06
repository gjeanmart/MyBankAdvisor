package com.gjeanmart.mybankadvisor.core.dao;

import com.gjeanmart.mybankadvisor.core.model.security.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends AbstractDao<User, Long> {

}
