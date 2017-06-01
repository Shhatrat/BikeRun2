package com.shhatrat.bikerun2.view.fragment.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.shhatrat.bikerun2.db.NormalContainer;
import com.shhatrat.bikerun2.db.NormalData;
import com.shhatrat.bikerun2.view.fragment.data.DataFragmentFactory;
import com.shhatrat.bikerun2.view.fragment.data.EnumDataType;

import java.util.List;

/**
 * Created by szymon on 01.05.17.
 */

public abstract class BaseContainer extends Fragment implements IContainer {

    EnumContainerType enumContainerType;
    NormalContainer normalContainer;
    List<Integer> listOfIds;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        normalContainer = NormalContainer.deserialize(b.getString(ContainerFactory.BUNDLE_KEY));
        enumContainerType = normalContainer.getContainerType();
    }

    public void prepareView(List<Integer> idList) {
        Stream.of(idList).forEach(l -> {
            Optional<NormalData> ch = Stream.of(normalContainer.getList()).filter(nc -> nc.getFieldName().equals(l + "")).findFirst();
            if (ch.isPresent()) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(l, DataFragmentFactory.getInstance(EnumDataType.valueOf(ch.get().getFieldName()), l + "")).commit();
            } else {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(l, DataFragmentFactory.getInstance(EnumDataType.BUTTON_BLANK, l + "")).commit();
            }
        });
    }
}