package bot.workout;

import bot.entities.Exercise;
import bot.entities.ExerciseType;
import bot.entities.Period;
import bot.entities.workout.LongWorkout;
import bot.entities.workout.MidWorkout;
import bot.entities.workout.ShortWorkout;
import bot.entities.workout.Workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WorkoutGenerator
{
    private List<Exercise> allExercises;

    public WorkoutGenerator(List<Exercise> allExercises) {
        this.allExercises = allExercises;
    }

    public Workout generate(Period period, ExerciseType exerciseType) {
        Workout workout = getWorkoutByPeriod(period);
        populateWorkoutWithType(workout, workout.getNumberOfExercises(), exerciseType);
        populateWorkoutWithType(workout, workout.getNumberOfAbsExercises(), ExerciseType.ABS);
        return workout;
    }

    public Workout generate(Period period) {
        Workout workout = getWorkoutByPeriod(period);
        populateWorkoutWithAllExpect(workout, workout.getNumberOfExercises(), ExerciseType.ABS);
        populateWorkoutWithType(workout, workout.getNumberOfAbsExercises(), ExerciseType.ABS);
        return workout;
    }

    private Workout getWorkoutByPeriod(Period period) {
        Workout workout;

        if (period.equals(Period.SHORT)) {
            workout = new ShortWorkout();
        }
        else if (period.equals(Period.MID)) {
            workout = new MidWorkout();
        }
        else {
            workout = new LongWorkout();
        }
        return workout;
    }

    private void populateWorkoutWithAllExpect(Workout workout, int numberOfExercises , ExerciseType type) {
        List<Exercise> allExercisesWithoutType = new ArrayList<>(allExercises).stream()
                .filter(exercise -> !exercise.getExerciseType().equals(type)).collect(Collectors.toList());
        populateWorkout(workout, numberOfExercises, allExercisesWithoutType);
    }

    private void populateWorkoutWithType(Workout workout, int numberOfExercises , ExerciseType type) {
        List<Exercise> allExercisesWithoutType = new ArrayList<>(allExercises).stream()
                .filter(exercise -> exercise.getExerciseType().equals(type)).collect(Collectors.toList());
        populateWorkout(workout, numberOfExercises, allExercisesWithoutType);
    }

    private void populateWorkout(Workout workout, int numberOfExercises, List<Exercise> exercises) {
        List<Exercise> copyAllExercises = new ArrayList<>(exercises);

        for (int i=0; i < numberOfExercises; i++) {

            if (copyAllExercises.isEmpty()) {
                copyAllExercises = new ArrayList<>(exercises);
            }

            int index = new Random().ints(0, copyAllExercises.size()).findFirst().getAsInt();
            workout.addExercise(copyAllExercises.get(index));
            copyAllExercises.remove(index);
        }
    }
}
