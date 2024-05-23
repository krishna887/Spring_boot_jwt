package com.f1soft.krishna.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("this is secure url which can access after login only");
    }
    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("This is secure Url which can be access from admin only");
    }

}