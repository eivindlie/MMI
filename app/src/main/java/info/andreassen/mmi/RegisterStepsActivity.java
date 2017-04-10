package info.andreassen.mmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterStepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_steps);

        final Button registerButton = (Button) findViewById(R.id.btn_register_steps);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText inRegisterSteps = (EditText) findViewById(R.id.in_register_steps);
                MainActivity.getCurrentCompetition().addSteps(Integer.parseInt(inRegisterSteps.getText().toString()));
                setResult(RESULT_OK, new Intent().putExtra("status", "competitionUpdated"));
                finish();
            }
        });
    }
}
