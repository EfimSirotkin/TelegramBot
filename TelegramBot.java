package main.java.redstar;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;

import org.telegram.telegrambots.api.objects.File;
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


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//1772803043:AAHgaorrPa42TUYFeBv124KtO8ute33ucqc

public class TelegramBot extends TelegramLongPollingBot {

    public final String ByTheme = "По тематике";
    public final String ByCategory = "По категориям";
    public final String ByAgeGender = "По полу/возрасту";
    //По тематике
    public final String firstTheme = "Готовые подарки";
    public final String secondTheme = "Новый год";
    public final String thirdTheme = "Крещение";
    public final String fourthTheme = "День учителя";
    //По категории
    public final String firstCategory = "Халаты";
    public final String secondCategory = "Свитшоты";
    public final String thirdCategory = "Полотенца";
    public final String fourthCategory = "Лен";
    public final String fifthCategory = "Пледы";
    //По полу/возрасту
    public final String forWomen = "Для женщин";
    public final String forMen = "Для мужчин";
    public final String forTeenager = "Для подростков";
    public final String forChildren = "Для детей";

    public final String toMainMenu = "В главное меню";

    public boolean isTheme = false;
    public boolean isCategory = false;
    public boolean isAgeGender = false;
    public boolean isMain = false;


    public ReplyKeyboardMarkup getMainKeyBoardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyBoardFirstRow = new KeyboardRow();

        keyBoardFirstRow.add(new KeyboardButton(ByTheme));
        keyBoardFirstRow.add(new KeyboardButton(ByCategory));
        keyBoardFirstRow.add(new KeyboardButton(ByAgeGender));

