package com.shhatrat.bikerun2.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.adapter.DraggableContainersAdapter;
import com.shhatrat.bikerun2.adapter.helper.OnStartDragListener;
import com.shhatrat.bikerun2.adapter.helper.SimpleItemTouchHelperCallback;
import com.shhatrat.bikerun2.db.DataRealm;
import com.shhatrat.bikerun2.service.SportType;
import com.shhatrat.bikerun2.view.fragment.container.EnumContainer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrepareContainersActivity extends AppCompatActivity implements OnStartDragListener {

    @BindView(R.id.prepare_recycleview)
    RecyclerView prepareRecycleview;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private ItemTouchHelper mItemTouchHelper;
    private SportType sportType;
    DraggableContainersAdapter dca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_containers);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sportType = (SportType) getIntent().getSerializableExtra(getResources().getString(R.string.config_screen));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<DataRealm> l = new ArrayList<>();
        l.add(new DataRealm("aa", "bb"));
        l.add(new DataRealm("a2", "df"));
        l.add(new DataRealm("a44", "sb"));

        dca = new DraggableContainersAdapter(getApplicationContext(), this, l);
        prepareRecycleview.setHasFixedSize(true);
        prepareRecycleview.setAdapter(dca);
        prepareRecycleview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(dca);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(prepareRecycleview);
    }

    @Override
    public void finish() {
        Intent i = new Intent();
        setResult(getResources().getInteger(R.integer.config_screen), i);
        super.finish();
    }

    @OnClick(R.id.fab)
    void showDialog() {
        new MaterialDialog.Builder(this)
                .title("Add screen")
                .items(EnumContainer.getEnumList())
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        dca.addSection(text.toString());
                    }
                })
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_container, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_help) {
            return true;
        }

        if (id == R.id.menu_done) {
            return true;
        }
        return false;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }
}