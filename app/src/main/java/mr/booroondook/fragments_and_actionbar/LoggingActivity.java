package mr.booroondook.fragments_and_actionbar;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class LoggingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getName(), "onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(getClass().getName(), "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getName(), "onResume");
    }

    @Override
    protected void onPause() {
        Log.d(getClass().getName(), "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(getClass().getName(), "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(getClass().getName(), "onDestroy");
        super.onDestroy();
    }
}
