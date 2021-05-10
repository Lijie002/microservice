package com.qixinmini.common.core.constant;
/**
 * @Description 项目常量
 * @author lijie
 * @Date 2021/2/15 15:02
 */
public interface MicroserviceConstant {
    /**
     * OAUTH2 令牌类型 https://oauth.net/2/bearer-tokens/
     */
    String OAUTH2_TOKEN_TYPE = "bearer";

    /**gateway 防护token key*/
    String GATEWAY_TOKEN_VALUE = "rainbow123456";
    String GATEWAY_TOKEN_HEADER = "gatewayToken";

    String DEFAULT_PASSWORD = "rainbow";

    String DEFAULT_AVATAR = "default.jpg";
    /**
     * Java默认临时目录
     */
    String JAVA_TEMP_DIR = "java.io.tmpdir";
    String LOGIN_MSG="rainbow 系统登录通知";
    interface log{
//         String OPERATION_LOG=0;
//         String OPERATION_LOG=1;
    }
}
