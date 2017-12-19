package xd.medicine.entity.bo;

import java.util.ArrayList;
import java.util.List;

public class TrustAttrExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TrustAttrExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(Byte value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(Byte value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(Byte value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(Byte value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(Byte value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(Byte value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<Byte> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<Byte> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(Byte value1, Byte value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(Byte value1, Byte value2) {
            addCriterion("department not between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDemandTitleIsNull() {
            addCriterion("demand_title is null");
            return (Criteria) this;
        }

        public Criteria andDemandTitleIsNotNull() {
            addCriterion("demand_title is not null");
            return (Criteria) this;
        }

        public Criteria andDemandTitleEqualTo(Byte value) {
            addCriterion("demand_title =", value, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleNotEqualTo(Byte value) {
            addCriterion("demand_title <>", value, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleGreaterThan(Byte value) {
            addCriterion("demand_title >", value, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleGreaterThanOrEqualTo(Byte value) {
            addCriterion("demand_title >=", value, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleLessThan(Byte value) {
            addCriterion("demand_title <", value, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleLessThanOrEqualTo(Byte value) {
            addCriterion("demand_title <=", value, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleIn(List<Byte> values) {
            addCriterion("demand_title in", values, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleNotIn(List<Byte> values) {
            addCriterion("demand_title not in", values, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleBetween(Byte value1, Byte value2) {
            addCriterion("demand_title between", value1, value2, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandTitleNotBetween(Byte value1, Byte value2) {
            addCriterion("demand_title not between", value1, value2, "demandTitle");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageIsNull() {
            addCriterion("demand_workage is null");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageIsNotNull() {
            addCriterion("demand_workage is not null");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageEqualTo(Byte value) {
            addCriterion("demand_workage =", value, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageNotEqualTo(Byte value) {
            addCriterion("demand_workage <>", value, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageGreaterThan(Byte value) {
            addCriterion("demand_workage >", value, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageGreaterThanOrEqualTo(Byte value) {
            addCriterion("demand_workage >=", value, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageLessThan(Byte value) {
            addCriterion("demand_workage <", value, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageLessThanOrEqualTo(Byte value) {
            addCriterion("demand_workage <=", value, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageIn(List<Byte> values) {
            addCriterion("demand_workage in", values, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageNotIn(List<Byte> values) {
            addCriterion("demand_workage not in", values, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageBetween(Byte value1, Byte value2) {
            addCriterion("demand_workage between", value1, value2, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandWorkageNotBetween(Byte value1, Byte value2) {
            addCriterion("demand_workage not between", value1, value2, "demandWorkage");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeIsNull() {
            addCriterion("demand_degree is null");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeIsNotNull() {
            addCriterion("demand_degree is not null");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeEqualTo(Byte value) {
            addCriterion("demand_degree =", value, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeNotEqualTo(Byte value) {
            addCriterion("demand_degree <>", value, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeGreaterThan(Byte value) {
            addCriterion("demand_degree >", value, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeGreaterThanOrEqualTo(Byte value) {
            addCriterion("demand_degree >=", value, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeLessThan(Byte value) {
            addCriterion("demand_degree <", value, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeLessThanOrEqualTo(Byte value) {
            addCriterion("demand_degree <=", value, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeIn(List<Byte> values) {
            addCriterion("demand_degree in", values, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeNotIn(List<Byte> values) {
            addCriterion("demand_degree not in", values, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeBetween(Byte value1, Byte value2) {
            addCriterion("demand_degree between", value1, value2, "demandDegree");
            return (Criteria) this;
        }

        public Criteria andDemandDegreeNotBetween(Byte value1, Byte value2) {
            addCriterion("demand_degree not between", value1, value2, "demandDegree");
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