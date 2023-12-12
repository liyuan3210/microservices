一.环境
openjdk11 + maven3.5

二.错误信息
1.eclipse问题
Errors occurred during the build.
Errors running builder 'Maven Project Builder' on project 'sprint_thymeleaf'.
Could not initialize class org.codehaus.plexus.archiver.jar.JarArchiver

2.直接mvn install问题
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ sprint_thymeleaf ---
[WARNING] Error injecting: org.codehaus.plexus.archiver.jar.JarArchiver
java.lang.ExceptionInInitializerError
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
三.解决办法
pom.xml添加配置
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-jar-plugin</artifactId>
	<version>2.5</version>
</plugin>
重新mvn install