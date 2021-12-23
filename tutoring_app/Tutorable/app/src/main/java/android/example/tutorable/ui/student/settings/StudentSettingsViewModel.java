package android.example.tutorable.ui.student.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StudentSettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StudentSettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the settings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}