package com.brandonio21.phyll.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brandonio21.chloromedia.Async.ProgressUpdateOperation;
import com.brandonio21.chloromedia.Data.Album;
import com.brandonio21.chloromedia.Data.ProviderItem;
import com.brandonio21.chloromedia.MediaProviders.AndroidMediaProvider;
import com.brandonio21.chloromedia.MediaProviders.MediaProvider;
import com.brandonio21.phyll.R;

import java.util.ArrayList;
import java.util.List;

public class MediaGrid extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mediaGridRecyclerView;
    private ProviderItemAdapter dataAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ProviderItem> providerItemDataset;


    public MediaGrid() {
        this.providerItemDataset = new ArrayList<ProviderItem>();
    }

    public static MediaGrid newInstance() {
        MediaGrid fragment = new MediaGrid();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_grid, container, false);

        this.mediaGridRecyclerView = (RecyclerView) rootView.findViewById(R.id.media_grid_recyclerview);
        this.layoutManager = new GridLayoutManager(this.getActivity(), 2);

        this.dataAdapter = new ProviderItemAdapter(this.providerItemDataset);
        this.mediaGridRecyclerView.setAdapter(this.dataAdapter);
        this.mediaGridRecyclerView.setLayoutManager(this.layoutManager);

        refreshDataSet();

        return rootView;
    }

    public void refreshDataSet() {
        MediaProvider.askForPermission(getActivity());
        AndroidMediaProvider mediaProvider = new AndroidMediaProvider(null);

        mediaProvider.getAlbums(null,
                new ProgressUpdateOperation<Album>() {
                    @Override
                    public void execute(Album... progressUpdate) {
                        providerItemDataset.add(progressUpdate[0]);
                        dataAdapter.notifyItemChanged(providerItemDataset.size()-1);
                    }
                }, null).execute(getActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
