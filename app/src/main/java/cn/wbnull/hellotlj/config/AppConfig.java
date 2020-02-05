package cn.wbnull.hellotlj.config;

import android.content.Context;
import android.content.SharedPreferences;

import cn.wbnull.hellotlj.App;
import cn.wbnull.helloutil.util.StringUtils;

/**
 * APP配置信息
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public class AppConfig {

    private static volatile SharedPreferences sharedPreferences;
    private static volatile SharedPreferences.Editor editor;

    private static final String USER_ID = "userId";
    private static final String NICKNAME = "nickname";
    private static final String SCORE = "score";
    private static final String WIN_NUM = "winNum";
    private static final String FAIL_NUM = "failNum";
    private static final String DOGFALL_NUM = "dogfallNum";

    static {
        String preferencesName = String.format("%s_preferences", App.getContext().getApplication().getPackageName());
        sharedPreferences = App.getInstance().getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static boolean hasLogin() {
        return sharedPreferences != null && !StringUtils.isEmpty(getUserId());
    }

    public static String getUserId() {
        return sharedPreferences.getString(USER_ID, "");
    }

    public static void setUserId(String userId) {
        editor.putString(USER_ID, userId);
        editor.commit();
    }

    public static String getNickname() {
        return sharedPreferences.getString(NICKNAME, "");
    }

    public static void setNickname(String nickname) {
        editor.putString(NICKNAME, nickname);
        editor.commit();
    }

    public static String getScore() {
        return sharedPreferences.getString(SCORE, "");
    }

    public static void setScore(String score) {
        editor.putString(SCORE, score);
        editor.commit();
    }

    public static String getWinNum() {
        return sharedPreferences.getString(WIN_NUM, "");
    }

    public static void setWinNum(String winNum) {
        editor.putString(WIN_NUM, winNum);
        editor.commit();
    }

    public static String getFailNum() {
        return sharedPreferences.getString(FAIL_NUM, "");
    }

    public static void setFailNum(String failNum) {
        editor.putString(FAIL_NUM, failNum);
        editor.commit();
    }

    public static String getDogfallNum() {
        return sharedPreferences.getString(DOGFALL_NUM, "");
    }

    public static void setDogfallNum(String dogfallNum) {
        editor.putString(DOGFALL_NUM, dogfallNum);
        editor.commit();
    }
}
