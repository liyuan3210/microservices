1.maven包引入
<dependency>
	<groupId>com.github.binarywang</groupId>
	<artifactId>weixin-java-mp</artifactId>
	<version>3.3.0</version>
</dependency>

2.接口代码
WechatController.java

3.菜单添加工具代码
WechatMenuUtils.java


4.接口获取配置菜单命令
	获取地址
	https://page.sto.cn/ux/webapp-operation/clock.html
	curl -d "menuUrl=https://page.sto.cn/ux/webapp-operation/clock.html&state=123" http://127.0.0.1:7001/wechat/getAuthorizationLink
	
	获取token执行利用`菜单添加工具代码`添加菜单
	curl http://127.0.0.1:7001/wechat/getBasicToken