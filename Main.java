package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws Exception {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new lastTask());
    }
}

//startbot1234_bot
//7340475513:AAErxw3bBdIZ7yJlDCW87fodZ-A1VhDRIfg