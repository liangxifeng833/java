package com.atguigu.springmvc.conterters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.atguigu.springmvc.crud.entities.Department;
import com.atguigu.springmvc.crud.entities.Employee;
/**
 * 自定义转换器，将前台页面传进来的employee字符串转换为Employee对象
 * @author lxf
 */
@Component
public class EmployeeConverter implements Converter<String, Employee> {
    /**
     * source 字符串格式：GG-gg@atguigu.com-0-105
     */
    @Override
    public Employee convert(String source) {
        // TODO Auto-generated method stub
        if(source != null)
        {
            String[] vals =  source.split("-");
            if(vals != null && vals.length ==4)
            {
                String lastName = vals[0];
                String email = vals[1];
                Integer gender = Integer.parseInt(vals[2]);
                Department department = new Department();
                department.setId(Integer.parseInt(vals[3]));
                Employee employ = new Employee(null, lastName, email, gender, department);
                System.out.println(source + "--convert--" + employ);
                return employ;
            }     
        }
        return null;
    }
}
