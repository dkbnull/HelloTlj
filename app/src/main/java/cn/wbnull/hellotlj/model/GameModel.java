package cn.wbnull.hellotlj.model;

import com.alibaba.fastjson.JSONArray;

/**
 * 游戏数据
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public class GameModel {

    private static String tableId;
    private static JSONArray pokers;

    public static String getTableId() {
        return tableId;
    }

    public static void setTableId(String tableId) {
        GameModel.tableId = tableId;
    }

    public static JSONArray getPokers() {
        return pokers;
    }

    public static void setPokers(JSONArray pokers) {
        GameModel.pokers = pokers;
    }
}
