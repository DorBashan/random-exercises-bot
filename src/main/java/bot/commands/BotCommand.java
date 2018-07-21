package bot.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public abstract class BotCommand
{
    private String command;

    public BotCommand(String command) {
        this.command = command;
    }

    public boolean isUpdateRelevant(Update update) {
        return update.message() != null && update.message().text() != null && update.message().text().equals(command);
    }

    // TODO: think about changing it to create request and other class will execute it,
    // TODO: using the telegrambot class
    public abstract void execute(TelegramBot bot, Update update);
}
