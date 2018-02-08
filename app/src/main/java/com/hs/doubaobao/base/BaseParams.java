package com.hs.doubaobao.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.hs.doubaobao.BuildConfig;
import com.hs.doubaobao.MyApplication;

import java.io.File;

/**
 * 作者：zhanghaitao on 2017/9/12 16:46
 * 邮箱：820159571@qq.com
 *
 * @describe:基本参数类，存放：联网地址、通用参数...
 */

public class BaseParams {

/*--常量--------------------------------------------------------------------------------------------*/

    //是否debug，尝试从BuildConfig里面获取
    public static boolean isDebug = BuildConfig.LOG_DEBUG;

    public static final String APP_KEY = "59CC8095F69F3F9F1D31935C771F1957";
    public static final String APP_SECRET = "875FF994A2CA061AB1634A6E7296DC94";
    // App端类型  ios:1  Android:2
    public static final String MOBILE_TYPE = "2";
    //测试地址
    public static final String TEST_URL = /*"http://manage.ahbabybean.com:8081/";*/
            "http://192.168.1.103:8081/";
            /*"http://117.71.50.199:4083/";*/

    public static final String TEST_FILE_URL = /*"http://manage.ahbabybean.com:8085/";*/
            " http://192.168.1.103:8085/";
            /*"http://117.71.50.199:4080/";*/

    //生产环境
    public static final String TRUE_URL =
           /* "http://manage.ahbabybean.com:8081/";*/
/*    "http://117.71.50.199:4083/";*/
            "http://192.168.1.103:8081/";

    public static final String TRUE_FILE_URL =
         /*   "http://192.168.1.103:8085/";*/
    /*"http://117.71.50.199:4080/";*/
    " http://192.168.1.103:8085/";

    //http路径
    public static final String BASE_URL = isDebug ? TEST_URL : TRUE_URL;
    public static final String FILE_URL = isDebug ? TEST_FILE_URL : TRUE_FILE_URL;

    public static String USER_ID = "";
    public static String OPERATOR_NAME = "";


    /**
     * 根路径
     */
    public static final String ROOT_PATH = getSDPath() + "/Ultron";
    /**
     * crash文件保存路径
     */
    public static final String CRASH_PATH = ROOT_PATH + "/crashLog";


/*--URL--------------------------------------------------------------------------------------------------*/

    //登陆地址
    public static final String LOGIN_URL = BASE_URL + "app/user/doLogin.html";
    //首页地址
    public static final String HOME_URL = BASE_URL + "app/common/list.html";
    //风控列表地址
    public static final String RISK_URL = BASE_URL + "app/riskControl/list.html";
    //总经理列表地址
    public static final String MANAGER_URL = BASE_URL + "app/manager/list.html";
    //无效列表地址
    public static final String INVALID_URL = BASE_URL + "/app/common/disableList.html";
    //无效详情列表地址
    public static final String INVALID_REASON_URL = BASE_URL + "app/common/disableDetail.html";
    //详情地址
    public static final String PARTICULARS_URL = BASE_URL + "app/common/infoDetail.html";
    //图片地址
    public static final String PICTRUE_URL = BASE_URL + "app/common/picList.html";
    //图片地址
    public static final String VIDEO_URL = BASE_URL + "app/common/vedioList.html";
    //参考表
    public static final String REFRERNCE_URL = BASE_URL + "app/common/reference.html";
    //审批
    public static final String APPROVAL_URL = BASE_URL + "app/approval.html";
    //贷款申请列表
    public static final String LOAN_APPLICATION_URL = BASE_URL + "app/borrower/borrowList.html";
    //部门初审
    public static final String DEPARTMENT_TRIAL_URL = BASE_URL + "app/borrower/borrowList.html";
    //门店一审
    public static final String STORES_INSTANCE_URL = BASE_URL + "app/borrower/borrowList.html";
    //部门统计
    public static final String DEPARTMENT_STATISTICS_URL = BASE_URL + "app/dept/countStatistics.html";
    //借款信息保存与提交
    public static final String SUBMIT_URL = BASE_URL + "app/borrow/dataSave.html";
    public static final String LOAN_APPLICATION_PARTICULARS_URL = BASE_URL + "/app/borrowData.html";
    //图片上传
    public static final String UPLOAD_PICTURE = FILE_URL + "/upload/appAvatar.html";
    //视频上传
    public static final String UPLOAD_VIDEO = FILE_URL + "/upload/saveVideo.html";





/*--获取常规值方法---------------------------------------------------------------------------------------*/

    /**
     * 获取SD卡的根目录
     */
    public static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        if (sdDir == null) {
            return "";
        } else {
            return sdDir.toString();
        }
    }

    /**
     * 获取VersionCode
     */
    public static int getVersion() {
        try {
            Context context = MyApplication.getContext();
            PackageManager pm = context.getPackageManager();//context为当前Activity上下文
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 1;
        }
    }

    /**
     * 获取VersionName
     */
    public static String getVersionName() {
        try {
            Context context = MyApplication.getContext();
            PackageManager pm = context.getPackageManager();//context为当前Activity上下文
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "--";
        }
    }


}
