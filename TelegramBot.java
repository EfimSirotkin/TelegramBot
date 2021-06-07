package main.java.redstar;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

//1772803043:AAHgaorrPa42TUYFeBv124KtO8ute33ucqc

public class TelegramBot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {

            telegramBotsApi.registerBot(new TelegramBot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(Message message, String text, ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void setButtonsPad(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyBoardFirstRow = new KeyboardRow();

        keyBoardFirstRow.add(new KeyboardButton("/help"));
        keyBoardFirstRow.add(new KeyboardButton("/setting"));

        keyboardRowList.add(keyBoardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);


    }

    public ReplyKeyboardMarkup getMainKeyBoardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyBoardFirstRow = new KeyboardRow();

        keyBoardFirstRow.add(new KeyboardButton("/help"));
        keyBoardFirstRow.add(new KeyboardButton("/setting"));

        keyboardRowList.add(keyBoardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getItemCategoriesPad() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyBoardFirstRow = new KeyboardRow();
        KeyboardRow keyBoardSecondRow = new KeyboardRow();

        keyBoardFirstRow.add(new KeyboardButton("/Халаты☀"));
        keyBoardFirstRow.add(new KeyboardButton("/Полотенца☀"));

        keyBoardSecondRow.add(new KeyboardButton("/Банные комплекты☀"));
        keyBoardSecondRow.add(new KeyboardButton("/Костюмы☀"));

        keyboardRowList.add(keyBoardFirstRow);
        keyboardRowList.add(keyBoardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }


    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Мужские");
        inlineKeyboardButton1.setCallbackData("Button \"Вы выбрали мужские халаты\" has been pressed");
        inlineKeyboardButton2.setText("Женские");
        inlineKeyboardButton2.setCallbackData("Button \"Вы выбрали женские халаты\" has been pressed");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Детские").setCallbackData("CallFi4a"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Забавы ради").setReplyMarkup(inlineKeyboardMarkup);
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if(update.hasCallbackQuery()) {
            try {
                execute(new SendMessage().setText(update.getCallbackQuery().getData()).setChatId(update.getCallbackQuery().getMessage().getChatId()));
            }catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help": ;
                    sendMsg(message, "Выберите интересующий товар...", getItemCategoriesPad());
                    break;
                case "/setting":
                    sendMsg(message, "Что будем настраивать?", getMainKeyBoardMarkup());
                    break;
                case "/Халаты☀":
                    sendMsg(message, "Окей", getMainKeyBoardMarkup());
                case "/Банные комплекты☀":
                    try {
                        execute(sendInlineKeyBoardMessage(message.getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                default:
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "TestRusanBot";
    }

    @Override
    public String getBotToken() {
        return "1772803043:AAHgaorrPa42TUYFeBv124KtO8ute33ucqc";
    }
}
