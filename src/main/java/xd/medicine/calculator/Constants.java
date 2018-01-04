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
}
