package bot.commands;

import bot.ExercisesDB;
import bot.keyboards.KeyboardFactory;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;

public class ShowExercisesCommand extends BotCommand
{
    public static final String COMMAND_TEXT =  "Show Exercises";
    private Keyboard exercisesKeyboard;

    public ShowExercisesCommand() {
        super(COMMAND_TEXT);
        exercisesKeyboard = KeyboardFactory.createExercisesKeyboard(ExercisesDB.get());
    }

    @Override
    public void execute(TelegramBot bot, Update update) {
        SendMessage request = new SendMessage(update.message().chat().id(), "Exercises List");
        request.replyMarkup(exercisesKeyboard);
        bot.execute(request);
    }
}
