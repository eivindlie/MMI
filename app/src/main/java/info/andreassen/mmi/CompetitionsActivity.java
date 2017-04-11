package info.andreassen.mmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Space;

public class CompetitionsActivity extends AppCompatActivity {

    static Competition[] competitions = {
        new Competition("Trondheim - Tromsø", 1130000, 4f, 50),
        new Competition("Oslo - Grimstad", 307000, 2f, 30),
        new Competition("Bergen - Haugesund", 156000, 1f, 20)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitions);

        LinearLayout list = (LinearLayout) findViewById(R.id.compList);

        CompetitionView competitionView = new CompetitionView(this, CompetitionView.ButtonType.WITHDRAW);
        competitionView.setCompetition(MainActivity.getCurrentCompetition());
        list.addView(competitionView);

        Space space = new Space(this);
        space.setMinimumHeight(32);
        list.addView(space);

        for(Competition c : competitions) {
            if(c != MainActivity.getCurrentCompetition()) {
                competitionView = new CompetitionView(this);
                competitionView.setCardBackgroundColor(0xFFD0D0D0);
                competitionView.setCompetition(c);
                list.addView(competitionView);

                space = new Space(getApplicationContext());
                space.setMinimumHeight(32);
                list.addView(space);
            }
        }
    }
}
