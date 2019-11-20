# 基本介绍
官方QQ交流群号:99135252;<br/>
Vert.X-generator是基于javafx8开发的图形界面Vert.x代码生成器,使用 <a target="_blank" href="https://freemarker.apache.org/">Apache FreeMarker</a> 
作为代码文件的模板,用户可以一键将数据库中的表生成为任意风格的.java代码文件(比如经典的三层模型);<br>
该工具支持所有实现JDBC规范的数据库;默认集成了Oracle , SqlServer , MySQL , PostgreSql数据库驱动jar包;<br>
该工具主要面向Vert.x这个tool-kit <a href="http://vertx.io" target="_blank">http://vertx.io</a> 如果你的Spring用户欢迎你使用 <a href="https://github.com/EliMirren/Spring-generator" target="_blank">Spring-generator</a><br>
虽然它面向Vert.x,但它并不仅仅局限于生成Vert.x代码,它的使命是将数据库中表的属性提取为实体类属性,剩下的事情就取决你如何使用FreeMarker操作它<br>
```html
Vert.x-generator不是框架它不会影响任何现有的结构,Vert.x-generator只是一个生成工具,不拘于语言,只取决于你怎么使用FreeMarker编写模板,SqlAssist是一个非常好用的帮助工具能做动态查询
``` 
# 执行方法
执行方式在项目根目录执行mvn jfx:jar进行打包;jar打包为jar包native打包为系统安装软件;<br/>
该软件基于jdk1.8.66开发,测试环境1.8.121,已知最低要求特性为1.8.40,理论上大于1.8.40就可以运行;没有1.8环境的可以自己下载一个jre包并编写执行脚本运行就可以,可以参考文档说明<br/>
```html
mvn jfx:jar
编译后进入target/jfx/app/ 双击执行VertX-Generator.jar  或者 java -jar VertX-Generator.jar
```

# 软件使用说明
[vertx-generator的使用帮助文档](http://mirren.gitee.io/vertx-generator-doc/)<br/>

# 模板仓库
[vertx-generator的模板仓库](https://github.com/EliMirren/Vert.X-generator-Template)<br/>

# 工具主页<br/>
![index](https://raw.githubusercontent.com/shenzhenMirren/MyGithubResources/master/image/vert.x-generator-index.png) 

# 特色功能预览：<br/>
<ol>
	<li>生成实体类</li>
	<li>生成WEB API相关接口 Router</li>
	<li>生成业务逻辑相关的Service/ServiceImpl</li>
	<li>生成操作数据库的SQL类</li>
	<li>生成单元测试</li>
	<li>软件最大的特色就是操作SQL类的SqlAssist查询帮助类(Assist是特别定制的查询工具,使用该工具一切操作都会变得超简单,比如:分页通过Assist只需要设置2个参数就可以实现,比如参数1=10,参数2=5,查询出来就是第10行到15行的数据,同时也可以防注入动态添加查询添加,去重,排序,自定义返回列等);
	</li>
</ol>

  


