package top.tianqi.vitality.tools.utils.change.impl;

import top.tianqi.vitality.tools.utils.change.AbstractNumChange;

/**
 * 阿拉伯数字转中文数字
 *
 * @Author wkh
 * @Date 2020/8/13 9:40
 */
public class NumberToChinese extends AbstractNumChange<String, Long> {

    static {
        numberToChinese  = new NumberToChinese();
    }

    private static final NumberToChinese numberToChinese;

    private NumberToChinese(){}

    /**
     * 阿拉伯数字转中文数字
     * @param num
     * @return
     */
    public static String toChange(Long num) {
        return numberToChinese.change(num);
    }

    /**
     * <p>阿拉伯数字转中文数字</p>
     *
     * @param num 数字
     * @return
     */
    @Override
    public String change(Long num) {
        // 如果是0，则直接返回“零”
        if (num == ZERO) {
            return getNumChar()[0];
        }
        int unitPos = 0;//位数
        StringBuffer cn = new StringBuffer();
        boolean needZero = false;//是否需要补零
        while (num > 0) {
            long section = num % SECTION_UNIT;//num对10000取模
            if (needZero) {
                cn.insert(0, getNumChar()[0]);
            }
            cn.insert(0, section != ZERO ? getUnitSection()[unitPos] : getUnitSection()[ZERO]);
            sectionToChinese(section, cn);
            needZero = (section < THOUSAND_UNIT && section > ZERO);//1000以下0以上的，需要补零
            num = num / SECTION_UNIT;
            unitPos++;
        }
        // 处理一十一，一十二等特殊数字
        if (cn.indexOf("一十") == 0) {
            cn = cn.deleteCharAt(0);
        }
        return cn.toString();
    }

    /**
     * <p>对取模的余数进行处理</p>
     *
     * @param @param section
     * @param @param cn
     */
    private void sectionToChinese(long section, StringBuffer cn) {
        //对万以下的进行处理
        int unitPos = 0;//位数
        boolean zero = true;
        while (section > 0) {
            int index = (int) (section % TEN);//说明是十的整倍数
            if (index == ZERO) {
                if (!zero) {//补零
                    zero = true;
                    cn.insert(0, getNumChar()[index]);
                }
            } else {
                zero = false;
                cn.insert(0, getUnitChar()[unitPos]);
                cn.insert(0, getNumChar()[index]);
            }
            unitPos++;//从个位开始，每循环一轮位数进一
            section = section / TEN;
        }
    }

    private static void out(Object obj) {
        System.out.println(obj.toString());
    }

    public static void main(String[] args) {
        NumberToChinese numberToChinese = new NumberToChinese();
        long num = 1001L;
        out(num + " : " + numberToChinese.change(num));
        num = 10001L;
        out(num + " : " + numberToChinese.change(num));
        num = 110001L;
        out(num + " : " + numberToChinese.change(num));
        num = 1110001L;
        out(num + " : " + numberToChinese.change(num));
        num = 11000001L;
        out(num + " : " + numberToChinese.change(num));
        num = 111000001L;
        out(num + " : " + numberToChinese.change(num));
        num = 1111000001L;
        out(num + " : " + numberToChinese.change(num));
        num = 1431294967295L;
        out(num + " : " + numberToChinese.change(num));

        System.out.println(toChange(num));
    }
}
