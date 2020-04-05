package lxf.spring.struts.actions;

import lxf.spring.struts.service.PersonService;
/**
 * 用户请求action
 * @author lxf
 */
public class PersonAction {
    private PersonService personService;
    public void setPersonService(PersonService personService)
    {
        this.personService = personService;
    }
    public String execute()
    {
        System.out.println("excute.....");
        personService.save();;
        return "success";
    }
}
