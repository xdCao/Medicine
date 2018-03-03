package xd.medicine.calculator;

/**
 * created by liubotao
 */
public final class Constants {
    private Constants(){
    }

    static final float MT_ALPHA = (float) 1/12; //计算MT时，权值的随机波动参数
    static final float HBT_M = 5; //计算HBT时，抽样的次数
    static final int HBT_N = 4; //计算HBT时，每次抽样的样本个数
    static final float DIFFER_MAX = (float) 1/2; //计算HBT时，抽样样本值和抽样平均值的最大平均差值
    static final float TRUST_U1 = (float)3/4; //MT的权值为TRUST_U1，HBT的权值为1-TRUST_U1
    static final float TRUST_U2 = (float)1/2; //bsTrust的权值为TRUST_U2，poobtrust的权值为1-TRUST_U2
    static final float THS_VALUE = (float)1/2; //计算MT时，当医生的属性满足病人的要求时的阈值
    static final float[] SENSITIVITY_W = { (float)1/10 , (float)1/10 , (float)1/5 , (float)1/5 , (float)1/5 , (float)1/5 }; //敏感度计算中，各项影响因素的权值
    //static final float OTHER_BS_TRUST = (float)1/2; //其他类型用户的bsTrust
    static final float D_AWARD = (float) 1/2; //二次评估中，每个等级差的奖励值d
    static float beta1 = (float) 7/10; //可信主体集区分级别的参数1
    static float beta2 = (float) 6/10; //可信主体集区分级别的参数2
    static float rThs = (float) 1/2; //风险阈值

    public static float getBeta1() {
        return beta1;
    }

    public static void setBeta1(float beta1) {
        Constants.beta1 = beta1;
    }

    public static float getBeta2() {
        return beta2;
    }

    public static void setBeta2(float beta2) {
        Constants.beta2 = beta2;
    }

    public static float getrThs() {
        return rThs;
    }

    public static void setrThs(float rThs) {
        Constants.rThs = rThs;
    }

}
