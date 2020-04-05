package com.lxf.demo;
import org.junit.*;
import org.junit.Assert.*;

public class TestHelloWorld {
  @Test
  public void testSay()
  {
      System.out.println("junit hello world =======================");
      Assert.assertEquals("hello", new HelloWorld().say());
  }
  
  @Test
  public void testWebServlet()
  {
      
  }
  
    
}
