package ir.driq.phonebook.application.addoreditcontact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ir.driq.phonebook.R;
import ir.driq.phonebook.business.model.Contact;

public class AddOrEditContactActivity extends AppCompatActivity {

    private AddOrEditContactFragment fragment;

    public static final String KEY_MODE = "KEY_MODE";
    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_PHONE = "KEY_PHONE";

    public static void start(Context context, AddOrEditContactFragment.Mode mode, Contact contact) {
        Intent starter = new Intent(context, AddOrEditContactActivity.class);
        starter.putExtra(KEY_MODE, mode);
        if (contact != null) {
            starter.putExtra(KEY_ID, contact.getId());
            starter.putExtra(KEY_NAME, contact.getName());
            starter.putExtra(KEY_PHONE, contact.getPhoneNumber());
        }
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        initToolbar();

        initViews(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSave:
                saveContact();
                break;
            default:
                break;
        }
        return true;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initViews(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            fragment = new AddOrEditContactFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.commit();
        }
    }

    private void saveContact() {
        fragment.saveContact();
    }
}
