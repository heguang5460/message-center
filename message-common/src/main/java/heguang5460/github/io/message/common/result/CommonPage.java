package heguang5460.github.io.message.common.result;

import java.util.List;
import lombok.Data;

/**
 * 分页数据封装类
 *
 * @author he guang
 */
@Data
public class CommonPage<T> {

    private List<T> records;

    private long total;

    private long size;

    private long current;

}
