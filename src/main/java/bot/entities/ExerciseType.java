package bot.entities;

public enum ExerciseType
{
    ABS("Abs"), CHEST("Chest"), LEG("Leg"), SHOULDERS("Shoulder")

    , MIXED("Mixed");

    private String description;

    ExerciseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isNotAbs() {
        return !this.equals(ABS);
    }
}
