package cn.wbnull.hellotlj.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.wbnull.hellotlj.App;
import cn.wbnull.hellotlj.boot.GlobalCallback;
import cn.wbnull.hellotlj.config.AppConfig;
import cn.wbnull.hellotlj.model.AppRequest;
import cn.wbnull.hellotlj.model.AppResponse;
import cn.wbnull.hellotlj.model.GameItemModel;
import cn.wbnull.hellotlj.model.game.GameJoinRequestData;
import cn.wbnull.hellotlj.model.game.GameJoinResponseData;
import cn.wbnull.hellotlj.model.user.RegisterResponseData;
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

    private LocalBroadcastManager mLocalBroadcastManager;
    private BroadcastReceiver mBroadcastReceiver;

    public void init() {
        initReceiver();
        initManager();
        initSocket();
    }

    private void initSocket() {
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
                    //如果没有连接上Socket，等待
                    while (!SocketUtils.connect) {

                    }

                    SocketUtils.sendMessage(request.toString());
                });
    }

    private void initManager() {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(App.getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SocketUtils.SOCKET_HEART_ACTION);
        intentFilter.addAction(SocketUtils.SOCKET_ACTION);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void initReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (SocketUtils.SOCKET_ACTION.equals(intent.getAction())) {
                    String method = intent.getStringExtra("method");
                    if (GameService.GAME_INFO.equals(method)) {
                        gameInfoUpdate(intent);
                    }
                }
            }
        };
    }

    private void gameInfoUpdate(Intent intent) {
        String data = intent.getStringExtra("data");
        AppResponse appResponse = JSONObject.parseObject(data, AppResponse.class);

        if (appResponse.isSuccess()) {
            GameJoinResponseData responseData = JSONObject.parseObject(appResponse.getData().toString(),
                    GameJoinResponseData.class);
            List<RegisterResponseData> userinfos = responseData.getUserinfos();
            for (RegisterResponseData userinfo : userinfos) {
                if (userinfo == null) {
                    continue;
                }

                GameItemModel gameItemModel = GameItemModel.build(userinfo);

                if (userinfo.getUserId().equals(AppConfig.getUserId())) {
                    mView.updateNowUser(gameItemModel);
                } else {
                    mView.addGameUser(gameItemModel);
                }
            }
        } else {
            mView.showHintDialog(appResponse.getMessage());
        }
    }

    public void unregister() {
        SocketUtils.disconnect();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }

    public void info() {
        mView.showLoadingDialog();
        GameService.info("1000", new GlobalCallback<GameJoinResponseData>() {
            @Override
            public void onSuccess(GameJoinResponseData gameJoinResponseData) {
                if (gameJoinResponseData.getGametable().isOwner()) {
                    mView.showBtnStart();
                }
                mView.hideLoadingDialog();
            }

            @Override
            public void onFailure(String msg) {
                mView.showHintDialog(msg);
                mView.hideLoadingDialog();
            }
        });
    }
}