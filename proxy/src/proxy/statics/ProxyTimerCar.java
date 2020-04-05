 package proxy.statics; 
 public class ProxyTimerCar  implements proxy.statics.MoveAble{ 
   //被代理Car对象 
  private proxy.statics.MoveAble m; 
   public ProxyTimerCar(proxy.statics.MoveAble m) 
   { 
       this.m = m; 
  } 
   @Override 
  public void  move()  { 
       //开始时间 
     Long start = System.currentTimeMillis(); 
      System.out.println("汽车开始行驶..."); 
     //执行被代理对象的方法 
       m. move(); 
      //结束时间 
       Long end = System.currentTimeMillis(); 
      System.out.println("汽车结束行驶..." + "行驶话费了：" + (end-start) + "ms"); 
   } 
}