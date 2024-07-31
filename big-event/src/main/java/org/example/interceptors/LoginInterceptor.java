package org.example.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Result;
import org.example.utils.JwtUtil;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

//拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         //令牌验证
        String token =  request.getHeader("Authorization");

        //验证token
        try {
            //从redis获取相同的token
            ValueOperations<String, String> Operations = redisTemplate.opsForValue();
            String redisToken = Operations.get(token);
            if (redisToken == null) {
                //已经失效了
                throw new Exception();
            }
            Map<String,Object> claims = JwtUtil.parseToken(token);
            //把业务数据存储到threadLocal中
            ThreadLocalUtil.set(claims);
            return true;//表示拦截器放行
        } catch (Exception e) {
            //http响应状态码401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空threadlocal中的数据  防止内存泄漏
        ThreadLocalUtil.remove();
    }
}
