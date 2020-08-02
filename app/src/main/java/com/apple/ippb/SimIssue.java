package com.apple.ippb;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SimIssue extends Fragment {


    public View onCreate(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.simissue, container, false);

    }
}
