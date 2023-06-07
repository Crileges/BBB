package com.example.bbbthirdtry.MainFragments.Quest;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bbbthirdtry.DatabaseHelper;
import com.example.bbbthirdtry.MainActivity;
import com.example.bbbthirdtry.QuestList;
import com.example.bbbthirdtry.R;

/**
 * A fragment representing a list of Items.
 */
public class QuestsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public static RecyclerView rv;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestsFragment() {
    }

    @SuppressWarnings("unused")
    public static QuestsFragment newInstance(int columnCount) {
        QuestsFragment fragment = new QuestsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rv = (RecyclerView) inflater.inflate(R.layout.fragment_quests_list, container, false);
        // Set the adapter
        Context context = rv.getContext();
        RecyclerView recyclerView = rv;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        showQuestListInRv();
        return rv;
    }

    public static void showQuestListInRv() {
        QuestRecyclerViewAdapter adapter = new QuestRecyclerViewAdapter(QuestList.getDisplayList());
        rv.setAdapter(adapter);
    }
}