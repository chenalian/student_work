package alian.config;

import alian.mapper.StudentMapper;
import alian.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDatalis implements UserDetailsService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String judge1=studentMapper.findpasswordbyid(s);
        String judge2=teacherMapper.findpasswordbyid(s);
        BCryptPasswordEncoder passwordencodeing=new BCryptPasswordEncoder();
        if(judge1==null&&judge2==null)
        {
            return new User(s, "@@@", AuthorityUtils.commaSeparatedStringToAuthorityList("error"));
        }
        if(judge1!=null)
        {
            return new User(s, passwordencodeing.encode(judge1), AuthorityUtils.commaSeparatedStringToAuthorityList("student"));
        }
        if(judge2!=null)
        {
            return new User(s, passwordencodeing.encode(judge2), AuthorityUtils.commaSeparatedStringToAuthorityList("teacher"));
        }
        return null;
    }
}
