package bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;

public class RandomExerciseBot
{
    public void run() {
        TelegramBot bot = new TelegramBot(Config.TOKEN);
        UpdatesReceiver updatesReceiver = new UpdatesReceiver(bot);

        bot.setUpdatesListener(updates -> {
            // todo: switch with logger
            System.out.println(updates);

            updatesReceiver.receive(updates);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
