package com.example.recognition.interfaces;

import com.example.recognition.types.Favorite;
import com.example.recognition.types.Options;
import java.util.List;

public interface ISaverAdapter {
    Favorite[] getFavorites();
    void setFavorites(Favorite[] favorites);
    Options getOptions();
    void setOptions(Options options);
}
