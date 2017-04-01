package com.jason798.timing;


import com.jason798.character.StringCheckUtil;
import com.jason798.common.CommonConstant;
import com.jason798.common.DateUtil;
import com.jason798.log.LogClient;
import com.jason798.timing.entity.TimingConstant;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 任务相关
 */
public class TimingTaskHelper {

    @Resource
    TimingCommonHelper timingHelper;

//    /**
//     * 开启任务
//     * @param key
//     */
//    public void startTask(String key){
//        GenTask rawTask = timingHelper.getTaskByKey(key);
//        /**
//         * 不存在，正在执行 ，则直接返回
//         */
//        if(rawTask==null
//                || StringCheckUtil.equal(CommonConstant.Y,rawTask.getExecuting())
//                ||StringCheckUtil.equal(CommonConstant.Y,rawTask.getProcessing())
//                ){
//            LogClient.writeInfo(TimingManagerImpl.class,"task notexist or already run");
//            return;
//        }
//
//        /**
//         * 检查其他参数
//         */
//        String tp = rawTask.getConfType();
//        if(!TimingConstant.validTp(tp)){
//            //未知的任务类型
//            return;
//        }
//        switch (tp){
//            case TimingConstant.TP_TM_INTERVAL:
//                /**
//                 * 计算 下次执行时间
//                 */
//                Long exeTm = rawTask.getConfStartTm();
//                Long now = DateUtil.getNowTS();
//                if(exeTm<now){
//                    //todo: 下次执行时间小于当前时间
//                    //判断是否
//
//
//                }else{
//
//                    Long nextStartTm = exeTm - now;
//                    Integer intervalTm = rawTask.getConfInterval();
//                    String unit = rawTask.getConfIntervalUnit();
//                    /**
//                     * 判断是否已经有进程执行
//                     */
//
//                    /**
//                     * 计算下次执行时间
//                     */
//                    Long delaySec = DateUtil.getIntervalSec(now, unit, intervalTm);
//                    switch (unit){
//                        //case
//                    }
//
//
//                }
//
//
//                //rawTask.get);
//                break;
//            case TimingConstant.TP_COND_INTERVAL:
//                break;
//        }
//
//        boolean preRes = startTaskPre(rawTask);
//        if(preRes){
//            /**
//             * 抢占成功，提交到线程池执行
//             */
//
//
//
//        }
//    }



//    /**
//     * 开启任务，前期处理，加独占锁，抢占处理权
//     * 设置processing=Y
//     * @param rawTask
//     */
//    public boolean startTaskPre(GenTask rawTask) {
//        String key = rawTask.getTkey();
//        boolean res = false;
//
//        long curtid = Thread.currentThread().getId();
//        /**
//         * 任务存在性， 是否已经有其他进程执行校验
//         */
//
//        Long tid = rawTask.getTid();
//        /**
//         * 手动开启事务
//         */
//        DataSourceTransactionManager transactionManager = null;
//        try {
//            transactionManager = ApplicationContextHepler.getBean(
//                    "transactionManager", DataSourceTransactionManager.class);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        if (transactionManager == null) {
//            LogClient.writeError(TimingManagerImpl.class.getSimpleName(), "get transactionManager null");
//        }
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//        def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
//        TransactionStatus transactionStatus = transactionManager.getTransaction(def);
//
//        DataSource ds = transactionManager.getDataSource();
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        PreparedStatement ustmt = null;
//        String sql = "select PROCESSING from ts_task where VALID= 'Y' AND TID=? for update";
//        String updateSql = "update ts_task set PROCESSING = 'Y' where VALID='Y' AND TID = ?";
//        try {
//            conn = ds.getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setLong(1,tid);
//            ResultSet rs = pstmt.executeQuery();
//            String processing = null;
//            while (rs.next()){
//                processing = rs.getString("PROCESSING");
//            }
//            rs.close();
//
//            System.out.println("get task id "+tid+",status "+processing);
//
//            if (StringCheckUtil.equal(processing, CommonConstant.Y)) {
//                //有其他线程 正在处理,donothing
//                System.out.println("other thread processing log "+curtid);
//                return res;
//            } else {
//                //无人处理，更新为正在处理
//                System.out.println("execsql "+updateSql);
//                ustmt = conn.prepareStatement(updateSql);
//                ustmt.setLong(1, tid );
//                boolean updateRes = ustmt.execute();
//                System.out.println("thread processing "+curtid+",res "+updateRes);
//                //SystemUtil.sleep(5*1000);//sleep 10s
//            }
//            return true;
//        } catch (Exception e) {
//            transactionManager.rollback(transactionStatus);
//            LogClient.writeError(TimingManagerImpl.class, "execute start processing trans exception ", e);
//        } finally {
//            if(pstmt!=null){
//                try {
//                    pstmt.close();
//                } catch (SQLException e) {
//                    //e.printStackTrace();
//                }
//            }
//            if(ustmt!=null){
//                try {
//                    ustmt.close();
//                } catch (SQLException e) {
//                    //e.printStackTrace();
//                }
//            }
//            if(conn!=null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    //e.printStackTrace();
//                }
//            }
//            transactionManager.commit(transactionStatus);
//        }
//        return res;
//    }


}
