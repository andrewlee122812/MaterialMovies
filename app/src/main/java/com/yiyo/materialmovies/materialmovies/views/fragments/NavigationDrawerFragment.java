package com.yiyo.materialmovies.materialmovies.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yiyo.materialmovies.materialmovies.R;

/**
 * Created by sumset on 25/05/15.
 */
public class NavigationDrawerFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout drawerContent = (LinearLayout) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);

        return drawerContent;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    }

    public void openFragment() {
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }
}
