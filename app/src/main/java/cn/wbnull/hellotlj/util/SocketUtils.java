package cn.wbnull.hellotlj.util;

import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.fastjson.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import cn.wbnull.hellotlj.App;
import cn.wbnull.hellotlj.constant.CommonConstants;

/**
 * Socket连接工具类
 *
 * @author dukunbiao(null)  2020-02-06
 * https://github.com/dkbnull/HelloTlj
 */
public class SocketUtils {

    public static final String SOCKET_HEART_ACTION = "cn.wbnull.hellotlj.socket.heart";
    public static final String SOCKET_ACTION = "cn.wbnull.hellotlj.socket";

    private static Socket socket = null;
    private static DataOutputStream dos = null;
    private static DataInputStream dis = null;

    public static boolean connect = false;

    private static LocalBroadcastManager mLocalBroadcastManager;

    public static void connect() {
        try {
            socket = new Socket(CommonConstants.SOCKET_IP, CommonConstants.SOCKET_PORT);
            LoggerUtils.getLogger().info("socket connect succeed");
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            connect = true;
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(App.getContext());
        } catch (Exception e) {
            LoggerUtils.getLogger().error("socket connect exception", e);
        }
    }

    public static void disconnect() {
        try {
            dos.close();
            dis.close();
            socket.close();
            connect = false;
        } catch (IOException e) {
            LoggerUtils.getLogger().error("socket disconnect exception", e);
        }
    }

    public static void sendMessage(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            LoggerUtils.getLogger().error("socket sendMessage exception", e);
        }
    }

    public static void receiveMessage() {
        try {
            while (connect) {
                String msg = dis.readUTF();
                JSONObject response = JSONObject.parseObject(msg);

                Intent intent = new Intent(SOCKET_ACTION);
                intent.putExtra("method", response.getString("method"));
                intent.putExtra("data", response.getString("data"));
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        } catch (Exception e) {
            LoggerUtils.getLogger().error("socket ReceiveThread exception", e);
        }
    }
}
