package cn.litchi.model.utils;

public class MallResultStatus {
    public static final Integer OK = 200;
    public static final Integer SERVER_OPERATION_FAIL = 401;// 数据库错误
    public static final Integer LOGIN_FAIL = 601;           // 登录失败
    public static final Integer PARAMETER_ERROR = 701;      // 参数错误
    public static final Integer PERMISSION_DENIED = 702;    // 没有权限
    public static final Integer USER_NAME_HAVE_USED = 301;  // 用户名已被占用
}
