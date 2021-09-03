package heguang5460.github.io.message.web.filter;

import cn.hutool.json.JSONUtil;
import heguang5460.github.io.message.common.constants.Constants;
import heguang5460.github.io.message.common.result.CommonResult;
import heguang5460.github.io.message.common.result.ResultCode;
import heguang5460.github.io.message.core.authorization.AuthorizationManager;
import heguang5460.github.io.message.web.config.WhiteIgnoreUrlsConfig;
import heguang5460.github.io.message.web.request.RepeatableHttpServletRequestWrapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * 认证和授权过滤器
 *
 * @author he guang
 */
@Order(2)
@Component
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    @Autowired
    private AuthorizationManager authorizationManager;
    @Autowired
    private WhiteIgnoreUrlsConfig whiteIgnoreUrlsConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // 转换
        RepeatableHttpServletRequestWrapper request = (RepeatableHttpServletRequestWrapper) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //白名单放行
        String uri = request.getRequestURI();
        List<String> ignoreUrls = whiteIgnoreUrlsConfig.getUrls();
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        // 参数
        String signature = request.getHeader(Constants.AUTHORIZATION);
        String bodyJson = RepeatableHttpServletRequestWrapper.convertRequestBodyToJsonStr(request);
        // 鉴权
        if (authorizationManager.judge(bodyJson, signature)) {
            filterChain.doFilter(request, response);
        } else {
            this.releaseResponse(response);
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 无权限返回
     *
     * @param response
     * @throws IOException
     */
    private void releaseResponse(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.failed(ResultCode.UNAUTHORIZED)));
        response.getWriter().flush();
    }
}
