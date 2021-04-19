package Controller;

import Model.Monster;
import Model.Spell;
import Model.User;

public class LoginController {
    public User user;

    public String createUser(String username, String nickname, String password) {
        if (User.isUserWithThisUsernameExists(username)) {
            return "user with username " + username + " already exists";
        } else if (User.isUserWithThisNicknameExists(nickname)) {
            return "user with nickname " + nickname + " already exists";
        } else {
            User newUser = new User(username, nickname, password);
            return "user created successfully!";
        }
    }

    public String login(String username, String password) {
        if (!User.isUserWithThisUsernameExists(username)) {
            return "Username and password didn't match!";
        } else if (!User.getUserByUsername(username).getPassword().equals(password)) {
            return "Username and password didn't match!";
        } else {
            user = User.getUserByUsername(username);
            createCard();
            return "user logged in successfully!";
        }
    }

    public void createCard() {
        createMonster();
        createSpell();
        createTrap();
    }

    public void createMonster() {
        Monster monster = new Monster("Texchanger", "Once per turn, when your monster is " +
                "targeted for an attack: You can negate that attack, then Special " +
                "Summon 1 Cyberse Normal Monster from your hand, Deck, or GY.", "Effect", 200,
                "monster", 100, 100, "Cyberse", "Dark", 1);
        Monster monster1 = new Monster("Scanner", "Once per turn, you can select 1 of your opponent's " +
                "monsters that is removed from play. Until the End Phase, this card's name is treated as the selected " +
                "monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this" +
                " card is removed from the field while this effect is applied, remove it from play.", "Effect"
                , 8000, "monster", 0, 0, "Machine", "LIGHT", 1);
        Monster monster2 = new Monster("Marshmallon", "Cannot be destroyed by battle." +
                " After damage calculation, if this card was attacked, " +
                "and was face-down at the start of the Damage Step: " +
                "The attacking player takes 1000 damage.", "Effect", 700,
                "monster", 500, 300, "Fairy", "LIGHT", 3);
        Monster monster3 = new Monster("Man-Eater Bug", "FLIP: Target 1 monster on the field;" +
                " destroy that target.", "Effect", 600, "monster", 600, 450
                , "Insect", "EARTH", 2);
        Monster monster4 = new Monster("Curtain of the dark ones", "A curtain that a spellcaster made," +
                " it is said to raise a dark power.", "Normal", 700, "monster", 500, 600
                , "Spellcaster", "DARK", 2);
        Monster monster5 = new Monster("Haniwa", "An earthen figure that protects the tomb of an ancient ruler.",
                "Normal", 600, "monster", 500, 500
                , "Rock", "EARTH", 2);
        Monster monster6 = new Monster("Battle OX", "A monster with tremendous power," +
                " it destroys enemies with a swing of its axe.",
                "Normal", 2900, "monster", 1000, 1700
                , "Beast-Warrior", "EARTH", 4);
        Monster monster7 = new Monster("Wattkid", "A creature that electrocutes opponents with bolts of lightning.",
                "Normal", 1300, "monster", 500, 1000
                , "Thunder", "LIGHT", 3);
        Monster monster8 = new Monster("Baby dragon", "Much more than just a child, this dragon is gifted with untapped power.",
                "Normal", 1600, "monster", 700, 1200
                , "Dragon", "WIND", 3);
        Monster monster9 = new Monster("Crawling dragon", "This weakened dragon can no longer fly," +
                " but is still a deadly force to be reckoned with.",
                "Normal", 3900, "monster", 1400, 1600
                , "Dragon", "EARTH", 5);
        Monster monster10 = new Monster("Bitron", "A new species found in electronic space." +
                " There's not much information on it.",
                "Normal", 1000, "monster", 200, 2000
                , "Cyberse", "EARTH", 2);
        Monster monster11 = new Monster("Hero of the east", "Feel da strength ah dis sword-swinging" +
                " samurai from da Far East.",
                "Normal", 1700, "monster", 1000, 1100
                , "Warrior", "EARTH", 3);
        Monster monster12 = new Monster("Warrior Dai Grepher", "The warrior who can manipulate dragons." +
                " Nobody knows his mysterious past.",
                "Normal", 3400, "monster", 1600, 1700
                , "Warrior", "EARTH", 4);
        Monster monster13 = new Monster("Silver Fang", "A snow wolf that's beautiful to the eye," +
                " but absolutely vicious in battle.",
                "Normal", 1700, "monster", 800, 1200
                , "Beast", "EARTH", 3);
        Monster monster14 = new Monster("Crab Turtle", "This monster can only be Ritual Summoned with the Ritual Spell Card," +
                "\"Turtle Oath\"." +
                " You must also offer monsters whose total Level Stars equal 8 or more as a Tribute from the field or your hand.",
                "Ritual", 10200, "monster", 2500, 2550
                , "Aqua", "WATER", 8);
        Monster monster15 = new Monster("The Calculator", "The ATK of this card is the combined" +
                " Levels of all face-up monsters you control x 300.",
                "Effect", 8000, "monster", 0, 0
                , "Thunder", "LIGHT", 2);
        Monster monster16 = new Monster("Leotron", "A territorial electronic monster that guards its own domain.",
                "Normal", 8000, "monster", 0, 2000
                , "Cyberse", "EARTH", 4);
        Monster monster17 = new Monster("Feral Imp", "A playful little fiend that lurks in the dark," +
                " waiting to attack an unwary enemy.",
                "Normal", 2800, "monster", 1400, 1300
                , "Fiend", "DARK", 4);
        Monster monster18 = new Monster("Command Knight", "All Warrior-Type monsters you control gain 400 ATK." +
                " If you control another monster," +
                " monsters your opponent controls cannot target this card for an attack.",
                "Effect", 2100, "monster", 1000, 1000
                , "Warrior", "Fire", 4);
        Monster monster19 = new Monster("Fireyarou", "A malevolent creature wrapped in flames" +
                " that attacks enemies with intense fire.",
                "Normal", 2500, "monster", 1000, 1300
                , "Pyro", "FIRE", 4);


    }

    public void createSpell() {
        // type : icon(property)
        Spell spell = new Spell("Monster Reborn","Target 1 monster in either GY; Special Summon it."
               ,"Normal",2500,"spell","Limited");
        Spell spell1 = new Spell("Terraforming","Add 1 Field Spell from your Deck to your hand."
                ,"Normal",2500,"spell","Limited");
        Spell spell2 = new Spell("Pot of Greed","Draw 2 cards."
                ,"Normal",2500,"spell","Limited");
        Spell spell3 = new Spell("Raigeki","Destroy all monsters your opponent controls."
                ,"Normal",2500,"spell","Limited");
        Spell spell4 = new Spell("Raigeki","Destroy all monsters your opponent controls."
                ,"Normal",2500,"spell","Limited");
    }

    public void createTrap() {

    }

}