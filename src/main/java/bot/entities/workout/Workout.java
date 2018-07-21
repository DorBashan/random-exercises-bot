package bot.entities.workout;

import bot.entities.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Workout
{
    private int numberOfExercises;
    private int numberOfAbsExercises;
    private List<Exercise> exercises = new ArrayList<>();

    public Workout(int numberOfExercises, int numberOfStomachExercises) {
        this.numberOfExercises = numberOfExercises;
        this.numberOfAbsExercises = numberOfStomachExercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public int getNumberOfExercises()
    {
        return numberOfExercises;
    }

    private int getNumberOfSets() {
        return 3;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public int getNumberOfAbsExercises()
    {
        return numberOfAbsExercises;
    }

    @Override
    public String toString() {

        StringBuilder text = new StringBuilder();

        List<Exercise> exercisesWithoutABS = exercises.stream().filter(exercise -> !exercise.isABS()).collect(Collectors.toList());
        List<Exercise> exercisesABS = exercises.stream().filter(Exercise::isABS).collect(Collectors.toList());

        for (int i = 0; i < exercisesWithoutABS.size(); i++) {
            if (i % getNumberOfSets() == 0) {
                text.append("\n").append("~10 Minutes\n").append("X").append(getNumberOfSets()).append(" Sets of:").append("\n");
            }

            text.append(exercisesWithoutABS.get(i).getName()).append("\n");
        }

        text.append("\n").append("~").append(getNumberOfAbsExercises()).append(" Minutes - 1 Minute per Exercise\n");

        for (Exercise exercisesAB : exercisesABS) {
            text.append(exercisesAB.getName()).append("\n");
        }

        return text.toString();
    }
}
