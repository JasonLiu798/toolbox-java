package com.atjl.dbtiming.domain.gen;

import java.util.ArrayList;
import java.util.List;

public class GenTaskHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GenTaskHistoryExample() {
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

        public Criteria andHtidIsNull() {
            addCriterion("HTID is null");
            return (Criteria) this;
        }

        public Criteria andHtidIsNotNull() {
            addCriterion("HTID is not null");
            return (Criteria) this;
        }

        public Criteria andHtidEqualTo(Long value) {
            addCriterion("HTID =", value, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidNotEqualTo(Long value) {
            addCriterion("HTID <>", value, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidGreaterThan(Long value) {
            addCriterion("HTID >", value, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidGreaterThanOrEqualTo(Long value) {
            addCriterion("HTID >=", value, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidLessThan(Long value) {
            addCriterion("HTID <", value, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidLessThanOrEqualTo(Long value) {
            addCriterion("HTID <=", value, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidIn(List<Long> values) {
            addCriterion("HTID in", values, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidNotIn(List<Long> values) {
            addCriterion("HTID not in", values, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidBetween(Long value1, Long value2) {
            addCriterion("HTID between", value1, value2, "htid");
            return (Criteria) this;
        }

        public Criteria andHtidNotBetween(Long value1, Long value2) {
            addCriterion("HTID not between", value1, value2, "htid");
            return (Criteria) this;
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

        public Criteria andProcessorIsNull() {
            addCriterion("PROCESSOR is null");
            return (Criteria) this;
        }

        public Criteria andProcessorIsNotNull() {
            addCriterion("PROCESSOR is not null");
            return (Criteria) this;
        }

        public Criteria andProcessorEqualTo(String value) {
            addCriterion("PROCESSOR =", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotEqualTo(String value) {
            addCriterion("PROCESSOR <>", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorGreaterThan(String value) {
            addCriterion("PROCESSOR >", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESSOR >=", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLessThan(String value) {
            addCriterion("PROCESSOR <", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLessThanOrEqualTo(String value) {
            addCriterion("PROCESSOR <=", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLike(String value) {
            addCriterion("PROCESSOR like", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotLike(String value) {
            addCriterion("PROCESSOR not like", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorIn(List<String> values) {
            addCriterion("PROCESSOR in", values, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotIn(List<String> values) {
            addCriterion("PROCESSOR not in", values, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorBetween(String value1, String value2) {
            addCriterion("PROCESSOR between", value1, value2, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotBetween(String value1, String value2) {
            addCriterion("PROCESSOR not between", value1, value2, "processor");
            return (Criteria) this;
        }

        public Criteria andThreadIsNull() {
            addCriterion("THREAD is null");
            return (Criteria) this;
        }

        public Criteria andThreadIsNotNull() {
            addCriterion("THREAD is not null");
            return (Criteria) this;
        }

        public Criteria andThreadEqualTo(String value) {
            addCriterion("THREAD =", value, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadNotEqualTo(String value) {
            addCriterion("THREAD <>", value, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadGreaterThan(String value) {
            addCriterion("THREAD >", value, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadGreaterThanOrEqualTo(String value) {
            addCriterion("THREAD >=", value, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadLessThan(String value) {
            addCriterion("THREAD <", value, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadLessThanOrEqualTo(String value) {
            addCriterion("THREAD <=", value, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadLike(String value) {
            addCriterion("THREAD like", value, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadNotLike(String value) {
            addCriterion("THREAD not like", value, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadIn(List<String> values) {
            addCriterion("THREAD in", values, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadNotIn(List<String> values) {
            addCriterion("THREAD not in", values, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadBetween(String value1, String value2) {
            addCriterion("THREAD between", value1, value2, "thread");
            return (Criteria) this;
        }

        public Criteria andThreadNotBetween(String value1, String value2) {
            addCriterion("THREAD not between", value1, value2, "thread");
            return (Criteria) this;
        }

        public Criteria andStartTmIsNull() {
            addCriterion("START_TM is null");
            return (Criteria) this;
        }

        public Criteria andStartTmIsNotNull() {
            addCriterion("START_TM is not null");
            return (Criteria) this;
        }

        public Criteria andStartTmEqualTo(Long value) {
            addCriterion("START_TM =", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotEqualTo(Long value) {
            addCriterion("START_TM <>", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmGreaterThan(Long value) {
            addCriterion("START_TM >", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmGreaterThanOrEqualTo(Long value) {
            addCriterion("START_TM >=", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmLessThan(Long value) {
            addCriterion("START_TM <", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmLessThanOrEqualTo(Long value) {
            addCriterion("START_TM <=", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmIn(List<Long> values) {
            addCriterion("START_TM in", values, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotIn(List<Long> values) {
            addCriterion("START_TM not in", values, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmBetween(Long value1, Long value2) {
            addCriterion("START_TM between", value1, value2, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotBetween(Long value1, Long value2) {
            addCriterion("START_TM not between", value1, value2, "startTm");
            return (Criteria) this;
        }

        public Criteria andEndTmIsNull() {
            addCriterion("END_TM is null");
            return (Criteria) this;
        }

        public Criteria andEndTmIsNotNull() {
            addCriterion("END_TM is not null");
            return (Criteria) this;
        }

        public Criteria andEndTmEqualTo(Long value) {
            addCriterion("END_TM =", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotEqualTo(Long value) {
            addCriterion("END_TM <>", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmGreaterThan(Long value) {
            addCriterion("END_TM >", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmGreaterThanOrEqualTo(Long value) {
            addCriterion("END_TM >=", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmLessThan(Long value) {
            addCriterion("END_TM <", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmLessThanOrEqualTo(Long value) {
            addCriterion("END_TM <=", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmIn(List<Long> values) {
            addCriterion("END_TM in", values, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotIn(List<Long> values) {
            addCriterion("END_TM not in", values, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmBetween(Long value1, Long value2) {
            addCriterion("END_TM between", value1, value2, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotBetween(Long value1, Long value2) {
            addCriterion("END_TM not between", value1, value2, "endTm");
            return (Criteria) this;
        }

        public Criteria andExeStatusIsNull() {
            addCriterion("EXE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andExeStatusIsNotNull() {
            addCriterion("EXE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andExeStatusEqualTo(String value) {
            addCriterion("EXE_STATUS =", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusNotEqualTo(String value) {
            addCriterion("EXE_STATUS <>", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusGreaterThan(String value) {
            addCriterion("EXE_STATUS >", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("EXE_STATUS >=", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusLessThan(String value) {
            addCriterion("EXE_STATUS <", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusLessThanOrEqualTo(String value) {
            addCriterion("EXE_STATUS <=", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusLike(String value) {
            addCriterion("EXE_STATUS like", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusNotLike(String value) {
            addCriterion("EXE_STATUS not like", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusIn(List<String> values) {
            addCriterion("EXE_STATUS in", values, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusNotIn(List<String> values) {
            addCriterion("EXE_STATUS not in", values, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusBetween(String value1, String value2) {
            addCriterion("EXE_STATUS between", value1, value2, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusNotBetween(String value1, String value2) {
            addCriterion("EXE_STATUS not between", value1, value2, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeCntIsNull() {
            addCriterion("EXE_CNT is null");
            return (Criteria) this;
        }

        public Criteria andExeCntIsNotNull() {
            addCriterion("EXE_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andExeCntEqualTo(Long value) {
            addCriterion("EXE_CNT =", value, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntNotEqualTo(Long value) {
            addCriterion("EXE_CNT <>", value, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntGreaterThan(Long value) {
            addCriterion("EXE_CNT >", value, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntGreaterThanOrEqualTo(Long value) {
            addCriterion("EXE_CNT >=", value, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntLessThan(Long value) {
            addCriterion("EXE_CNT <", value, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntLessThanOrEqualTo(Long value) {
            addCriterion("EXE_CNT <=", value, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntIn(List<Long> values) {
            addCriterion("EXE_CNT in", values, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntNotIn(List<Long> values) {
            addCriterion("EXE_CNT not in", values, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntBetween(Long value1, Long value2) {
            addCriterion("EXE_CNT between", value1, value2, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andExeCntNotBetween(Long value1, Long value2) {
            addCriterion("EXE_CNT not between", value1, value2, "exeCnt");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andProcessorLikeInsensitive(String value) {
            addCriterion("upper(PROCESSOR) like", value.toUpperCase(), "processor");
            return (Criteria) this;
        }

        public Criteria andThreadLikeInsensitive(String value) {
            addCriterion("upper(THREAD) like", value.toUpperCase(), "thread");
            return (Criteria) this;
        }

        public Criteria andExeStatusLikeInsensitive(String value) {
            addCriterion("upper(EXE_STATUS) like", value.toUpperCase(), "exeStatus");
            return (Criteria) this;
        }

        public Criteria andMemoLikeInsensitive(String value) {
            addCriterion("upper(MEMO) like", value.toUpperCase(), "memo");
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