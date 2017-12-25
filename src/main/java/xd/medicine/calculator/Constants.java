package xd.medicine.calculator;

/**
 * created by liubotao
 */
public final class Constants {
    private Constants(){
    }

    static final float MTALPHA = (float) 1/12; //计算MT时，权值的随机波动参数
    static final float HBTM = 5; //计算HBT时，抽样的次数
    static final int HBTN = 4; //计算HBT时，每次抽样的样本个数
    static final float DIFFERMAX = (float) 1/2; //计算HBT时，抽样样本值和抽样平均值的最大平均差值
    static final float TRUSTU = (float)1/2; //MT的权值为TRUSTU，HBT的权值为1-TRUSTU
    static final float THSVALUE = (float)1/2; //计算MT时，当医生的属性满足病人的要求时的阈值
}
