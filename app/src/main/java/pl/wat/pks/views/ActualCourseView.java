package pl.wat.pks.views;

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
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;
import pl.wat.pks.R;
import pl.wat.pks.models.dto.BTCCurencyListDTO;
import pl.wat.pks.models.dto.CryptoDTO;
import pl.wat.pks.models.dto.XYCorordinates;
import pl.wat.pks.rest.RestController;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActualCourseView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActualCourseView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActualCourseView extends Fragment {
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

    private ValueLineChart mCubicValueLineChart;

    public ActualCourseView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActualCourseView.
     */
    // TODO: Rename and change types and number of parameters
    public static ActualCourseView newInstance(String param1, String param2) {
        ActualCourseView fragment = new ActualCourseView();
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
                        Log.d(getTag(), "onNext: " + value.usd().symbol());
                        Log.d(getTag(), "onNext: " + value.pln().symbol());
                        Log.d(getTag(), "onNext: " + value.eur().symbol());
                        btcCurencyListDTO = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(getTag(), "Błąd przy pobraniu aktualnej wartości");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(getTag(), "onComplete:");
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
                        setValueForChart(value);
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

    private void setValueForChart(CryptoDTO value) {
        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);
        for(XYCorordinates corordinates : value.coordniateList()){
            series.addPoint(new ValueLinePoint(corordinates.price()));
        }
        mCubicValueLineChart.clearStandardValues();
        mCubicValueLineChart.setShowIndicator(false);
        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.setUseDynamicScaling(true);
        mCubicValueLineChart.startAnimation();
        mCubicValueLineChart.refreshDrawableState();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.aktualne_kursy_fragment, container, false);

        mCubicValueLineChart = rootView.findViewById(R.id.cubiclinechart);

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