#todo list
##功能
Field增加默认值，如果为空，则添加，空则不添加(会修改原始值)
Field是否trim处理(会修改原始值)

##优化
递归校验，错误信息展示优化

多个校验器顺序的校验



---
#ok
##校验规则开发
* before:date
* required_if:anotherfield,value,…
验证字段在另一个字段等于指定值value时是必须的
* required_unless:anotherfield,value,…
除了 anotherfield字段等于value，验证字段不能空
* required_with:foo,bar,…
验证字段只有在任一其它指定字段存在的话才是必须的
* required_with_all:foo,bar,…
验证字段只有在所有指定字段存在的情况下才是必须的
* required_without:foo,bar,…
验证字段只有当任一指定字段不存在的情况下才是必须的
* required_without_all:foo,bar,…
验证字段只有当所有指定字段不存在的情况下才是必须的
* same:field
给定字段和验证字段必须匹配


##功能点
* 校验器通过字符串初始化 
    - 字符串初步识别分类
    - 无参数 ok
    - 单个参数 
    - 多个参数 

* 对象递归 校验
* 集合字段校验


