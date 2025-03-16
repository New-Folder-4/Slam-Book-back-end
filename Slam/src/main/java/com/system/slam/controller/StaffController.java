package com.system.slam.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/test")
    public ResponseEntity<String> getStaffTest() {
        return ResponseEntity.ok("This is staff test");
    }
}