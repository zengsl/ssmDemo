package com.ssm.pt.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.pt.common.controller.BaseControllerImp;
import com.ssm.pt.model.User;
import com.ssm.pt.service.IUserService;

/**
 * @author asus
 *
 */
@Controller  
@RequestMapping("/user")  
public class UserController extends BaseControllerImp{  
    @Resource  
    private IUserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        User user = this.userService.getUserById(userId);  
        model.addAttribute("user", user);  
        return "showUser";  
    }  
}  
