package com.shhatrat.bikerun2.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoBackgroundColorAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoScaleAnimation;
import com.nightonke.wowoviewpager.Enum.Chameleon;
import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.WoWoViewPager;
import com.nightonke.wowoviewpager.WoWoViewPagerAdapter;
import com.shhatrat.bikerun2.R;

public class StravaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strava);
        WoWoViewPager wowo = (WoWoViewPager) findViewById(R.id.wowo_viewpager);
        wowo.setAdapter(WoWoViewPagerAdapter.builder()
                .fragmentManager(getSupportFragmentManager())
                .count(5)                       // Fragment Count
                .build());
        ViewAnimation viewAnimation = new ViewAnimation(findViewById(R.id.test));
        viewAnimation.add(WoWoScaleAnimation.builder().page(0).fromXY(1).toXY(0.5).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(1).fromXY(0.5).toXY(4).build());
        viewAnimation.add(WoWoBackgroundColorAnimation.builder().page(0)
                .from("#ff0000").to("#00ff00").chameleon(Chameleon.RGB).build());
        viewAnimation.add(WoWoBackgroundColorAnimation.builder().page(1)
                .from("#00ff00").to("#0000ff").chameleon(Chameleon.RGB).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(2).start(0).end(0.5).fromX(4).toX(1).keepY(4).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(2).start(0.5).end(1).keepX(1).fromY(4).toY(1).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(3).start(0).end(0.5).keepX(1).fromY(1).toY(3).build());
        viewAnimation.add(WoWoScaleAnimation.builder().page(3).start(0.5).end(1).fromX(1).toX(3).keepY(3).build());

        wowo.addAnimation(viewAnimation);
        wowo.ready();

    }
}
