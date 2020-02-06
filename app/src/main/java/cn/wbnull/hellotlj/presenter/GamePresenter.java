package cn.wbnull.hellotlj.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.fastjson.JSONObject;

import cn.wbnull.hellotlj.App;
import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.model.AppRequest;
import cn.wbnull.hellotlj.model.AppResponse;
import cn.wbnull.hellotlj.model.game.GameJoinRequestData;
import cn.wbnull.hellotlj.model.game.GameJoinResponseData;
import cn.wbnull.hellotlj.service.game.GameService;
import cn.wbnull.hellotlj.util.SocketUtils;
import cn.wbnull.hellotlj.util.ThreadFactoryUtils;
import cn.wbnull.hellotlj.view.IGameView;

/**
 * 游戏界面 Presenter
 *
 * @author dukunbiao(null)  2020-02-06
 * https://github.com/dkbnull/HelloTlj
 */
public class GamePresenter extends BasePresenter<IGameView> {

    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter mIntentFilter;
    private Intent mServiceIntent;
    private MessageBackReciver mReciver;

    public void init() {
        ThreadFactoryUtils.getExecutorService("socket-%d").execute(
                () -> {
                    SocketUtils.connect();
                    SocketUtils.receiveMessage();
                });

        AppRequest<GameJoinRequestData> requestData = GameJoinRequestData.buildTable("1000");
        JSONObject request = new JSONObject();
        request.put("method", GameService.GAME_INFO);
        request.put("data", requestData);
        ThreadFactoryUtils.getExecutorService("socket-%d").execute(
                () -> {
                    while (!SocketUtils.connect) {

                    }

                    SocketUtils.sendMessage(request.toString());
                });

        initVariables();
        localBroadcastManager = LocalBroadcastManager.getInstance(App.getContext());
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(SocketUtils.SOCKET_HEART_ACTION);
        mIntentFilter.addAction(SocketUtils.SOCKET_ACTION);
        localBroadcastManager.registerReceiver(mReciver, mIntentFilter);
    }

    public void destroy() {
        SocketUtils.disconnect();
        localBroadcastManager.unregisterReceiver(mReciver);
    }

    public void info() {
        mView.showLoadingDialog();
        GameService.info("1000", new GlobalCallback<GameJoinResponseData>() {
            @Override
            public void onSuccess(GameJoinResponseData registerResponseData) {
                mView.hideLoadingDialog();
            }

            @Override
            public void onFailure(String msg) {
                mView.showHintDialog(msg);
                mView.hideLoadingDialog();
            }
        });
    }

    protected void initVariables() {
        mReciver = new MessageBackReciver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (SocketUtils.SOCKET_ACTION.equals(intent.getAction())) {
                    String method = intent.getStringExtra("method");
                    if (GameService.GAME_INFO.equals(method)) {
                        String data = intent.getStringExtra("data");
                        AppResponse appResponse = JSONObject.parseObject(data, AppResponse.class);

                        if (appResponse.isSuccess()) {
                            GameJoinResponseData responseData = JSONObject.parseObject(appResponse.getData().toString(),
                                    GameJoinResponseData.class);

                            mView.showHintDialog(responseData.toString());
                        } else {
                            mView.showHintDialog(appResponse.getMessage());
                        }
                    }
                }
            }
        };
    }

    public abstract class MessageBackReciver extends BroadcastReceiver {
        @Override
        public abstract void onReceive(Context context, Intent intent);
    }

//    private Messenger mMessenger = new Messenger(new Handler() {
//        @Override
//        public void handleMessage(Message msgFromServer) {
//            switch (msgFromServer.what) {
//                case MSG_SUM:
//                    TextView tv = (TextView) mLyContainer.findViewById(msgFromServer.arg1);
//                    tv.setText(tv.getText() + "=>" + msgFromServer.arg2);
//                    break;
//            }
//            super.handleMessage(msgFromServer);
//        }
//    });
}