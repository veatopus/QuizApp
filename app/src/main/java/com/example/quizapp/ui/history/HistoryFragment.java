package com.example.quizapp.ui.history;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.R;
import com.example.quizapp.adapters.HistoryAdapter;
import com.example.quizapp.databinding.HistoryFragmentBinding;
import com.example.quizapp.models.HistoryResultModel;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryFragmentBinding binding;
    private HistoryAdapter adapter;
    private List<HistoryResultModel> historyModels;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HistoryFragmentBinding.bind(inflater.inflate(R.layout.history_fragment, container, false));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HistoryViewModel mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        init();
        mViewModel.updateData();
        binding.recyclerview.setAdapter(adapter);
        mViewModel.listHistoryMutableLiveData.observeForever(historyModels -> {
            this.historyModels = historyModels;
            adapter.addData(historyModels);
        });

        testGraph();
    }

    private void testGraph() {
        DataPoint[] dataPoints = new DataPoint[historyModels.size()];
        for (int i = 0; i < historyModels.size(); i++) {
            dataPoints[i] = (new DataPoint(i, historyModels.get(i).getCorrectAns()));
        }


        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        binding.graph.addSeries(series);

        //series.setValueDependentColor(data -> Color.rgb(148, 0, 211));

        //series.setSpacing(10);
        //series.setValuesOnTopSize(50);

        binding.graph.setTitle("success");
        binding.graph.setCursorMode(true);

    }

    private void init() {
        adapter = new HistoryAdapter();
    }


}
