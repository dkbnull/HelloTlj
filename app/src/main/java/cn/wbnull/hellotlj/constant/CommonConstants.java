package cn.wbnull.hellotlj.constant;

import android.os.Environment;

import java.io.File;

/**
 * 常量
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
public class CommonConstants {

    public static final String URL = "http://127.0.0.1:8081/hellotlj/";

    private static final String DEMO_PATH = Environment.getExternalStorageDirectory() + "/HelloTlj" + File.separator;
    public static final String LOG_PATH = DEMO_PATH + File.separator + "logs" + File.separator;
    public static final String CRASH_PATH = DEMO_PATH + File.separator + "crash" + File.separator;
}
