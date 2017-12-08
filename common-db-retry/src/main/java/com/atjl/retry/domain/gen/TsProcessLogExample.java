package com.atjl.retry.domain.gen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TsProcessLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TsProcessLogExample() {
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

        public Criteria andDataProcessIdIsNull() {
            addCriterion("DATA_PROCESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdIsNotNull() {
            addCriterion("DATA_PROCESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdEqualTo(Long value) {
            addCriterion("DATA_PROCESS_ID =", value, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdNotEqualTo(Long value) {
            addCriterion("DATA_PROCESS_ID <>", value, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdGreaterThan(Long value) {
            addCriterion("DATA_PROCESS_ID >", value, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DATA_PROCESS_ID >=", value, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdLessThan(Long value) {
            addCriterion("DATA_PROCESS_ID <", value, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdLessThanOrEqualTo(Long value) {
            addCriterion("DATA_PROCESS_ID <=", value, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdIn(List<Long> values) {
            addCriterion("DATA_PROCESS_ID in", values, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdNotIn(List<Long> values) {
            addCriterion("DATA_PROCESS_ID not in", values, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdBetween(Long value1, Long value2) {
            addCriterion("DATA_PROCESS_ID between", value1, value2, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andDataProcessIdNotBetween(Long value1, Long value2) {
            addCriterion("DATA_PROCESS_ID not between", value1, value2, "dataProcessId");
            return (Criteria) this;
        }

        public Criteria andServiceKeyIsNull() {
            addCriterion("SERVICE_KEY is null");
            return (Criteria) this;
        }

        public Criteria andServiceKeyIsNotNull() {
            addCriterion("SERVICE_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andServiceKeyEqualTo(String value) {
            addCriterion("SERVICE_KEY =", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyNotEqualTo(String value) {
            addCriterion("SERVICE_KEY <>", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyGreaterThan(String value) {
            addCriterion("SERVICE_KEY >", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_KEY >=", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyLessThan(String value) {
            addCriterion("SERVICE_KEY <", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_KEY <=", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyLike(String value) {
            addCriterion("SERVICE_KEY like", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyNotLike(String value) {
            addCriterion("SERVICE_KEY not like", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyIn(List<String> values) {
            addCriterion("SERVICE_KEY in", values, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyNotIn(List<String> values) {
            addCriterion("SERVICE_KEY not in", values, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyBetween(String value1, String value2) {
            addCriterion("SERVICE_KEY between", value1, value2, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyNotBetween(String value1, String value2) {
            addCriterion("SERVICE_KEY not between", value1, value2, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNull() {
            addCriterion("TOTAL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNotNull() {
            addCriterion("TOTAL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCountEqualTo(Long value) {
            addCriterion("TOTAL_COUNT =", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotEqualTo(Long value) {
            addCriterion("TOTAL_COUNT <>", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThan(Long value) {
            addCriterion("TOTAL_COUNT >", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_COUNT >=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThan(Long value) {
            addCriterion("TOTAL_COUNT <", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_COUNT <=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIn(List<Long> values) {
            addCriterion("TOTAL_COUNT in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotIn(List<Long> values) {
            addCriterion("TOTAL_COUNT not in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountBetween(Long value1, Long value2) {
            addCriterion("TOTAL_COUNT between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_COUNT not between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalPageIsNull() {
            addCriterion("TOTAL_PAGE is null");
            return (Criteria) this;
        }

        public Criteria andTotalPageIsNotNull() {
            addCriterion("TOTAL_PAGE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPageEqualTo(Long value) {
            addCriterion("TOTAL_PAGE =", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotEqualTo(Long value) {
            addCriterion("TOTAL_PAGE <>", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageGreaterThan(Long value) {
            addCriterion("TOTAL_PAGE >", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_PAGE >=", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageLessThan(Long value) {
            addCriterion("TOTAL_PAGE <", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_PAGE <=", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageIn(List<Long> values) {
            addCriterion("TOTAL_PAGE in", values, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotIn(List<Long> values) {
            addCriterion("TOTAL_PAGE not in", values, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageBetween(Long value1, Long value2) {
            addCriterion("TOTAL_PAGE between", value1, value2, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_PAGE not between", value1, value2, "totalPage");
            return (Criteria) this;
        }

        public Criteria andAddCountIsNull() {
            addCriterion("ADD_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAddCountIsNotNull() {
            addCriterion("ADD_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAddCountEqualTo(Long value) {
            addCriterion("ADD_COUNT =", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountNotEqualTo(Long value) {
            addCriterion("ADD_COUNT <>", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountGreaterThan(Long value) {
            addCriterion("ADD_COUNT >", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountGreaterThanOrEqualTo(Long value) {
            addCriterion("ADD_COUNT >=", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountLessThan(Long value) {
            addCriterion("ADD_COUNT <", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountLessThanOrEqualTo(Long value) {
            addCriterion("ADD_COUNT <=", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountIn(List<Long> values) {
            addCriterion("ADD_COUNT in", values, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountNotIn(List<Long> values) {
            addCriterion("ADD_COUNT not in", values, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountBetween(Long value1, Long value2) {
            addCriterion("ADD_COUNT between", value1, value2, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountNotBetween(Long value1, Long value2) {
            addCriterion("ADD_COUNT not between", value1, value2, "addCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountIsNull() {
            addCriterion("UPD_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andUpdCountIsNotNull() {
            addCriterion("UPD_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andUpdCountEqualTo(Long value) {
            addCriterion("UPD_COUNT =", value, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountNotEqualTo(Long value) {
            addCriterion("UPD_COUNT <>", value, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountGreaterThan(Long value) {
            addCriterion("UPD_COUNT >", value, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountGreaterThanOrEqualTo(Long value) {
            addCriterion("UPD_COUNT >=", value, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountLessThan(Long value) {
            addCriterion("UPD_COUNT <", value, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountLessThanOrEqualTo(Long value) {
            addCriterion("UPD_COUNT <=", value, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountIn(List<Long> values) {
            addCriterion("UPD_COUNT in", values, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountNotIn(List<Long> values) {
            addCriterion("UPD_COUNT not in", values, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountBetween(Long value1, Long value2) {
            addCriterion("UPD_COUNT between", value1, value2, "updCount");
            return (Criteria) this;
        }

        public Criteria andUpdCountNotBetween(Long value1, Long value2) {
            addCriterion("UPD_COUNT not between", value1, value2, "updCount");
            return (Criteria) this;
        }

        public Criteria andFailCountIsNull() {
            addCriterion("FAIL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andFailCountIsNotNull() {
            addCriterion("FAIL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFailCountEqualTo(Long value) {
            addCriterion("FAIL_COUNT =", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountNotEqualTo(Long value) {
            addCriterion("FAIL_COUNT <>", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountGreaterThan(Long value) {
            addCriterion("FAIL_COUNT >", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountGreaterThanOrEqualTo(Long value) {
            addCriterion("FAIL_COUNT >=", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountLessThan(Long value) {
            addCriterion("FAIL_COUNT <", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountLessThanOrEqualTo(Long value) {
            addCriterion("FAIL_COUNT <=", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountIn(List<Long> values) {
            addCriterion("FAIL_COUNT in", values, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountNotIn(List<Long> values) {
            addCriterion("FAIL_COUNT not in", values, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountBetween(Long value1, Long value2) {
            addCriterion("FAIL_COUNT between", value1, value2, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountNotBetween(Long value1, Long value2) {
            addCriterion("FAIL_COUNT not between", value1, value2, "failCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountIsNull() {
            addCriterion("NO_NEED_UPD_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountIsNotNull() {
            addCriterion("NO_NEED_UPD_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountEqualTo(Long value) {
            addCriterion("NO_NEED_UPD_COUNT =", value, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountNotEqualTo(Long value) {
            addCriterion("NO_NEED_UPD_COUNT <>", value, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountGreaterThan(Long value) {
            addCriterion("NO_NEED_UPD_COUNT >", value, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountGreaterThanOrEqualTo(Long value) {
            addCriterion("NO_NEED_UPD_COUNT >=", value, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountLessThan(Long value) {
            addCriterion("NO_NEED_UPD_COUNT <", value, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountLessThanOrEqualTo(Long value) {
            addCriterion("NO_NEED_UPD_COUNT <=", value, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountIn(List<Long> values) {
            addCriterion("NO_NEED_UPD_COUNT in", values, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountNotIn(List<Long> values) {
            addCriterion("NO_NEED_UPD_COUNT not in", values, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountBetween(Long value1, Long value2) {
            addCriterion("NO_NEED_UPD_COUNT between", value1, value2, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andNoNeedUpdCountNotBetween(Long value1, Long value2) {
            addCriterion("NO_NEED_UPD_COUNT not between", value1, value2, "noNeedUpdCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountIsNull() {
            addCriterion("FAIL_PAGE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andFailPageCountIsNotNull() {
            addCriterion("FAIL_PAGE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFailPageCountEqualTo(Long value) {
            addCriterion("FAIL_PAGE_COUNT =", value, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountNotEqualTo(Long value) {
            addCriterion("FAIL_PAGE_COUNT <>", value, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountGreaterThan(Long value) {
            addCriterion("FAIL_PAGE_COUNT >", value, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountGreaterThanOrEqualTo(Long value) {
            addCriterion("FAIL_PAGE_COUNT >=", value, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountLessThan(Long value) {
            addCriterion("FAIL_PAGE_COUNT <", value, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountLessThanOrEqualTo(Long value) {
            addCriterion("FAIL_PAGE_COUNT <=", value, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountIn(List<Long> values) {
            addCriterion("FAIL_PAGE_COUNT in", values, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountNotIn(List<Long> values) {
            addCriterion("FAIL_PAGE_COUNT not in", values, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountBetween(Long value1, Long value2) {
            addCriterion("FAIL_PAGE_COUNT between", value1, value2, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andFailPageCountNotBetween(Long value1, Long value2) {
            addCriterion("FAIL_PAGE_COUNT not between", value1, value2, "failPageCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountIsNull() {
            addCriterion("UNKNOWN_FAIL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountIsNotNull() {
            addCriterion("UNKNOWN_FAIL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountEqualTo(Long value) {
            addCriterion("UNKNOWN_FAIL_COUNT =", value, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountNotEqualTo(Long value) {
            addCriterion("UNKNOWN_FAIL_COUNT <>", value, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountGreaterThan(Long value) {
            addCriterion("UNKNOWN_FAIL_COUNT >", value, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountGreaterThanOrEqualTo(Long value) {
            addCriterion("UNKNOWN_FAIL_COUNT >=", value, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountLessThan(Long value) {
            addCriterion("UNKNOWN_FAIL_COUNT <", value, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountLessThanOrEqualTo(Long value) {
            addCriterion("UNKNOWN_FAIL_COUNT <=", value, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountIn(List<Long> values) {
            addCriterion("UNKNOWN_FAIL_COUNT in", values, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountNotIn(List<Long> values) {
            addCriterion("UNKNOWN_FAIL_COUNT not in", values, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountBetween(Long value1, Long value2) {
            addCriterion("UNKNOWN_FAIL_COUNT between", value1, value2, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andUnknownFailCountNotBetween(Long value1, Long value2) {
            addCriterion("UNKNOWN_FAIL_COUNT not between", value1, value2, "unknownFailCount");
            return (Criteria) this;
        }

        public Criteria andTotalCostIsNull() {
            addCriterion("TOTAL_COST is null");
            return (Criteria) this;
        }

        public Criteria andTotalCostIsNotNull() {
            addCriterion("TOTAL_COST is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCostEqualTo(Long value) {
            addCriterion("TOTAL_COST =", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostNotEqualTo(Long value) {
            addCriterion("TOTAL_COST <>", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostGreaterThan(Long value) {
            addCriterion("TOTAL_COST >", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_COST >=", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostLessThan(Long value) {
            addCriterion("TOTAL_COST <", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_COST <=", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostIn(List<Long> values) {
            addCriterion("TOTAL_COST in", values, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostNotIn(List<Long> values) {
            addCriterion("TOTAL_COST not in", values, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostBetween(Long value1, Long value2) {
            addCriterion("TOTAL_COST between", value1, value2, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_COST not between", value1, value2, "totalCost");
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

        public Criteria andProcessEndIsNull() {
            addCriterion("PROCESS_END is null");
            return (Criteria) this;
        }

        public Criteria andProcessEndIsNotNull() {
            addCriterion("PROCESS_END is not null");
            return (Criteria) this;
        }

        public Criteria andProcessEndEqualTo(String value) {
            addCriterion("PROCESS_END =", value, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndNotEqualTo(String value) {
            addCriterion("PROCESS_END <>", value, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndGreaterThan(String value) {
            addCriterion("PROCESS_END >", value, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_END >=", value, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndLessThan(String value) {
            addCriterion("PROCESS_END <", value, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_END <=", value, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndLike(String value) {
            addCriterion("PROCESS_END like", value, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndNotLike(String value) {
            addCriterion("PROCESS_END not like", value, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndIn(List<String> values) {
            addCriterion("PROCESS_END in", values, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndNotIn(List<String> values) {
            addCriterion("PROCESS_END not in", values, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndBetween(String value1, String value2) {
            addCriterion("PROCESS_END between", value1, value2, "processEnd");
            return (Criteria) this;
        }

        public Criteria andProcessEndNotBetween(String value1, String value2) {
            addCriterion("PROCESS_END not between", value1, value2, "processEnd");
            return (Criteria) this;
        }

        public Criteria andSuccessIsNull() {
            addCriterion("SUCCESS is null");
            return (Criteria) this;
        }

        public Criteria andSuccessIsNotNull() {
            addCriterion("SUCCESS is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessEqualTo(String value) {
            addCriterion("SUCCESS =", value, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessNotEqualTo(String value) {
            addCriterion("SUCCESS <>", value, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessGreaterThan(String value) {
            addCriterion("SUCCESS >", value, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessGreaterThanOrEqualTo(String value) {
            addCriterion("SUCCESS >=", value, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessLessThan(String value) {
            addCriterion("SUCCESS <", value, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessLessThanOrEqualTo(String value) {
            addCriterion("SUCCESS <=", value, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessLike(String value) {
            addCriterion("SUCCESS like", value, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessNotLike(String value) {
            addCriterion("SUCCESS not like", value, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessIn(List<String> values) {
            addCriterion("SUCCESS in", values, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessNotIn(List<String> values) {
            addCriterion("SUCCESS not in", values, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessBetween(String value1, String value2) {
            addCriterion("SUCCESS between", value1, value2, "success");
            return (Criteria) this;
        }

        public Criteria andSuccessNotBetween(String value1, String value2) {
            addCriterion("SUCCESS not between", value1, value2, "success");
            return (Criteria) this;
        }

        public Criteria andServiceKeyLikeInsensitive(String value) {
            addCriterion("upper(SERVICE_KEY) like", value.toUpperCase(), "serviceKey");
            return (Criteria) this;
        }

        public Criteria andProcessEndLikeInsensitive(String value) {
            addCriterion("upper(PROCESS_END) like", value.toUpperCase(), "processEnd");
            return (Criteria) this;
        }

        public Criteria andSuccessLikeInsensitive(String value) {
            addCriterion("upper(SUCCESS) like", value.toUpperCase(), "success");
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