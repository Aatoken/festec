package com.mk.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.mk.latte.ec.R;
import com.mk.latte.delegates.bottom.BottomItemDelegate;
import com.mk.latte.ec.main.sort.content.ContentDelegate;
import com.mk.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * @author lenovo
 * @data 2017/10/30
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        //左侧delegate
        final VerticalListDelegate listDelegate=new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }




}
