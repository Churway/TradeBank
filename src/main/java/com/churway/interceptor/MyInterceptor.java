package com.churway.interceptor;

import com.churway.model.SysUser;
import com.churway.utils.BaseController;
import com.churway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/11/12
 * @since 1.0.0
 */
@Component
public class MyInterceptor extends BaseController implements HandlerInterceptor {
    @Value("${login.getToken}")
    private String mgr_getToken;
    @Value("${login.login}")
    private String mgr_login;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 查询本系统token
        String token_u = request.getParameter("token") == null ? "" : request.getParameter("token").trim();
        String token_c = super.getCookieVal(request, "token");
        String token = token_u.equals("") ? token_c : token_u;
        // 本系统无token时
        if (token.equals("")) {
            response.sendRedirect(mgr_getToken + "?callbackurl=" + request.getRequestURL().toString());
            return false;
        }
        // 本系统有token时
        SysUser sysUser = JwtUtils.getObject(token, SysUser.class);
        if (sysUser == null) {
            response.sendRedirect(mgr_login);
            return false;
        }
        // success
        super.setCookieVal(response, "token", token);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
