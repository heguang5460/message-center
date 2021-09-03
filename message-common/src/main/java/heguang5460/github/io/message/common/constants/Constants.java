package heguang5460.github.io.message.common.constants;

import com.google.common.collect.Maps;
import java.util.Map;

/**
 * 常量类
 *
 * @author he guang
 */
public interface Constants {

    /**
     * 空对象
     */
    Map<String, Object> EMPTY = Maps.newHashMap();
    /**
     * 空串
     */
    String EMPTY_STRING = "";
    /**
     * 请求头中做认证的字段
     */
    String AUTHORIZATION = "Authorization";
}
