package demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class UserController implements Controller {
	 
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(new User(1, "翡青", new Date(), "man", "浙江-杭州"));
        users.add(new User(2, "小芳", new Date(), "woman", "山东-青岛"));
 
        ModelAndView result = new ModelAndView();
        result.addObject("users", users);
        result.setViewName("users");
        return result;
    }
}