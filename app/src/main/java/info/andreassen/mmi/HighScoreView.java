package info.andreassen.mmi;

import android.content.Context;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;

import java.util.Locale;

public class HighScoreView extends CardView {

    public HighScoreView(Context context, int number, String name, int walkedSteps, int totalSteps) {
        super(context);
        buildView(context, number, name, walkedSteps, totalSteps);
    }

    private void buildView(Context context, int number, String name, int walkedSteps, int totalSteps) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(30, 15, 10, 15);
        this.addView(layout);

        TextView txtNumber = new TextView(context);
        txtNumber.setTextSize(35);
        txtNumber.setTypeface(Typeface.DEFAULT_BOLD);
        txtNumber.setText(Integer.toString(number));
        layout.addView(txtNumber);

        Space space = new Space(context);
        space.setMinimumWidth(50);
        layout.addView(space);

        LinearLayout layout2 = new LinearLayout(context);
        layout2.setOrientation(LinearLayout.VERTICAL);
        layout.addView(layout2);
        
        TextView txtName = new TextView(context);
        txtName.setTextSize(18);
        txtName.setText(name);
        layout2.addView(txtName);

        TextView txtSteps = new TextView(context);
        txtSteps.setTextSize(18);
        txtSteps.setText(String.format(Locale.GERMANY, "%,d / %,d", walkedSteps, totalSteps));
        layout2.addView(txtSteps);
    }
}
