package main.java;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String login(String name, String password) {

        System.out.println(name);
        System.out.println(password);

        return "success";
    }

    @RequestMapping(value = "/loginSystem", method = RequestMethod.GET)
    public ModelAndView loginSystem(String name, String password) {

        System.out.println(name);
        System.out.println(password);

        ModelAndView modelAndView = new ModelAndView();

        if (name != null && password != null) {
            if (name.equals("zhangsan") && password.equals("123456")) {
                modelAndView.setViewName("/loginSuccess");
                modelAndView.addObject("msg", "成功");
            } else {
                modelAndView.setViewName("/loginFailure");
                modelAndView.addObject("msg", "用户名密码错误");
            }
        } else {
            modelAndView.setViewName("/loginFailure");
            modelAndView.addObject("msg", "用户名密码不能为空");
        }


        return modelAndView;
    }

}
