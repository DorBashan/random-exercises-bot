package bot.workout;

import bot.entities.workout.Workout;

public class WorkoutDescriptor
{
    public String parse(Workout workout) {
        return "Here is your workout for today! :weight_lifter: :sports_medal: \n" +
                workout.toString() +
                "\n Enjoy :muscle:";
    }
}
