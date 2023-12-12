
import com.google.gson.Gson;

import WechatResult;
import WechatAccountConfig;

import com.sto.transport.tms.util.HttpsUtils;
import com.sto.transport.tms.util.SunfireLogBuilder;
import com.sto.transport.tms.util.WechatUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import org.slf4j.Logger;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author liyuan
 * @description 微信网页授权：
 * 1、https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
 * 参考博客：
 * 1、https://blog.51cto.com/zero01/2117892
 * 2、https://blog.csdn.net/qq_35164810/article/details/78710417
 * @date 2019-03-27 14:05
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);
    public static final String WX_BASIC_ACCESS_TOKEN="WX_BASIC_ACCESS_TOKEN";
    public static final String JSAPI_TICKET="JSAPI_TICKET";
    Gson gson=new Gson();

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    // 第二步：通过code换取网页授权access_token（网页授权token）
    @RequestMapping("/getAccessToken")
    public String getAccessToken(@RequestBody RequestParameter requestParams){
        RestResponse<WechatResult> resultRestResponse=new RestResponse<>();
        WechatResult result=new WechatResult();
        String openid=requestParams.getLogisticsInterface().getOpenId();
        String code=requestParams.getLogisticsInterface().getCode();
        String fromCode=requestParams.getFromCode();
        String msgType=requestParams.getMsgType();
        // 接入sunfire
        long startTime=System.currentTimeMillis();
        boolean resultFlag=true;

        logger.info("请求参数：openid===>" + openid + ",code===>" + code + ",fromCode===>" + fromCode + ",msgType===>" + msgType);

        try {
            if (StringUtils.isBlank(code)){
                resultRestResponse.setResMessage("code不能为空===>" + code);
                resultRestResponse.setRespCode(ErrorCode.INVALID_PARAMATER_ERROR);
                resultRestResponse.setData(result);
                return gson.toJson(resultRestResponse);
            }
            if (!"weixin".equals(fromCode)){
                resultRestResponse.setResMessage("不合法的fromCode===>" + fromCode);
                resultRestResponse.setRespCode(ErrorCode.INVALID_PARAMATER_ERROR);
                resultRestResponse.setData(result);
                return gson.toJson(resultRestResponse);
            }

            //从redis缓存中取
            if (StringUtils.isNotBlank(openid) && redisTemplate.hasKey(openid)){
                logger.info("openid过期时间===>" + redisTemplate.getExpire(openid) * 1000 + "毫秒");
                return redisTemplate.opsForValue().get(openid);
            }

            long millisecondNow=System.currentTimeMillis();
            // 获取网页授权token、openid
            String reqUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            reqUrl=reqUrl.replace("APPID", wechatAccountConfig.getAppid())
                    .replace("SECRET", wechatAccountConfig.getSecret()).replace("CODE", code);
            String info= HttpsUtils.httpsRequest(reqUrl, "GET", null);
            logger.info("第二步：通过code获取openid，返回报文===>" + info);
            AuthorizationTokenDTO auToken=gson.fromJson(info, AuthorizationTokenDTO.class);
            if (auToken!=null && StringUtils.isNotBlank(auToken.getOpenid())){
                result.setOpenid(auToken.getOpenid());
                result.setAccessToken(auToken.getAccess_token());
            }else {
                resultRestResponse.setResMessage("获取openid===>" + info);
                resultRestResponse.setRespCode(ErrorCode.SERVER_ERROR);
                resultRestResponse.setData(result);
                return gson.toJson(resultRestResponse);
            }

            // 多減去5秒
            long millisecond=7200000 - (System.currentTimeMillis() - millisecondNow) - 5000;
            resultRestResponse.setRespCode(RestResponse.SUCCESS_CODE);
            resultRestResponse.setData(result);
            resultRestResponse.setResMessage("请求成功");
            // 放入redis中，key为openid
            redisTemplate.opsForValue().set(result.getOpenid(), gson.toJson(resultRestResponse), millisecond, TimeUnit.MILLISECONDS);
            logger.info("放入redis成功，openid过期时间===>" + millisecond + "毫秒" + "，放入信息===>" + gson.toJson(resultRestResponse));
        } catch (Exception e) {
            resultFlag=false;
            logger.error("WechatController.getAccessToken出错===>" + e.toString());
        } finally {
            SunfireLogBuilder.getInstance("getAccessToken", System.currentTimeMillis() - startTime, true, null, null).print();
        }
        return gson.toJson(resultRestResponse);
    }

    // 获取JS-SDK使用权限签名
    @RequestMapping("/getSignature")
    public String getSignature(@RequestBody RequestParameter requestParams){
        RestResponse<WechatResult> resultRestResponse=new RestResponse<>();
        WechatResult result=new WechatResult();
        String fromCode=requestParams.getFromCode();
        String msgType=requestParams.getMsgType();
        String pageUrl=requestParams.getLogisticsInterface().getPageUrl();
        // 接入sunfire
        long startTime=System.currentTimeMillis();
        boolean resultFlag=true;

        logger.info("请求参数：fromCode===>" + fromCode + ",msgType===>" + msgType + ",pageUrl===>" + pageUrl);

        try {
            if (!"weixin".equals(fromCode)){
                resultRestResponse.setResMessage("不合法的fromCode===>" + fromCode);
                resultRestResponse.setRespCode(ErrorCode.INVALID_PARAMATER_ERROR);
                resultRestResponse.setData(result);
                return gson.toJson(resultRestResponse);
            }

            // 定义ticket变量
            String ticket="";
            //从redis缓存中取
            if (redisTemplate.hasKey(JSAPI_TICKET)){
                logger.info("JSAPI_TICKET过期时间===>" + redisTemplate.getExpire(JSAPI_TICKET) * 1000 + "毫秒");
                ticket=redisTemplate.opsForValue().get(JSAPI_TICKET);
            }else {
            	long millisecondNow=System.currentTimeMillis();
                // 采用http GET方式请求获得jsapi_ticket
                String reqUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
                reqUrl=reqUrl.replace("ACCESS_TOKEN", basicToken());
                String info=HttpsUtils.httpsRequest(reqUrl, "GET", null);
                logger.info("获取jsapi_ticket，返回报文===>" + info);
                TicketDTO dto=gson.fromJson(info, TicketDTO.class);
                if (dto!=null && StringUtils.isNotBlank(dto.getTicket())){
                    ticket=dto.getTicket();
                    result.setTicket(ticket);
                    // 多減去5秒
                    long millisecond=7200000 - (System.currentTimeMillis() - millisecondNow) - 5000;
                    if (StringUtils.isNotBlank(ticket)){
                        // 放入redis中，key为JSAPI_TICKET
                        redisTemplate.opsForValue().set(JSAPI_TICKET, ticket, millisecond, TimeUnit.MILLISECONDS);
                    }
                }else {
                    resultRestResponse.setResMessage("获取jsapi_ticket===>" + info);
                    resultRestResponse.setRespCode(ErrorCode.SERVER_ERROR);
                    resultRestResponse.setData(result);
                    return gson.toJson(resultRestResponse);
                }
            }
            // 时间戳和随机字符串
            String timestamp=String.valueOf(System.currentTimeMillis() / 1000);
            String noncestr=UUID.randomUUID().toString().replace("-", "").substring(0, 16);
            // 将参数排序并拼接字符串
            String str="jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + pageUrl;
            // 将字符串进行sha1加密
            String signature= WechatUtil.SHA1(str);
            logger.info("参数===>" + str + "，签名===>" + signature);

            result.setTimestamp(timestamp);
            result.setNoncestr(noncestr);
            result.setSignature(signature);
            result.setAppId(wechatAccountConfig.getAppid());

            resultRestResponse.setRespCode(RestResponse.SUCCESS_CODE);
            resultRestResponse.setData(result);
            resultRestResponse.setResMessage("请求成功");
            logger.info("获取签名成功，返回信息===>" + gson.toJson(resultRestResponse));
        } catch (Exception e) {
            resultFlag=false;
            logger.error("WechatController.getSignature出错===>" + e.toString());
        } finally {
            SunfireLogBuilder.getInstance("getSignature", System.currentTimeMillis() - startTime, true, null, null).print();
        }
        return gson.toJson(resultRestResponse);
    }

    //第三步：刷新access_token（如果需要）
    @RequestMapping("/getRefreshToken")
    public String getRefreshToken(@RequestParam("refreshToken") String refreshToken){
        String reqUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        reqUrl=reqUrl.replace("APPID", wechatAccountConfig.getAppid()).replace("REFRESH_TOKEN", refreshToken);
        String info=HttpsUtils.httpsRequest(reqUrl, "GET", null);
        logger.info("第三步：刷新access_token，返回报文===>" + info);
        return info;
    }

    // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
    @RequestMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("accessToken") String accessToken, @RequestParam("openId") String openId){
        String reqUrl="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        reqUrl=reqUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        String info=HttpsUtils.httpsRequest(reqUrl, "GET", null);
        return info;
    }

    //自定义菜单创建接口
    @RequestMapping("/getCreateMenu")
    public String getCreateMenu(@RequestParam("createMenuToken") String createMenuToken, @RequestParam("menu") String menu){
        String reqUrl="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        reqUrl=reqUrl.replace("ACCESS_TOKEN", createMenuToken);
        try {
            //BASE64解码
            BASE64Decoder decoder=new BASE64Decoder();
            menu=new String(decoder.decodeBuffer(menu), "utf-8");
        } catch (IOException e) {
            logger.error("自定义菜单创建接口，BASE64解码出错===>" + e.getMessage());
        }
        String info=HttpsUtils.httpsRequest(reqUrl, "POST", menu);
        logger.info("自定义菜单创建接口，返回报文===>" + info);
        return info;
    }

    //自定义菜单删除接口
    @RequestMapping("/getDeleteMenu")
    public String getDeleteMenu(@RequestParam("deleteMenuToken") String deleteMenuToken){
        String reqUrl="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
        reqUrl=reqUrl.replace("ACCESS_TOKEN", deleteMenuToken);
        String info=HttpsUtils.httpsRequest(reqUrl, "GET", null);
        logger.info("自定义菜单删除接口，返回报文===>" + info);
        return info;
    }

    //生成授权链接
    @RequestMapping("/getAuthorizationLink")
    public String getAuthorizationLink(@RequestParam("menuUrl") String menuUrl, @RequestParam("state") String state){
        // 生成授权链接
        String authorizationLink=wxMpService.oauth2buildAuthorizationUrl(menuUrl,
                WxConsts.OAuth2Scope.SNSAPI_USERINFO, state);
        logger.info("生成授权链接===>" + authorizationLink);
        return authorizationLink;
    }

    // 获取基础token
    // https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
    @RequestMapping("/getBasicToken")
    public String getBasicToken(){
        return basicToken();
    }

    private String basicToken(){
        //从redis缓存中取
        if (redisTemplate.hasKey(WX_BASIC_ACCESS_TOKEN)){
            logger.info("WX_BASIC_ACCESS_TOKEN过期时间===>" + redisTemplate.getExpire(WX_BASIC_ACCESS_TOKEN) * 1000 + "毫秒");
            return redisTemplate.opsForValue().get(WX_BASIC_ACCESS_TOKEN);
        }
        long millisecondNow=System.currentTimeMillis();
        // 获取基础token（GET） 限200（次/天）
        String reqUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        reqUrl=reqUrl.replace("APPID", wechatAccountConfig.getAppid())
                .replace("APPSECRET", wechatAccountConfig.getSecret());
        String info=HttpsUtils.httpsRequest(reqUrl, "GET", null);
        logger.info("获取基础token，返回报文===>" + info);
        BasicTokenDTO token=gson.fromJson(info, BasicTokenDTO.class);
        String accessToken="";
        if (token!=null && token.getAccess_token()!=null){
            accessToken=token.getAccess_token();
        }
        // 访问超时，或者访问出错，accessToken为空时，不放入redis中
        if (StringUtils.isEmpty(accessToken)){
            return accessToken;
        }
        // 多減去5秒
        long millisecond=7200000 - (System.currentTimeMillis() - millisecondNow) - 5000;
        // 放入redis中，key为WX_BASIC_ACCESS_TOKEN
        redisTemplate.opsForValue().set(WX_BASIC_ACCESS_TOKEN, accessToken, millisecond, TimeUnit.MILLISECONDS);
        logger.info("放入redis成功，WX_BASIC_ACCESS_TOKEN过期时间===>" + millisecond + "毫秒" + "，accessToken===>" + accessToken);
        return accessToken;
    }

    // 发送模板消息
    // https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277
    @RequestMapping("/sendTemplateMsg")
    public String sendTemplateMsg(@RequestParam("data") String data){
        String reqUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        reqUrl=reqUrl.replace("ACCESS_TOKEN", getBasicToken());
        String info=HttpsUtils.httpsRequest(reqUrl, "POST", data);
        logger.info("发送模板消息接口，返回报文===>" + info);
        return info;
    }

}
