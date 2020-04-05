package com.atguigu.springmvc.crud.handlers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.springmvc.crud.dao.DepartmentDao;
import com.atguigu.springmvc.crud.dao.EmployeeDao;
import com.atguigu.springmvc.crud.entities.Employee;
/**
 *  员工控制器
 * @author lxf
 */
@RequestMapping("/")
@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;

	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,
			Map<String, Object> map){
		if(id != null){
			map.put("employee", employeeDao.get(id));
		}
	}
	/**
	 * 修改员工信息操作
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);
		
		return "redirect:/emps";
	}
	/**
	 * 显示修改页面，其实是一个表单回显
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map){
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}
	
	/**
	 * 删除员工信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	/**
	 * 新增操作,如果数据绑定过程中出现错误,则错误信息放在BindingResult中
	 * 需校验的 Bean 对象Employee和其绑定结果对象或错误对象BindingResult时成对出现的,它们
之间不允许声明其他的入参
      * 如何校验 ? 注解 ?
            ①. 使用 JSR 303 验证标准
            ②. 加入 hibernate validator 验证框架的 jar 包
            ③. 在 SpringMVC 配置文件中添加 <mvc:annotation-driven />
            ④. 需要在 bean 的属性上添加对应的注解
            ⑤. 在目标方法 bean 类型的前面添加 @Valid 注解
	 * @param employee
	 * @param result
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public String save(@Valid Employee employee,BindingResult result, Map<String,Object>map){
		System.out.println("save: " + employee);		
		
		//如果验证失败
		if(result.getErrorCount() > 0)
		{
		    System.out.println("出错了!");
		    for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }
		   //获取所有部门
	        map.put("departments", departmentDao.getDepartments());
		    return "input";
		}	
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	/**
	 * 新增页面get
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public String input(Map<String, Object> map){
	    //获取所有部门
		map.put("departments", departmentDao.getDepartments());
		//将员工信息放到request域模型中
		map.put("employee", new Employee());
		return "input";
	}
	
	/**
	 * 列表页
	 * @param map
	 * @return
	 */
	@RequestMapping("/emps")
	public String list(Map<String, Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	
	@RequestMapping("/testA")
	public String testA()
	{
	    System.out.println("testA");
	    return "success";
	}
	
	/**
	 * 不自动将前台表单数据绑定到对应的bean的lastName属性, 需要我们自行处理
	 * @param binder
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields("lastName");
	} */ 
	
}
