package com.system.slam.repository.impl;

import com.system.slam.repository.custom.CustomAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class CustomAuthorRepositoryImpl implements CustomAuthorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    

}