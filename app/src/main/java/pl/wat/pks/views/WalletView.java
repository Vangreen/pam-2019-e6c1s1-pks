/**
 * Widok portfela
 */

package pl.wat.pks.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.wat.pks.R;
import pl.wat.pks.currency.settings.CurrencySetting;
import pl.wat.pks.currency.settings.CurrencySettingViewModel;
import pl.wat.pks.currency.wallet.WalletAdapter;



public class WalletView extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WalletView() {
    }

    private WalletAdapter adapter;
    private int no_text = 0;
    TextView noWalletText;

    // TODO: Rename and change types and number of parameters
    public static WalletView newInstance(String param1, String param2) {
        WalletView fragment = new WalletView();
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

        final View rootView = inflater.inflate(R.layout.portfel_fragment, container, false);

        final CurrencySettingViewModel currencyViewModel = ViewModelProviders.of(this).get(CurrencySettingViewModel.class);
        currencyViewModel.getAllCurrencySettings().observe(this, words -> {

            for (CurrencySetting word : words) {
                Log.d("Czy Pusty", String.valueOf(word.isNotificationBool()));
                Log.d("petla", word.toString());
                if (word.isNotificationBool()) {
                    Log.d("Pusto", "pusto");
                    noTextAdd();
                }
            }
            adapter.setSettings(words);
        });

        noWalletText = rootView.findViewById(R.id.noWalletText);


        RecyclerView recyclerView = rootView.findViewById(R.id.walletRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new WalletAdapter();

        recyclerView.setAdapter(adapter);


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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void noTextAdd() {
        this.no_text++;
        Log.d("Dodaje", String.valueOf(this.no_text));
        Log.d("Widoczny", String.valueOf(this.no_text));
        if (this.no_text != 0) {
            noWalletText.setVisibility(View.VISIBLE);
        } else {
            noWalletText.setVisibility(View.GONE);
        }
    }


}
