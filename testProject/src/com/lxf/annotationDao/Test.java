package com.lxf.annotationDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 测试所用( 使用java反射机制以及注解获取实体类属性和值，并拼接sql语句 )
 * @author lxf
 * @date 2017-02-21
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Filter f1 = new Filter();
		f1.setId(1);//查询id=1的用户
		
		Filter f2 = new Filter();
		f2.setUser_name("lisi"); //模糊查询用户名为lisi的用户
		
		Filter f3 = new Filter();
		f3.setEmail("a@163.com, b@qq.com"); //查询邮箱为其中任意一个
		
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3  = query(f3);
		
		System.out.println(sql1); //SELECT * FROM user WHERE 1=1  AND id = 1
		System.out.println(sql2); //SELECT * FROM user WHERE 1=1  AND user_name =  ' lisi ' 
		System.out.println(sql3); //SELECT * FROM user WHERE 1=1  AND email IN(  ' a@163.com ' , '  b@qq.com '  ) 
		
	}
	
	public static String query(Filter f)
	{
		StringBuilder sb = new StringBuilder();
		//1.得到class
		Class c = f.getClass();
		//2.得到Table的名字
		boolean exists =  c.isAnnotationPresent(Table.class); //查询Filter中是否包含Table注解
		if( !exists )
			return null;
		
		//获取Table注解
		Table  table = (Table) c.getAnnotation(Table.class);
		String tableName = table.value();
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE 1=1 " );
		//3.遍历所有字段
		Field[] fArray = c.getDeclaredFields();
		for (Field field : fArray) {
			//4.处理每个字段对应的sql
			//4.1拿到字段名
			boolean fExists = field.isAnnotationPresent(Column.class);
			if(!fExists)
				continue;
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			
			//4.2拿到字段的值
			String fieldName = field.getName();//获取字段名
			String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			Object fieldValue = null;
			try {
				//通过方法反射调用Field类的geter方法
				Method getMethod = c.getMethod(getMethodName);
				fieldValue = getMethod.invoke(f);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue == 0))
				continue;
			
			//4.3拼装sql
			sb.append(" AND " ).append(fieldName);
			if(fieldValue instanceof String)
			{
				//组合sql中的 where in
				if(((String) fieldValue).contains(",")){
					String[] fields = ((String) fieldValue).split(",");
					sb.append(" IN( ");
					for (String string : fields) {
						sb.append(" ' ").append(string).append(" ' ").append(",");
					}
					//删除sql语句的最后一个逗号
					sb.deleteCharAt(sb.length()-1);
					sb.append(" ) ");
				}else
				{
					//组合普通的where 字符串条件
					sb.append(" = " ).append(" ' ").append(fieldValue).append(" ' ");
				}
				
			}else if(fieldValue instanceof Integer)
			{
				//组合整型where条件
				sb.append(" = " ).append(fieldValue);
			}	
		}
		return sb.toString();
	}

}