        keyboardRowList.add(keyBoardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getThematicalKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyBoardFirstRow = new KeyboardRow();
        KeyboardRow keyBoardSecondRow = new KeyboardRow();
        KeyboardRow keyBoardThirdRow = new KeyboardRow();

        keyBoardFirstRow.add(new KeyboardButton(firstTheme));
        keyBoardFirstRow.add(new KeyboardButton(secondTheme));
        keyBoardSecondRow.add(new KeyboardButton(thirdTheme));
        keyBoardSecondRow.add(new KeyboardButton(fourthTheme));
        keyBoardThirdRow.add(new KeyboardButton(toMainMenu));

        keyboardRowList.add(keyBoardFirstRow);
        keyboardRowList.add(keyBoardSecondRow);
        keyboardRowList.add(keyBoardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getCategoryKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyBoardFirstRow = new KeyboardRow();
        KeyboardRow keyBoardSecondRow = new KeyboardRow();
        KeyboardRow keyBoardThirdRow = new KeyboardRow();

        keyBoardFirstRow.add(new KeyboardButton(firstCategory));
        keyBoardFirstRow.add(new KeyboardButton(secondCategory));
        keyBoardSecondRow.add(new KeyboardButton(thirdCategory));
        keyBoardSecondRow.add(new KeyboardButton(fifthCategory));
        keyBoardThirdRow.add(new KeyboardButton(toMainMenu));

        keyboardRowList.add(keyBoardFirstRow);
        keyboardRowList.add(keyBoardSecondRow);
        
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getAgeGenderKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyBoardFirstRow = new KeyboardRow();
        KeyboardRow keyBoardSecondRow = new KeyboardRow();

        keyBoardFirstRow.add(new KeyboardButton(forWomen));
        keyBoardFirstRow.add(new KeyboardButton(forMen));
        keyBoardSecondRow.add(new KeyboardButton(forTeenager));
        keyBoardSecondRow.add(new KeyboardButton(forChildren));

        keyboardRowList.add(keyBoardFirstRow);
        keyboardRowList.add(keyBoardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }

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


    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Мужские");
        inlineKeyboardButton1.setCallbackData("Вы выбрали мужские халаты");
        inlineKeyboardButton2.setText("Женские");
        inlineKeyboardButton2.setCallbackData("Вы выбрали женские халаты");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Детские").setCallbackData("Вы выбрали детские халаты"));
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

        if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage().setText(update.getCallbackQuery().getData()).setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (message != null && message.hasText()) {
            switch (message.getText()) {

                case "/start":
                    sendMsg(message, "Привет! Какая категория подарков Вам интересна?...", getMainKeyBoardMarkup());
                    isMain = true;
                    break;

                case "/help":
                    ;
                    sendMsg(message, "Привет! Какая категория подарков Вам интересна?...", getMainKeyBoardMarkup());
                    isMain = true;
                    break;

                case toMainMenu:
                    sendMsg(message, "Возвращаемся", getMainKeyBoardMarkup());
                    break;

                case ByTheme:
                    isTheme = true;
                    sendMsg(message, "Выберите интересующую тематику...", getThematicalKeyboardMarkup());

                    break;

                case ByCategory:
                    isCategory = true;
                    sendMsg(message, "Выберите интересующую категорию...", getCategoryKeyboardMarkup());
                    break;

                case ByAgeGender:
                    isAgeGender = true;
                    sendMsg(message, "Выберите пол/возраст...", getAgeGenderKeyboardMarkup());

                    break;


                // По тематике
                case firstTheme: //Готовые подарки
                    sendMsg(message, "Мы готовы предложить готовые подарки:", getThematicalKeyboardMarkup());


                    sendImageFromUrl("https://rusan.by/assets/uploads/2020/11/IMG_6878-scaled-e1606409814429.jpg", message.getChatId().toString());
                    sendMsg(message, "Итак, махровый халат без вышивки \n" +
                            "Цена: 105 руб \n" +
                            "Изготовление: 1+ рабочий день, упаковка в подарок!\n" +
                            "Цвет: \uD83D\uDFE5", getThematicalKeyboardMarkup());


                    sendImageFromUrl("https://rusan.by/assets/uploads/2021/02/photo5242572141495431556.jpg", message.getChatId().toString());
                    sendMsg(message, "Свитшот с вышивкой \"Здесь&Сейчас\"\n" +
                            "Цена: 85 руб \n" +
                            "Изготовление: 1+ рабочий день, упаковка в подарок!\n" +
                            "Цвет: \uD83D\uDFE9", getThematicalKeyboardMarkup());


                    sendImageFromUrl("https://rusan.by/assets/uploads/2020/09/photo5332538555156770661.jpg", message.getChatId().toString());
                    sendMsg(message, "Халат \"Любимая Мама\" \n" +
                            "Цена: 130 руб \n" +
                            "Изготовление: 1+ рабочий день, упаковка в подарок!\n" +
                            "Цвет: \uD83D\uDFE8", getThematicalKeyboardMarkup());


                    break;
                case secondTheme: // Новый Год
                    sendMsg(message, "У нас вы найдете подарки к Новому Году", getThematicalKeyboardMarkup());
                    break;

                case thirdTheme: // Крещение
                    sendMsg(message, "Мы предлагаем подарки специально к Крещению", getThematicalKeyboardMarkup());
                    break;

                case fourthTheme: // День учителя
                    sendMsg(message, "Специальное предложение ко дню учителя", getThematicalKeyboardMarkup());
                    // По категориям

                case firstCategory: // Халаты

                    break;

                case secondCategory: // Свитшоты
                    break;

                case thirdCategory: // Полотенца
                    break;

                case fourthCategory: // Лен

                    break;

                case fifthCategory: // Пледы

                    break;

                    // По полу/возрасту

                case forWomen: // Для

                    break;

                case forMen:

                    break;

                case forTeenager:

                    break;

                case forChildren:

                    break;

                case "/Халаты☀":
                    sendImageFromUrl("https://rusan-art.ru/assets/uploads/2020/11/photo5298806749597381279-768x1024.jpg", message.getChatId().toString());
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

    public void sendImageFromUrl(String url, String chatID) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatID);
        sendPhoto.setPhoto(url);

        try {
            sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
