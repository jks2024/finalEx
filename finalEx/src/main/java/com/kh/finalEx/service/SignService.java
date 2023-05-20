package com.kh.finalEx.service;

import com.kh.finalEx.dto.SignInResultDto;
import com.kh.finalEx.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;

}