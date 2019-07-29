package telegramBot;

import com.tibbo.Server;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AccoBoyBot extends TelegramLongPollingBot {
    private static final String BOT_TOKEN = "975140539:AAGLYHI38jLkzHmZR37q9Yp0BSYaLIl6pXM";
    private static final String BOT_USERNAME = "@AccoBoy_bot";

    private static Server server;
    private static int portCount = 1025;
    private static final String HOST = "localhost";
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    private String CONNECTION_ERROR = "Bot can't reach the server. Please try again later";

    AccoBoyBot() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(HOST, getPort()));
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String result;

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        System.out.println("Recieved message: " + message);
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId id чата
     * @param str    Строка, которую необходимот отправить в качестве сообщения.
     */
    @SuppressWarnings("deprecation")
    public synchronized void sendMsg(String chatId, String str) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);

        try {
            outputStream.writeUTF(str);
            outputStream.flush();
            result = inputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            result = CONNECTION_ERROR;
        }

        System.out.println("Thread work is complete!");
        sendMessage.setText(result);
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private static Integer getPort() {
        return portCount;
    }

    /*private void increasePortCount() {
        portCount++;
    }*/

    /*public synchronized void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }*/

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("7"));
        keyboardFirstRow.add(new KeyboardButton("8"));
        keyboardFirstRow.add(new KeyboardButton("9"));
        keyboardFirstRow.add(new KeyboardButton("∶"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("4"));
        keyboardSecondRow.add(new KeyboardButton("5"));
        keyboardSecondRow.add(new KeyboardButton("6"));
        keyboardSecondRow.add(new KeyboardButton("*"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("1"));
        keyboardThirdRow.add(new KeyboardButton("2"));
        keyboardThirdRow.add(new KeyboardButton("3"));
        keyboardThirdRow.add(new KeyboardButton("-"));

        KeyboardRow keyboardFouthRow = new KeyboardRow();
        keyboardFouthRow.add(new KeyboardButton("√"));
        keyboardFouthRow.add(new KeyboardButton("0"));
        keyboardFouthRow.add(new KeyboardButton("."));
        keyboardFouthRow.add(new KeyboardButton("+"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public static void main(String[] args) {
        setUp();

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new AccoBoyBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public static void setUp(){
        server = new Server();
        try {
            server.launch(getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tearDown(){
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
