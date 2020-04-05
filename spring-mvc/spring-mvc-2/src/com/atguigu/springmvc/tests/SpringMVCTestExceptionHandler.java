package com.atguigu.springmvc.tests;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/**
 * @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来出来当前方法出现的异常, 
 * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常. 
 * 如果在Handler控制器中能找到@ExceptionHandler注释的方法,则优先执行该方法
 * @author lxf
 */
@ControllerAdvice
public class SpringMVCTestExceptionHandler {
  @ExceptionHandler({ArithmeticException.class})
  public ModelAndView handlerArithmeticException(Exception ex)
  {
      ModelAndView mv = new ModelAndView("error");
      mv.addObject("exception", ex);
      System.out.println("--->[出现了算数异常] : " + ex);
      return mv;
  }
}
