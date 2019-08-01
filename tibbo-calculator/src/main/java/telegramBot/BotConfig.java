package telegramBot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public abstract class BotConfig extends TelegramLongPollingBot {
    private static final String BASE_URL = "http://104.248.243.143:18012/";

    BotConfig(){
        super(getDefaultOptions());
    }

    private static DefaultBotOptions botOptions = new DefaultBotOptions() {
        @Override
        public String getBaseUrl() {
            return BASE_URL;
        }
    };

    public static DefaultBotOptions getDefaultOptions(){
        return botOptions;
    }
}
