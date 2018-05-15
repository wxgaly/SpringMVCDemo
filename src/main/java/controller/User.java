package controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class User {

    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public String user(String name, String password){

        System.out.println(name);
        System.out.println(password);

        return name + password;
    }

}
