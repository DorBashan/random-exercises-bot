package bot.callbacks;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public abstract class KeyboardCallback
{
    private String id;

    public KeyboardCallback(String id) {
        this.id = id;
    }

    public abstract void execute(TelegramBot bot, Update update);

    public boolean isCallBackRelevant(String id) {
        return id.startsWith(this.id);
    }
}
