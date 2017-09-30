package com.atjl.dbtiming.helper;

import com.atjl.dbtiming.domain.gen.GenTask;
import com.atjl.dbtiming.service.TimingServiceImpl;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.common.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.atjl.dbtiming.domain.biz.TimingConstant;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * lock helper
 */
@Component
public class TimingLockHelper {
    private static final Logger logger = LoggerFactory.getLogger(TimingLockHelper.class);
    @Resource
    TimingCommonHelper timingCommonHelper;
    @Resource
    TimingDbHelper timingDbHelper;

    /**
     * is having lock
     *
     * @param mutex
     * @param mutexTm
     * @return
     */
    public boolean hasLock(String mutex, Long mutexTm) {
        Long now = DateUtil.getNowTS();
        if (StringCheckUtil.equal(mutex,
                TimingConstant.Y)) {
            Long mutexInterval = now - mutexTm;
            if (mutexInterval < TimingConstant.MUTEX_INTERVAL) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLock(Long mutexTm) {
        if (mutexTm == null) {
            return false;
        }
        Long now = DateUtil.getNowTS();
        Long mutexInterval = now - mutexTm;
        if (mutexInterval < TimingConstant.MUTEX_INTERVAL) {
            return true;
        }
        return false;
    }

    /**
     * start task,set processing Y
     * pre: task exist
     * todo: add checker, stop dead lock
     */
    public boolean lock(Long tid) {
        boolean res = false;
        long curtid = Thread.currentThread().getId();

        //start trans
        DataSourceTransactionManager transactionManager = timingDbHelper.getTransManager();
        if (transactionManager == null) {
            return res;
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        TransactionStatus transactionStatus = transactionManager.getTransaction(def);

        DataSource ds = transactionManager.getDataSource();
        if (ds == null) {
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "select " +
                TimingConstant.COL_MUTEX + "," +
                TimingConstant.COL_MUTEX_TM + "," +
                TimingConstant.COL_STATUS + "," +
                TimingConstant.COL_ALIVE_TM +
                " from ts_task where TID=? and VALID = 'Y' for update";
        String updateSql = "update ts_task set " + TimingConstant.COL_MUTEX + " = 'Y',MUTEX_TM=? where TID = ? and VALID= 'Y' ";
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, tid);
            ResultSet rs = pstmt.executeQuery();
            String mutex = null;
            Long mutexTm = 0L;
            String status = null;
            Long aliveTm = 0L;
            while (rs.next()) {
                mutex = rs.getString(TimingConstant.COL_MUTEX);
                mutexTm = rs.getLong(TimingConstant.COL_MUTEX_TM);
                status = rs.getString(TimingConstant.COL_STATUS);
                aliveTm = rs.getLong(TimingConstant.COL_ALIVE_TM);
            }
            rs.close();
            pstmt.close();
            if (mutexTm == null) {
                mutexTm = 0L;
            }
            if (aliveTm == null) {
                aliveTm = 0L;
            }

            Long now = DateUtil.getNowTS();
            Long mutexInterval = now - mutexTm;
            Long aliveInterval = now - aliveTm;
            if (logger.isDebugEnabled()) {
                logger.debug("get tid {},mutex {},mutex tm {},interval {},alive {},aliveInterval {}",
                        tid, mutex, mutexTm, mutexInterval, aliveTm, aliveInterval);
            }

            if ((StringCheckUtil.equal(mutex, TimingConstant.Y)
                    && mutexInterval < TimingConstant.MUTEX_INTERVAL
            ) || (
                    TimingConstant.validCanExeStatus(status) && aliveInterval < TimingConstant.NOT_ALIVE_REF)
                    ) {
                //other thread get lock,do nothing
                if (logger.isDebugEnabled()) {
                    logger.debug("get lock fail,tid " + curtid);
                }
                res = false;
            } else {
                //no body processing
                pstmt = conn.prepareStatement(updateSql);
                pstmt.setLong(1, now);
                pstmt.setLong(2, tid);
                boolean updateRes = pstmt.execute();
                if (logger.isDebugEnabled()) {
                    logger.debug("get lock succ,tid " + curtid + ",res " + updateRes);
                }
                res = true;

            }
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            LogDbUtil.error(TimingServiceImpl.class, "execute start processing trans exception ", e);
            res = false;
        } finally {
            if( pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    LogDbUtil.error(TimingConstant.MODULE_TIMING, "close pstmt error ", e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LogDbUtil.error(TimingConstant.MODULE_TIMING, "close conn error ", e);
                }
            }
        }
        return res;
    }

    /**
     * unlock
     *
     * @param tid
     * @return
     */
    public boolean unlock(Long tid) {
        GenTask t = new GenTask();
        t.setTid(tid);
        t.setTmutex(TimingConstant.N);
        t.setMutexTm(DateUtil.getNowTS());
        return timingDbHelper.updateTaskByPk(t);
    }

}
