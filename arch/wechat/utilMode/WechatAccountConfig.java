
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liyuan
 * @description 配置工具类
 * @date 2019-03-27 13:49
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**公众号的唯一标识*/
    private String appid;
    /**公众号的appsecret*/
    private String secret;

    public String getAppid(){
        return appid;
    }

    public void setAppid(String appid){
        this.appid=appid;
    }

    public String getSecret(){
        return secret;
    }

    public void setSecret(String secret){
        this.secret=secret;
    }

}
