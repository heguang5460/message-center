package heguang5460.github.io.message.dao.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义SQL日志打印格式
 * 日志打印包括 实际执行数据库、实际执行表、执行的耗时、执行的sql语句、参数
 *
 * @author he guang
 */
@Slf4j
public class CustomizedP6SpyLogger implements MessageFormattingStrategy {

    @Override
    public String formatMessage(
            int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ?
                " Consume Time：" + elapsed + " ms " + now + "\n Execute SQL：" + sql.replaceAll("[\\s]+", " ") + "\n"
                : "";
    }
}