package telegramBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class AccoBoyBot extends BotConfig {
    private static final String BOT_TOKEN = "975140539:AAGLYHI38jLkzHmZR37q9Yp0BSYaLIl6pXM";
    private static final String BOT_USERNAME = "@AccoBoy_bot";
    private Socket socket;
    private static Integer port = 1025;
    private static final String HOST = "localhost";
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    private static final String CONNECTION_ERROR = "Bot can't reach the server. Please try again later";
    private String result;

    private void connectToServer() {
        try {
            socket.connect(new InetSocketAddress(HOST, port));
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            result = CONNECTION_ERROR;
        }
    }

    AccoBoyBot() {
        super();
        socket = new Socket();
        connectToServer();
    }

    public static void main(String[] args) {
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception exc) {
            System.out.println(exc);
        }
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new AccoBoyBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

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
    private synchronized void sendMsg(String chatId, String str) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        if (socket.isConnected()) {
            try {
                outputStream.writeUTF(str);
                outputStream.flush();
                result = inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            connectToServer();
        }

        System.out.println("Thread work is complete!");
        sendMessage.setText(result);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

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
}
