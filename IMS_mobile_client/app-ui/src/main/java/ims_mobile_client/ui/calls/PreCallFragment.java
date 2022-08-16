package ims_mobile_client.ui.calls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.presentation.viewModels.CallViewModel;
import ims_mobile_client.ui.R;
import ims_mobile_client.ui.databinding.PreCallFragmentBinding;

@AndroidEntryPoint
public class PreCallFragment extends Fragment {

    private PreCallFragmentBinding binding;
    private CallViewModel callViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.pre_call_fragment, container, false);
        callViewModel = new ViewModelProvider(requireActivity()).get(CallViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StringBuilder title = new StringBuilder();
        if(isIncoming) {    // INCOMING CALL
            title.append(displayName + "\n(" + remoteUri + ")\nis calling.");
            binding.preCallTitle.setText(title);
            binding.acceptCallButton.setVisibility(View.VISIBLE);
            binding.rejectCallButton.setVisibility(View.VISIBLE);
            binding.cancelCallButton.setVisibility(View.GONE);

            binding.acceptCallButton.setOnClickListener(v -> {
//                SipServiceCommand.acceptIncomingCall(requireActivity(), usrSipUri, callID, isVideo);
                requireActivity().getSupportFragmentManager().popBackStack();
            });

            binding.rejectCallButton.setOnClickListener(v -> {
//                SipServiceCommand.declineIncomingCall(requireActivity(), usrSipUri, callID);
                unloadPreCallFragment();
            });
        } else {    // OUTGOING CALL
            title.append("Calling to\n" + displayName + "\n(" + remoteUri + ")");
            binding.preCallTitle.setText(title);
            binding.acceptCallButton.setVisibility(View.GONE);
            binding.rejectCallButton.setVisibility(View.GONE);
            binding.cancelCallButton.setVisibility(View.VISIBLE);

            binding.cancelCallButton.setOnClickListener(v -> {
//                SipServiceCommand.hangUpActiveCalls(requireActivity(), usrSipUri);
                unloadPreCallFragment();
            });
        }

    }

    private void unloadPreCallFragment() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

}
