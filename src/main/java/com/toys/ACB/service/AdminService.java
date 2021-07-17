package com.toys.ACB.service;


public interface AdminService {
    int updatePassword(String username, String oldPW, String newPW) throws Exception;
    int updateUserPassword(String username, String password) throws Exception;
}
