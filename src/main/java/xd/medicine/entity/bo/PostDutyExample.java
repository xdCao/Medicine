package xd.medicine.entity.bo;

import java.util.ArrayList;
import java.util.List;

public class PostDutyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PostDutyExample() {
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

        public Criteria andDutyContentIsNull() {
            addCriterion("duty_content is null");
            return (Criteria) this;
        }

        public Criteria andDutyContentIsNotNull() {
            addCriterion("duty_content is not null");
            return (Criteria) this;
        }

        public Criteria andDutyContentEqualTo(String value) {
            addCriterion("duty_content =", value, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentNotEqualTo(String value) {
            addCriterion("duty_content <>", value, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentGreaterThan(String value) {
            addCriterion("duty_content >", value, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentGreaterThanOrEqualTo(String value) {
            addCriterion("duty_content >=", value, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentLessThan(String value) {
            addCriterion("duty_content <", value, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentLessThanOrEqualTo(String value) {
            addCriterion("duty_content <=", value, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentLike(String value) {
            addCriterion("duty_content like", value, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentNotLike(String value) {
            addCriterion("duty_content not like", value, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentIn(List<String> values) {
            addCriterion("duty_content in", values, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentNotIn(List<String> values) {
            addCriterion("duty_content not in", values, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentBetween(String value1, String value2) {
            addCriterion("duty_content between", value1, value2, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andDutyContentNotBetween(String value1, String value2) {
            addCriterion("duty_content not between", value1, value2, "dutyContent");
            return (Criteria) this;
        }

        public Criteria andPresetTimeIsNull() {
            addCriterion("preset_time is null");
            return (Criteria) this;
        }

        public Criteria andPresetTimeIsNotNull() {
            addCriterion("preset_time is not null");
            return (Criteria) this;
        }

        public Criteria andPresetTimeEqualTo(Byte value) {
            addCriterion("preset_time =", value, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeNotEqualTo(Byte value) {
            addCriterion("preset_time <>", value, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeGreaterThan(Byte value) {
            addCriterion("preset_time >", value, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeGreaterThanOrEqualTo(Byte value) {
            addCriterion("preset_time >=", value, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeLessThan(Byte value) {
            addCriterion("preset_time <", value, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeLessThanOrEqualTo(Byte value) {
            addCriterion("preset_time <=", value, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeIn(List<Byte> values) {
            addCriterion("preset_time in", values, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeNotIn(List<Byte> values) {
            addCriterion("preset_time not in", values, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeBetween(Byte value1, Byte value2) {
            addCriterion("preset_time between", value1, value2, "presetTime");
            return (Criteria) this;
        }

        public Criteria andPresetTimeNotBetween(Byte value1, Byte value2) {
            addCriterion("preset_time not between", value1, value2, "presetTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeIsNull() {
            addCriterion("grace_time is null");
            return (Criteria) this;
        }

        public Criteria andGraceTimeIsNotNull() {
            addCriterion("grace_time is not null");
            return (Criteria) this;
        }

        public Criteria andGraceTimeEqualTo(Byte value) {
            addCriterion("grace_time =", value, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeNotEqualTo(Byte value) {
            addCriterion("grace_time <>", value, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeGreaterThan(Byte value) {
            addCriterion("grace_time >", value, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeGreaterThanOrEqualTo(Byte value) {
            addCriterion("grace_time >=", value, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeLessThan(Byte value) {
            addCriterion("grace_time <", value, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeLessThanOrEqualTo(Byte value) {
            addCriterion("grace_time <=", value, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeIn(List<Byte> values) {
            addCriterion("grace_time in", values, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeNotIn(List<Byte> values) {
            addCriterion("grace_time not in", values, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeBetween(Byte value1, Byte value2) {
            addCriterion("grace_time between", value1, value2, "graceTime");
            return (Criteria) this;
        }

        public Criteria andGraceTimeNotBetween(Byte value1, Byte value2) {
            addCriterion("grace_time not between", value1, value2, "graceTime");
            return (Criteria) this;
        }

        public Criteria andEmerIsNull() {
            addCriterion("emer is null");
            return (Criteria) this;
        }

        public Criteria andEmerIsNotNull() {
            addCriterion("emer is not null");
            return (Criteria) this;
        }

        public Criteria andEmerEqualTo(Float value) {
            addCriterion("emer =", value, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerNotEqualTo(Float value) {
            addCriterion("emer <>", value, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerGreaterThan(Float value) {
            addCriterion("emer >", value, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerGreaterThanOrEqualTo(Float value) {
            addCriterion("emer >=", value, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerLessThan(Float value) {
            addCriterion("emer <", value, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerLessThanOrEqualTo(Float value) {
            addCriterion("emer <=", value, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerIn(List<Float> values) {
            addCriterion("emer in", values, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerNotIn(List<Float> values) {
            addCriterion("emer not in", values, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerBetween(Float value1, Float value2) {
            addCriterion("emer between", value1, value2, "emer");
            return (Criteria) this;
        }

        public Criteria andEmerNotBetween(Float value1, Float value2) {
            addCriterion("emer not between", value1, value2, "emer");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andChooseIsNull() {
            addCriterion("choose is null");
            return (Criteria) this;
        }

        public Criteria andChooseIsNotNull() {
            addCriterion("choose is not null");
            return (Criteria) this;
        }

        public Criteria andChooseEqualTo(Boolean value) {
            addCriterion("choose =", value, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseNotEqualTo(Boolean value) {
            addCriterion("choose <>", value, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseGreaterThan(Boolean value) {
            addCriterion("choose >", value, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("choose >=", value, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseLessThan(Boolean value) {
            addCriterion("choose <", value, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseLessThanOrEqualTo(Boolean value) {
            addCriterion("choose <=", value, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseIn(List<Boolean> values) {
            addCriterion("choose in", values, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseNotIn(List<Boolean> values) {
            addCriterion("choose not in", values, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseBetween(Boolean value1, Boolean value2) {
            addCriterion("choose between", value1, value2, "choose");
            return (Criteria) this;
        }

        public Criteria andChooseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("choose not between", value1, value2, "choose");
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