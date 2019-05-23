package pl.wat.pks.adapters.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

import pl.wat.pks.R;
import pl.wat.pks.models.recycler.CurrencySettingModel;

public class SettingsListAdapter extends RecyclerView.Adapter<SettingsListAdapter.ViewHolder> {

    private List<CurrencySettingModel> settingsList;

    public SettingsListAdapter(List<CurrencySettingModel> settingsList) {this.settingsList = settingsList;}

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
        //Holdery
        String currencyName = settingsList.get(position).getCurrencyName();
        holder.currencyName.setText(currencyName);
        holder.currencyIcon.setImageDrawable(settingsList.get(position).getCurrencyIcon());
        String exchangeRateMin = String.valueOf(settingsList.get(position).getExchangeRateMin());
        holder.currencyExRateMin.setText(exchangeRateMin);
        String exchangeRateMax = String.valueOf(settingsList.get(position).getExchangeRateMax());
        holder.currencyExRateMax.setText(exchangeRateMax);
        boolean notificationBoll = settingsList.get(position).isNotificationBool();
        holder.notificationSwitch.setChecked(notificationBoll);

    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        public ImageView currencyIcon;
        public SwitchMaterial notificationSwitch;
        public EditText currencyExRateMax;
        public EditText currencyExRateMin;
        public TextView currencyName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            currencyIcon = itemView.findViewById(R.id.currencyIcon);
            currencyName = itemView.findViewById(R.id.currencyNameTextView);
            notificationSwitch = itemView.findViewById(R.id.notificationSwitch);
            currencyExRateMax = itemView.findViewById(R.id.currencyExRateMax);
            currencyExRateMin = itemView.findViewById(R.id.currencyExRateMin);
        }
    }
}
