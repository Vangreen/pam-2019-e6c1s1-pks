package pl.wat.pks.currency.settings;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

import pl.wat.pks.R;

public class SettingsListAdapter extends RecyclerView.Adapter<SettingsListAdapter.ViewHolder> {

    private List<CurrencySetting> settingsList;

    public SettingsListAdapter(List<CurrencySetting> settingsList) {this.settingsList = settingsList;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View rowView = inflater.inflate(R.layout.settings_list_element, parent, false);

        ViewHolder viewHolder = new ViewHolder(rowView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //Holdery
        String currencyName = settingsList.get(position).getCurrencyName();
        holder.currencyName.setText(currencyName);
        holder.currencyIcon.setImageDrawable(settingsList.get(position).getCurrencyIcon());
        String exchangeRateMin = String.valueOf(settingsList.get(position).getExchangeRateMin());
        holder.currencyExRateMin.setText(exchangeRateMin);
        holder.currencyExRateMin.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(s.toString().isEmpty())) {
                    settingsList.get(position).setExchangeRateMin(Double.parseDouble(s.toString()));
                    Log.d("SettingChanged", settingsList.get(position).toString());
                }
            }
        });
        String exchangeRateMax = String.valueOf(settingsList.get(position).getExchangeRateMax());
        holder.currencyExRateMax.setText(exchangeRateMax);
        holder.currencyExRateMax.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(s.toString().isEmpty())) {
                    settingsList.get(position).setExchangeRateMax(Double.parseDouble(s.toString()));
                    Log.d("SettingChanged", settingsList.get(position).toString());
                }
            }
        });
        boolean notificationBoll = settingsList.get(position).isNotificationBool();
        if(notificationBoll) {
            holder.settingsView.setVisibility(View.VISIBLE);
        }else{
            holder.settingsView.setVisibility(View.GONE);
        }
        holder.notificationSwitch.setChecked(notificationBoll);
        holder.notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingsList.get(position).setNotificationBool(holder.notificationSwitch.isChecked());
                Log.d("SettingChanged", settingsList.get(position).toString());
                if(holder.notificationSwitch.isChecked()) {
                    holder.settingsView.setVisibility(View.VISIBLE);
                }else{
                    holder.settingsView.setVisibility(View.GONE);
                }
            }
        });


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
        public LinearLayout settingsView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            currencyIcon = itemView.findViewById(R.id.currencyIcon);
            currencyName = itemView.findViewById(R.id.currencyNameTextView);
            notificationSwitch = itemView.findViewById(R.id.notificationSwitch);
            currencyExRateMax = itemView.findViewById(R.id.currencyExRateMax);
            currencyExRateMin = itemView.findViewById(R.id.currencyExRateMin);
            settingsView = itemView.findViewById(R.id.settingsLinearLayout);
        }
    }
}
