package android.example.tutorable.ui.student.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StudentHomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StudentHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}