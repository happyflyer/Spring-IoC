package org.example.spring_ioc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lifei
 */
@Controller
public class SessionController {
    @RequestMapping("testSession")
    @ResponseBody
    public String test() {
        return this.toString();
    }
}
