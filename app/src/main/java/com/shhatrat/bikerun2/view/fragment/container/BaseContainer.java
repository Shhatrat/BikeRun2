package com.shhatrat.bikerun2.view.fragment.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.shhatrat.bikerun2.db.NormalContainer;
import com.shhatrat.bikerun2.db.NormalData;
import com.shhatrat.bikerun2.db.RealmContainer;
import com.shhatrat.bikerun2.view.fragment.data.DataFragmentFactory;
import com.shhatrat.bikerun2.view.fragment.data.EnumDataType;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by szymon on 01.05.17.
 */

public abstract class BaseContainer extends Fragment implements IContainer {

    EnumContainerType enumContainerType;
    NormalContainer normalContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        normalContainer = NormalContainer.deserialize(b.getString(ContainerFactory.BUNDLE_KEY));
        enumContainerType = normalContainer.getContainerType();
    }

    public void prepareView() {
        List<Integer> idList = getListOfIds();
        Stream.of(idList).forEach(l -> {
            Optional<NormalData> ch = Stream.of(normalContainer.getList()).filter(nc -> nc.getFieldName().equals(l + "")).findFirst();
            if (ch.isPresent()) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(l, DataFragmentFactory.getInstance(ch.get())).commit();
            } else {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                NormalData nd = new NormalData();
                nd.setFieldName(l + "");
                nd.saveDataType(EnumDataType.BUTTON_BLANK);
                transaction.replace(l, DataFragmentFactory.getInstance(nd)).commit();
            }
        });
    }

    @Override
    public void setDataField(String tag) {
        new MaterialDialog.Builder(BaseContainer.this.getActivity())
                .title(tag)
                .items(EnumDataType.getEnumList())
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                        NormalData nd = new NormalData();
                        nd.setFieldName(Integer.parseInt(tag) + "");
                        nd.saveDataType(EnumDataType.valueOf(text.toString()));

                        transaction.replace(Integer.parseInt(tag), DataFragmentFactory.getInstance(nd)).commit();
                        saveToDb(normalContainer, tag, EnumDataType.valueOf(text.toString()));
                    }
                })
                .show();
    }

    void saveToDb(NormalContainer normalContainer, String tag, EnumDataType enumDataType) {
        //// TODO: 6/1/17
        List<NormalData> reduced = Stream.of(normalContainer.getList()).filter(it -> !it.getFieldName().equals(tag)).toList();
        reduced.add(new NormalData("", tag, enumDataType.name()));
        normalContainer.setList(reduced);

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm r = Realm.getInstance(config);
        r.beginTransaction();
        RealmResults<RealmContainer> res = r.where(RealmContainer.class).equalTo("id", normalContainer.getId()).findAll();
        for (RealmContainer re : res) {
            re.getList().deleteAllFromRealm();
        }
        r.insertOrUpdate(new RealmContainer(normalContainer));
        r.commitTransaction();
    }

    abstract List<Integer> getListOfIds();
}