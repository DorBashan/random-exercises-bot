package bot.callbacks;

import bot.YouTubeAPI;
import bot.keyboards.KeyboardFactory;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class ShowExerciseVideoCallback extends KeyboardCallback
{
    public static final String ID = "show-exercise-video";
    private YouTubeAPI youTubeAPI = new YouTubeAPI();

    public ShowExerciseVideoCallback() {
        super(ID);
    }

    @Override
    public void execute(TelegramBot bot, Update update) {

        String youtubeURL = youTubeAPI.getStringVideoBySearchParameter(update.callbackQuery().data().replace(ID, ""));

        SendMessage request = new SendMessage(update.callbackQuery().message().chat().id(), youtubeURL);
        request.replyMarkup(KeyboardFactory.createMainKeyboard());
        bot.execute(request);

    }
}
