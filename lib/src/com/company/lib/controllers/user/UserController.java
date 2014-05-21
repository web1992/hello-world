package com.company.lib.controllers.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.lib.controllers.LoginRequired;
import com.company.lib.dao.UserDAO;
import com.company.lib.entity.User;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

@LoginRequired
@Path("")
public class UserController {
	@Autowired
	private UserDAO userDAO;
    // 11)
	@LoginRequired
    @Get
    public String list(Invocation inv) {
		
		List<User> users = userDAO.find();
		inv.addModel("users", users);
		HttpSession session = inv.getRequest().getSession(true);
		User userInfo=(User)session.getAttribute("userInfo");
		//System.out.println("useInfo in user-List = "+useInfo);
		if(userInfo.getName().equals("admin")){
			inv.addModel("isEdit", true);
			inv.addModel("userInfo", userInfo);
			return "user-list";
		}
		// not admin 
		return "user-page";
		
    }

    // 12)
    @Post("addUser")
    public String add(User user) {
    	try {
			 userDAO.save(user);
			 return "r:/lib/user";
		} catch (Exception e) {
			e.printStackTrace();
			return "user-add";
		}
       
    }

    // 13)
    @Get("add")
    public String showAdd(@Param("isEdit") boolean isEdit) {
    	if (isEdit) {
   		return "user-add";
   	 }
        return "user-page";
        
    }

    // 14)
    @Get("{userId}")
    public String show(Invocation inv,@Param("userId") String userId, @Param("isEdit") boolean isEdit) {
    	System.out.println("show userId= "+userId);
    	System.out.println(" show isEdit= "+isEdit);
        if (isEdit) {
        	long lid=Long.parseLong(userId);
        	User user = userDAO.get(lid);
        	inv.addModel("updateUser", user);
            return "user-edit";
        }
        return "user-page";
    }

    // 15)
    @Get("delete")
    public String delete(@Param("userId") String userId,@Param("isEdit") boolean isEdit) {
    	System.out.println("delete userId= "+userId);
    	 if (isEdit) {
    		 long lid=Long.parseLong(userId);
    		 userDAO.delete(lid);
    		 return "r:/lib/user";
    	 }
         return "user-page";
       
    }
    
    //16)
    @Post("update")
    public String update(User user){
    	
    	try {
			userDAO.update(user);
			 return "r:/lib/user";
		} catch (Exception e) {
			e.printStackTrace();
			 return "r:/lib/user";
		}
    }
    
}