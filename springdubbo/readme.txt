1.dubbo监控
2.使用controller调用消费
3.编写dubbo所理解的，与官网学习文档
	*github官网
	https://github.com/apache/dubbo
	默认是最新dubbo3.x版本，README使用的是最基础，服务创建，服务消费者（不依赖任何组件）
	*一般我们选择dubbo spring boot(基于目前项目使用框架组件spring boot)
	https://github.com/apache/dubbo-spring-boot-project
	可以选择dubbo分支2.7.x,3.0.x版本,进来后可以选择四种samples，其中两种分别是 基于zookeeper，nacos
	*最后我们点击基于`zookeeper`进去，查看分析pom.xml
	创建我们自己的dubbo骨架工程
	
	
springdubbo
	dubbo-api
		com.liyuan3210.dubbo.client
	dubbo-provider
		com.liyuan3210.dubbo.provider
		ProviderMain
	dubbo-consumer
		com.liyuan3210.dubbo.consumer
		ConsumerMain
		
 <revision>2.7.10-SNAPSHOT</revision>
