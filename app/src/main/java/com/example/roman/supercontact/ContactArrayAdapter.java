package com.example.roman.supercontact;

/**
 * Created by Roman on 28/02/2017.
 */
        import java.util.ArrayList;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

public class ContactArrayAdapter extends BaseAdapter {
    public ContactArrayAdapter(Context c, ArrayList<Contact> al)
    {
        context = c;
        al_items = al;
    }
    public View getView(int position, View convert_view, ViewGroup parent) {
        ViewHolder holder;
        if(convert_view == null) {
            holder = new ViewHolder();
            LayoutInflater inflator = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convert_view = inflator.inflate(R.layout.custom_contact_entry, parent,
                    false);
            holder.tv_first_name = (TextView) convert_view.findViewById(R.id.tv_first_name);
            holder.tv_last_name = (TextView) convert_view.findViewById(R.id.tv_last_name);
            convert_view.setTag(holder);
        }
        else {
            holder = (ViewHolder) convert_view.getTag();
        }
        holder.tv_first_name.setText(al_items.get(position).first_name);
        holder.tv_last_name.setText(al_items.get(position).last_name);
        return convert_view;
    }
    public int getCount() { return al_items.size(); }
    // returns the rowid of the item at the given position. Given that we are using an
    // array list the rowid will be equal to the index of the item
    public long getItemId(int position) { return position; }
    // overridden method that will return the item at the given position in the list
    public Object getItem(int position) { return al_items.get(position); }

    private Context context;
    private ArrayList<Contact> al_items;
    static class ViewHolder {
        public TextView tv_first_name;
        public TextView tv_last_name;
    }
}