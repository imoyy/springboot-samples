# springboot-hello 结构笔记

## 包名 com.hxc.springboothello

用 Spring Initializr 生成时，artifactId 填的 `springboot-hello`，包名就直接把中划线去掉变成 `springboothello` 了。
实际项目里包名一般是 `com.hxc.hello` 或者按模块拆，这种平铺的命名方式后面加东西会比较乱。

## pom.xml 里一堆空标签

`<licenses>`、`<developers>`、`<scm>` 这几个块都是空的，Initializr 默认生成的模板带着。
不影响构建，但留着也没意义，实际项目里要么填上要么删掉。

## contextLoads 测试

`SpringbootHelloApplicationTests` 里只有一个空的 `contextLoads()` 测试，作用是跑一次完整的 Spring 上下文加载，
确保配置没问题就算通过。看起来什么都没测，但如果 Bean 初始化报错，这个测试会挂掉 —— 算是最低限度的冒烟测试。

## application.properties vs application.yml

默认生成的是 `.properties` 格式，只有一行 `spring.application.name=springboot-hello`。
`.yml` 层级结构更清晰，但对于现在这个空项目来说无所谓，等配置多了再考虑要不要换。

## spring-boot-starter-web 依赖了什么

引入 `spring-boot-starter-web` 之后，Tomcat、Spring MVC、Jackson 都跟着进来了，
现在项目里没有一个 Controller，Tomcat 照样会在启动时占用 8080 端口 —— 感觉有点浪费，
不过这本来就是 Web 项目的起点，后面加接口自然就用上了。
