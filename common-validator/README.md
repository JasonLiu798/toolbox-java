#history



v6.0.3 增加NotExistWith校验器
v6.0.2 增加ExistWith校验器（exist可参考其他字段值作为查询条件）
v6.0.1 依赖包更新

v1.0 校验框架搭建，提供基础校验器，校验表单，字段校验器
v1.1 增加ehcache缓存，新增refForm
v1.2 字段类型新增
    FormField，字段可以为另一个表单，嵌套校验
    ListField，字段可以为List，循环嵌套校验
v1.3 新增更细粒度Require各类校验器，增加form内部多线程隔离
v1.4 新增日期范围校验

---
#自带校验规则
##空值校验
规则    | 规则含义 | 校验的正则
--------|---------|-----------
optional | 可为空   |
require  | 不能为空 | 
requireIf| 如果指定字段f值等于x，则不能为空
requireWith | 指定字段f1,..fn，至少一个不为空，则不能为空
requireWithAll | 指定字段f1,..fn，都不为空，则不能为空
requireWithOut | 指定字段f1,..fn，至少一个为空，则不能为空
requireWithOutAll | 指定字段f1,..fn，都为空，则不能为空

##常用
规则    | 规则含义 | 校验的正则
--------|---------|-----------
alpha           | 字母            | 
alphaNum        | 字母数字        | 
alphaNumDash    | 字母数字下划线  | 
email           | 邮箱    | ^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$
ipv4    | ip    |
timestamp    | 时间戳 |
url    | url |
uuid    | uuid |
isBoolean | boolean | 0,1,true,false,t,f,y,n,yes,no大小写均可
chinese | 中文    | [\u4e00-\uFA29]+

numeric | 正整数  | ^[1-9]\\d*|0$
mobile  | 手机    | ^1\\d{10}$
reg:    | 用户自定义正则  |  使用冒号后的规则
time    | 时分    | ^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$
zo      | 0或1    | ^0|1$


#缓存
按form缓存，校验器暂无缓存，由于校验器粒度太小，且和参数，错误信息等相关，缓存key生成较麻烦，目前缓存整个表单

