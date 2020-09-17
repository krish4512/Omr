package com.example.omr;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class OMRSheetViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private int numberOfQuestions;
    private int w;
    private int h;

    public OMRSheetViewModelFactory(int numberOfQuestions, int w, int h) {
        this.numberOfQuestions = numberOfQuestions;
        this.w = w;
        this.h = h;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OMRSheet();
    }
}
