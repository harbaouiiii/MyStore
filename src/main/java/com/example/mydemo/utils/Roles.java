package com.example.mydemo.utils;

public class Roles {
    private Roles () {}
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
    public static final String HAS_ROLE_USER = "hasRole('USER')";
    public static final String HAS_ROLE_MOD = "hasRole('MOD')";
    public static final String HAS_ROLE_ADMIN_OR_MOD = "hasRole('ADMIN') or hasRole('MOD')";

}
