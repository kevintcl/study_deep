package com.zeus.proguard.fragmenterror;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zeus.proguard.R;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-22.
 * =======================================
 */
public class ErrorFragment extends Fragment {

    private static final String TAG = "kevint";
    String name;

    public ErrorFragment() {
        Log.e("kevint", "ErrorFragment");
    }

    public ErrorFragment(String name) {
        this.name = name;
        Log.e("kevint", "ErrorFragment name=" + name);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("kevint", "ErrorFragment onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("kevint", "onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.e("kevint", "onCreateView = " + name);

        return inflater.inflate(R.layout.error_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("kevint", "onViewCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("kevint", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("kevint", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("kevint", "onDetach");
    }
}
