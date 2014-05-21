package com.company.lib.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.lib.dao.UserDAO;
import com.company.lib.entity.User;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
@Path("login")
public class LoginController {
	@Autowired
	private UserDAO userDAO;
    // 9)
    @Get
    public String show() {
    	System.out.println("show...");
        return "login";
    }

    // 10)
    @Post
    public String doLogin(User user,Invocation inv) {
    	//System.out.println("loginName= "+user.getLoginName());
    	//System.out.println("password= "+user.getPassword());
    	User dbuser = userDAO.getByLoginName(user.getLoginName());
    		if(dbuser==null)  return "r:/lib/login";
    		if( dbuser.getPassword().equals(user.getPassword())){
    			//loginUser
    			HttpSession session = inv.getRequest().getSession(true);
    			session.setAttribute("userInfo", dbuser);
    			return "r:/lib/book";
    		}
    		
    	        //return "r:/lib/book";
    	    	return "r:/lib/login/";
    }
    
}