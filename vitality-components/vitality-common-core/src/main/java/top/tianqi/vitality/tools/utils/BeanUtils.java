package top.tianqi.vitality.tools.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * 对象深拷贝工具类
 *
 * @author wukh
 * @Date 2020/04/30
 */
public class BeanUtils {

    private BeanUtils() {}

    /**
     * @Description <p> 拷贝非空对象属性值 </P>
     * @param source 原始对象
     * @param dest 目标对象
     */
    public static void copyProperties(final Object source, final Object dest) {
        if (source == null) {
            throw new IllegalArgumentException("source目标对象未定义");
        }
        if (dest == null) {
            throw new IllegalArgumentException("dest目标对象未定义");
        }
        org.springframework.beans.BeanUtils.copyProperties(source, dest, getNullPropertyNames(source));
    }

    /**
     * @Description <p>获取到对象中属性为null的属性名</P>
     * @param source 要拷贝的对象
     * @return
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
