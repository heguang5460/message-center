package heguang5460.github.io.message.common.result;

import lombok.Data;

/**
 * 分页参数
 *
 * @author he guang
 * @date 2021/8/31 10:40
 */
@Data
public class BasePage {

    private long size = 10L;

    private long current = 1L;

}
