package pl.wat.pks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import pl.wat.pks.models.dto.BTCCurencyListDTO;
import pl.wat.pks.models.dto.CryptoDTO;
import pl.wat.pks.rest.RestController;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AktualneKursyTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AktualneKursyTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AktualneKursyTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Observable<CryptoDTO> cryptoDTOObservable;
    private Observable<BTCCurencyListDTO> btcCurencyListDTOObservable;

    private CryptoDTO cryptoDTO;
    private BTCCurencyListDTO btcCurencyListDTO;

    public AktualneKursyTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AktualneKursyTab.
     */
    // TODO: Rename and change types and number of parameters
    public static AktualneKursyTab newInstance(String param1, String param2) {
        AktualneKursyTab fragment = new AktualneKursyTab();
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
        getValuesForCharts();
        getActualValuesInCurrencies();
    }

    private void getActualValuesInCurrencies() {
        btcCurencyListDTOObservable = RestController.getActualValuesInCurrencies();
        btcCurencyListDTOObservable
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<BTCCurencyListDTO>() {
                    @Override
                    public void onNext(BTCCurencyListDTO value) {
                        Log.d("Aktualne", "onNext: " + value.usd().symbol());
                        btcCurencyListDTO = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Aktualne", "Błąd przy pobraniu aktualnej wartości");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Aktualne", "onComplete:");
                    }
                });
    }

    private void getValuesForCharts() {
        cryptoDTOObservable = RestController.getValuesForCharts();
        cryptoDTOObservable
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<CryptoDTO>() {
                    @Override
                    public void onNext(CryptoDTO value) {
                        Log.d(getTag(), "onNext: " + value.status());
                        cryptoDTO = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(getTag(), "Błąd przy pobraniu api");
                        e.printStackTrace();
                    }


                    @Override
                    public void onComplete() {
                        Log.d(getTag(), "onComplete:");
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.aktualne_kursy_fragment, container, false);
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
