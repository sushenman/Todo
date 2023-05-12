package com.example.todo_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
//import from recycle_view.xml
import com.example.todo_app.databinding.EachRvBinding;
public class RVAdapter extends ListAdapter<Todo, RVAdapter.ViewHolder> {

    public RVAdapter(MainActivity mainActivity){
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Todo> CALLBACK=new DiffUtil.ItemCallback<Todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getDate().equals(newItem.getDate());


        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv,parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = getItem(position);
          holder.binding.titletodo.setText(todo.getTitle());
            holder.binding.descrption.setText(todo.getDescription());
            holder.binding.date.setText(todo.getDate().toString());
            }

    public Todo getTodo(int position){
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        EachRvBinding binding;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=EachRvBinding.bind(itemView);

        }
    }
}