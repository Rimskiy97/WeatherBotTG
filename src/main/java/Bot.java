import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
        public void onUpdateReceived(Update update) {
            Message message = update.getMessage();
            if (message !=null && message.hasText()){
                switch (message.getText()){
                case "/help":
                    sendMsg(message, "Список комманд: /setting - настройка, /about - о проекте, /diary - дневник разработки");
                    break;
                case "/setting":
                    sendMsg(message, "Что будем настраивать?");
                case "/about":
                    sendMsg(message, "Данный бот любительский тест, задуман в будущем как создание бота прогноза погоды");
                case "/diary":
                    sendMsg(message, "01.09.2019 - день рождения бота. Первые команды /setting, /diary версия 1.00." + "\n Первый патч 1.01. добавлен дневник /diary, добавлен /about");
                    break;
            default:
            }
        }
    }
    public String getBotUsername(){
        return "AatroxBot";
    }

    public String getBotToken() {
        return "874125760:AAECmbENpIg4oFZcVWSxvIb1LfkyiN2xsi8";
    }
}