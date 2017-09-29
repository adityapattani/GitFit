package com.example.adityapattani.gitfit;

public class ExerciseDataProvider {
    private int exercise_poster;
    private String exercise_title;

    public int getExercise_poster() {
        return exercise_poster;
    }

    public void setExercise_poster(int exercise_poster) {
        this.exercise_poster = exercise_poster;
    }

    public String getExercise_title() {
        return exercise_title;
    }

    public void setExercise_title(String exercise_title) {
        this.exercise_title = exercise_title;
    }

    public ExerciseDataProvider(int poster, String name){
        this.setExercise_poster(poster);
        this.setExercise_title(name);
    }
}
