package bot.callbacks;

import bot.ExercisesDB;
import bot.entities.ExerciseType;
import bot.entities.Period;
import bot.entities.workout.Workout;
import bot.keyboards.KeyboardFactory;
import bot.workout.WorkoutDescriptor;
import bot.workout.WorkoutGenerator;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vdurmont.emoji.EmojiParser;

import java.util.Arrays;

public class ChooseWorkoutTypeCallBack extends KeyboardCallback
{
    public static final String ID = "choose-workout-type";
    private WorkoutGenerator workoutGenerator = new WorkoutGenerator(ExercisesDB.get());
    private WorkoutDescriptor workoutDescriptor = new WorkoutDescriptor();

    public ChooseWorkoutTypeCallBack() {
        super(ID);
    }

    @Override
    public void execute(TelegramBot bot, Update update) {
        String[] params = update.callbackQuery().data().replace(ID, "").split(":");
        Period period = Period.valueOf(params[0]);
        ExerciseType exerciseType =
                Arrays.stream(ExerciseType.values()).
                        filter(t-> t.getDescription().equals(params[1])).findFirst().get();

        Workout workout;
        if (exerciseType.equals(ExerciseType.MIXED)) {
            workout = workoutGenerator.generate(period);
        }
        else {
            workout = workoutGenerator.generate(period, exerciseType);
        }

        SendMessage request = new SendMessage(update.callbackQuery().message().chat().id(),
                EmojiParser.parseToUnicode(workoutDescriptor.parse(workout)));
                request.replyMarkup(KeyboardFactory.createMainKeyboard());
                bot.execute(request);
    }
}
