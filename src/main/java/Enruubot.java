import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Enruubot extends TelegramLongPollingBot {

    String bil205 = "NESNE YoNELiMLi PROGRAMLAMA I\nKASIM BARIKTABASOV\nMFFB 523\n",
            bil301 = "PROGRAMLAMA DiLLERi III\nBAKIT SARSEMBAYEV\nMFFB 523",
            bil303 = "YAZILIM MuHENDiSLigi I\nCINARA CUMABAYEVA\nMFFB 524",
            bil305 = "VERiTABANI YoNETiM SiSTEMLERi I\nBAKIT SARSEMBAYEV\nMFFB 525",
            bil307 = "MANTIKSAL TASARIM\nMEHMET SELiM ELMALI\nMFFB 524",
            bil309 = "VERi iLETisiMi VE BiLGiSAYAR AgLARI\nAYCARKIN SAiT KIZI\nMFFB 525",
            bil311 = "YAPAY ZEKA VE UZMAN SiSTEMLER\nRAYIMBEK SULTANOV\nMFFB 523",
            bil371 = "WEB YAZILIMLARI TASARIMI\nKASIM BARIKTABASOV\nMFFB 321",
            bil373 = "GENEL AMAcLi MODELLEME SiSTEMi\nAYCARKIN SAiT KIZI\nMFFB 412";

    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        SendMessage msg = new SendMessage();
        String result = "Done";


        String[] commandArray = command.split("\n");
        if (commandArray[0].equals("/add") && commandArray.length == 1) {
            msg.setText("Use this command to add info and/or task to class in the form command, class name, task, date each in new line\n" +
                    "Ex:\n" +
                    "/add\n" +
                    "bil300\n" +
                    "To finish project N\n" +
                    "25-01-2020");
        } else if (commandArray[0].equals("/add") && commandArray.length == 4) {
            switch (commandArray[1]) {
                case "bil205" : bil205 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                case "bil301" : bil301 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                case "bil303" : bil303 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                case "bil305" : bil305 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                case "bil307" : bil307 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                case "bil309" : bil309 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                case "bil311" : bil311 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                case "bil371" : bil371 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                case "bil373" : bil373 += "\nTask until " + commandArray[3] + " is " + commandArray[2];
                default: result = "0";
            }
            if (result.equals("0")) {
                msg.setText(commandArray[1] + " does not exist");
            }
        } else if (commandArray[0].equals("/add")) {
            msg.setText("Please, follow this form:\n" +
                    "command:      /add\n" +
                    "class name:   bil300\n" +
                    "task to do:   To finish project N1\n" +
                    "deadline:     25-01-2020");
        } else {
            switch (command) {
                case "/start" : msg.setText("Welcome to BM-3 bot-recorder for homeworks\nUse commands to add new and/or to see old tasks"); break;
                //case "/add" : msg.setText("")
                case "/bil205" : msg.setText(bil205); break;
                case "/bil301" : msg.setText(bil301); break;
                case "/bil303" : msg.setText(bil303); break;
                case "/bil305" : msg.setText(bil305); break;
                case "/bil307" : msg.setText(bil307); break;
                case "/bil309" : msg.setText(bil309); break;
                case "/bil311" : msg.setText(bil311); break;
                case "/bil371" : msg.setText(bil371); break;
                case "/bil373" : msg.setText(bil373); break;
            }
        }

        msg.setChatId(update.getMessage().getChatId());

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        /*
        String command = update.getMessage().getText();

        SendMessage msg = new SendMessage();

        if (command.equals("/myname")) {
            System.out.println(update.getMessage().getFrom().getFirstName());
            msg.setText(update.getMessage().getFrom().getFirstName());
        } else if (command.equals("/mylastname")) {
            if (update.getMessage().getFrom().getLastName().isEmpty()) {
                msg.setText("You have no lastname");
            } else {
                msg.setText(update.getMessage().getFrom().getLastName());
            }
        } else if (command.equals("/myfullname")) {
            if (update.getMessage().getFrom().getLastName().isEmpty()) {
                msg.setText(update.getMessage().getFrom().getFirstName());
            } else {
                msg.setText(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName());
            }
        }
        msg.setChatId(update.getMessage().getChatId());
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/
    }

    public String getBotUsername() {
        return "enruubot";
    }

    public String getBotToken() {
        return "1070759418:AAGqIcvEke1SuOfvdWn7nBQKE5Nn4sxbdPM";
    }
}
