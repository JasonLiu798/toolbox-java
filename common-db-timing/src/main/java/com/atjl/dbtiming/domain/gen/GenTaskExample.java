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

        public Criteria andTserviceIsNull() {
            addCriterion("TSERVICE is null");
            return (Criteria) this;
        }

        public Criteria andTserviceIsNotNull() {
            addCriterion("TSERVICE is not null");
            return (Criteria) this;
        }

        public Criteria andTserviceEqualTo(String value) {
            addCriterion("TSERVICE =", value, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceNotEqualTo(String value) {
            addCriterion("TSERVICE <>", value, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceGreaterThan(String value) {
            addCriterion("TSERVICE >", value, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceGreaterThanOrEqualTo(String value) {
            addCriterion("TSERVICE >=", value, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceLessThan(String value) {
            addCriterion("TSERVICE <", value, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceLessThanOrEqualTo(String value) {
            addCriterion("TSERVICE <=", value, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceLike(String value) {
            addCriterion("TSERVICE like", value, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceNotLike(String value) {
            addCriterion("TSERVICE not like", value, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceIn(List<String> values) {
            addCriterion("TSERVICE in", values, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceNotIn(List<String> values) {
            addCriterion("TSERVICE not in", values, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceBetween(String value1, String value2) {
            addCriterion("TSERVICE between", value1, value2, "tservice");
            return (Criteria) this;
        }

        public Criteria andTserviceNotBetween(String value1, String value2) {
            addCriterion("TSERVICE not between", value1, value2, "tservice");
            return (Criteria) this;
        }

        public Criteria andParamIsNull() {
            addCriterion("PARAM is null");
            return (Criteria) this;
        }

        public Criteria andParamIsNotNull() {
            addCriterion("PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andParamEqualTo(String value) {
            addCriterion("PARAM =", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotEqualTo(String value) {
            addCriterion("PARAM <>", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThan(String value) {
            addCriterion("PARAM >", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM >=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThan(String value) {
            addCriterion("PARAM <", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThanOrEqualTo(String value) {
            addCriterion("PARAM <=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLike(String value) {
            addCriterion("PARAM like", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotLike(String value) {
            addCriterion("PARAM not like", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamIn(List<String> values) {
            addCriterion("PARAM in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotIn(List<String> values) {
            addCriterion("PARAM not in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamBetween(String value1, String value2) {
            addCriterion("PARAM between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotBetween(String value1, String value2) {
            addCriterion("PARAM not between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andConfTypeIsNull() {
            addCriterion("CONF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andConfTypeIsNotNull() {
            addCriterion("CONF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andConfTypeEqualTo(String value) {
            addCriterion("CONF_TYPE =", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotEqualTo(String value) {
            addCriterion("CONF_TYPE <>", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeGreaterThan(String value) {
            addCriterion("CONF_TYPE >", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_TYPE >=", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLessThan(String value) {
            addCriterion("CONF_TYPE <", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLessThanOrEqualTo(String value) {
            addCriterion("CONF_TYPE <=", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLike(String value) {
            addCriterion("CONF_TYPE like", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotLike(String value) {
            addCriterion("CONF_TYPE not like", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeIn(List<String> values) {
            addCriterion("CONF_TYPE in", values, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotIn(List<String> values) {
            addCriterion("CONF_TYPE not in", values, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeBetween(String value1, String value2) {
            addCriterion("CONF_TYPE between", value1, value2, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotBetween(String value1, String value2) {
            addCriterion("CONF_TYPE not between", value1, value2, "confType");
            return (Criteria) this;
        }

        public Criteria andConfHasParamIsNull() {
            addCriterion("CONF_HAS_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andConfHasParamIsNotNull() {
            addCriterion("CONF_HAS_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andConfHasParamEqualTo(String value) {
            addCriterion("CONF_HAS_PARAM =", value, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamNotEqualTo(String value) {
            addCriterion("CONF_HAS_PARAM <>", value, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamGreaterThan(String value) {
            addCriterion("CONF_HAS_PARAM >", value, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_HAS_PARAM >=", value, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamLessThan(String value) {
            addCriterion("CONF_HAS_PARAM <", value, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamLessThanOrEqualTo(String value) {
            addCriterion("CONF_HAS_PARAM <=", value, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamLike(String value) {
            addCriterion("CONF_HAS_PARAM like", value, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamNotLike(String value) {
            addCriterion("CONF_HAS_PARAM not like", value, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamIn(List<String> values) {
            addCriterion("CONF_HAS_PARAM in", values, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamNotIn(List<String> values) {
            addCriterion("CONF_HAS_PARAM not in", values, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamBetween(String value1, String value2) {
            addCriterion("CONF_HAS_PARAM between", value1, value2, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfHasParamNotBetween(String value1, String value2) {
            addCriterion("CONF_HAS_PARAM not between", value1, value2, "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionIsNull() {
            addCriterion("CONF_CRON_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionIsNotNull() {
            addCriterion("CONF_CRON_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionEqualTo(String value) {
            addCriterion("CONF_CRON_EXPRESSION =", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionNotEqualTo(String value) {
            addCriterion("CONF_CRON_EXPRESSION <>", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionGreaterThan(String value) {
            addCriterion("CONF_CRON_EXPRESSION >", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_CRON_EXPRESSION >=", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionLessThan(String value) {
            addCriterion("CONF_CRON_EXPRESSION <", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionLessThanOrEqualTo(String value) {
            addCriterion("CONF_CRON_EXPRESSION <=", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionLike(String value) {
            addCriterion("CONF_CRON_EXPRESSION like", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionNotLike(String value) {
            addCriterion("CONF_CRON_EXPRESSION not like", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionIn(List<String> values) {
            addCriterion("CONF_CRON_EXPRESSION in", values, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionNotIn(List<String> values) {
            addCriterion("CONF_CRON_EXPRESSION not in", values, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionBetween(String value1, String value2) {
            addCriterion("CONF_CRON_EXPRESSION between", value1, value2, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionNotBetween(String value1, String value2) {
            addCriterion("CONF_CRON_EXPRESSION not between", value1, value2, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmIsNull() {
            addCriterion("CONF_DELAY_TM is null");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmIsNotNull() {
            addCriterion("CONF_DELAY_TM is not null");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmEqualTo(Long value) {
            addCriterion("CONF_DELAY_TM =", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmNotEqualTo(Long value) {
            addCriterion("CONF_DELAY_TM <>", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmGreaterThan(Long value) {
            addCriterion("CONF_DELAY_TM >", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_DELAY_TM >=", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmLessThan(Long value) {
            addCriterion("CONF_DELAY_TM <", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmLessThanOrEqualTo(Long value) {
            addCriterion("CONF_DELAY_TM <=", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmIn(List<Long> values) {
            addCriterion("CONF_DELAY_TM in", values, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmNotIn(List<Long> values) {
            addCriterion("CONF_DELAY_TM not in", values, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmBetween(Long value1, Long value2) {
            addCriterion("CONF_DELAY_TM between", value1, value2, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmNotBetween(Long value1, Long value2) {
            addCriterion("CONF_DELAY_TM not between", value1, value2, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmIsNull() {
            addCriterion("CONF_INTERVAL_TM is null");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmIsNotNull() {
            addCriterion("CONF_INTERVAL_TM is not null");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmEqualTo(Long value) {
            addCriterion("CONF_INTERVAL_TM =", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmNotEqualTo(Long value) {
            addCriterion("CONF_INTERVAL_TM <>", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmGreaterThan(Long value) {
            addCriterion("CONF_INTERVAL_TM >", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_INTERVAL_TM >=", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmLessThan(Long value) {
            addCriterion("CONF_INTERVAL_TM <", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmLessThanOrEqualTo(Long value) {
            addCriterion("CONF_INTERVAL_TM <=", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmIn(List<Long> values) {
            addCriterion("CONF_INTERVAL_TM in", values, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmNotIn(List<Long> values) {
            addCriterion("CONF_INTERVAL_TM not in", values, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmBetween(Long value1, Long value2) {
            addCriterion("CONF_INTERVAL_TM between", value1, value2, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmNotBetween(Long value1, Long value2) {
            addCriterion("CONF_INTERVAL_TM not between", value1, value2, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIsNull() {
            addCriterion("CONF_EXE_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIsNotNull() {
            addCriterion("CONF_EXE_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES =", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES <>", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesGreaterThan(Long value) {
            addCriterion("CONF_EXE_TIMES >", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES >=", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesLessThan(Long value) {
            addCriterion("CONF_EXE_TIMES <", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesLessThanOrEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES <=", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIn(List<Long> values) {
            addCriterion("CONF_EXE_TIMES in", values, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotIn(List<Long> values) {
            addCriterion("CONF_EXE_TIMES not in", values, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesBetween(Long value1, Long value2) {
            addCriterion("CONF_EXE_TIMES between", value1, value2, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotBetween(Long value1, Long value2) {
            addCriterion("CONF_EXE_TIMES not between", value1, value2, "confExeTimes");
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

        public Criteria andTserviceLikeInsensitive(String value) {
            addCriterion("upper(TSERVICE) like", value.toUpperCase(), "tservice");
            return (Criteria) this;
        }

        public Criteria andParamLikeInsensitive(String value) {
            addCriterion("upper(PARAM) like", value.toUpperCase(), "param");
            return (Criteria) this;
        }

        public Criteria andConfTypeLikeInsensitive(String value) {
            addCriterion("upper(CONF_TYPE) like", value.toUpperCase(), "confType");
            return (Criteria) this;
        }

        public Criteria andConfHasParamLikeInsensitive(String value) {
            addCriterion("upper(CONF_HAS_PARAM) like", value.toUpperCase(), "confHasParam");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionLikeInsensitive(String value) {
            addCriterion("upper(CONF_CRON_EXPRESSION) like", value.toUpperCase(), "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andProcessorLikeInsensitive(String value) {
            addCriterion("upper(PROCESSOR) like", value.toUpperCase(), "processor");
            return (Criteria) this;
        }

        public Criteria andTmutexLikeInsensitive(String value) {
            addCriterion("upper(TMUTEX) like", value.toUpperCase(), "tmutex");
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