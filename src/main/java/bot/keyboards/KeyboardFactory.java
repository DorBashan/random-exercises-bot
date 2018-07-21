package bot.keyboards;

import bot.callbacks.ChoosePeriodCallBack;
import bot.callbacks.ChooseWorkoutTypeCallBack;
import bot.callbacks.ShowExerciseVideoCallback;
import bot.commands.GenerateCommand;
import bot.commands.ShowExercisesCommand;
import bot.entities.Exercise;
import bot.entities.ExerciseType;
import bot.entities.Period;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KeyboardFactory
{
    public static Keyboard createMainKeyboard() {
        return new ReplyKeyboardMarkup(
                new String[]{ShowExercisesCommand.COMMAND_TEXT, GenerateCommand.COMMAND_TEXT})
                .resizeKeyboard(true);
    }

    public static Keyboard createPeriodsKeyboard() {
        return new InlineKeyboardMarkup(
                Arrays.stream(Period.values()).map(period -> new InlineKeyboardButton(period.minutes() +  " Minutes")
                        .callbackData(ChoosePeriodCallBack.ID + period.name()))
                .collect(Collectors.toList()).toArray(new InlineKeyboardButton[0])
        );
    }

    public static Keyboard createExercisesKeyboard(List<Exercise> exercises) {
        DynamicKeyboardCreator dynamicKeyboardCreator =
                new DynamicKeyboardCreator(exercises.stream().map(Exercise::getName).collect(Collectors.toList()),
                        ShowExerciseVideoCallback.ID);
        return dynamicKeyboardCreator.create();
    }

    public static Keyboard createExerciseTypesKeyboard(String period) {
        DynamicKeyboardCreator dynamicKeyboardCreator =
                new DynamicKeyboardCreator(
                        Arrays.stream(ExerciseType.values()).filter(ExerciseType::isNotAbs)
                                .map(ExerciseType::getDescription)
                                .collect(Collectors.toList()), ChooseWorkoutTypeCallBack.ID + period + ":");
        return dynamicKeyboardCreator.create();
    }
}
