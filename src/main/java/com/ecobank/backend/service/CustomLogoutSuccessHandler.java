package com.ecobank.backend.service;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.ecobank.backend.persistence.domain.backend.User;
import com.ecobank.web.controllers.FileController;

public class CustomLogoutSuccessHandler extends
  SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
 
	private static final Logger LOGGER = Logger.getLogger(CustomLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Authentication authentication) 
      throws IOException, ServletException {
  
        String refererUrl = request.getHeader("Referer");

        LOGGER.info("Logout from: " + refererUrl+" @ "+Calendar.getInstance().getTime());
        response.sendRedirect("/login");
        //super.onLogoutSuccess(request, response, authentication);
    }
}