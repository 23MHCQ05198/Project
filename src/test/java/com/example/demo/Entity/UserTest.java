package com.example.demo.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void testUserGettersSetters() {

        User user = new User();

        user.setId(1);
        user.setUsername("ramya");
        user.setPassword("1234");

        assertEquals(1, user.getId());
        assertEquals("ramya", user.getUsername());
        assertEquals("1234", user.getPassword());
    }

    @Test
    void testAllArgsConstructor() {

        User user = new User(1, "ramya", "1234");

        assertEquals(1, user.getId());
        assertEquals("ramya", user.getUsername());
        assertEquals("1234", user.getPassword());
    }

    @Test
    void testToString() {

        User user = new User(1, "ramya", "1234");

        String result = user.toString();

        // just checking it contains values
        assert(result.contains("ramya"));
    }
}