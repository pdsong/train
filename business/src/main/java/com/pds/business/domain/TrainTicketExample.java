package com.pds.business.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainTicketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TrainTicketExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTrainIdIsNull() {
            addCriterion("train_id is null");
            return (Criteria) this;
        }

        public Criteria andTrainIdIsNotNull() {
            addCriterion("train_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrainIdEqualTo(Long value) {
            addCriterion("train_id =", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdNotEqualTo(Long value) {
            addCriterion("train_id <>", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdGreaterThan(Long value) {
            addCriterion("train_id >", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("train_id >=", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdLessThan(Long value) {
            addCriterion("train_id <", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdLessThanOrEqualTo(Long value) {
            addCriterion("train_id <=", value, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdIn(List<Long> values) {
            addCriterion("train_id in", values, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdNotIn(List<Long> values) {
            addCriterion("train_id not in", values, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdBetween(Long value1, Long value2) {
            addCriterion("train_id between", value1, value2, "trainId");
            return (Criteria) this;
        }

        public Criteria andTrainIdNotBetween(Long value1, Long value2) {
            addCriterion("train_id not between", value1, value2, "trainId");
            return (Criteria) this;
        }

        public Criteria andTicketNumIsNull() {
            addCriterion("ticket_num is null");
            return (Criteria) this;
        }

        public Criteria andTicketNumIsNotNull() {
            addCriterion("ticket_num is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNumEqualTo(Integer value) {
            addCriterion("ticket_num =", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotEqualTo(Integer value) {
            addCriterion("ticket_num <>", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumGreaterThan(Integer value) {
            addCriterion("ticket_num >", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ticket_num >=", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLessThan(Integer value) {
            addCriterion("ticket_num <", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLessThanOrEqualTo(Integer value) {
            addCriterion("ticket_num <=", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumIn(List<Integer> values) {
            addCriterion("ticket_num in", values, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotIn(List<Integer> values) {
            addCriterion("ticket_num not in", values, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumBetween(Integer value1, Integer value2) {
            addCriterion("ticket_num between", value1, value2, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ticket_num not between", value1, value2, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andSaleFlagIsNull() {
            addCriterion("sale_flag is null");
            return (Criteria) this;
        }

        public Criteria andSaleFlagIsNotNull() {
            addCriterion("sale_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSaleFlagEqualTo(Integer value) {
            addCriterion("sale_flag =", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagNotEqualTo(Integer value) {
            addCriterion("sale_flag <>", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagGreaterThan(Integer value) {
            addCriterion("sale_flag >", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_flag >=", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagLessThan(Integer value) {
            addCriterion("sale_flag <", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagLessThanOrEqualTo(Integer value) {
            addCriterion("sale_flag <=", value, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagIn(List<Integer> values) {
            addCriterion("sale_flag in", values, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagNotIn(List<Integer> values) {
            addCriterion("sale_flag not in", values, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagBetween(Integer value1, Integer value2) {
            addCriterion("sale_flag between", value1, value2, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_flag not between", value1, value2, "saleFlag");
            return (Criteria) this;
        }

        public Criteria andSaleDateIsNull() {
            addCriterion("sale_date is null");
            return (Criteria) this;
        }

        public Criteria andSaleDateIsNotNull() {
            addCriterion("sale_date is not null");
            return (Criteria) this;
        }

        public Criteria andSaleDateEqualTo(Date value) {
            addCriterion("sale_date =", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotEqualTo(Date value) {
            addCriterion("sale_date <>", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateGreaterThan(Date value) {
            addCriterion("sale_date >", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateGreaterThanOrEqualTo(Date value) {
            addCriterion("sale_date >=", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateLessThan(Date value) {
            addCriterion("sale_date <", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateLessThanOrEqualTo(Date value) {
            addCriterion("sale_date <=", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateIn(List<Date> values) {
            addCriterion("sale_date in", values, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotIn(List<Date> values) {
            addCriterion("sale_date not in", values, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateBetween(Date value1, Date value2) {
            addCriterion("sale_date between", value1, value2, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotBetween(Date value1, Date value2) {
            addCriterion("sale_date not between", value1, value2, "saleDate");
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