package ir.driq.phonebook.application.addoreditcontact;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.driq.phonebook.R;
import ir.driq.phonebook.business.model.Contact;

import static ir.driq.phonebook.application.addoreditcontact.AddOrEditContactActivity.KEY_ID;
import static ir.driq.phonebook.application.addoreditcontact.AddOrEditContactActivity.KEY_MODE;
import static ir.driq.phonebook.application.addoreditcontact.AddOrEditContactActivity.KEY_NAME;
import static ir.driq.phonebook.application.addoreditcontact.AddOrEditContactActivity.KEY_PHONE;

/**
 * A placeholder fragment showing single contact view.
 */
public class AddOrEditContactFragment extends Fragment {

    @BindView(R.id.nameEditText)
    EditText nameEditText;

    @BindView(R.id.phoneNumberEditText)
    EditText phoneNumberEditText;

    private AddOrEditContactViewModel viewModel;

    private Mode mode;
    private Contact contact;

    public enum Mode {
        ADD_CONTACT,
        EDIT_CONTACT
    }

    public AddOrEditContactFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(AddOrEditContactViewModel.class);

        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        ButterKnife.bind(this, view);

        initViews();

        return view;
    }

    private void getData() {
        contact = new Contact();
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            mode = (Mode) extras.getSerializable(KEY_MODE);
            contact.setId(extras.getInt(KEY_ID));
            contact.setName(extras.getString(KEY_NAME));
            contact.setPhoneNumber(extras.getString(KEY_PHONE));
        }
    }

    private void initViews() {
        if (mode == Mode.EDIT_CONTACT && contact != null) {
            nameEditText.setText(contact.getName());
            phoneNumberEditText.setText(contact.getPhoneNumber());
        }
    }

    public void saveContact() {
        if (nameEditText.getText().toString().isEmpty() || phoneNumberEditText.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            contact.setName(nameEditText.getText().toString());
            contact.setPhoneNumber(phoneNumberEditText.getText().toString());
            if (mode == Mode.ADD_CONTACT) {
                viewModel.addContact(contact);
            } else {
                viewModel.updateContact(contact);
            }
            getActivity().finish();
        }
    }
}
