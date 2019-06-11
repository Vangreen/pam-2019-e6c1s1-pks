package pl.wat.pks.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.wat.pks.R;
import pl.wat.pks.currency.settings.CurrencySettingViewModel;
import pl.wat.pks.currency.settings.SettingsListAdapter;
import pl.wat.pks.currency.settings.CurrencySetting;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsView() {
        // Required empty public constructor
    }

    //Lista
//    private List<CurrencySetting> setingsList = new ArrayList<>();
    private SettingsListAdapter adapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsView.
     */
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
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.ustawienia_fragment, container, false);


        // Deklaracja i inicjalizacja ViewModel
        final CurrencySettingViewModel currencyViewModel = ViewModelProviders.of(this).get(CurrencySettingViewModel.class);
        currencyViewModel.getAllCurrencySettings().observe(this, new Observer<List<CurrencySetting>>() {
            @Override
            public void onChanged(@Nullable final List<CurrencySetting> words) {
                // pojawiły się nowe recenzje,
                // zaktualizuj recenzje w adapterze
                adapter.setSettings(words);
            }
        });

        RecyclerView recyclerView = rootView.findViewById(R.id.currencySetingsRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new SettingsListAdapter();

        recyclerView.setAdapter(adapter);

        final Button button = rootView.findViewById(R.id.addUserSettinsButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}