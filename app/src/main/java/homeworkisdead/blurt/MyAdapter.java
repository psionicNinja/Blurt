package homeworkisdead.blurt;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dcook on 11/6/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<MyResponse> breaches;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<MyResponse> responses) {
        breaches = responses;
    }


    //Todo: html encoding for description textview, add donate page, add acknowledgements page,make no results page, save email, News pop up dialog, clean up ui, make logo, submit biotch
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(breaches.get(position).getTitle());
        holder.description.setText(Html.fromHtml(breaches.get(position).getDescription()));
        holder.name.setText(breaches.get(position).getName());
        holder.date.setText(breaches.get(position).getBreachDate());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return breaches.size();
    }





    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View  view;

        TextView title;
        TextView date;
        TextView name;
        TextView description;
        public ViewHolder(View v) {
            super(v);
            view = v;

            title = (TextView) v.findViewById(R.id.title_textView);
            date = (TextView) v.findViewById(R.id.date_textView);
            name = (TextView) v.findViewById(R.id.name_textView);
            description = (TextView) v.findViewById(R.id.description_textView);
        }
    }
}


