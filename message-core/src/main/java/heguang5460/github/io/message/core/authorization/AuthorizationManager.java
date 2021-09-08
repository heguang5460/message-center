package heguang5460.github.io.message.core.authorization;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.stereotype.Component;

/**
 * 认证授权处理器
 *
 * @author he guang
 */
@Component
public class AuthorizationManager {

    /**
     * 认证和授权逻辑
     * 比较签名，签名正确即放行
     *
     * @return
     * @author he guang
     */
    public boolean judge(String bodyJson, String clientSignature) {
        if (StrUtil.isBlank(clientSignature)) {
            return false;
        }
        //Md5
        String md5 = DigestUtil.md5Hex(bodyJson);
        //SHA1
        String serverSignature = DigestUtil.sha1Hex(md5);
        if (clientSignature.equals(serverSignature)) {
            return true;
        }
        return false;
    }
}
