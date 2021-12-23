package android.example.tutorable.ui.tutor.registration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TutorRegistrationViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public TutorRegistrationViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is the Tutor registration fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}