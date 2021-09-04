package heguang5460.github.io.message.common.util;

import cn.hutool.core.util.StrUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * @author he guang
 * @date 2021/9/3 15:29
 */
public final class TemplateUtil {

    private static final Pattern pattern = Pattern.compile("((?<=\\$\\{) [^\\}]*  (?=\\}))");

    private TemplateUtil() {
    }

    /**
     * 将模板转换为内容
     *
     * @param params
     * @param templateContent
     * @return
     * @author heguang
     */
    public static String templateContentToMessageContent(Map<String, String> params, String templateContent) {
        if (templateContent.contains("${")) {
            List<String> paramList = listTemplateParams(templateContent);
            for (String param : paramList) {
                String value = params.get(param);
                if (StrUtil.isBlank(value)) {
                    continue;
                }
                templateContent = templateContent.replaceAll("\\$\\{" + param + "\\}", value);
            }
        }
        return templateContent;
    }

    /**
     * 获取模板中所有参数
     * 例如：尊敬的${name}会员你好，验证码是${code}
     * 返回：List<String>  ["name","code"]
     *
     * @param template
     * @return
     */
    public static List<String> listTemplateParams(String template) {
        List<String> parameterList = new ArrayList<>();
        Matcher m = pattern.matcher(template);
        while (m.find()) {
            for (int i = 0; i < m.groupCount(); i++) {
                if (StringUtils.hasLength(m.group(i))) {
                    parameterList.add(m.group(i).trim());
                }
            }
        }
        return parameterList;
    }

}
