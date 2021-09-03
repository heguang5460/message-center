package heguang5460.github.io.message.web.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置白名单路径
 *
 * @author he guang
 */
@Data
@ConfigurationProperties(prefix = "white.ignore")
public class WhiteIgnoreUrlsConfig {

    /**
     * 白名单路径
     */
    private List<String> urls;
}
