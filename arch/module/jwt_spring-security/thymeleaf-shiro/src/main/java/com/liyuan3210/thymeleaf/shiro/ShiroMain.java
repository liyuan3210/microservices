package com.liyuan3210.thymeleaf.shiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
   实例
 https://www.cnblogs.com/moonlightL/p/8126910.html
 * @author liyuan
 */
public class ShiroMain {
	private static final transient Logger log = LoggerFactory.getLogger(ShiroMain.class);
	public static void main(String[] args) {
		// 读取 shiro.ini 文件内容
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            System.out.println("someKey 的值：" + value);
        }
        
        //登录
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "zhangsan");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("用户名不存在:" + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            System.out.println("账户密码 " + token.getPrincipal()  + " 不正确!");
        } catch (LockedAccountException lae) {
            System.out.println("用户名 " + token.getPrincipal() + " 被锁定 !");
        }
        
        //认证后
        // 认证成功后
        if (currentUser.isAuthenticated()) {
            
            System.out.println("用户 " + currentUser.getPrincipal() + " 登陆成功！");
            
            //测试角色
            System.out.println("是否拥有 manager 角色：" + currentUser.hasRole("manager"));
            
            //测试权限
            System.out.println("是否拥有 user:create 权限" + currentUser.isPermitted("user:create"));
            
            //退出
            currentUser.logout();
        }
	}

}
