package cn.wbnull.hellotlj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.wbnull.hellotlj.R;
import cn.wbnull.hellotlj.model.GameItemModel;
import cn.wbnull.helloutil.util.StringUtils;
import lombok.NonNull;

/**
 * 游戏界面玩家 RecyclerView 适配器
 *
 * @author dukunbiao(null)  2020-02-07
 * https://github.com/dkbnull/HelloTlj
 */
public class GameItemAdapter extends RecyclerView.Adapter<GameItemAdapter.ListViewHolder> implements View.OnClickListener {

    private List<GameItemModel> gameItemModels;

    private OnItemClickListener onItemClickListener;

    private Context context;

    public GameItemAdapter(RecyclerView recyclerView) {
        this.gameItemModels = new ArrayList<>();
        this.context = recyclerView.getContext();
    }

    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_game_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        GameItemModel gameItemModel = gameItemModels.get(i);

        if (!StringUtils.isEmpty(gameItemModel.getNickname())) {
            listViewHolder.gameItemTvUser.setText(gameItemModel.getNickname());
        }
        listViewHolder.gameItemTvPoker.setText(gameItemModel.getPokersFormat());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return gameItemModels.size();
    }

    public void addItem(GameItemModel gameItemModel) {
        gameItemModels.add(gameItemModel);
        notifyDataSetChanged();
    }

    public void updateItem(GameItemModel gameItemModel) {
        for (GameItemModel itemModel : gameItemModels) {
            if (itemModel.getUserId().equals(gameItemModel.getUserId())) {
                itemModel.setPokers(gameItemModel.getPokers());
            }
        }

        notifyDataSetChanged();
    }

    public GameItemModel getItem(int position) {
        return gameItemModels.get(position);
    }

    public void removeItem(int position) {
        gameItemModels.remove(position);
        notifyDataSetChanged();
    }

    public void clearItem() {
        gameItemModels.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();

        if (onItemClickListener != null) {

        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView gameItemTvUser;
        public TextView gameItemTvPoker;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            gameItemTvUser = itemView.findViewById(R.id.gameItemTvUser);
            gameItemTvPoker = itemView.findViewById(R.id.gameItemTvPoker);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, GameItemBtnName btnName, int position);
    }

    public enum GameItemBtnName {

    }
}
