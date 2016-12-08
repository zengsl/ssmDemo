package com.ssm.pt.sys.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pt.common.controller.BaseControllerImp;
import com.ssm.pt.util.ImageCreator;

/**
 * 用户模块
 * @author zengsl
 *
 */
@Controller  
@RequestMapping("/sys/user")  
public class SysUserController extends BaseControllerImp {
	
	@RequestMapping("/login")  
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("/sys/user/login");
		return modelAndView;
	}
	
	@RequestMapping("/imageCode")  
	public void imageCode(HttpServletResponse response) {
		 //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
		response.setContentType("image/png");
		try {
			ImageCreator.generate(response);
			response.getOutputStream().flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
