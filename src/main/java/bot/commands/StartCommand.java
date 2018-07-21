package bot.commands;

import bot.keyboards.KeyboardFactory;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vdurmont.emoji.EmojiParser;

public class StartCommand extends BotCommand
{
    public StartCommand() {
        super("/start");
    }

    public void execute(TelegramBot bot, Update update)
    {
        SendMessage request = new SendMessage(update.message().chat().id(), EmojiParser.parseToUnicode(
                "Welcome to Random Exercise Bot! :running:" + "\n" +
                        "Have you ever wonder how to make your workouts less boring? :thinking:" + "\n\n" +
                        "Try new exercises each time! :wink:" +
                        "\n\n" +
                        "How can i help you? :sunglasses:"));

        request.replyMarkup(KeyboardFactory.createMainKeyboard());
        bot.execute(request);
    }
}
