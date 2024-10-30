package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;


public class lastTask extends TelegramLongPollingBot {
    public lastTask() {
        super("7340475513:AAErxw3bBdIZ7yJlDCW87fodZ-A1VhDRIfg");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var text = update.getMessage().getText();

        try {
            if (text.equals("/start")) {
                sendMessage(chatId,"Hello!");
            } else if (text.equals("btc")) {
                sendPicture(chatId, "Bitcoin.png");
                sendPrice(chatId, "BTC");
            } else if (text.equals("eth")) {
                sendPicture(chatId, "Ethereum.png");
                sendPrice(chatId, "ETH");
            } else if (text.equals("doge")) {
                sendPicture(chatId, "Dogecoin.png");
                sendPrice(chatId, "DOGE");
            } else if (text.equals("/all")) {
                sendPrice(chatId, "BTC");
                sendPrice(chatId, "ETH");
                sendPrice(chatId, "DOGE");
            } else if (text.matches("^(btc|eth|doge) \\d+$")) {
                String[] parts = text.split(" ");
                String currency = parts[0];
                int amount = Integer.parseInt(parts[1]);

                var price = CryptoPrice.spotPrice(currency.toUpperCase()).getAmount().doubleValue();

                double cryptoAmount = Math.floor(amount / price);

                sendMessage(chatId, "На " + amount + " USD можна купити:\n " + currency.toUpperCase() + ": " + cryptoAmount);

                System.out.println("Amount: " + (amount));
                System.out.println("Currency: " + currency);

            } else {
                sendMessage(chatId, "Unknown command!");
            }

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    void sendPrice(long chatId, String name) throws Exception {
        var price = CryptoPrice.spotPrice(name);
        sendMessage(chatId, name + " price: " + price.getAmount().doubleValue());
    }

    void sendPicture(long chatId, String name) throws Exception {
        var photo = getClass().getClassLoader().getResourceAsStream(name);
        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, name));
        execute(message);
    }

    void sendMessage(long chatId, String text) throws Exception {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        execute(message);
    }

    @Override
    public String getBotUsername() {
        return "startbot1234_bot";
    }
}





//