package proxy.dynamic.custom;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.apache.commons.io.FileUtils;
import javax.tools.JavaCompiler.CompilationTask;
import proxy.statics.Car;
import proxy.statics.MoveAble;
/**
 * 动态代理类
 * @author lxf
 * 参数：
 * Class 代理类和被代理类实现的接口
 *  InvocationHandler h 自己实现的事务处理器proxy.dynamic.custom.TimerHandler
 *
 */
public class Proxy {
    
    /**
     * 自己实现JDK生成动态代理类及对象
     * @param infce 接口class，有了接口就可以通过接口获取接口中定义的方法，为生成代理类而用
     * @param h 自己创建的proxy.dynamic.custom.InvocationHandler 事务处理器（处理主要业务罗辑）
     * 　　　　　 事务处理器中包含被代理对象Car，具体处理业务罗辑的代码
     * @return
     * @throws IOException
     */
    public static Object newProxyInstance(Class infce, InvocationHandler h) throws IOException
    {
        String br = "\n"; //换行符
        String methodStr = "";
        //获取接口中所有的方法，遍历组合为代理类的方法
        for(Method m : infce.getMethods()){
            methodStr += "  @Override" + br +
            "   public void " + m.getName() + "() {" + br +
            "  try{" + br +
            "  Method md = " + infce.getName() + ".class.getMethod(\"" 
                                        + m.getName() + "\");" + br +
            "  h.myinvoke(this,md);" +br+ 
            "  }catch(Exception e){ e.printStackTrace();}" + br +
            "   }" ;
        }
        
        /**
         * 动态代理类字符串
         */
        String str = 
        " package proxy.dynamic.custom; " + br + 
        " import proxy.dynamic.custom.InvocationHandler; " + br +
        "  import java.lang.reflect.Method;" + br +
        " public class $Proxy0  implements "+infce.getName()+"{ " + br + 
         "  private InvocationHandler h; " + br + 
         "   public $Proxy0( InvocationHandler h ) " + br + 
         "  { " + br + 
         "       this.h = h; " + br + 
         "  } " + br +
         methodStr + br +
        "}";
        
        /*
         * 自行实现动态代理的流程
         * 1. 动态生成代理类.java源文件
         */
        //获取当前项目路径,/home/lxf/workspaceEE/proxy
        String filename = System.getProperty("user.dir") + "/bin/proxy/dynamic/custom/$Proxy0.java";
        System.out.println(filename);
        File file = new File(filename);
        FileUtils.writeStringToFile(file, str);
        
        /*
         * 2. 将动态生成的代理类.java源文件编译为class字节码文件
         */
        //编译
        //拿到编译器
        JavaCompiler complier = ToolProvider.getSystemJavaCompiler();
        //文件管理者
        StandardJavaFileManager fileMgr = 
                complier.getStandardFileManager(null, null, null);
        //获取文件
        Iterable units = fileMgr.getJavaFileObjects(filename);
        //编译任务
        CompilationTask t = complier.getTask(null, fileMgr, null, null, null, units);
        //进行编译
        t.call();
        fileMgr.close();
           
       /**
        * load到内存中
        */
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c;
        try {
            c = cl.loadClass("proxy.dynamic.custom.$Proxy0");
            //System.out.println(c.getName());
            //通过构造器返回代理对象
            Constructor ctr = c.getConstructor(InvocationHandler.class);
            return ctr.newInstance(h);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return null;
    }
}
