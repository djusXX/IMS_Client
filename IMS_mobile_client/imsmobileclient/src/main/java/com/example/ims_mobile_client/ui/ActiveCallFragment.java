package com.example.ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.databinding.ActiveCallFragmentBinding;

import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipServiceCommand;

public class ActiveCallFragment extends Fragment {

    private static final String TAG = ActiveCallFragment.class.getName();

    private ActiveCallFragmentBinding binding;
    private final String usrSipUri;
    private final int callID;
    private final boolean isVideo;

    public ActiveCallFragment(String accountID, int callID, boolean isVideo) {
        this.usrSipUri = accountID;
        this.callID = callID;
        this.isVideo = isVideo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.active_call_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.muteButton.setOnClickListener(v -> {
            SipServiceCommand.toggleCallMute(requireActivity(), usrSipUri, callID);
        });

        binding.endButton.setOnClickListener(v -> {
            SipServiceCommand.hangUpCall(requireActivity(), usrSipUri, callID);
        });

        if (isVideo) {
            binding.switchCameraButton.setOnClickListener(v -> {
                SipServiceCommand.switchVideoCaptureDevice(requireActivity(), usrSipUri, callID);
            });

            binding.localUserView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    SipServiceCommand.startVideoPreview(requireActivity(), usrSipUri, callID, binding.localUserView.getHolder().getSurface());
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    SipServiceCommand.stopVideoPreview(requireActivity(), usrSipUri, callID);
                }});

            binding.remoteUserView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    Logger.debug(TAG, "remote surfaceCreated()");
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                    Logger.debug(TAG, "remote surfaceChanged()");
                    SipServiceCommand.setupIncomingVideoFeed(requireActivity(), usrSipUri, callID, surfaceHolder.getSurface());
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    Logger.debug(TAG, "remote surfaceDestroyed()");
                    SipServiceCommand.setupIncomingVideoFeed(requireActivity(), usrSipUri, callID, null);
                }
            });
        } else {
            binding.switchCameraButton.setVisibility(View.GONE);
            binding.localUserView.setVisibility(View.GONE);
            binding.remoteUserView.setVisibility(View.GONE);
        }

    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
