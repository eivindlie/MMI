package info.andreassen.mmi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.Locale;


public class CompetitionView extends CardView implements View.OnClickListener {

    private LinearLayout layout;
    private LinearLayout outerLayout;
    private TextView txtName;
    private TextView txtSteps;
    private ProgressBar progress;
    private RatingBar difficulty;
    private TextView duration;
    private Button button;

    private ButtonType buttonType;

    private Competition competition;

    public CompetitionView(Context context, ButtonType buttonType) {
        super(context);
        buildView(context, null, 0, buttonType);
    }

    public CompetitionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        buildView(context, attrs, defStyleAttr, ButtonType.NONE);
    }

    public CompetitionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        buildView(context, attrs, 0, ButtonType.NONE);
    }

    public CompetitionView(Context context) {
        super(context);
        buildView(context, null, 0, ButtonType.NONE);
    }

    private void buildView(Context context, AttributeSet attrs, int defStyleAttr, ButtonType buttonType) {
        this.buttonType = buttonType;

        outerLayout = new LinearLayout(context);
        outerLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        outerLayout.setOrientation(LinearLayout.VERTICAL);

        layout = new LinearLayout(context);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 48, 48, 48);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, 300, 0, 300);

            txtName = new TextView(context);
            txtName.setText("Trondheim - Tromsø");
            txtName.setTextSize(24);
            txtName.setTypeface(Typeface.DEFAULT_BOLD);
            layout.addView(txtName, layoutParams);

            Space space = new Space(context);
            space.setMinimumHeight(32);
            layout.addView(space);

            txtSteps = new TextView(context);
            txtSteps.setLayoutParams(layoutParams);
            txtSteps.setText("30.000 / 1.130.000 skritt");
            layout.addView(txtSteps, layoutParams);

            space = new Space(context);
            space.setMinimumHeight(32);
            layout.addView(space);

            progress = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progress.setProgressTintList(ColorStateList.valueOf(Color.CYAN));
            progress.setMax(1130000);
            progress.setProgress(60000);
            layout.addView(progress, layoutParams);

            space = new Space(context);
            space.setMinimumHeight(32);
            layout.addView(space);

            RelativeLayout bLayout = new RelativeLayout(context);

                difficulty = new RatingBar(context, null, android.R.attr.ratingBarStyleSmall);
                difficulty.setRating(3.0f);
                bLayout.addView(difficulty);

                duration = new TextView(context);
                duration.setText("50d");
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                bLayout.addView(duration, params);

            layout.addView(bLayout, layoutParams);

        outerLayout.addView(layout);

        if(buttonType == ButtonType.JOIN) {
            button = new Button(context);
            button.setBackgroundColor(0xFF00C907);
            button.setText("Bli med");
            button.setVisibility(GONE);
            outerLayout.addView(button);

            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.setCurrentCompetition(competition);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    getContext().startActivity(intent);
                    ((Activity) getContext()).finish();
                }
            });
        } else if(buttonType == ButtonType.WITHDRAW) {
            button = new Button(context);
            button.setBackgroundColor(0xFFDE0B0B);
            button.setTextColor(0xFFFFFFFF);
            button.setText("Trekk deg");
            button.setVisibility(GONE);
            outerLayout.addView(button);

            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getCurrentCompetition().resetSteps();
                    MainActivity.setCurrentCompetition(null);
                    Intent intent = new Intent(getContext(), StartActivity.class);
                    getContext().startActivity(intent);
                    ((Activity) getContext()).finish();
                }
            });
        }

        this.addView(outerLayout);

        if(button != null) {
            setOnClickListener(this);
        }
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
        txtName.setText(competition.getName());
        txtSteps.setText(String.format(Locale.GERMANY, "%,d / %,d skritt", competition.getWalkedSteps(), competition.getTotalSteps()));
        progress.setMax(competition.getTotalSteps());
        progress.setProgress(competition.getWalkedSteps());
        difficulty.setRating(competition.getDifficulty());
        duration.setText(competition.getDurationDays() + "d");
    }

    @Override
    public void onClick(View v) {
        if(button != null) {
            if(button.getVisibility() == GONE)
                button.setVisibility(VISIBLE);
            else
                button.setVisibility(GONE);
        }
    }

    public enum ButtonType {
        NONE,
        JOIN,
        WITHDRAW
    }
}
