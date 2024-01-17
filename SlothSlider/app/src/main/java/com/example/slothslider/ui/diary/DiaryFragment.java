package com.example.slothslider.ui.diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.slothslider.databinding.FragmentDiaryBinding;

import java.util.List;

public class DiaryFragment extends Fragment {

    private FragmentDiaryBinding binding;
    private List list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DiaryViewModel diaryViewModel =
                new ViewModelProvider(this).get(DiaryViewModel.class);

        binding = FragmentDiaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView2;
        diaryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Movie> movieList = getMovieData(); // Implementa este método para obtener tus datos

        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(movieAdapter);
    }

    // Implementa este método para obtener tus datos
    private List<Movie> getMovieData() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(R.drawable.lehaine, "La Haine", 20));
        movieList.add(new Movie(R.drawable.thelobster, "The Lobster", 26));
        movieList.add(new Movie(R.drawable.apocalypsenow, "Apocalypse Now", 5));
        // Agrega más películas según sea necesario
        return movieList;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}