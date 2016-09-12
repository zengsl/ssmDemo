package com.ssm.pt.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pt.common.controller.BaseControllerImp;

/**
 * @author asus
 *
 */
@Controller  
@RequestMapping("/test")  
public class TestLotteryDraw extends BaseControllerImp{
	
	@RequestMapping("/testLotteryDraw")  
    public ModelAndView toIndex(HttpServletRequest request,Model model){
		ModelAndView mv = new ModelAndView();
		//添加模型数据 可以是任意的POJO对象
        mv.addObject("message", "Hello World!");
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        mv.setViewName("testLotteryDraw");
        return mv;                                         //○3 模型数据和逻辑视图名
//        return "/testLotteryDraw";  
    }  
	
	@RequestMapping("/testLotteryDraw2")  
	public String toIndex2(HttpServletRequest request,Model model){  
		return "/testLotteryDraw2";  
	}  
}
