package dev.germangonzalez.security.jdbc.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static org.springframework.http.ResponseEntity.ok;

@Controller
public class HomeResource {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ok(greeting("public") + "<h2>No roles required</h2><br>Current time: " + now());
    }


    private String greeting(String area) {
        return format("<h1>Hello [%s] this is %s area.</h1>", getName(), area);
    }


    private String getName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return ok(greeting("user") + "<h2>Roles allowed: USER, ADMIN</h2><br>Current time: " + now());
    }


    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ok(greeting("admin") + "<h2>The only role allowed is: ADMIN</h2><br>Current time: " + now());
    }
}
