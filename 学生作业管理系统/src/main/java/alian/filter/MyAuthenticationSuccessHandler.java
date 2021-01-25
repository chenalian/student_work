package alian.filter;

import alian.domain.Student;
import alian.domain.Teacher;
import alian.mapper.StudentMapper;
import alian.mapper.TeacherMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.util.*;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired StudentMapper studentMapper;

    @Autowired TeacherMapper teacherMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        Collection<? extends GrantedAuthority> authorities= authentication.getAuthorities();
        Iterator <SimpleGrantedAuthority>  temp= (Iterator<SimpleGrantedAuthority>) authorities.iterator();
        String role=null;
        while(temp.hasNext())
        {
            SimpleGrantedAuthority a=temp.next();
            role=a.toString();
            map.put("msg",role);
        }
        map.put("code","0");
        String name=authentication.getName();
        if(role.equals("student"))
        {
            Student student=studentMapper.findinfoByname(name);
            httpServletRequest.getSession().setAttribute("user", student);
        }
        else{
            Teacher teacher=teacherMapper.findinfoByname(name);
            httpServletRequest.getSession().setAttribute("user", teacher);
        }
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
