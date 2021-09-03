package heguang5460.github.io.message.web.filter;

import heguang5460.github.io.message.web.request.RepeatableHttpServletRequestWrapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 将ServletRequest转成自定义包装类，包装类支持流的重复读取，做增强
 * 请求体中的数据是InputStream，只能读取一次，但是再做认证鉴权时需要读取，导致接口@RequestBody无法再次读取，故做增强
 *
 * @author heguang
 */
@Order(1)
@Component
@WebFilter(filterName = "RequestWrapperFilter", urlPatterns = "/*")
public class RequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletRequest requestWrapper = new RepeatableHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
    }
}
