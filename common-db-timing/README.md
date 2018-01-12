

#todo
整体重构




#!!!
ConfigUtil，默认使用DB_PLAIN

#v17.06.01
##bug 备份task到ts_task_runned报crtTm字段类型不匹配
修改ts_task_runned表自动生成的GenTaskRunned对象的crtTm字段，修改为时间戳类型。
波及类：
TimingInnerManager，修改delTaskAndBackup和clearTaskNotInDbButInPool，手动转换并拷贝ts_task对象的crtTm字段

##增加手动清理任务接口
List<GenTask> manualClearTask()
