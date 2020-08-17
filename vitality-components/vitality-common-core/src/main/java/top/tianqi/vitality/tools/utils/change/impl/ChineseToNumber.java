package top.tianqi.vitality.tools.utils.change.impl;

import top.tianqi.vitality.tools.utils.change.AbstractNumChange;

/**
 * 中文数字转阿拉伯数字
 *
 * @Author wkh
 * @Date 2020/8/13 9:31
 */
public class ChineseToNumber extends AbstractNumChange<Long, String> {

    static {
        chineseToNumber = new ChineseToNumber();
    }

    private static ChineseToNumber chineseToNumber;

    private ChineseToNumber(){}

    /**
     * 中文数字转阿拉伯数字
     *
     * @param value
     * @return
     */
    public static Long toChange(String value) {
        return chineseToNumber.change(value);
    }

    /**
     * 中文数字转阿拉伯数字
     * @param str 中文数字
     * @return
     */
    @Override
    public Long change(String str) {
        Long nc = Long.valueOf(ZERO);
        long section = 0;
        long number = 0;
        BooleanEx secUnit = new BooleanEx(0);
        int pos = ZERO;

        while (str.length() > pos) {
            long num = chineseToValue(str.substring(pos, pos + 1));
            if (num >= 0) {
                number = num;
                pos++;
                if (pos >= str.length()) {
                    section += number;
                    nc += section;
                    break;
                }
            } else {
                long unit = chineseToUnit(str.substring(pos, pos + 1), secUnit);
                if (secUnit.getBoolean()) {
                    section = (section + number) * unit;
                    nc += section;
                    section = 0;
                } else {
                    section += (number * unit);
                }
                number = 0;
                pos++;
                if (pos >= str.length()) {
                    nc += section;
                    break;
                }
            }
        }

        return nc;
    }

    /**
     * 判断是数字还是单位
     *
     * @param str
     * @return
     */
    private int chineseToValue(String str) {
        for (int i = 0; i < getNumChar().length; i++) {
            if (str.equals(getNumChar()[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断是否是节权位，如果是 则返回节权位的值
     *
     * @param str
     * @param secUnit
     * @return
     */
    private long chineseToUnit(String str, BooleanEx secUnit) {
        for (int i = 0; i < getChnValuePair().length; i++) {
            if (str.equals(getChnValuePair()[i][0])) {
                secUnit.setSeed((int)getChnValuePair()[i][2]);
                return (long)getChnValuePair()[i][1];
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        ChineseToNumber change = new ChineseToNumber();
        System.out.println(change.change("一兆四千三百一十二亿九千四百九十六万七千二百九十五"));
        System.out.println(toChange("一万五千"));
    }
}
