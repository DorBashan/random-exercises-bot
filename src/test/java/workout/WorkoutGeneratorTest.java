package workout;

import bot.entities.Exercise;
import bot.entities.ExerciseType;
import bot.entities.Period;
import bot.entities.workout.Workout;
import bot.workout.WorkoutGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WorkoutGeneratorTest
{
    private WorkoutGenerator workoutGenerator;

    @Before
    public void init() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Push-ups", ExerciseType.CHEST));
        exercises.add(new Exercise("Leg Raise", ExerciseType.ABS));
        exercises.add(new Exercise("Bench Dip", ExerciseType.SHOULDERS));

        workoutGenerator = new WorkoutGenerator(exercises);
    }

    @Test
    public void testGenerateWorkout_ShouldProduceExercisesAsNumbered() throws Exception {
        Workout workout = workoutGenerator.generate(Period.SHORT);
        assertEquals(workout.getNumberOfExercises() +
                workout.getNumberOfAbsExercises(), workout.getExercises().size());
    }

    @Test
    public void testGenerateWorkoutByType_ShouldProduceExercisesInType() {
        Workout workout = workoutGenerator.generate(Period.SHORT, ExerciseType.CHEST);
        for (Exercise exercise : workout.getExercises().stream().filter(e -> !e.isABS()).collect(Collectors.toList())) {
            if (!exercise.getExerciseType().equals(ExerciseType.CHEST)) {
                fail("Not Same Types");
            }
        }
    }
}
