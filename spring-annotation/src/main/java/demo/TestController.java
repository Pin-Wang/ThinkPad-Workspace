package demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/test")
public class TestController
{
    @RequestMapping("common")
    public String dispatchTest(Test test,Model model)
    {
    	model.addAttribute("modelKey", "modelValue");
    	System.out.println("controller");
        return "test";
    }
}