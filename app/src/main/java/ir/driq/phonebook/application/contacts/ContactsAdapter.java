package ir.driq.phonebook.application.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.driq.phonebook.R;
import ir.driq.phonebook.application.addoreditcontact.AddOrEditContactActivity;
import ir.driq.phonebook.application.addoreditcontact.AddOrEditContactFragment;
import ir.driq.phonebook.business.model.Contact;

/**
 * Contacts List Adapter
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsListViewHolder> {

    private List<Contact> contacts;
    private Context context;

    ContactsAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public ContactsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ContactsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactsListViewHolder holder, final int position) {
        holder.nameTextView.setText(contacts.get(position).getName());
        holder.phoneNumberTextView.setText(contacts.get(position).getPhoneNumber());
        holder.contactItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOrEditContactActivity.start(context, AddOrEditContactFragment.Mode.EDIT_CONTACT, contacts.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (contacts == null || contacts.isEmpty()) ? 0 : contacts.size();
    }

    class ContactsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nameTextView)
        TextView nameTextView;

        @BindView(R.id.phoneNumberTextView)
        TextView phoneNumberTextView;

        @BindView(R.id.contactItemContainer)
        View contactItemContainer;

        ContactsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
