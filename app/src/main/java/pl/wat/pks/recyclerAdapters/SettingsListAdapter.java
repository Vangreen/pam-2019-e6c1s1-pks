package pl.wat.pks.recyclerAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.wat.pks.R;
import pl.wat.pks.recylerModels.Setting;

public class SettingsListAdapter extends RecyclerView.Adapter<SettingsListAdapter.ViewHolder> {

    private List<Setting> settingsList;

    public SettingsListAdapter(List<Setting> settingsList) {this.settingsList = settingsList;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View rowView = inflater.inflate(R.layout.settings_list_element, parent, false);

        ViewHolder viewHolder = new ViewHolder(rowView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
