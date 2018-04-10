package com.og.oms.service;

public interface ILdapService {
    boolean authenticate(String account, String password);
}
