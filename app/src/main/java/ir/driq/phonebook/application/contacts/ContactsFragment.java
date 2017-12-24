package ir.driq.phonebook.application.contacts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.driq.phonebook.R;
import ir.driq.phonebook.business.model.Contact;

/**
 * A placeholder fragment showing list of contacts.
 */

public class ContactsFragment extends Fragment {

    @BindView(R.id.contactsRecyclerView)
    RecyclerView contactsRecyclerView;

    @BindView(R.id.emptyTextView)
    TextView emptyTextView;

    private ContactsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ContactsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ButterKnife.bind(this, view);

        viewModel.getContacts().observe(getActivity(), contacts -> {
            if (contacts == null || contacts.isEmpty()) {
                showEmpty();
            } else {
                showContacts(contacts);
            }
        });
        return view;
    }

    private void showEmpty() {
        emptyTextView.setVisibility(View.VISIBLE);
        contactsRecyclerView.setVisibility(View.GONE);
    }

    private void showContacts(List<Contact> contacts) {
        emptyTextView.setVisibility(View.GONE);
        contactsRecyclerView.setVisibility(View.VISIBLE);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ContactsAdapter adapter = new ContactsAdapter(contacts, getContext());
        contactsRecyclerView.setAdapter(adapter);
    }
}