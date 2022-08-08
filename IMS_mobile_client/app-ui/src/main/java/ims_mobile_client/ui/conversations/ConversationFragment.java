package ims_mobile_client.ui.conversations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.presentation.viewModels.ChatViewModel;
import ims_mobile_client.ui.R;
import ims_mobile_client.ui.databinding.ConversationFragmentBinding;

@AndroidEntryPoint
public class ConversationFragment extends Fragment {

    public static final String TAG = ConversationFragment.class.getName();

    private ConversationFragmentBinding binding;
    private static String buddySipUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        handleArguments(getArguments());
        binding = DataBindingUtil.inflate(inflater, R.layout.conversation_fragment, container, false);
        ChatViewModel viewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);

        MessageListAdapter messageListAdapter = new MessageListAdapter();
        binding.conversationRecyclerViewer.setAdapter(messageListAdapter);
        viewModel.getMessages(buddySipUri).observe(requireActivity(), messageListAdapter::submitList);

        binding.sendButton.setOnClickListener(v -> {
            String content = binding.messageInput.getText().toString();
            Date currentDate = new Date();
            viewModel.sendMessage(buddySipUri, content, currentDate.getTime());
            binding.messageInput.setText("");
        });

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.conversation_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (R.id.option_call_audio == id) {
                    navigateToPreCall(false);
                    return true;
                }
                if (R.id.option_call_video == id) {
                    navigateToPreCall(true);
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        return binding.getRoot();
    }

    private void handleArguments(Bundle arguments) {
        if (arguments == null) {
            return;
        }

        buddySipUri = arguments.getString("buddySipUri");
    }

    private void navigateToPreCall(boolean isVideo) {
        Bundle data = new Bundle();
        data.putBoolean("isVideo", isVideo);
        NavHostFragment.findNavController(this).navigate(R.id.action_conversationFragment_to_preCallFragment, data);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
