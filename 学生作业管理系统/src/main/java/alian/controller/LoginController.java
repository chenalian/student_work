package alian.controller;

import alian.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
 * 登录控制器
 *
 */
@Controller
@RequestMapping(path = "/login")
public class LoginController {
    @GetMapping()
    public String login()
    {
        return "login";
    }
    @RequestMapping(value ="indentycode")
    public void indentycode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCodeUtil.setHeight(37);
        VerifyCodeUtil.setWidth(83);
        VerifyCodeUtil.setSize(10);
        VerifyCodeUtil.setDsize(10);
        String indentycode=VerifyCodeUtil.generateVerifyCode();
        request.getSession().setAttribute("code", indentycode);
        BufferedImage image = VerifyCodeUtil.getBufferedImage(indentycode);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
    /*测试*/
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/hello")
    public @ResponseBody void greeting(String name)throws Exception{
        simpMessagingTemplate.convertAndSend("/topic/greetings",name);
    }
    @RequestMapping("/welcome")
    public String welcome()
    {
        return "welcome";
    }

}
