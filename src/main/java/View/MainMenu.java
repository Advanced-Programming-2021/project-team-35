package View;

import Controller.DuelController;
import Controller.LoginController;
import Model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    private boolean invalidCommand;

    public void run(Scanner scanner) {
        String input;

        while (true) {
            invalidCommand = true;
            input = scanner.nextLine();
            showMenu(getCommandMatcher(input, "^menu show-current$"));
            enterMenu(getCommandMatcher(input, "^menu enter (\\S+)$"), scanner);
            newduel(getCommandMatcher(input, "duel -new -second-player (\\S+) -rounds (\\d+)"),scanner);

            if (input.equals("user logout") || input.equals("menu exit")) {
                break;
            }
            if (invalidCommand) {
                System.out.println("invalid command");
            }
        }
    }

    protected void showMenu(Matcher matcher) {
        if (matcher.find()) {
            invalidCommand = false;
            System.out.println("Main Menu");
        }
    }

    public void enterMenu(Matcher matcher, Scanner scanner) {
        if (matcher.find()) {
            invalidCommand = false;
            String menuName = matcher.group(1);
            if (menuName.equals("Login")) {
                LoginView loginView = new LoginView();
                loginView.run();
            } else if (menuName.equals("Duel")) {

                DuelView duelView = new DuelView();
                duelView.run(scanner);
            } else if (menuName.equals("Deck")) {
                DeckView deckView = DeckView.getInstance();
                deckView.run(scanner);
            } else if (menuName.equals("Scoreboard")) {
                ScoreBoardView scoreBoardView = ScoreBoardView.getInstance();
                scoreBoardView.run(scanner);
            } else if (menuName.equals("Profile")) {
                ProfileView profileView = ProfileView.getInstance();
                profileView.run(scanner);
            } else if (menuName.equals("Shop")) {
                ShopView shopView = ShopView.getInstance();
                shopView.run(scanner);
            } else if (menuName.equals("ImportAndExport")) {
                ImportAndExport importAndExport = ImportAndExport.getInstance();
                importAndExport.run(scanner);
            } else {
                System.out.println("invalid command");
            }
        }
    }

    protected Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    private void newduel(Matcher matcher,Scanner scanner) {
        if (matcher.find()) {
            String secondPlayerUserName = matcher.group(1);
            int round = Integer.parseInt(matcher.group(2));
            if (!User.isUserWithThisUsernameExists(secondPlayerUserName))
                System.out.println("there is no player with this username");
        else{
                User secondUser = User.getUserByUsername(secondPlayerUserName);
                if (LoginController.user.getActiveDeck() == null)
                    System.out.println(LoginController.user.getUsername() + " has no active deck");
                else if (secondUser.getActiveDeck() == null)
                    System.out.println(secondUser.getUsername() + " has no active deck");
                else if(round==3||round==1){
                    DuelController duelController=new DuelController();
                    duelController.run(scanner);
                }else System.out.println("number of rounds is not supported");
            }
        }
    }


}