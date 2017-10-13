package com.atjl.dbtiming.domain.gen;

import java.util.ArrayList;
import java.util.List;

public class GenTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GenTaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTidIsNull() {
            addCriterion("TID is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("TID is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Long value) {
            addCriterion("TID =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Long value) {
            addCriterion("TID <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Long value) {
            addCriterion("TID >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Long value) {
            addCriterion("TID >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Long value) {
            addCriterion("TID <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Long value) {
            addCriterion("TID <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Long> values) {
            addCriterion("TID in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Long> values) {
            addCriterion("TID not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Long value1, Long value2) {
            addCriterion("TID between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Long value1, Long value2) {
            addCriterion("TID not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTkeyIsNull() {
            addCriterion("TKEY is null");
            return (Criteria) this;
        }

        public Criteria andTkeyIsNotNull() {
            addCriterion("TKEY is not null");
            return (Criteria) this;
        }

        public Criteria andTkeyEqualTo(String value) {
            addCriterion("TKEY =", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotEqualTo(String value) {
            addCriterion("TKEY <>", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyGreaterThan(String value) {
            addCriterion("TKEY >", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyGreaterThanOrEqualTo(String value) {
            addCriterion("TKEY >=", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLessThan(String value) {
            addCriterion("TKEY <", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLessThanOrEqualTo(String value) {
            addCriterion("TKEY <=", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLike(String value) {
            addCriterion("TKEY like", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotLike(String value) {
            addCriterion("TKEY not like", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyIn(List<String> values) {
            addCriterion("TKEY in", values, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotIn(List<String> values) {
            addCriterion("TKEY not in", values, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyBetween(String value1, String value2) {
            addCriterion("TKEY between", value1, value2, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotBetween(String value1, String value2) {
            addCriterion("TKEY not between", value1, value2, "tkey");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("TASK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("TASK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(String value) {
            addCriterion("TASK_TYPE =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(String value) {
            addCriterion("TASK_TYPE <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(String value) {
            addCriterion("TASK_TYPE >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(String value) {
            addCriterion("TASK_TYPE <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLike(String value) {
            addCriterion("TASK_TYPE like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotLike(String value) {
            addCriterion("TASK_TYPE not like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<String> values) {
            addCriterion("TASK_TYPE in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<String> values) {
            addCriterion("TASK_TYPE not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(String value1, String value2) {
            addCriterion("TASK_TYPE between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(String value1, String value2) {
            addCriterion("TASK_TYPE not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andDatasIsNull() {
            addCriterion("DATAS is null");
            return (Criteria) this;
        }

        public Criteria andDatasIsNotNull() {
            addCriterion("DATAS is not null");
            return (Criteria) this;
        }

        public Criteria andDatasEqualTo(String value) {
            addCriterion("DATAS =", value, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasNotEqualTo(String value) {
            addCriterion("DATAS <>", value, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasGreaterThan(String value) {
            addCriterion("DATAS >", value, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasGreaterThanOrEqualTo(String value) {
            addCriterion("DATAS >=", value, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasLessThan(String value) {
            addCriterion("DATAS <", value, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasLessThanOrEqualTo(String value) {
            addCriterion("DATAS <=", value, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasLike(String value) {
            addCriterion("DATAS like", value, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasNotLike(String value) {
            addCriterion("DATAS not like", value, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasIn(List<String> values) {
            addCriterion("DATAS in", values, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasNotIn(List<String> values) {
            addCriterion("DATAS not in", values, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasBetween(String value1, String value2) {
            addCriterion("DATAS between", value1, value2, "datas");
            return (Criteria) this;
        }

        public Criteria andDatasNotBetween(String value1, String value2) {
            addCriterion("DATAS not between", value1, value2, "datas");
            return (Criteria) this;
        }

        public Criteria andTmutexIsNull() {
            addCriterion("TMUTEX is null");
            return (Criteria) this;
        }

        public Criteria andTmutexIsNotNull() {
            addCriterion("TMUTEX is not null");
            return (Criteria) this;
        }

        public Criteria andTmutexEqualTo(String value) {
            addCriterion("TMUTEX =", value, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexNotEqualTo(String value) {
            addCriterion("TMUTEX <>", value, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexGreaterThan(String value) {
            addCriterion("TMUTEX >", value, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexGreaterThanOrEqualTo(String value) {
            addCriterion("TMUTEX >=", value, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexLessThan(String value) {
            addCriterion("TMUTEX <", value, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexLessThanOrEqualTo(String value) {
            addCriterion("TMUTEX <=", value, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexLike(String value) {
            addCriterion("TMUTEX like", value, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexNotLike(String value) {
            addCriterion("TMUTEX not like", value, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexIn(List<String> values) {
            addCriterion("TMUTEX in", values, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexNotIn(List<String> values) {
            addCriterion("TMUTEX not in", values, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexBetween(String value1, String value2) {
            addCriterion("TMUTEX between", value1, value2, "tmutex");
            return (Criteria) this;
        }

        public Criteria andTmutexNotBetween(String value1, String value2) {
            addCriterion("TMUTEX not between", value1, value2, "tmutex");
            return (Criteria) this;
        }

        public Criteria andMutexTmIsNull() {
            addCriterion("MUTEX_TM is null");
            return (Criteria) this;
        }

        public Criteria andMutexTmIsNotNull() {
            addCriterion("MUTEX_TM is not null");
            return (Criteria) this;
        }

        public Criteria andMutexTmEqualTo(Long value) {
            addCriterion("MUTEX_TM =", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmNotEqualTo(Long value) {
            addCriterion("MUTEX_TM <>", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmGreaterThan(Long value) {
            addCriterion("MUTEX_TM >", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmGreaterThanOrEqualTo(Long value) {
            addCriterion("MUTEX_TM >=", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmLessThan(Long value) {
            addCriterion("MUTEX_TM <", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmLessThanOrEqualTo(Long value) {
            addCriterion("MUTEX_TM <=", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmIn(List<Long> values) {
            addCriterion("MUTEX_TM in", values, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmNotIn(List<Long> values) {
            addCriterion("MUTEX_TM not in", values, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmBetween(Long value1, Long value2) {
            addCriterion("MUTEX_TM between", value1, value2, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmNotBetween(Long value1, Long value2) {
            addCriterion("MUTEX_TM not between", value1, value2, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andLastExecutorIsNull() {
            addCriterion("LAST_EXECUTOR is null");
            return (Criteria) this;
        }

        public Criteria andLastExecutorIsNotNull() {
            addCriterion("LAST_EXECUTOR is not null");
            return (Criteria) this;
        }

        public Criteria andLastExecutorEqualTo(String value) {
            addCriterion("LAST_EXECUTOR =", value, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorNotEqualTo(String value) {
            addCriterion("LAST_EXECUTOR <>", value, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorGreaterThan(String value) {
            addCriterion("LAST_EXECUTOR >", value, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_EXECUTOR >=", value, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorLessThan(String value) {
            addCriterion("LAST_EXECUTOR <", value, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorLessThanOrEqualTo(String value) {
            addCriterion("LAST_EXECUTOR <=", value, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorLike(String value) {
            addCriterion("LAST_EXECUTOR like", value, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorNotLike(String value) {
            addCriterion("LAST_EXECUTOR not like", value, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorIn(List<String> values) {
            addCriterion("LAST_EXECUTOR in", values, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorNotIn(List<String> values) {
            addCriterion("LAST_EXECUTOR not in", values, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorBetween(String value1, String value2) {
            addCriterion("LAST_EXECUTOR between", value1, value2, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecutorNotBetween(String value1, String value2) {
            addCriterion("LAST_EXECUTOR not between", value1, value2, "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmIsNull() {
            addCriterion("LAST_EXECUTE_TM is null");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmIsNotNull() {
            addCriterion("LAST_EXECUTE_TM is not null");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmEqualTo(Long value) {
            addCriterion("LAST_EXECUTE_TM =", value, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmNotEqualTo(Long value) {
            addCriterion("LAST_EXECUTE_TM <>", value, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmGreaterThan(Long value) {
            addCriterion("LAST_EXECUTE_TM >", value, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmGreaterThanOrEqualTo(Long value) {
            addCriterion("LAST_EXECUTE_TM >=", value, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmLessThan(Long value) {
            addCriterion("LAST_EXECUTE_TM <", value, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmLessThanOrEqualTo(Long value) {
            addCriterion("LAST_EXECUTE_TM <=", value, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmIn(List<Long> values) {
            addCriterion("LAST_EXECUTE_TM in", values, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmNotIn(List<Long> values) {
            addCriterion("LAST_EXECUTE_TM not in", values, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmBetween(Long value1, Long value2) {
            addCriterion("LAST_EXECUTE_TM between", value1, value2, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andLastExecuteTmNotBetween(Long value1, Long value2) {
            addCriterion("LAST_EXECUTE_TM not between", value1, value2, "lastExecuteTm");
            return (Criteria) this;
        }

        public Criteria andTstatusIsNull() {
            addCriterion("TSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andTstatusIsNotNull() {
            addCriterion("TSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTstatusEqualTo(String value) {
            addCriterion("TSTATUS =", value, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusNotEqualTo(String value) {
            addCriterion("TSTATUS <>", value, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusGreaterThan(String value) {
            addCriterion("TSTATUS >", value, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusGreaterThanOrEqualTo(String value) {
            addCriterion("TSTATUS >=", value, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusLessThan(String value) {
            addCriterion("TSTATUS <", value, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusLessThanOrEqualTo(String value) {
            addCriterion("TSTATUS <=", value, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusLike(String value) {
            addCriterion("TSTATUS like", value, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusNotLike(String value) {
            addCriterion("TSTATUS not like", value, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusIn(List<String> values) {
            addCriterion("TSTATUS in", values, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusNotIn(List<String> values) {
            addCriterion("TSTATUS not in", values, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusBetween(String value1, String value2) {
            addCriterion("TSTATUS between", value1, value2, "tstatus");
            return (Criteria) this;
        }

        public Criteria andTstatusNotBetween(String value1, String value2) {
            addCriterion("TSTATUS not between", value1, value2, "tstatus");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("VALID is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("VALID is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(String value) {
            addCriterion("VALID =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(String value) {
            addCriterion("VALID <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(String value) {
            addCriterion("VALID >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(String value) {
            addCriterion("VALID >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(String value) {
            addCriterion("VALID <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(String value) {
            addCriterion("VALID <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLike(String value) {
            addCriterion("VALID like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotLike(String value) {
            addCriterion("VALID not like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<String> values) {
            addCriterion("VALID in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<String> values) {
            addCriterion("VALID not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(String value1, String value2) {
            addCriterion("VALID between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(String value1, String value2) {
            addCriterion("VALID not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andCrtTmIsNull() {
            addCriterion("CRT_TM is null");
            return (Criteria) this;
        }

        public Criteria andCrtTmIsNotNull() {
            addCriterion("CRT_TM is not null");
            return (Criteria) this;
        }

        public Criteria andCrtTmEqualTo(Long value) {
            addCriterion("CRT_TM =", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmNotEqualTo(Long value) {
            addCriterion("CRT_TM <>", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmGreaterThan(Long value) {
            addCriterion("CRT_TM >", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmGreaterThanOrEqualTo(Long value) {
            addCriterion("CRT_TM >=", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmLessThan(Long value) {
            addCriterion("CRT_TM <", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmLessThanOrEqualTo(Long value) {
            addCriterion("CRT_TM <=", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmIn(List<Long> values) {
            addCriterion("CRT_TM in", values, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmNotIn(List<Long> values) {
            addCriterion("CRT_TM not in", values, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmBetween(Long value1, Long value2) {
            addCriterion("CRT_TM between", value1, value2, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmNotBetween(Long value1, Long value2) {
            addCriterion("CRT_TM not between", value1, value2, "crtTm");
            return (Criteria) this;
        }

        public Criteria andTkeyLikeInsensitive(String value) {
            addCriterion("upper(TKEY) like", value.toUpperCase(), "tkey");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLikeInsensitive(String value) {
            addCriterion("upper(TASK_TYPE) like", value.toUpperCase(), "taskType");
            return (Criteria) this;
        }

        public Criteria andDatasLikeInsensitive(String value) {
            addCriterion("upper(DATAS) like", value.toUpperCase(), "datas");
            return (Criteria) this;
        }

        public Criteria andTmutexLikeInsensitive(String value) {
            addCriterion("upper(TMUTEX) like", value.toUpperCase(), "tmutex");
            return (Criteria) this;
        }

        public Criteria andLastExecutorLikeInsensitive(String value) {
            addCriterion("upper(LAST_EXECUTOR) like", value.toUpperCase(), "lastExecutor");
            return (Criteria) this;
        }

        public Criteria andTstatusLikeInsensitive(String value) {
            addCriterion("upper(TSTATUS) like", value.toUpperCase(), "tstatus");
            return (Criteria) this;
        }

        public Criteria andValidLikeInsensitive(String value) {
            addCriterion("upper(VALID) like", value.toUpperCase(), "valid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}