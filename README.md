# 基本介绍
官方QQ交流群号:99135252;<br/>
vertx-generator是一款基于javafx8开发的一款图形界面的vert.x逆向工程;<br/>
该工具支持Oracle , SqlServer , MySQL , PostgreSql数据库的逆向生成;<br/>
生成采用模板生成的方式,用户自定义模板,工具根据模板进行生成<br/>

# 执行方法
执行方式在项目根目录执行mvn jfx:jar进行打包;jar打包为jar包native打包为系统安装软件;<br/>
该软件基于jdk1.8.66开发,测试环境1.8.121,已知最低要求特性为1.8.40,理论上大于1.8.40就可以运行;<br/>
```html
mvn jfx:jar
编译后进入target/jfx/app/ 双击执行VertX-Generator.jar  或者 java -jar VertX-Generator.jar
```

# 软件使用说明
[vertx-generator的使用帮助文档](http://duhua.gitee.io/vertx-generator-doc/)<br/>

# 模板仓库
[vertx-generator的模板仓库](https://github.com/shenzhenMirren/vertx-generator-template)<br/>

# 工具主页<br/>
![index](https://raw.githubusercontent.com/shenzhenMirren/vertx-generator-doc/master/resource/images/index.png)

# 基本常用功能：<br/>
<ol>
<li>实体类(可以自定义：get/set,有参无参构造方法,自定义类型与属性,序列化等,toJson,formJson与自定义内容等);</li>
<li>DaoVerticle(查询数据总行数,查询全部数据，分页查询数据,通过对象查询数据,通过ID查询数据,插入全部属性,插入不为空的属性,通过ID更新全部属性,通过ID更新不为空的属性,通过Assist更新全部属性,通过Assist更新不为空的属性,通过ID删除信息,通过Assist删除信息,批量插入数据等);</li>
<li>ServiceVerticle(与dao差不多),主要用于做相依的逻辑处理,模板可以选择非空判断与长度判断,可选项;</li>
<li>Router(与dao和Service差不多),主要用于接收和返回结果,可选项;</li>
<li>查询工具Assist,主要用于动态生成SQL语句(Assist为特别定制的查询工具,使用该工具一切操作都变得超简单,比如：分页通过Assist只需要设置2个参数就可以实现比如参数1=10,参数2=5,查询出来就是第10行到15行的数据,同时也可以防注入动态添加查询添加,去重,排序,自定义返回列等)</li>
<li>
SQL类,SQL类继承自AbstractSQL(所有SQL的父类,里面拥有一系列SQL语句),该类用于返回操作数据库的Sql和参数Params,用户可以拿其结果进行执行
</li>
</ol>

  


