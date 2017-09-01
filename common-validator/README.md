#Validate
v1.5

#配置
##AOP方式
1. 添加要校验的函数
返回值：框架内DTO ValidateResultDto
函数内容：其他操作或逻辑校验
```java
public ValidateResultDto check(RequestDto ulm) {
	....//逻辑操作或校验
	return new ValidateResultDto();
}
```

2. 配置AOP
aop:pointcut 配置为刚写的函数
具体规则见spring文档
```xml
	<!-- 定义aspectj -->
    <aop:aspectj-autoproxy proxy-target-class="true" />
    <!-- validate 框架加载 -->
    <bean id="validateParamAdvice" class="com.juanpi.validate.core.ValidateParamAdvice" />
    <aop:config>
        <aop:aspect id="validateaop" ref="validateParamAdvice">
            <aop:pointcut id="validateService" expression="execution(* com.juanpi.member.helper.*Helper.check(..))"/>
            <!-- || execution(* com.juanpi.processor.Processor.load(..)) " /> -->
            <aop:around pointcut-ref="validateService" method="doAround" />
        </aop:aspect>
    </aop:config>
```

3. 配置校验xml类
classpath:validate/*-validator.xml
actionValidator
	actionUrl：校验方法所在类
param
	name：参数名
	rule：校验规则，多个规则使用空格分隔，规则中有空格使用转义字符 &#xA0; 替换

```
<actionValidators>
    <!-- 不加null，默认校验非空-->
    <!-- 手机认证 -->
    <actionValidator actionUrl="com.juanpi.member.helper.MobileBindHelper">
        <param name="uid" rule="numeric" />
        <param name="mobile" rule="mobile" />
        <param name="oldmobile" rule="mobile null" />
        <param name="isvest" rule="zo null" />
    </actionValidator>
<actionValidators>
```

目前支持的校验规则：
规则    | 规则含义 | 校验的正则
--------|---------|-----------
numeric | 正整数  | ^[1-9]\\d*|0$
mobile  | 手机    | ^1\\d{10}$
email   | 邮箱    | ^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$
reg:    | 用户自定义正则  |  使用冒号后的规则
time    | 时分    | ^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$
zo      | 0或1    | ^0|1$
chinese | 中文    | [\u4e00-\uFA29]+


##手动调用方式
与AOP方式相同
函数原型：
```
ValidateResultDto Validator.validate(String url,Object param);
//调用方式
ValidateResultDto res = Validator.validate(String url,);
```

----
#todo list
* 使用配置文件配置正则规则
* 返回 出错提示 类型改为 list
* 自定义方法
* 区间校验
* before:date
* 存在性校验(db)
* Unique (Database)
* Not In
* Max
* Min
* length
* 自定义出错提示
