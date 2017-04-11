package info.andreassen.mmi;

/**
 * Created by ela49 on 10.04.2017.
 */

public class Competition {
    private String name;
    private int totalSteps;
    private int walkedSteps;
    private float difficulty;
    private int durationDays;

    public Competition(String name, int totalSteps, float difficulty, int durationDays) {
        this.name = name;
        this.totalSteps = totalSteps;
        this.difficulty = difficulty;
        this.durationDays = durationDays;
        walkedSteps = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public int getWalkedSteps() {
        return walkedSteps;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void addSteps(int steps) {
        walkedSteps += steps;
        if(walkedSteps > totalSteps) walkedSteps = totalSteps;
    }

    public void resetSteps() {
        walkedSteps = 0;
    }
}
