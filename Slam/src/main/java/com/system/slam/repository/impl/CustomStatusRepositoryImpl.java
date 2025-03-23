package com.system.slam.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomStatusRepositoryImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
