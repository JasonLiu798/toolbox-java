package com.atjl.dbfront.domain.gen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TsContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TsContentExample() {
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

        public Criteria andCnameIsNull() {
            addCriterion("CNAME is null");
            return (Criteria) this;
        }

        public Criteria andCnameIsNotNull() {
            addCriterion("CNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCnameEqualTo(String value) {
            addCriterion("CNAME =", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotEqualTo(String value) {
            addCriterion("CNAME <>", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThan(String value) {
            addCriterion("CNAME >", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThanOrEqualTo(String value) {
            addCriterion("CNAME >=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThan(String value) {
            addCriterion("CNAME <", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThanOrEqualTo(String value) {
            addCriterion("CNAME <=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLike(String value) {
            addCriterion("CNAME like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotLike(String value) {
            addCriterion("CNAME not like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameIn(List<String> values) {
            addCriterion("CNAME in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotIn(List<String> values) {
            addCriterion("CNAME not in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameBetween(String value1, String value2) {
            addCriterion("CNAME between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotBetween(String value1, String value2) {
            addCriterion("CNAME not between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCtypeIsNull() {
            addCriterion("CTYPE is null");
            return (Criteria) this;
        }

        public Criteria andCtypeIsNotNull() {
            addCriterion("CTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCtypeEqualTo(String value) {
            addCriterion("CTYPE =", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeNotEqualTo(String value) {
            addCriterion("CTYPE <>", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeGreaterThan(String value) {
            addCriterion("CTYPE >", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeGreaterThanOrEqualTo(String value) {
            addCriterion("CTYPE >=", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeLessThan(String value) {
            addCriterion("CTYPE <", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeLessThanOrEqualTo(String value) {
            addCriterion("CTYPE <=", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeLike(String value) {
            addCriterion("CTYPE like", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeNotLike(String value) {
            addCriterion("CTYPE not like", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeIn(List<String> values) {
            addCriterion("CTYPE in", values, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeNotIn(List<String> values) {
            addCriterion("CTYPE not in", values, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeBetween(String value1, String value2) {
            addCriterion("CTYPE between", value1, value2, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeNotBetween(String value1, String value2) {
            addCriterion("CTYPE not between", value1, value2, "ctype");
            return (Criteria) this;
        }

        public Criteria andCversionIsNull() {
            addCriterion("CVERSION is null");
            return (Criteria) this;
        }

        public Criteria andCversionIsNotNull() {
            addCriterion("CVERSION is not null");
            return (Criteria) this;
        }

        public Criteria andCversionEqualTo(String value) {
            addCriterion("CVERSION =", value, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionNotEqualTo(String value) {
            addCriterion("CVERSION <>", value, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionGreaterThan(String value) {
            addCriterion("CVERSION >", value, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionGreaterThanOrEqualTo(String value) {
            addCriterion("CVERSION >=", value, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionLessThan(String value) {
            addCriterion("CVERSION <", value, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionLessThanOrEqualTo(String value) {
            addCriterion("CVERSION <=", value, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionLike(String value) {
            addCriterion("CVERSION like", value, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionNotLike(String value) {
            addCriterion("CVERSION not like", value, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionIn(List<String> values) {
            addCriterion("CVERSION in", values, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionNotIn(List<String> values) {
            addCriterion("CVERSION not in", values, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionBetween(String value1, String value2) {
            addCriterion("CVERSION between", value1, value2, "cversion");
            return (Criteria) this;
        }

        public Criteria andCversionNotBetween(String value1, String value2) {
            addCriterion("CVERSION not between", value1, value2, "cversion");
            return (Criteria) this;
        }

        public Criteria andPreVersionIsNull() {
            addCriterion("PRE_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andPreVersionIsNotNull() {
            addCriterion("PRE_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andPreVersionEqualTo(String value) {
            addCriterion("PRE_VERSION =", value, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionNotEqualTo(String value) {
            addCriterion("PRE_VERSION <>", value, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionGreaterThan(String value) {
            addCriterion("PRE_VERSION >", value, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_VERSION >=", value, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionLessThan(String value) {
            addCriterion("PRE_VERSION <", value, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionLessThanOrEqualTo(String value) {
            addCriterion("PRE_VERSION <=", value, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionLike(String value) {
            addCriterion("PRE_VERSION like", value, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionNotLike(String value) {
            addCriterion("PRE_VERSION not like", value, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionIn(List<String> values) {
            addCriterion("PRE_VERSION in", values, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionNotIn(List<String> values) {
            addCriterion("PRE_VERSION not in", values, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionBetween(String value1, String value2) {
            addCriterion("PRE_VERSION between", value1, value2, "preVersion");
            return (Criteria) this;
        }

        public Criteria andPreVersionNotBetween(String value1, String value2) {
            addCriterion("PRE_VERSION not between", value1, value2, "preVersion");
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

        public Criteria andCnameLikeInsensitive(String value) {
            addCriterion("upper(CNAME) like", value.toUpperCase(), "cname");
            return (Criteria) this;
        }

        public Criteria andCtypeLikeInsensitive(String value) {
            addCriterion("upper(CTYPE) like", value.toUpperCase(), "ctype");
            return (Criteria) this;
        }

        public Criteria andCversionLikeInsensitive(String value) {
            addCriterion("upper(CVERSION) like", value.toUpperCase(), "cversion");
            return (Criteria) this;
        }

        public Criteria andPreVersionLikeInsensitive(String value) {
            addCriterion("upper(PRE_VERSION) like", value.toUpperCase(), "preVersion");
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