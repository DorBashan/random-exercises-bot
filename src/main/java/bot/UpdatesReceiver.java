package bot;

import bot.callbacks.ChoosePeriodCallBack;
import bot.callbacks.ChooseWorkoutTypeCallBack;
import bot.callbacks.KeyboardCallback;
import bot.callbacks.ShowExerciseVideoCallback;
import bot.commands.BotCommand;
import bot.commands.GenerateCommand;
import bot.commands.ShowExercisesCommand;
import bot.commands.StartCommand;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

import java.util.ArrayList;
import java.util.List;

public class UpdatesReceiver
{
    private List<BotCommand> commands = new ArrayList<>();
    private List<KeyboardCallback> callbacks = new ArrayList<>();
    private TelegramBot bot;

    public UpdatesReceiver(TelegramBot bot) {
        this.bot = bot;
        initializeCommands();
        initializeCallBacks();
    }

    private void initializeCommands() {
        commands.add(new StartCommand());
        commands.add(new ShowExercisesCommand());
        commands.add(new GenerateCommand());
    }

    private void initializeCallBacks() {
        callbacks.add(new ShowExerciseVideoCallback());
        callbacks.add(new ChoosePeriodCallBack());
        callbacks.add(new ChooseWorkoutTypeCallBack());
    }

    public void receive(List<Update> updates) {
        updates.forEach(update -> {

            if (update.callbackQuery() != null) {
                callbacks.stream().filter(callback -> callback.isCallBackRelevant(update.callbackQuery().data()))
                        .forEach(callback -> callback.execute(bot, update));
            }

            commands.stream().filter(command -> command.isUpdateRelevant(update))
                    .forEach(command -> command.execute(bot, update));
        });
    }
}
