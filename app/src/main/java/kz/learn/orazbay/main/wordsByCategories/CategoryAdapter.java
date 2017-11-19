package kz.learn.orazbay.main.wordsByCategories;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kz.learn.orazbay.R;
import kz.learn.orazbay.main.wordsByCategories.words.WordsFragment;
import kz.learn.orazbay.utils.Functions;

/**
 * Created by orazbay on 10/29/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Category> categories;


    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView textView;
        private ImageView imageView;

        private CategoryViewHolder(View view) {
            super(view);
            this.view=view;
            textView = view.findViewById(R.id.textView);
            imageView=view.findViewById(R.id.imageView);
        }
    }
    //adapters constructor
    public CategoryAdapter(Context context, ArrayList<Category> categories){
        this.context=context;
        this.categories=categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category category=categories.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsFragment wordsFragment=new WordsFragment();
                wordsFragment.setCategory(category.getName());
                Functions.ReplaceFragmentWithStack(context,wordsFragment);
            }
        });
        holder.textView.setText(category.getName());
        holder.imageView.setImageResource(category.getImageId());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}


