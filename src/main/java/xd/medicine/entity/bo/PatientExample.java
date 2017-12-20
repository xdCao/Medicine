package xd.medicine.entity.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PatientExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PatientExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNull() {
            addCriterion("doctor_id is null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNotNull() {
            addCriterion("doctor_id is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdEqualTo(Integer value) {
            addCriterion("doctor_id =", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotEqualTo(Integer value) {
            addCriterion("doctor_id <>", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThan(Integer value) {
            addCriterion("doctor_id >", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("doctor_id >=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThan(Integer value) {
            addCriterion("doctor_id <", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThanOrEqualTo(Integer value) {
            addCriterion("doctor_id <=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIn(List<Integer> values) {
            addCriterion("doctor_id in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotIn(List<Integer> values) {
            addCriterion("doctor_id not in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdBetween(Integer value1, Integer value2) {
            addCriterion("doctor_id between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("doctor_id not between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andRegistryDateIsNull() {
            addCriterion("registry_date is null");
            return (Criteria) this;
        }

        public Criteria andRegistryDateIsNotNull() {
            addCriterion("registry_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegistryDateEqualTo(Date value) {
            addCriterionForJDBCDate("registry_date =", value, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("registry_date <>", value, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateGreaterThan(Date value) {
            addCriterionForJDBCDate("registry_date >", value, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registry_date >=", value, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateLessThan(Date value) {
            addCriterionForJDBCDate("registry_date <", value, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registry_date <=", value, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateIn(List<Date> values) {
            addCriterionForJDBCDate("registry_date in", values, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("registry_date not in", values, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registry_date between", value1, value2, "registryDate");
            return (Criteria) this;
        }

        public Criteria andRegistryDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registry_date not between", value1, value2, "registryDate");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdIsNull() {
            addCriterion("trustAttr_id is null");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdIsNotNull() {
            addCriterion("trustAttr_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdEqualTo(Integer value) {
            addCriterion("trustAttr_id =", value, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdNotEqualTo(Integer value) {
            addCriterion("trustAttr_id <>", value, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdGreaterThan(Integer value) {
            addCriterion("trustAttr_id >", value, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trustAttr_id >=", value, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdLessThan(Integer value) {
            addCriterion("trustAttr_id <", value, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdLessThanOrEqualTo(Integer value) {
            addCriterion("trustAttr_id <=", value, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdIn(List<Integer> values) {
            addCriterion("trustAttr_id in", values, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdNotIn(List<Integer> values) {
            addCriterion("trustAttr_id not in", values, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdBetween(Integer value1, Integer value2) {
            addCriterion("trustAttr_id between", value1, value2, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andTrustattrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trustAttr_id not between", value1, value2, "trustattrId");
            return (Criteria) this;
        }

        public Criteria andSenseAwareIsNull() {
            addCriterion("sense_aware is null");
            return (Criteria) this;
        }

        public Criteria andSenseAwareIsNotNull() {
            addCriterion("sense_aware is not null");
            return (Criteria) this;
        }

        public Criteria andSenseAwareEqualTo(Boolean value) {
            addCriterion("sense_aware =", value, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareNotEqualTo(Boolean value) {
            addCriterion("sense_aware <>", value, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareGreaterThan(Boolean value) {
            addCriterion("sense_aware >", value, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareGreaterThanOrEqualTo(Boolean value) {
            addCriterion("sense_aware >=", value, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareLessThan(Boolean value) {
            addCriterion("sense_aware <", value, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareLessThanOrEqualTo(Boolean value) {
            addCriterion("sense_aware <=", value, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareIn(List<Boolean> values) {
            addCriterion("sense_aware in", values, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareNotIn(List<Boolean> values) {
            addCriterion("sense_aware not in", values, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareBetween(Boolean value1, Boolean value2) {
            addCriterion("sense_aware between", value1, value2, "senseAware");
            return (Criteria) this;
        }

        public Criteria andSenseAwareNotBetween(Boolean value1, Boolean value2) {
            addCriterion("sense_aware not between", value1, value2, "senseAware");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionIsNull() {
            addCriterion("illness_condition is null");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionIsNotNull() {
            addCriterion("illness_condition is not null");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionEqualTo(String value) {
            addCriterion("illness_condition =", value, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionNotEqualTo(String value) {
            addCriterion("illness_condition <>", value, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionGreaterThan(String value) {
            addCriterion("illness_condition >", value, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionGreaterThanOrEqualTo(String value) {
            addCriterion("illness_condition >=", value, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionLessThan(String value) {
            addCriterion("illness_condition <", value, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionLessThanOrEqualTo(String value) {
            addCriterion("illness_condition <=", value, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionLike(String value) {
            addCriterion("illness_condition like", value, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionNotLike(String value) {
            addCriterion("illness_condition not like", value, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionIn(List<String> values) {
            addCriterion("illness_condition in", values, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionNotIn(List<String> values) {
            addCriterion("illness_condition not in", values, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionBetween(String value1, String value2) {
            addCriterion("illness_condition between", value1, value2, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andIllnessConditionNotBetween(String value1, String value2) {
            addCriterion("illness_condition not between", value1, value2, "illnessCondition");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
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