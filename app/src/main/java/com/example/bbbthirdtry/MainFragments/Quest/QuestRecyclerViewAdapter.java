package com.example.bbbthirdtry.MainFragments.Quest;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bbbthirdtry.R;
import com.example.bbbthirdtry.User;
import com.example.bbbthirdtry.databinding.FragmentQuestsItemBinding;

import java.util.List;

public class QuestRecyclerViewAdapter extends RecyclerView.Adapter<QuestRecyclerViewAdapter.ViewHolder> {

    private final List<Quest> cardBtn;

    public QuestRecyclerViewAdapter(List<Quest> items) {
        cardBtn = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentQuestsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.quest = cardBtn.get(position);
        setQuestIcons(holder);
        holder.cardBtn.setText(holder.quest.title);
        holder.cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showMoreButtons
                showOrNotShowMoreOptions(holder);
            }
        });
        updateQuests(holder);
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
        if (holder.quest.done){
            holder.ivQuestCardIcon.setBackgroundResource(R.drawable.check);
            holder.cardBtn.setBackgroundResource(R.drawable.questcardcomplete);
            holder.btnRoute.setBackgroundResource(R.drawable.questcardcomplete);
            holder.btnClaim.setBackgroundResource(R.drawable.questcardcomplete);
            holder.btnInfo.setBackgroundResource(R.drawable.questcardcomplete);
        }
    }

    private void setQuestIcons(ViewHolder holder) {
        holder.ivQuestCardIcon.setBackgroundResource(R.drawable.camera);
        int categoryId = 0;
        switch (holder.quest.category){
            case SIGHTSEEING: categoryId = R.drawable.camera; break;
            case BAR: categoryId = R.drawable.bar; break;
            case THEATER: categoryId = R.drawable.theater; break;
        }
        holder.ivQuestCardIcon.setBackgroundResource(categoryId);

        int pointId = 0;
        switch (holder.quest.points){
            case FIFTY: pointId = R.drawable.points50; break;
            case ONEHUDRED: pointId = R.drawable.points100; break;
            case TWOHUNDRED: pointId = R.drawable.points200; break;
        }
        holder.ivQuestCardPoints.setBackgroundResource(pointId);
    }

    private void claimQuest(ViewHolder holder){
        if(!holder.quest.done){
            if(true){ //Radius überprüfen
                holder.ivQuestCardIcon.setBackgroundResource(R.drawable.check);
                holder.cardBtn.setBackgroundResource(R.drawable.questcardcomplete);
                holder.btnRoute.setBackgroundResource(R.drawable.questcardcomplete);
                holder.btnClaim.setBackgroundResource(R.drawable.questcardcomplete);
                holder.btnInfo.setBackgroundResource(R.drawable.questcardcomplete);
                //QuestList.completeQuest(holder.getAbsoluteAdapterPosition());
                User.getUser().points += Points.getIntFromValue(holder.quest.points);
                //this.notifyItemMoved(position, position);
            }
        }

    }

    @Override
    public int getItemCount() {
        return cardBtn.size();
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