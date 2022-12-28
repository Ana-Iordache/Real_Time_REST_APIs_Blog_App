package com.blog.blogrestapi.service;

import com.blog.blogrestapi.payload.LoginDto;
import com.blog.blogrestapi.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
