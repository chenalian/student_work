package alian.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by linziyu on 2019/2/12.
 *
 * 用户登出处理类
 */

@Component("UserLogoutSuccessHandler")

public class UserLogoutSuccessHandler implements LogoutSuccessHandler{

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.flush();
        out.close();



//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        // 1 匹配到/logout请求
//        if (requiresLogout(request, response)) {
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            // 2 清空Cookie、remember-me、session和SecurityContext
//            this.handler.logout(request, response, auth);
//            // 3 重定向到注册界面
//            logoutSuccessHandler.onLogoutSuccess(request, response, auth);
//
//            return;
//        }


    }
}
