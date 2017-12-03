package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 8/12/2017.
 */

public class TaskResult {
    private String calories_loss,played_time,distance,jumping_count,done_date;

    public TaskResult(String calories_loss, String played_time, String distance, String jumping_count, String done_date) {
        this.calories_loss = calories_loss;
        this.played_time = played_time;
        this.distance = distance;
        this.jumping_count = jumping_count;
        this.done_date = done_date;
    }

    public String getCalories_loss() {
        return calories_loss;
    }

    public String getPlayed_time() {
        return played_time;
    }

    public String getDistance() {
        return distance;
    }

    public String getJumping_count() {
        return jumping_count;
    }

    public String getDone_date() {
        return done_date;
    }
}
