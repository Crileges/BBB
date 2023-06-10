package com.example.bbbthirdtry.MainFragments.Quest;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bbbthirdtry.QuestList;
import com.example.bbbthirdtry.R;
import com.example.bbbthirdtry.databinding.FragmentQuestsItemBinding;

import java.util.List;

public class QuestRecyclerViewAdapter extends RecyclerView.Adapter<QuestRecyclerViewAdapter.ViewHolder> {

    public static void setList(List<Quest> list) {
        QuestRecyclerViewAdapter.list = list;
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    public static List<Quest> list;
    public static QuestRecyclerViewAdapter adapter = null;

    public QuestRecyclerViewAdapter(List<Quest> items) {
        adapter = this;
        list = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentQuestsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.quest = list.get(position);
        setQuestIcons(holder);
        updateQuests(holder);


        holder.cardBtn.setText(holder.quest.title);
        holder.cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showMoreButtons
                showOrNotShowMoreOptions(holder);
            }
        });
        holder.btnClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claimQuest(holder);
            }
        });
    }

    private void showOrNotShowMoreOptions(ViewHolder holder) {
        if(holder.expandedBtns.getVisibility() == View.VISIBLE){
            holder.expandedBtns.setVisibility(View.GONE);
        } else{
            holder.expandedBtns.setVisibility(View.VISIBLE);
        }
    }

    private void updateQuests(ViewHolder holder) {
        if(holder.quest.getCategory() == Categories.MAIN){
            holder.cardBtn.setBackgroundResource(R.drawable.questcard_main);
            holder.btnRoute.setBackgroundResource(R.drawable.questcard_main);
            holder.btnClaim.setBackgroundResource(R.drawable.questcard_main);
            holder.btnInfo.setBackgroundResource(R.drawable.questcard_main);
        } else {
            holder.cardBtn.setBackgroundResource(R.drawable.questcard);
            holder.btnRoute.setBackgroundResource(R.drawable.questcard);
            holder.btnClaim.setBackgroundResource(R.drawable.questcard);
            holder.btnInfo.setBackgroundResource(R.drawable.questcard);
        }
        if (holder.quest.done){
            holder.ivQuestCardIcon.setBackgroundResource(R.drawable.check);
            holder.cardBtn.setBackgroundResource(R.drawable.questcardcomplete);
            holder.btnRoute.setBackgroundResource(R.drawable.questcardcomplete);
            holder.btnClaim.setBackgroundResource(R.drawable.questcardcomplete);
            holder.btnInfo.setBackgroundResource(R.drawable.questcardcomplete);
        }
    }

    private void setQuestIcons(ViewHolder holder) {
        int categoryId = 0;
        switch (holder.quest.category){
            case SIGHTSEEING: categoryId = R.drawable.camera; break;
            case BAR: categoryId = R.drawable.bar; break;
            case THEATER: categoryId = R.drawable.theater; break;
            case MUSEUM: categoryId = R.drawable.museum; break;
            case MAIN: categoryId = R.drawable.main_quest; break;
            case FOOD: categoryId = R.drawable.food; break;
            default:categoryId = R.drawable.camera; break;
        }
        holder.ivQuestCardIcon.setBackgroundResource(categoryId);

        int pointId = 0;
        switch (holder.quest.points){
            case FIFTY: pointId = R.drawable.points50; break;
            case ONEHUDRED: pointId = R.drawable.points100; break;
            case TWOHUNDRED: pointId = R.drawable.points200; break;
            default: pointId = R.drawable.points100; break;
        }

        holder.ivQuestCardPoints.setBackgroundResource(pointId);

    }

    private void claimQuest(ViewHolder holder){
        if(!holder.quest.done){
            QuestList.completeQuest(holder.quest);
            QuestsFragment.showQuestListInRv();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final Button cardBtn;
        public Quest quest;
        public ImageView ivQuestCardIcon;
        public ImageView ivQuestCardPoints;
        public ConstraintLayout expandedBtns;
        public Button btnClaim;
        public Button btnRoute;
        public Button btnInfo;

        public ViewHolder(FragmentQuestsItemBinding binding) {
            super(binding.getRoot());
            cardBtn = binding.content;
            ivQuestCardIcon = binding.ivQuestCardIcon;
            ivQuestCardPoints = binding.ivQuestCardPoints;
            expandedBtns = binding.expandedBtns;
            btnClaim = binding.btnClaim;
            btnRoute = binding.btnRoute;
            btnInfo = binding.btnInfo;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + cardBtn.getText() + "'";
        }
    }
}