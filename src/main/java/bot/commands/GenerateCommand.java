package bot.commands;

import bot.keyboards.KeyboardFactory;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class GenerateCommand extends BotCommand
{
    public static final String COMMAND_TEXT =  "Generate Workout";

    public GenerateCommand() {
        super(COMMAND_TEXT);
    }

    @Override
    public void execute(TelegramBot bot, Update update) {
        SendMessage request = new SendMessage(update.message().chat().id(), "Choose Workout Period");
        request.replyMarkup(KeyboardFactory.createPeriodsKeyboard());
        bot.execute(request);
    }
}
