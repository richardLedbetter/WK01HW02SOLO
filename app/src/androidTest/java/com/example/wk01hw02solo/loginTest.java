package com.example.wk01hw02solo;

import org.junit.Test;

import static org.junit.Assert.*;

public class loginTest {

    @Test
    public void signin() {
        assertEquals("wrong username",MainActivity.signin("test333","12"));
        assertEquals("wrong password",MainActivity.signin("test1","12"));
        assertEquals("success2",MainActivity.signin("test1","12345"));
    }
}