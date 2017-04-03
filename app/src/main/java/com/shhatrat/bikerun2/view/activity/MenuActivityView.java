package com.shhatrat.bikerun2.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.presenter.activity.IMenuActivityPresenter;
import com.shhatrat.bikerun2.presenter.activity.MenuActivityPresenter;

public class MenuActivityView extends AppCompatActivity implements IMenuActivityView{

    private IMenuActivityPresenter menuActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menuActivityPresenter = new MenuActivityPresenter(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setOfflineIcon() {

    }

    @Override
    public void setLoggedIcon(String name) {

    }
}
