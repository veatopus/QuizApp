package com.example.quizapp.ui.history;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;

import android.view.View;
import android.widget.PopupMenu;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapters.HistoryAdapter;
import com.example.quizapp.core.BaseFragment;
import com.example.quizapp.databinding.HistoryFragmentBinding;
import com.example.quizapp.models.HistoryResultModel;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class HistoryFragment extends BaseFragment {

    private HistoryFragmentBinding binding;
    private HistoryAdapter adapter;
    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public int getLayoutID() {
        return R.layout.history_fragment;
    }

    @Override
    protected void init(View view) {
        binding = HistoryFragmentBinding.bind(view);
        adapter = new HistoryAdapter();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void observe(LifecycleOwner owner) {
        mViewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
        mViewModel.listHistoryMutableLiveData.observe(owner, historyModels -> {
            if (historyModels.isEmpty()){
                binding.message.setVisibility(View.VISIBLE);
                binding.graph.setVisibility(View.GONE);
                binding.message.setText("you have no history yet");
            } else {
                binding.message.setVisibility(View.GONE);
                binding.graph.setVisibility(View.VISIBLE);
                graph(historyModels);
            }
            adapter.addData(historyModels);
        });
    }

    @Override
    protected void setArg() {
        binding.recyclerview.setAdapter(adapter);
        adapter.setOnPopupMenuClick(this::showPopupMenu);
    }

    private void graph(List<HistoryResultModel> historyModels) {
        binding.graph.removeAllSeries();

        DataPoint[] dataPoints = new DataPoint[historyModels.size()];
        for (int i = 0; i < historyModels.size(); i++)
            dataPoints[i] = (new DataPoint(i, historyModels.get(i).getCorrectAns()));

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        binding.graph.addSeries(series);

        binding.graph.getViewport().setYAxisBoundsManual(true);
        binding.graph.getViewport().setMaxY(20);

        binding.graph.getViewport().setXAxisBoundsManual(true);
        binding.graph.getViewport().setMaxX(historyModels.size());

        // enable scaling and scrolling
        binding.graph.getViewport().setScalable(true);
        binding.graph.getViewport().setScalableY(true);

        binding.graph.setTitle("success");
        binding.graph.setCursorMode(true);
    }

    @SuppressLint("NonConstantResourceId")
    private void showPopupMenu(View v, int position) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), v);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu
                .setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.delete:
                            mViewModel.popupMenuDelete(position);
                            return true;
                        case R.id.no:

                            return true;
                        default:
                            return false;
                    }
                });

        popupMenu.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTheme(App.getInstance().getPrefs().getTheme());
    }
}
