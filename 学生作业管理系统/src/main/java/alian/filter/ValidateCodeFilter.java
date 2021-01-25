package alian.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ValidateCodeFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        /*
         *
         * 拦截表单登录url 进行验证码的提前验证
         *
         * */
        if(StringUtils.equals("/login/form", request.getRequestURI())&& StringUtils.equalsIgnoreCase("post", request.getMethod()))
        {
                String validatecode=request.getParameter("validatecode");
                String code= (String) request.getSession().getAttribute("code");
                Map<String, Object> map = new HashMap<>();
                map.put("code","0");
               ObjectMapper objectMapper=new ObjectMapper();

                if(StringUtils.isEmpty(validatecode))
                {
                    map.put("msg","验证码不能为空");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(objectMapper.writeValueAsString(map));
                    return;
                }
                if(StringUtils.isEmpty(validatecode))
                {
                    map.put("msg","验证码已过期");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(objectMapper.writeValueAsString(map));
                    return;
                }

                if(code.toUpperCase().equals(validatecode.toUpperCase()))
                {
                    filterChain.doFilter(request, response);
                }
                else{
                    map.put("msg","验证码输入不正确");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(objectMapper.writeValueAsString(map));
                    return;
                }
        }
        else {
            filterChain.doFilter(request, response);
        }
    }
}
