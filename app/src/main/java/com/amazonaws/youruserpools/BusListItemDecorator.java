package com.amazonaws.youruserpools;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BusListItemDecorator extends RecyclerView.ItemDecoration{

    private final int verticalSpaceHeight;

    public BusListItemDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.bottom = verticalSpaceHeight;
    }
}