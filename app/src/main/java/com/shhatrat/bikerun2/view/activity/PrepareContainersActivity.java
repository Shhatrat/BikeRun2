package com.shhatrat.bikerun2.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.adapter.DraggableContainersAdapter;
import com.shhatrat.bikerun2.adapter.helper.OnStartDragListener;
import com.shhatrat.bikerun2.adapter.helper.SimpleItemTouchHelperCallback;
import com.shhatrat.bikerun2.db.DataRealm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrepareContainersActivity extends AppCompatActivity implements OnStartDragListener {

    @BindView(R.id.prepare_recycleview)
    RecyclerView prepareRecycleview;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_containers);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<DataRealm> l = new ArrayList<>();
        l.add(new DataRealm("aa", "bb"));
        l.add(new DataRealm("a2", "df"));
        l.add(new DataRealm("a44", "sb"));

        DraggableContainersAdapter dca = new DraggableContainersAdapter(getApplicationContext(), this, l);
        prepareRecycleview.setHasFixedSize(true);
        prepareRecycleview.setAdapter(dca);
        prepareRecycleview.setLayoutManager(new LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL , false));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(dca);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(prepareRecycleview);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }
}
