SpringMVC使用 ueditor1.4.3  注意      一定不能有多个版本的ueditor.jar包，多版本就会有冲突
1、ueditor1.4.3jsp版在上传图片报"未找到上传文件"解决方案










Struts2.3 配置 ueditor1.4.3 

1、ueditor1.4.3jsp版在上传图片报"未找到上传文件"解决方案

这是因为struts2的过滤器，解决方法是自定义一个过滤器

注意上传文件的路径：  修改在  ueditor>jsp>controller.jsp中 设置上传文件路径

	//String reslPath = "D:/my";  //设置图片上传目录
	out.write( new ActionEnter( request, rootPath ).exec() );

1：看下是否是apche的jar冲突，检查一下是否存在多个以commons-io名称开始的jar
2：FileUtils 引用jar是否版本不一致，其中没有你要的方法

	解决方法   去除默认的StrutsPrepareAndExecuteFilter  过滤器  采用自己的过滤器
	<!-- 配置 Struts2 的 Filter -->
	<filter>
		<filter-name>struts2</filter-name>
		<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>struts2</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
  <filter>
    <filter-name>struts2</filter-name>
    <filter-class>com.gssh.filter.MyStrutsFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>struts2</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>