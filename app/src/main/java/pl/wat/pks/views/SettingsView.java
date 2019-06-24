/**
 * Widok ustawień kryptowalut
 */
package pl.wat.pks.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.wat.pks.R;
import pl.wat.pks.currency.settings.CurrencySetting;
import pl.wat.pks.currency.settings.CurrencySettingViewModel;
import pl.wat.pks.currency.settings.SettingsListAdapter;

import java.util.List;


public class SettingsView extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsView() {
    }


    private SettingsListAdapter adapter;


    // TODO: Rename and change types and number of parameters
    public static SettingsView newInstance(String param1, String param2) {
        SettingsView fragment = new SettingsView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.ustawienia_fragment, container, false);

        final CurrencySettingViewModel currencyViewModel = ViewModelProviders.of(this).get(CurrencySettingViewModel.class);
        currencyViewModel.getAllCurrencySettings().observe(this, words -> adapter.setSettings(words));

        RecyclerView recyclerView = rootView.findViewById(R.id.currencySetingsRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new SettingsListAdapter();

        recyclerView.setAdapter(adapter);

        final Button button = rootView.findViewById(R.id.addUserSettinsButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<CurrencySetting> settingsNew = adapter.getSettings();
                for (CurrencySetting setting : settingsNew) {
                    Log.d("Zapisuje", setting.toString());
                }
                currencyViewModel.updateAll(settingsNew);
                adapter.setSettings(settingsNew);
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
