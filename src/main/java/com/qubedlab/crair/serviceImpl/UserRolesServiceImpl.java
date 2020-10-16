package com.qubedlab.crair.serviceImpl;

import com.qubedlab.crair.models.UserRoles;
import com.qubedlab.crair.repository.UserRolesRepository;
import com.qubedlab.crair.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    UserRolesService userRolesService;
    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    UserRoles userRoles;

    @Override
    public UserRoles save(UserRoles UserRoles) {
        return userRolesRepository.save(userRoles);
    }
}
