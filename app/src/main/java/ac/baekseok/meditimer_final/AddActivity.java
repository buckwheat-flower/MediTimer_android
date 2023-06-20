package ac.baekseok.meditimer_final;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private EditText editText;
    private Button buttonSave,buttonCancel;

    private Alarm alarm;
    private boolean needRefresh;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_add);

        timePicker=findViewById(R.id.timePicker);
        editText=findViewById(R.id.name);
        buttonSave=findViewById(R.id.btnSave);
        buttonCancel=findViewById(R.id.btnCancel);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String name=editText.getText().toString();

                DB databaseHelper=new DB(getApplicationContext());
                alarm=new Alarm(hour,minute,true,name);
                databaseHelper.addAlarm(alarm);
                Toast.makeText(getApplicationContext(), "알람이 등록되었습니다.", Toast.LENGTH_LONG).show();
                needRefresh=true;
                onBackPressed();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    public void finish() {
        Intent data=new Intent();
        data.putExtra("needRefresh",needRefresh);
        this.setResult(RESULT_OK,data);
        super.finish();
    }
}
