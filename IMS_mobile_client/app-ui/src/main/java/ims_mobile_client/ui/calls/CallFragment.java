package ims_mobile_client.ui.calls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.domain.models.CallSimpleState;
import ims_mobile_client.presentation.viewModels.CallViewModel;
import ims_mobile_client.ui.databinding.CallFragmentBinding;

@AndroidEntryPoint
public class CallFragment extends Fragment {

    private CallFragmentBinding binding;
    private CallViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CallFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(CallViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getCallSimpleState().observe(getViewLifecycleOwner(), this::setLayout);
        binding.localUserView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                viewModel.updateVideoPreview(surfaceHolder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                viewModel.stopVideoPreview();
            }});

        binding.remoteUserView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                viewModel.updateVideoWindow(true);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                viewModel.updateVideoWindow(false);
            }
        });
    }

    private void setLayout(CallSimpleState callSimpleState) {
        if (callSimpleState == CallSimpleState.INCOMING) {
            binding.remoteUserView.setVisibility(View.GONE);
            binding.localUserView.setVisibility(View.GONE);
            binding.preCallTitle.setVisibility(View.VISIBLE);
            binding.preCallTitle.setText("INCOMING Call\n");
            binding.remoteSipUri.setVisibility(View.VISIBLE);
            binding.muteButton.setVisibility(View.GONE);
            binding.switchCameraButton.setVisibility(View.GONE);
            binding.acceptCallButton.setVisibility(View.VISIBLE);
            binding.rejectCallButton.setVisibility(View.VISIBLE);
            binding.endButton.setVisibility(View.VISIBLE);
            return;
        }
        if (callSimpleState == CallSimpleState.OUTGOING) {
            binding.remoteUserView.setVisibility(View.GONE);
            binding.localUserView.setVisibility(View.GONE);
            binding.preCallTitle.setVisibility(View.VISIBLE);
            binding.preCallTitle.setText("OUTGOING Call to\n");
            binding.remoteSipUri.setVisibility(View.VISIBLE);
            binding.muteButton.setVisibility(View.GONE);
            binding.switchCameraButton.setVisibility(View.GONE);
            binding.acceptCallButton.setVisibility(View.GONE);
            binding.rejectCallButton.setVisibility(View.GONE);
            binding.endButton.setVisibility(View.VISIBLE);
            return;
        }
        if (callSimpleState == CallSimpleState.ACTIVE_VOICE) {
            binding.remoteUserView.setVisibility(View.GONE);
            binding.localUserView.setVisibility(View.GONE);
            binding.preCallTitle.setVisibility(View.GONE);
            binding.remoteSipUri.setVisibility(View.GONE);
            binding.muteButton.setVisibility(View.VISIBLE);
            binding.switchCameraButton.setVisibility(View.VISIBLE);
            binding.acceptCallButton.setVisibility(View.GONE);
            binding.rejectCallButton.setVisibility(View.GONE);
            binding.endButton.setVisibility(View.VISIBLE);
            return;
        }
        if (callSimpleState == CallSimpleState.ACTIVE_VIDEO) {
            binding.remoteUserView.setVisibility(View.VISIBLE);
            binding.localUserView.setVisibility(View.VISIBLE);
            binding.preCallTitle.setVisibility(View.GONE);
            binding.remoteSipUri.setVisibility(View.GONE);
            binding.muteButton.setVisibility(View.VISIBLE);
            binding.switchCameraButton.setVisibility(View.VISIBLE);
            binding.acceptCallButton.setVisibility(View.GONE);
            binding.rejectCallButton.setVisibility(View.GONE);
            binding.endButton.setVisibility(View.VISIBLE);
            return;
        }
        if (callSimpleState == CallSimpleState.ENDED) {
            binding.remoteUserView.setVisibility(View.GONE);
            binding.localUserView.setVisibility(View.GONE);
            binding.preCallTitle.setVisibility(View.VISIBLE);
            binding.preCallTitle.setText("CALL ENDED");
            binding.remoteSipUri.setVisibility(View.GONE);
            binding.muteButton.setVisibility(View.GONE);
            binding.switchCameraButton.setVisibility(View.GONE);
            binding.acceptCallButton.setVisibility(View.GONE);
            binding.rejectCallButton.setVisibility(View.GONE);
            binding.endButton.setVisibility(View.GONE);
            return;
        }
    }

}
