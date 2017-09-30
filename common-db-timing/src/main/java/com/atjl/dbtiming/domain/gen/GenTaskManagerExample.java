package com.atjl.dbtiming.domain.gen;

import java.util.ArrayList;
import java.util.List;

public class GenTaskManagerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GenTaskManagerExample() {
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

        public Criteria andMidIsNull() {
            addCriterion("MID is null");
            return (Criteria) this;
        }

        public Criteria andMidIsNotNull() {
            addCriterion("MID is not null");
            return (Criteria) this;
        }

        public Criteria andMidEqualTo(Long value) {
            addCriterion("MID =", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotEqualTo(Long value) {
            addCriterion("MID <>", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThan(Long value) {
            addCriterion("MID >", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThanOrEqualTo(Long value) {
            addCriterion("MID >=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThan(Long value) {
            addCriterion("MID <", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThanOrEqualTo(Long value) {
            addCriterion("MID <=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidIn(List<Long> values) {
            addCriterion("MID in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotIn(List<Long> values) {
            addCriterion("MID not in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidBetween(Long value1, Long value2) {
            addCriterion("MID between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotBetween(Long value1, Long value2) {
            addCriterion("MID not between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAliveTmIsNull() {
            addCriterion("ALIVE_TM is null");
            return (Criteria) this;
        }

        public Criteria andAliveTmIsNotNull() {
            addCriterion("ALIVE_TM is not null");
            return (Criteria) this;
        }

        public Criteria andAliveTmEqualTo(Long value) {
            addCriterion("ALIVE_TM =", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmNotEqualTo(Long value) {
            addCriterion("ALIVE_TM <>", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmGreaterThan(Long value) {
            addCriterion("ALIVE_TM >", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmGreaterThanOrEqualTo(Long value) {
            addCriterion("ALIVE_TM >=", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmLessThan(Long value) {
            addCriterion("ALIVE_TM <", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmLessThanOrEqualTo(Long value) {
            addCriterion("ALIVE_TM <=", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmIn(List<Long> values) {
            addCriterion("ALIVE_TM in", values, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmNotIn(List<Long> values) {
            addCriterion("ALIVE_TM not in", values, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmBetween(Long value1, Long value2) {
            addCriterion("ALIVE_TM between", value1, value2, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmNotBetween(Long value1, Long value2) {
            addCriterion("ALIVE_TM not between", value1, value2, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmIsNull() {
            addCriterion("LAST_UPDATE_TM is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmIsNotNull() {
            addCriterion("LAST_UPDATE_TM is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmEqualTo(Long value) {
            addCriterion("LAST_UPDATE_TM =", value, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmNotEqualTo(Long value) {
            addCriterion("LAST_UPDATE_TM <>", value, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmGreaterThan(Long value) {
            addCriterion("LAST_UPDATE_TM >", value, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmGreaterThanOrEqualTo(Long value) {
            addCriterion("LAST_UPDATE_TM >=", value, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmLessThan(Long value) {
            addCriterion("LAST_UPDATE_TM <", value, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmLessThanOrEqualTo(Long value) {
            addCriterion("LAST_UPDATE_TM <=", value, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmIn(List<Long> values) {
            addCriterion("LAST_UPDATE_TM in", values, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmNotIn(List<Long> values) {
            addCriterion("LAST_UPDATE_TM not in", values, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmBetween(Long value1, Long value2) {
            addCriterion("LAST_UPDATE_TM between", value1, value2, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTmNotBetween(Long value1, Long value2) {
            addCriterion("LAST_UPDATE_TM not between", value1, value2, "lastUpdateTm");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(NAME) like", value.toUpperCase(), "name");
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