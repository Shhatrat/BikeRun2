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
import android.view.MotionEvent;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.adapter.DraggableContainersAdapter;
import com.shhatrat.bikerun2.adapter.helper.OnStartDragListener;
import com.shhatrat.bikerun2.adapter.helper.SimpleItemTouchHelperCallback;
import com.shhatrat.bikerun2.db.RealmContainer;
import com.shhatrat.bikerun2.di.UtilImpl;
import com.shhatrat.bikerun2.presenter.activity.PrepareContainersPresenter;
import com.shhatrat.bikerun2.presenter.activity.models.IPrepareContainersPresenter;
import com.shhatrat.bikerun2.service.EnumSportType;
import com.shhatrat.bikerun2.view.activity.models.IPrepareContainersActivity;
import com.shhatrat.bikerun2.view.fragment.container.EnumContainerType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

import static com.shhatrat.bikerun2.RealmUtils.prepareNormalFromRealm;
import static com.shhatrat.bikerun2.RealmUtils.prepareRealmFromNormal;

public class PrepareContainersActivity extends AppCompatActivity implements OnStartDragListener, IPrepareContainersActivity {

    @BindView(R.id.prepare_recycleview)
    RecyclerView prepareRecycleview;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private ItemTouchHelper mItemTouchHelper;
    private EnumSportType enumSportType;
    DraggableContainersAdapter dca;
    IPrepareContainersPresenter containersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_containers);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enumSportType = (EnumSportType) getIntent().getSerializableExtra(getResources().getString(R.string.config_screen));
        dca = new DraggableContainersAdapter(getApplicationContext(), this, new ArrayList<>());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //todo check changes and ask about it
            }
        });
        Realm realm = new UtilImpl(this).getRealm(); //// TODO: 26.05.17
        containersPresenter = new PrepareContainersPresenter(realm, enumSportType, this);
        containersPresenter.loadConfigFromDB();
        prepareRecycleview.setHasFixedSize(true);
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
                .items(EnumContainerType.getEnumList())
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        RealmContainer rc= new RealmContainer();
                        rc.saveContainerType(EnumContainerType.valueOf(text.toString()));
                        rc.saveSportType(enumSportType);
                        dca.addSection(rc);
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
            showTips();
            return true;
        }

        if (id == R.id.menu_done) {
            List<RealmContainer> d = prepareRealmFromNormal(dca.getCollection());
            containersPresenter.saveConfigFromScreen(d);
            finish();
            return true;
        }
        return false;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public void preapreRecycleViewData(List<RealmContainer> list) {
        dca = new DraggableContainersAdapter(getApplicationContext(), this, prepareNormalFromRealm(list));
        prepareRecycleview.setAdapter(dca);
    }

    void showTips()
    {
        new MaterialTapTargetPrompt.Builder(this)
                .setTarget(findViewById(R.id.fab))
                .setBackgroundColourFromRes(R.color.colorPrimaryDark)
                .setPrimaryText("Click this button")
                .setCaptureTouchEventOnFocal(true)
                .setCaptureTouchEventOutsidePrompt(true)
                .setSecondaryText("to add new container screen")
                .setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
                {
                    @Override
                    public void onHidePrompt(MotionEvent event, boolean tappedTarget)
                    {
                    }

                    @Override
                    public void onHidePromptComplete()
                    {
                        new MaterialTapTargetPrompt.Builder(PrepareContainersActivity.this)
                                .setTarget(findViewById(R.id.menu_done))
                                .setBackgroundColourFromRes(R.color.colorPrimaryDark)
                                .setPrimaryText("Click this button")
                                .setCaptureTouchEventOnFocal(true)
                                .setCaptureTouchEventOutsidePrompt(true)
                                .setSecondaryText("to save screens")
                                .setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
                                {
                                    @Override
                                    public void onHidePrompt(MotionEvent event, boolean tappedTarget)
                                    {
                                    }

                                    @Override
                                    public void onHidePromptComplete()
                                    {
                                        new MaterialTapTargetPrompt.Builder(PrepareContainersActivity.this)
                                                .setTarget(findViewById(R.id.toolbar))
                                                .setBackgroundColourFromRes(R.color.colorPrimaryDark)
                                                .setPrimaryText("This is preview of screens")
                                                .setCaptureTouchEventOnFocal(true)
                                                .setCaptureTouchEventOutsidePrompt(true)
                                                .setSecondaryText("you can remove it by swapping it down or change order by long click")
                                                .show();
                                    }
                                })
                                .show();
                    }
                })
                .show();
    }
}