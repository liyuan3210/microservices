

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Button;
import Menu;
import ViewButton;

/**
 * @author liyuan
 * @description ${todo}
 * @date 2019-03-28 9:31
 */
public class WechatMenuUtils {

	private static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static Menu getMenu() {

		Menu menu = new Menu();

		ViewButton btnCar = new ViewButton();
		// 注意按钮名字不要太长，不然会报40018错误
		btnCar.setName("发车到车");
		btnCar.setType("view");
		btnCar.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd498d31bccf2078b&redirect_uri=http%3A%2F%2Fstow.natapp1.cc%2Findex.html&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect");

		Button btnService = new Button();
		btnService.setName("服务");

		ViewButton button31 = new ViewButton();
		button31.setName("备案");
		button31.setType("view");
		button31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd498d31bccf2078b&redirect_uri=http://stow.natapp1.cc/mark.html&response_type=code&scope=snsapi_userinfo&state=123,111&connect_redirect=1#wechat_redirect");

		ViewButton button32 = new ViewButton();
		button32.setName("网点查询");
		button32.setType("view");
		button32.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd498d31bccf2078b&redirect_uri=http%3A%2F%2Fstow.natapp1.cc%2Fsearchnet.html&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect");

		ViewButton button33 = new ViewButton();
		button33.setName("操作说明");
		button33.setType("view");
		button33.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd498d31bccf2078b&redirect_uri=http%3A%2F%2Fstow.natapp1.cc%2Fexplain.html&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect");

		btnService.setSub_button(new Button[] { button31, button32,button33 });

		Button btnMe = new Button();
		btnMe.setName("我的");
		ViewButton button11 = new ViewButton();
		button11.setName("签到记录");
		button11.setType("view");
		button11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd498d31bccf2078b&redirect_uri=http%3A%2F%2Fstow.natapp1.cc%2Fclock.html&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect");

		ViewButton button12 = new ViewButton();
		button12.setName("磅单记录");
		button12.setType("view");
		button12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd498d31bccf2078b&redirect_uri=http%3A%2F%2Fstow.natapp1.cc%2Fpound.html&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect");

		ViewButton button13 = new ViewButton();
		button13.setName("运输任务");
		button13.setType("view");
		button13.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd498d31bccf2078b&redirect_uri=http%3A%2F%2Fstow.natapp1.cc%2Fcarriage.html&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect");

		ViewButton button14 = new ViewButton();
		button14.setName("我的手机号");
		button14.setType("view");
		button14.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd498d31bccf2078b&redirect_uri=http%3A%2F%2Fstow.natapp1.cc%2Fuser.html&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect");

		btnMe.setSub_button(new Button[] { button11, button12, button13,button14 });

		menu.setButton(new Button[] { btnService, btnCar, btnMe });
		return menu;
	}

	public static void main(String[] args) throws Exception{
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String str = gson.toJson(getMenu());
		System.out.println(str);

		/*//BASE64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String result = encoder.encode(str.getBytes("utf-8"));
		System.out.println(result);*/

		String accessToken = "30_o2P3Hd-0Mt3Y4xxIKQYu0NeLX5_Jw8IVkKDp7es5oq1VIlfpcEKoq56x2d5HVYSG0Y-vuZZvW_tAOvHDXyGMO7TYawjW0o7YmmomTfDkkRO4azMn8AK4HdJnXAyrMJtbpeC5xFUPIxf7RpEhIDQdAJACXK";

		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成JSON字符串
		// 发起POST请求创建菜单
		String result2 = HttpsUtils.httpsRequest(url, "POST", str);
		System.out.println("result==" + result2);

	}
}
