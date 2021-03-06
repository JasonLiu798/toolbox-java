package com.atjl.retry.domain.gen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TsProcessLogDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TsProcessLogDetailExample() {
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

        public Criteria andProcessLogDetailIdIsNull() {
            addCriterion("PROCESS_LOG_DETAIL_ID is null");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdIsNotNull() {
            addCriterion("PROCESS_LOG_DETAIL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdEqualTo(Long value) {
            addCriterion("PROCESS_LOG_DETAIL_ID =", value, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdNotEqualTo(Long value) {
            addCriterion("PROCESS_LOG_DETAIL_ID <>", value, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdGreaterThan(Long value) {
            addCriterion("PROCESS_LOG_DETAIL_ID >", value, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROCESS_LOG_DETAIL_ID >=", value, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdLessThan(Long value) {
            addCriterion("PROCESS_LOG_DETAIL_ID <", value, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("PROCESS_LOG_DETAIL_ID <=", value, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdIn(List<Long> values) {
            addCriterion("PROCESS_LOG_DETAIL_ID in", values, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdNotIn(List<Long> values) {
            addCriterion("PROCESS_LOG_DETAIL_ID not in", values, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdBetween(Long value1, Long value2) {
            addCriterion("PROCESS_LOG_DETAIL_ID between", value1, value2, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("PROCESS_LOG_DETAIL_ID not between", value1, value2, "processLogDetailId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdIsNull() {
            addCriterion("PROCESS_LOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdIsNotNull() {
            addCriterion("PROCESS_LOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdEqualTo(Long value) {
            addCriterion("PROCESS_LOG_ID =", value, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdNotEqualTo(Long value) {
            addCriterion("PROCESS_LOG_ID <>", value, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdGreaterThan(Long value) {
            addCriterion("PROCESS_LOG_ID >", value, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROCESS_LOG_ID >=", value, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdLessThan(Long value) {
            addCriterion("PROCESS_LOG_ID <", value, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdLessThanOrEqualTo(Long value) {
            addCriterion("PROCESS_LOG_ID <=", value, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdIn(List<Long> values) {
            addCriterion("PROCESS_LOG_ID in", values, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdNotIn(List<Long> values) {
            addCriterion("PROCESS_LOG_ID not in", values, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdBetween(Long value1, Long value2) {
            addCriterion("PROCESS_LOG_ID between", value1, value2, "processLogId");
            return (Criteria) this;
        }

        public Criteria andProcessLogIdNotBetween(Long value1, Long value2) {
            addCriterion("PROCESS_LOG_ID not between", value1, value2, "processLogId");
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

        public Criteria andCrtTmEqualTo(Date value) {
            addCriterion("CRT_TM =", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmNotEqualTo(Date value) {
            addCriterion("CRT_TM <>", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmGreaterThan(Date value) {
            addCriterion("CRT_TM >", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmGreaterThanOrEqualTo(Date value) {
            addCriterion("CRT_TM >=", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmLessThan(Date value) {
            addCriterion("CRT_TM <", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmLessThanOrEqualTo(Date value) {
            addCriterion("CRT_TM <=", value, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmIn(List<Date> values) {
            addCriterion("CRT_TM in", values, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmNotIn(List<Date> values) {
            addCriterion("CRT_TM not in", values, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmBetween(Date value1, Date value2) {
            addCriterion("CRT_TM between", value1, value2, "crtTm");
            return (Criteria) this;
        }

        public Criteria andCrtTmNotBetween(Date value1, Date value2) {
            addCriterion("CRT_TM not between", value1, value2, "crtTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmIsNull() {
            addCriterion("UPD_TM is null");
            return (Criteria) this;
        }

        public Criteria andUpdTmIsNotNull() {
            addCriterion("UPD_TM is not null");
            return (Criteria) this;
        }

        public Criteria andUpdTmEqualTo(Date value) {
            addCriterion("UPD_TM =", value, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmNotEqualTo(Date value) {
            addCriterion("UPD_TM <>", value, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmGreaterThan(Date value) {
            addCriterion("UPD_TM >", value, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmGreaterThanOrEqualTo(Date value) {
            addCriterion("UPD_TM >=", value, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmLessThan(Date value) {
            addCriterion("UPD_TM <", value, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmLessThanOrEqualTo(Date value) {
            addCriterion("UPD_TM <=", value, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmIn(List<Date> values) {
            addCriterion("UPD_TM in", values, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmNotIn(List<Date> values) {
            addCriterion("UPD_TM not in", values, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmBetween(Date value1, Date value2) {
            addCriterion("UPD_TM between", value1, value2, "updTm");
            return (Criteria) this;
        }

        public Criteria andUpdTmNotBetween(Date value1, Date value2) {
            addCriterion("UPD_TM not between", value1, value2, "updTm");
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