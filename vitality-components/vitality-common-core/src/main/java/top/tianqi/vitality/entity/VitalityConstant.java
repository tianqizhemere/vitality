package top.tianqi.vitality.entity;

/**
 * 系统常量
 *
 * @Author wkh
 * @Date 2020/8/19 15:14
 */
public class VitalityConstant {

    /** 验证码类型，gif类型 */
    public static final String GIF = "gif";
    /**验证码类型，png类型 */
    public static final String PNG = "png";

    /** 验证码 key前缀*/
    public static final String CODE_PREFIX = "vitality.captcha.";


    /** Gateway请求头TOKEN名称*/
    public static final String GATEWAY_TOKEN_HEADER = "GatewayToken";
    /** Gateway名称请求头TOKEN值 */
    public static final String GATEWAY_TOKEN_VALUE = "vitality:gateway:123456";

}
