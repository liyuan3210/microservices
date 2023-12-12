

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yanyugang
 * @description 
 * 参考文档：
 * 1、https://blog.csdn.net/qq_35164810/article/details/78710417
 * @date 2019-03-27 14:01
 */
@Component
public class WechatMpConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    // 首先将appid和secret放进一个WxMpService，创建一个WxMpService对象
    // 此注解指定在Spring容器启动时，就执行该方法并将该方法返回的对象交由Spring容器管理
    @Bean
    public WxMpService wxMpService(){
        //创建WxMpService实例并设置appid和sectret
        WxMpService wxMpService=new WxMpServiceImpl();
        // 设置配置信息的存储位置
        wxMpService.setWxMpConfigStorage(wxConfigProvider());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxConfigProvider(){
        WxMpInMemoryConfigStorage wxConfigProvider=new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(wechatAccountConfig.getAppid());
        wxConfigProvider.setSecret(wechatAccountConfig.getSecret());
        return wxConfigProvider;
    }
}
