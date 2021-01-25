package alian.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

//@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.nullDestMatcher().authenticated()  //任何没有目的地的消息（即消息类型为MESSAGE或SUBSCRIBE以外的任何消息）将要求用户进行身份验证
                .simpSubscribeDestMatchers("/user/queue/errors").permitAll() //任何人都可以订阅/ user / queue / error
                .simpDestMatchers("/app/**").hasRole("USER")  //任何目的地以“/ app /”开头的消息都要求用户具有角色ROLE_USER
                .anyMessage().denyAll(); //拒绝任何其他消息。这是一个好主意，以确保您不会错过任何消息。
    }
    // @formatter:on

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
