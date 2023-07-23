package com.example.mydemo.utils;

public class Roles {
    private Roles () {}
    public static final String HAS_ROLE_ADMIN = "hasAuthority('ADMIN')";
    public static final String HAS_ROLE_USER = "hasAuthority('USER')";
    public static final String HAS_ROLE_MOD = "hasAuthority('MOD')";
    public static final String HAS_ROLE_ADMIN_OR_MOD = "hasAuthority('ADMIN') or hasAuthority('MOD')";

}
