package ir.driq.phonebook.application.contacts;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.driq.phonebook.R;
import ir.driq.phonebook.application.addoreditcontact.AddOrEditContactActivity;
import ir.driq.phonebook.application.addoreditcontact.AddOrEditContactFragment;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        initViews(savedInstanceState);
    }

    @OnClick(R.id.addFab)
    public void onAddFabClick() {
        AddOrEditContactActivity.start(this, AddOrEditContactFragment.Mode.ADD_CONTACT, null);
    }

    private void initViews(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ContactsFragment fragment = new ContactsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.commit();
        }
    }
}
