package com.atjl.dbtiming.mapper.biz;

import org.apache.ibatis.annotations.Param;
import com.atjl.dbtiming.domain.biz.TaskDomain;
import java.util.List;

/**
 * task mapper
 */
public interface TaskMapper {
    TaskDomain getTask(Long tid);
    List<TaskDomain> getTasks(@Param("valid") String showValid,
                             @Param("showEnd") String showEnd);

}
