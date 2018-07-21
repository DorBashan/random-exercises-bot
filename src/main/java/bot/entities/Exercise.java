package bot.entities;

public class Exercise
{
    private String name;
    private ExerciseType exerciseType;

    public Exercise(String name, ExerciseType exerciseType)
    {
        this.name = name;
        this.exerciseType = exerciseType;
    }

    public String getName()
    {
        return name;
    }

    public boolean isABS() {
        return exerciseType.equals(ExerciseType.ABS);
    }

    public ExerciseType getExerciseType()
    {
        return exerciseType;
    }
}
