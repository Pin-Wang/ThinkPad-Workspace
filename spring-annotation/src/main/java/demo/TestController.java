package demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//test
@Controller
@RequestMapping(value = "/test")
public class TestController
{
    @RequestMapping
    public String dispatchTest()
    {
        System.out.println("Enter TestController.dispatchTest");
        return "test";
    }
}