package pl.wat.pks.currency.wallet;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;
import java.util.Locale;

import pl.wat.pks.R;
import pl.wat.pks.currency.settings.CurrencySetting;
import pl.wat.pks.currency.settings.SettingsListAdapter;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {
    private List<CurrencySetting> settingsList;

    public WalletAdapter() {}

    @NonNull
    @Override
    public WalletAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View rowView = inflater.inflate(R.layout.account_element, parent, false);

        ViewHolder viewHolder = new ViewHolder(rowView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final WalletAdapter.ViewHolder holder, final int position) {
        //Holdery
        String currencyName = settingsList.get(position).getCurrencyName();
        holder.currencyName.setText(currencyName);
        holder.currencyIcon.setImageDrawable(holder.itemView.getContext().getDrawable(settingsList.get(position).getCurrencyIcon()));
        holder.currencyAccount.setText(settingsList.get(position).getAccount());
        String balanceString = String.format(Locale.US,"%.8f", settingsList.get(position).getBalance());
        holder.balance.setText(balanceString);

        boolean notificationBoll = settingsList.get(position).isNotificationBool();
        if(notificationBoll) {
            holder.settingsView.setVisibility(View.VISIBLE);
        }else{
            holder.settingsView.setVisibility(View.GONE);
            holder.balance.setText("OFF");
            holder.accountCardView.setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) holder.accountCardView.getLayoutParams();
            cardViewMarginParams.setMargins(0, 0, 0, 0);
            holder.accountCardView.requestLayout();
        }

    }



    @Override
    public int getItemCount() {
        if (settingsList != null) {
            return settingsList.size();
        }
        return 0;
    }

    public void setSettings(List<CurrencySetting> words) {
        this.settingsList = words;
        notifyDataSetChanged();
    }

    public List<CurrencySetting> getSettings() {
        List<CurrencySetting> settingsNew = settingsList;
        notifyDataSetChanged();
        return settingsNew;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        public ImageView currencyIcon;
        public TextView currencyName;
        public LinearLayout settingsView;
        public TextView currencyAccount;
        public TextView balance;
        public CardView accountCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            currencyIcon = itemView.findViewById(R.id.wallletIcon);
            currencyName = itemView.findViewById(R.id.walletCurrencyName);
            settingsView = itemView.findViewById(R.id.accountCard);
            currencyAccount = itemView.findViewById(R.id.walletAccount);
            balance = itemView.findViewById(R.id.walletBalance);
            accountCardView = itemView.findViewById(R.id.accountCardView);
        }
    }
}
