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


public class ActualCourseView extends Fragment {

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
    }


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
        View rootView = inflater.inflate(R.layout.aktualne_kursy_fragment, container, false);

        mCubicValueLineChart = rootView.findViewById(R.id.cubiclinechart);

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
