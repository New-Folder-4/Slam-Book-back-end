package com.system.slam.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomReviewRepositoryImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
