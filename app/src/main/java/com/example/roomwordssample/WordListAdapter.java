
package com.example.roomwordssample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordssample.R;
import com.example.roomwordssample.data.Word;

import java.util.ArrayList;
import java.util.List;

public class WordListAdapter
        extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private List<Word> words = new ArrayList<>();

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull WordViewHolder holder, int position) {

        Word current = words.get(position);
        holder.wordItemView.setText(current.getWord());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public void setWords(List<Word> words) {
        this.words = words != null ? words : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;

        WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}