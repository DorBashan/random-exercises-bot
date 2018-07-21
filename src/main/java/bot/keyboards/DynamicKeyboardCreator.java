package bot.keyboards;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicKeyboardCreator
{
    private InlineKeyboardButton[] allButtons;
    private static final int NUMBER_OF_COLS = 3;
    private int NUMBER_OF_ROWS;
    private String callBackDataID;

    public DynamicKeyboardCreator(List<String> strings, String callBackDataID) {
        NUMBER_OF_ROWS = (strings.size() % NUMBER_OF_COLS) == 0 ?
                strings.size() / NUMBER_OF_COLS :
                (strings.size() / NUMBER_OF_COLS) + 1;

        this.callBackDataID = callBackDataID;

        allButtons = transformStringsToButtons(strings);
    }

    public Keyboard create() {
        InlineKeyboardButton[][] keyboardButtons = new InlineKeyboardButton[NUMBER_OF_ROWS][NUMBER_OF_COLS];

        int j = 0;
        for (int i = 0; i < allButtons.length; i = i + NUMBER_OF_COLS) {
            keyboardButtons[j] = getButtonsArrayFromIndex(i);
            j++;
        }

        return new InlineKeyboardMarkup(keyboardButtons);
    }

    private InlineKeyboardButton[] getButtonsArrayFromIndex(int i) {
        InlineKeyboardButton[] splitArray = Arrays.copyOfRange(allButtons, i, i + NUMBER_OF_COLS);
        fillArrayWithEmptyButtons(splitArray);

        return splitArray;
    }

    /**
     * Apparently Telegram keyboard isn't working if the array size is not in the size that
     * was given in the initialization. So we have to add empty buttons, but the user won't see them
     * @param splitArray
     */
    private void fillArrayWithEmptyButtons(InlineKeyboardButton[] splitArray) {
        for (int j = 0; j < NUMBER_OF_COLS; j++) {
            if (splitArray[j] == null) {
                splitArray[j] = new InlineKeyboardButton("").callbackData("empty");
            }
        }
    }

    private InlineKeyboardButton[] transformStringsToButtons(List<String> strings) {
        return strings.stream().map(s -> new InlineKeyboardButton(s).callbackData(callBackDataID + s))
                .collect(Collectors.toList()).toArray(new InlineKeyboardButton[0]);
    }
}
