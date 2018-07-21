package bot.callbacks;

import bot.keyboards.KeyboardFactory;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class ChoosePeriodCallBack extends KeyboardCallback
{
    public static final String ID = "choose-period";

    public ChoosePeriodCallBack() {
        super(ID);
    }

    @Override
    public void execute(TelegramBot bot, Update update) {
        String period = update.callbackQuery().data().replace(ID, "");

        SendMessage request = new SendMessage(update.callbackQuery().message().chat().id(),
                "Choose Workout Type");
        request.replyMarkup(KeyboardFactory.createExerciseTypesKeyboard(period));
        bot.execute(request);
    }
}
