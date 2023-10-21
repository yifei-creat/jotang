package com.task.filter;

import com.alibaba.fastjson.JSONObject;
import com.task.pojo.Result;
import com.task.Utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}", url);

        //2.判断请求url中是否包含login,如果包含,则放行
        if (url.contains("login")) {
            log.info("登录操作,放行");
            chain.doFilter(request, response);
            return;
        }

        //3.获取请求头中的令牌
        String jwt = request.getHeader("token");

        //4.判断令牌是否存在,不存在,返回错误结果(未登录)
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空,返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //转换成JSON-----阿里巴巴fastJSON
            String notlogin = JSONObject.toJSONString(error);

            //返回响应
            response.getWriter().write(notlogin);
            return;
        }

        //5.解析token,如果解析失败,返回未登录的错误结果
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//解析失败
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //转换成JSON
            String notlogin = JSONObject.toJSONString(error);
            response.getWriter().write(notlogin);
            return;
        }

        //放行
        log.info("令牌合法,放行");
        chain.doFilter(request, response);

    }
}
