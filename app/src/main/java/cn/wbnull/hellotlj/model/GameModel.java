package cn.wbnull.hellotlj.model;

/**
 * 游戏数据
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public class GameModel {

    private static String tableId;

    public static String getTableId() {
        return tableId;
    }

    public static void setTableId(String tableId) {
        GameModel.tableId = tableId;
    }
}
