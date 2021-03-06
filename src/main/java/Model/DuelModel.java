package Model;

import Controller.*;
import View.DuelView;
import View.GraveYard;
import View.MainMenu;
import View.StartDuelView;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuelModel {
    private final ArrayList<Integer> lifePoints;
    private final ArrayList<String> usernames;
    public int turn = 0;
    public Card monsterSetOrSummonInThisTurn;
    public int thePlaceOfMonsterSetOrSummonInThisTurn;
    public boolean[] setPosition = new boolean[5];
    public Card monsterFlipSummonOrNormalSummonForTrapHole = null;
    public Card monsterSummonForEffectOfSomeTraps = null;
    ArrayList<LinkedHashMap<Card, Boolean>> spellOrTrapActivated;
    private ArrayList<ArrayList<Card>> selectedCards;
    private ArrayList<HashMap<Card, String>> detailOfSelectedCard;
    private ArrayList<ArrayList<Card>> playersCards;
    private ArrayList<ArrayList<Card>> monstersInField;
    private ArrayList<ArrayList<Card>> spellsAndTrapsInFiled;
    private ArrayList<ArrayList<String>> monsterCondition;
    private ArrayList<ArrayList<String>> spellAndTrapCondition;
    private ArrayList<ArrayList<Card>> graveyard;
    private ArrayList<ArrayList<Card>> handCards;
    private ArrayList<ArrayList<Card>> field;
    private ArrayList<Card> borrowCards;
    private ArrayList<String> conditionOfBorrowCards;
    private ArrayList<HashMap<Card, Integer>> swordsCard;
    private ArrayList<ArrayList<Card>> supplySquadCards;
    private ArrayList<ArrayList<Card>> monsterDestroyedInThisTurn;
    private ArrayList<ArrayList<Card>> spellAbsorptionCards;
    private ArrayList<ArrayList<Card>> messengerOfPeace;
    private ArrayList<ArrayList<Card>> spellsAndTarpsSetInThisTurn;
    private ArrayList<ArrayList<Boolean>> spellZoneActivate;
    private ArrayList<ArrayList<Card>> activatedMonsterEffects;
    private HashMap<Card, Integer> cardsInsteadOfScanners;
    private ArrayList<HashMap<Card, Card>> equipSpells;


    public DuelModel(String playerUsername, String opponentUsername) {
        lifePoints = new ArrayList<>();
        int lifePointUser = 8000;
        int lifePointOpponent = 8000;
        lifePoints.add(lifePointUser);
        lifePoints.add(lifePointOpponent);
        usernames = new ArrayList<>();
        usernames.add(playerUsername);
        usernames.add(opponentUsername);
        someNewFieldsForStartDuel(playerUsername, opponentUsername);
        otherNewFileForStartDuel();
    }

    public void someNewFieldsForStartDuel(String playerUsername, String opponentUsername) {
        User user = User.getUserByUsername(playerUsername);
        assert user != null;
        Deck activeDeck = user.getActiveDeck();
        ArrayList<Card> cardsInPlayerActiveDeck = activeDeck.getCardsMain();
        Collections.shuffle(cardsInPlayerActiveDeck);
        playersCards = new ArrayList<>();
        ArrayList<Card> playerCard1 = new ArrayList<>();
        ArrayList<Card> playerCard2 = new ArrayList<>();
        playersCards.add(playerCard1);
        playersCards.add(playerCard2);
        for (Card card : cardsInPlayerActiveDeck) {
            playersCards.get(0).add(card);
        }
        User opponentUser = User.getUserByUsername(opponentUsername);
        assert opponentUser != null;
        Deck activeOpponentDeck = opponentUser.getActiveDeck();
        ArrayList<Card> cardsInOpponentActiveDeck = activeOpponentDeck.getCardsMain();
        Collections.shuffle(cardsInOpponentActiveDeck);
        for (Card card : cardsInOpponentActiveDeck) {
            playersCards.get(1).add(card);
        }
        selectedCards = new ArrayList<>();
        ArrayList<Card> selectCard1 = new ArrayList<>();
        ArrayList<Card> selectCard2 = new ArrayList<>();
        selectCard2.add(null);
        selectCard1.add(null);
        selectedCards.add(selectCard1);
        selectedCards.add(selectCard2);
        monstersInField = new ArrayList<>();
        ArrayList<Card> monstersInField1 = new ArrayList<>();
        ArrayList<Card> monstersInField2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            monstersInField1.add(null);
            monstersInField2.add(null);
        }
        monstersInField.add(monstersInField1);
        monstersInField.add(monstersInField2);
        spellsAndTrapsInFiled = new ArrayList<>();
        ArrayList<Card> spellsAndTraps1 = new ArrayList<>();
        ArrayList<Card> spellsAndTraps2 = new ArrayList<>();
        ArrayList<String> spellAndTrapCondition1 = new ArrayList<>();
        ArrayList<String> spellAndTrapCondition2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            spellsAndTraps1.add(null);
            spellsAndTraps2.add(null);
            spellAndTrapCondition1.add(null);
            spellAndTrapCondition2.add(null);
        }
        spellsAndTrapsInFiled.add(spellsAndTraps1);
        spellsAndTrapsInFiled.add(spellsAndTraps2);
        monsterCondition = new ArrayList<>();
        ArrayList<String> monsterCondition1 = new ArrayList<>();
        ArrayList<String> monsterCondition2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            monsterCondition1.add(null);
            monsterCondition2.add(null);
        }
        monsterCondition.add(monsterCondition1);
        monsterCondition.add(monsterCondition2);
        spellAndTrapCondition = new ArrayList<>();
        spellAndTrapCondition.add(spellAndTrapCondition1);
        spellAndTrapCondition.add(spellAndTrapCondition2);
    }

    public void otherNewFileForStartDuel() {
        graveyard = new ArrayList<>();
        ArrayList<Card> graveyard1 = new ArrayList<>();
        ArrayList<Card> graveyard2 = new ArrayList<>();
        graveyard.add(graveyard1);
        graveyard.add(graveyard2);
        handCards = new ArrayList<>();
        field = new ArrayList<>();
        ArrayList<Card> field1 = new ArrayList<>();
        field1.add(null);
        field1.add(null);
        ArrayList<Card> field2 = new ArrayList<>();
        field2.add(null);
        field2.add(null);
        field.add(field1);
        field.add(field2);
        detailOfSelectedCard = new ArrayList<>();
        HashMap<Card, String> detailOfSelectedCard1 = new HashMap<>();
        HashMap<Card, String> detailOfSelectedCard2 = new HashMap<>();
        detailOfSelectedCard.add(detailOfSelectedCard1);
        detailOfSelectedCard.add(detailOfSelectedCard2);
        borrowCards = new ArrayList<>();
        conditionOfBorrowCards = new ArrayList<>();
        swordsCard = new ArrayList<>();
        HashMap<Card, Integer> swordsCard1 = new HashMap<>();
        HashMap<Card, Integer> swordsCard2 = new HashMap<>();
        swordsCard.add(swordsCard1);
        swordsCard.add(swordsCard2);
        monsterDestroyedInThisTurn = new ArrayList<>();
        ArrayList<Card> monsterDestroyedInThisTurn1 = new ArrayList<>();
        ArrayList<Card> monsterDestroyedInThisTurn2 = new ArrayList<>();
        monsterDestroyedInThisTurn.add(monsterDestroyedInThisTurn1);
        monsterDestroyedInThisTurn.add(monsterDestroyedInThisTurn2);
        supplySquadCards = new ArrayList<>();
        ArrayList<Card> supplySquadCards1 = new ArrayList<>();
        ArrayList<Card> supplySquadCards2 = new ArrayList<>();
        supplySquadCards.add(supplySquadCards1);
        supplySquadCards.add(supplySquadCards2);
        spellAbsorptionCards = new ArrayList<>();
        ArrayList<Card> spellAbsorptionCards1 = new ArrayList<>();
        ArrayList<Card> spellAbsorptionCards2 = new ArrayList<>();
        spellAbsorptionCards.add(spellAbsorptionCards1);
        spellAbsorptionCards.add(spellAbsorptionCards2);
        messengerOfPeace = new ArrayList<>();
        ArrayList<Card> messengerOfPeace1 = new ArrayList<>();
        ArrayList<Card> messengerOfPeace2 = new ArrayList<>();
        messengerOfPeace.add(messengerOfPeace1);
        messengerOfPeace.add(messengerOfPeace2);
        spellsAndTarpsSetInThisTurn = new ArrayList<>();
        ArrayList<Card> spellsAndTarpsSetInThisTurn1 = new ArrayList<>();
        ArrayList<Card> spellsAndTarpsSetInThisTurn2 = new ArrayList<>();
        spellsAndTarpsSetInThisTurn.add(spellsAndTarpsSetInThisTurn1);
        spellsAndTarpsSetInThisTurn.add(spellsAndTarpsSetInThisTurn2);
        ArrayList<ArrayList<Card>> activatedMonsterEffects = new ArrayList<>();
        ArrayList<Card> activatedMonsterEffects1 = new ArrayList<>();
        ArrayList<Card> activatedMonsterEffects2 = new ArrayList<>();
        activatedMonsterEffects.add(activatedMonsterEffects1);
        activatedMonsterEffects.add(activatedMonsterEffects2);
        cardsInsteadOfScanners = new HashMap<>();
        spellOrTrapActivated = new ArrayList<>();
        LinkedHashMap<Card, Boolean> spellOrTrapActivated1 = new LinkedHashMap<>();
        LinkedHashMap<Card, Boolean> spellOrTrapActivated2 = new LinkedHashMap<>();
        spellOrTrapActivated.add(spellOrTrapActivated1);
        spellOrTrapActivated.add(spellOrTrapActivated2);
        equipSpells = new ArrayList<>();
        HashMap<Card, Card> equipspellUser = new HashMap<>();
        HashMap<Card, Card> equipspellOpponent = new HashMap<>();
        equipSpells.add(equipspellUser);
        equipSpells.add(equipspellOpponent);
    }

    public ArrayList<LinkedHashMap<Card, Boolean>> getSpellOrTrapActivated() {
        return spellOrTrapActivated;
    }

    public void decreaseLifePoint(int decreaseLifePoint, int turn) {
        int lifePoint = lifePoints.get(turn);
        lifePoints.set(turn, lifePoint - decreaseLifePoint);
        DuelView.getInstance().showBoard();
    }

    public void increaseLifePoint(int increaseLifePoint, int turn) {
        int lifePoint = lifePoints.get(turn);
        lifePoints.set(turn, lifePoint + increaseLifePoint);
        DuelView.getInstance().showBoard();
    }

    public int getLifePoint(int turn) {
        return lifePoints.get(turn);
    }

    public void addBorrowCard(Card card, String condition) {
        borrowCards.add(card);
        conditionOfBorrowCards.add(condition);
        DuelView.getInstance().showBoard();
    }

    public ArrayList<Card> getBorrowCards() {
        return borrowCards;
    }

    public ArrayList<String> getConditionOfBorrowCards() {
        return conditionOfBorrowCards;
    }

    public void deleteBorrowCard() {
        borrowCards.clear();
        conditionOfBorrowCards.clear();
        DuelView.getInstance().showBoard();
    }

    public String getCreatorUsername(int turn) {
        return usernames.get(turn);
    }

    public ArrayList<Card> addCardToHand() {
        if (handCards.size() <= turn) {
            ArrayList<Card> handCardsPlayer = new ArrayList<>();
            handCards.add(handCardsPlayer);
            ArrayList<Card> firstCardsInPlayerHand = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                firstCardsInPlayerHand.add(playersCards.get(turn).get(playersCards.get(turn).size() - 1));
                playersCards.get(turn).remove(playersCards.get(turn).get(playersCards.get(turn).size() - 1));
            }
            handCards.get(turn).addAll(firstCardsInPlayerHand);
            return firstCardsInPlayerHand;
        } else {
            ArrayList<Card> cardAddedInPlayerHand = new ArrayList<>();
            handCards.get(turn).add(playersCards.get(turn).get(playersCards.get(turn).size() - 1));
            cardAddedInPlayerHand.add(playersCards.get(turn).get(playersCards.get(turn).size() - 1));
            playersCards.get(turn).remove(playersCards.get(turn).get(playersCards.get(turn).size() - 1));
            DuelView.getInstance().showBoard();
            return cardAddedInPlayerHand;
        }
    }

    public void addMonsterFromHandToGame(String condition, int index) {
        monstersInField.get(turn).set(index, selectedCards.get(turn).get(0));
        monsterCondition.get(turn).set(index, condition);
        deleteCardFromHand(getMonster(turn, index + 1));
        DuelView.getInstance().showBoard();
    }

    public void addSpellAndTrapFromHandToGame(String condition, int index) {
        spellsAndTrapsInFiled.get(turn).set(index, selectedCards.get(turn).get(0));
        spellAndTrapCondition.get(turn).set(index, condition);
        deleteCardFromHand(getSpellAndTrap(turn, index + 1));
        DuelView.getInstance().showBoard();
    }

    public void addMonsterCondition(int turn, int index, String condition) {
        monsterCondition.get(turn).set(index, condition);
    }

    public void changeAttackAndDefense(int place) {
        if (monsterCondition.get(turn).get(place).equals("OO"))
            monsterCondition.get(turn).set(place, "DO");
        else monsterCondition.get(turn).set(place, "OO");
        DuelView.getInstance().showBoard();
    }

    public ArrayList<ArrayList<Card>> getField() {
        return field;
    }

    public void setField(Card card) {
        field.get(turn).set(1, card);
    }

    public Card getFieldZoneCard(int turn) {
        return field.get(turn).get(0);
    }

    public ArrayList<ArrayList<Boolean>> getSpellZoneActivate() {
        return spellZoneActivate;
    }

    public void deleteMonster(int turn, int place) {
        monstersInField.get(turn).set(place, null);
        monsterCondition.get(turn).set(place, "");
        DuelView.getInstance().showBoard();
    }

    public void deleteSpellAndTrap(int turn, int place) {
        spellsAndTrapsInFiled.get(turn).set(place, null);
        spellAndTrapCondition.get(turn).set(place, "");
        DuelView.getInstance().showBoard();
    }

    public void activateSpell(int turn, int place) {

    }

    public void changeSetToFaceUp(int place) {

    }

    public ArrayList<Card> getGraveyard(int turn) {
        return graveyard.get(turn);
    }

    public Card getMonster(int turn, int place) {
        return monstersInField.get(turn).get(place - 1);
    }

    public String getMonsterCondition(int turn, int place) {
        return monsterCondition.get(turn).get(place - 1);
    }

    public Card getSpellAndTrap(int turn, int place) {
        return spellsAndTrapsInFiled.get(turn).get(place - 1);
    }

    public String getSpellAndTrapCondition(int turn, int place) {
        return spellAndTrapCondition.get(turn).get(place - 1);
    }

    public void changePositionOfSpellOrTrapCard(int turn, int place) {
        String[] condition = spellAndTrapCondition.get(turn).get(place - 1).split("/");
        if (condition[0].equals("H")) {
            spellAndTrapCondition.get(turn).set(place - 1, "O/" + place);
        }
        DuelView.getInstance().showBoard();
    }

    public void activeField(Card card) {
        if (field.get(1 - turn).get(0) != null)
            addCardToGraveyard(turn, field.get(1 - turn).get(0));
        if (field.get(turn).get(0) != null)
            addCardToGraveyard(turn, field.get(turn).get(0));
        DuelView.getInstance().showBoard();
        field.get(1 - turn).set(0, null);
        field.get(turn).set(0, card);
        DuelView.getInstance().showBoard();
    }

    public void addCardToGraveyard(int turn, Card card) {
        if (card.getCategory().equals("Monster")) {
            //???????????? ???????? ?????????? ?????? ???????? ???? ???????? ?????? ???????? ????????
            card.setAttackPower(Card.getCardByName(card.name).getAttackPower());
            card.setDefensePower(Card.getCardByName(card.name).getDefensePower());
            monsterDestroyedInThisTurn.get(turn).add(card);
            if (equipSpells.get(turn).containsValue(card)) {
                for (Card card1 : equipSpells.get(turn).keySet()) {
                    if (equipSpells.get(turn).get(card1).equals(card))
                        spellsAndTrapsInFiled.set(spellsAndTrapsInFiled.get(turn).indexOf(card1), null);
                    addCardToGraveyard(turn, card1);
                }
            }
        }
        if (card.getCategory().equals("Spell") && card.getCardType().equals("Equip")) {
            DuelController.getInstance().deActiveEquipSpell(equipSpells.get(turn).get(card), card.getName());
        }

        graveyard.get(turn).add(card);
        DuelView.getInstance().showBoard();
    }

    public void setSelectedCard(int turn, Card card, String condition) {
        if (selectedCards.get(turn).get(0) != null) {
            deSelectedCard();
        }
        selectedCards.get(turn).set(0, card);
        detailOfSelectedCard.get(turn).put(card, condition);
    }

    public void deSelectedCard() {
        selectedCards.get(turn).set(0, null);
    }

    public ArrayList<ArrayList<Card>> getHandCards() {
        return handCards;
    }

    public ArrayList<ArrayList<Card>> getPlayersCards() {
        return playersCards;
    }

    public ArrayList<ArrayList<Card>> getSelectedCards() {
        return selectedCards;
    }

    public ArrayList<HashMap<Card, String>> getDetailOfSelectedCard() {
        return detailOfSelectedCard;
    }

    public void getBoard() {
        DuelView.userUsernameLBL.setText("    " + usernames.get(turn));
        DuelView.opponentUsernameLBL.setText("    " + usernames.get(1 - turn));
        DuelView.userLifPointLBL.setText("    " + lifePoints.get(turn));
        DuelView.opponentLifPointLBL.setText("   " + lifePoints.get(1 - turn));
        ImageView imageView1;
        URL url = null;
        try {
            url = new File(Objects.requireNonNull(User.getUserByUsername(usernames.get(turn))).getProfileURL()).
                    toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        imageView1 = new ImageView(new Image(Objects.requireNonNull(url).toString()));
        DuelView.userProfile.setImage(imageView1.getImage());

        ImageView imageView2;
        URL url1 = null;
        try {
            url1 = new File(Objects.requireNonNull(User.getUserByUsername(usernames.get(1 - turn))).getProfileURL()).
                    toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        imageView2 = new ImageView(new Image(Objects.requireNonNull(url1).toString()));
        DuelView.opponentProfile.setImage(imageView2.getImage());

        ArrayList<String> board = new ArrayList<>();
        String handCardOpponent = "    ";
        String handCardUser = "    ";
        for (int i = 0; i < 8; i++) {
            ImageView image;
            handCardOpponent = handCardOpponent + "c    ";
            if (i < handCards.get(1 - turn).size()) {
                image = getUnknownCard();
                DuelView.hBoxS.getChildren().set(i, image);
                showUnKnownCard(image);
            } else {
                image = getEmptyCardForHand();
                DuelView.hBoxS.getChildren().set(i, image);
            }
        }
        for (int i = 0; i < 8; i++) {
            ImageView image;
            handCardUser = handCardUser + "c    ";
            if (i < handCards.get(turn).size()) {
                image = getCardImage(handCards.get(turn).get(i));
                DuelView.downHBoxS.getChildren().set(i, image);
                Card card = handCards.get(turn).get(i);
                showCard(image, card);
                image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (card.getCategory().equals("Monster")) {
                            if (!card.isHasSpecialSummon() && !card.getCardType().equals("Ritual")
                                    && card.getLevel() <= 4) {
                                normalSummonGraphic(card);
                            }
                        } else if (card.getCategory().equals("Spell")) {
                            setAndActivateSpellGraphic(card);
                        } else if (card.getCategory().equals("Trap")) {
                            setTrapGraphic(card);
                        }
                    }
                });
            } else {
                image = getEmptyCardForHand();
                DuelView.downHBoxS.getChildren().set(i, image);
            }
        }
        ArrayList<String> spellConditionOpponent = new ArrayList<>();
        ArrayList<String> spellConditionUser = new ArrayList<>();
        ArrayList<String> conditionMonsterOpponent = new ArrayList<>();
        ArrayList<String> conditionMonsterUser = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (monstersInField.get(1 - turn).get(i) != null) {
                if (monsterCondition.get(1 - turn).get(i).split("/")[0].equals("OO") ||
                        monsterCondition.get(1 - turn).get(i).split("/")[0].equals("DO")) {
                    ImageView image = getCardImage(monstersInField.get(1 - turn).get(i));
                    DuelView.hboxOpponentMonsterS.getChildren().set(i, image);
                    showCard(image, monstersInField.get(1 - turn).get(i));
                } else {
                    ImageView image = getUnknownCard();
                    DuelView.hboxOpponentMonsterS.getChildren().set(i, image);
                    showUnKnownCard(image);
                }
            } else {
                DuelView.hboxOpponentMonsterS.getChildren().set(i, getEmptyCardForBoard());
            }
            if (monstersInField.get(turn).get(i) != null) {
                int counter = i + 1;
                Card card = monstersInField.get(turn).get(i);
                conditionMonsterUser.add(monsterCondition.get(turn).get(i).split("/")[0]);
                if (monsterCondition.get(turn).get(i).split("/")[0].equals("OO") ||
                        monsterCondition.get(turn).get(i).split("/")[0].equals("DO")) {
                    ImageView image = getCardImage(monstersInField.get(turn).get(i));
                    DuelView.hboxMonsterS.getChildren().set(i, image);
                    showCard(image, monstersInField.get(turn).get(i));
                    int finalI = i;
                    image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (monsterCondition.get(turn).get(finalI).split("/")[0].equals("OO")) {
                                attack(finalI, monstersInField.get(turn).get(finalI), event);
                            }
                        }
                    });
                } else {
                    ImageView image1 = getUnknownCard();
                    DuelView.hboxMonsterS.getChildren().set(i, image1);
                    showCard(image1, monstersInField.get(turn).get(i));
                    image1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            flipSummonGraphic(card, counter);
                        }
                    });
                }
            } else {
                DuelView.hboxMonsterS.getChildren().set(i, getEmptyCardForBoard());
                conditionMonsterUser.add("E");
            }

            if (spellsAndTrapsInFiled.get(1 - turn).get(i) != null) {
                spellConditionOpponent.add(spellAndTrapCondition.get(1 - turn).get(i).split("/")[0]);
                if (spellAndTrapCondition.get(1 - turn).get(i).split("/")[0].equals("O")) {
                    ImageView image = getCardImage(spellsAndTrapsInFiled.get(1 - turn).get(i));
                    DuelView.hboxOpponenetSpellS.getChildren().set(i, image);
                    showCard(image, spellsAndTrapsInFiled.get(1 - turn).get(i));
                } else {
                    ImageView image1 = getUnknownCard();
                    DuelView.hboxOpponenetSpellS.getChildren().set(i, image1);
                    showUnKnownCard(image1);
                }
            } else {
                spellConditionOpponent.add("E");
                DuelView.hboxOpponenetSpellS.getChildren().set(i, getEmptyCardForBoard());
            }
            if (spellsAndTrapsInFiled.get(turn).get(i) != null) {
                spellConditionUser.add(spellAndTrapCondition.get(turn).get(i).split("/")[0]);
                if (spellAndTrapCondition.get(turn).get(i).split("/")[0].equals("O")) {
                    ImageView image = getCardImage(spellsAndTrapsInFiled.get(turn).get(i));
                    DuelView.hboxSpellS.getChildren().set(i, image);
                    showCard(image, spellsAndTrapsInFiled.get(turn).get(i));
                } else {
                    Card card = spellsAndTrapsInFiled.get(turn).get(i);
                    int counter = i + 1;
                    ImageView image1 = getUnknownCard();
                    DuelView.hboxSpellS.getChildren().set(i, image1);
                    showCard(image1, spellsAndTrapsInFiled.get(turn).get(i));
                    if (spellsAndTrapsInFiled.get(turn).get(i).getCategory().equals("Spell")) {
                        image1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                activeSpellCard(card, counter);
                            }
                        });
                    }
                }
            } else {
                spellConditionUser.add("E");
                DuelView.hboxSpellS.getChildren().set(i, getEmptyCardForBoard());
            }

        }
        DuelView.opponentBinS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showGraveYard();
                for (int i = 0; i < graveyard.get(1 - turn).size(); i++) {
                    GraveYard.graveYardHboxS.getChildren().set(i, getCardImage(graveyard.get(1 - turn).get(i)));
                }
            }
        });
        if (graveyard.get(1 - turn).

                size() > 0)
            DuelView.opponentBinS =

                    getCardImage(graveyard.get(1 - turn).

                            get(0));
        DuelView.userBinS.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showGraveYard();
                for (int i = 0; i < graveyard.get(turn).size(); i++) {
                    GraveYard.graveYardHboxS.getChildren().set(i, getCardImage(graveyard.get(turn).get(i)));
                }
            }
        });

        if (graveyard.get(turn).

                size() > 0)
            DuelView.opponentBinS =

                    getCardImage(graveyard.get(turn).

                            get(0));
        if (field.get(turn).

                get(0) != null)
            DuelView.userFieldS =

                    getCardImage(field.get(turn).

                            get(0));
        if (field.get(1 - turn).

                get(0) != null)
            DuelView.opponentFieldS =

                    getCardImage(field.get(1 - turn).

                            get(0));

        String spellFieldOfOpponent = "    " + spellConditionOpponent.get(3) + "    " + spellConditionOpponent.get(1) + "    " + spellConditionOpponent.get(0) + "    " + spellConditionOpponent.get(2) + "    " + spellConditionOpponent.get(4);
        String spellFieldUser = "    " + spellConditionUser.get(4) + "    " + spellConditionUser.get(2) + "    " + spellConditionUser.get(0) + "    " + spellConditionUser.get(1) + "    " + spellConditionUser.get(3);
        String monsterFieldUser = "    " + conditionMonsterUser.get(4) + "    " + conditionMonsterUser.get(2) + "    " + conditionMonsterUser.get(0) + "    " + conditionMonsterUser.get(1) + "    " + conditionMonsterUser.get(3);
//        String monsterFieldOpponent = "    " + conditionMonsterOpponent.get(3) + "    " + conditionMonsterOpponent.get(1) + "    " + conditionMonsterOpponent.get(0) + "    " + conditionMonsterOpponent.get(2) + "    " + conditionMonsterOpponent.get(4);
        board.add(usernames.get(1 - turn) + ":" + lifePoints.get(1 - turn));
        board.add(handCardOpponent);
        board.add(String.valueOf(playersCards.get(1 - turn).

                size()));
        board.add(spellFieldOfOpponent);
        //   board.add(monsterFieldOpponent);
        if (field.get(1 - turn).

                get(0) == null)
            board.add(graveyard.get(1 - turn).

                    size() + "                        " + "E");
        else board.add(graveyard.get(1 - turn).

                size() + "                        " + "O");
        board.add("-------------------------------");
        if (field.get(turn).

                get(0) == null)
            board.add("E" + "                        " + graveyard.get(turn).

                    size());
        else board.add("O" + "                        " + graveyard.get(1 - turn).

                size());
        board.add(monsterFieldUser);
        board.add(spellFieldUser);
        board.add(handCardUser);
        board.add(String.valueOf(playersCards.get(turn).

                size()));
        board.add(usernames.get(turn) + ":" + lifePoints.get(turn));

    }

    public void attack(int i, Card card, MouseEvent event) {
        if (DuelView.currentPhase.equals("battlePhase")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Choose attack");
            alert.setContentText("choose attack to a card or direct?");
            ButtonType attack1Button = new ButtonType("attack 1");
            ButtonType attack2Button = new ButtonType("attack 2");
            ButtonType attack3Button = new ButtonType("attack 3");
            ButtonType attack4Button = new ButtonType("attack 4");
            ButtonType attack5Button = new ButtonType("attack 5");
            ButtonType attackDirectButton = new ButtonType("direct attack");
            ButtonType cancelButton = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(attack1Button, attack2Button, attack3Button
                    , attack4Button, attack5Button, attackDirectButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == attack1Button) {
                    setSelectedCard(turn, card, "My/OO/" + i + 1);
                    Pattern pattern1 = Pattern.compile("^attack (\\d+)$");
                    Matcher matcher1 = pattern1.matcher("attack 1");
                    if (matcher1.find()) {
                        BattlePhaseController.getInstance().attack(matcher1);
                    }
                    selectedCards.get(turn).set(0, null);
                } else if (type == attack2Button) {
                    setSelectedCard(turn, card, "My/OO/" + i + 1);
                    Pattern pattern1 = Pattern.compile("^attack (\\d+)$");
                    Matcher matcher1 = pattern1.matcher("attack 2");
                    if (matcher1.find()) {
                        BattlePhaseController.getInstance().attack(matcher1);
                    }
                    selectedCards.get(turn).set(0, null);
                } else if (type == attack3Button) {
                    setSelectedCard(turn, card, "My/OO/" + i + 1);
                    Pattern pattern1 = Pattern.compile("^attack (\\d+)$");
                    Matcher matcher1 = pattern1.matcher("attack 3");
                    if (matcher1.find()) {
                        BattlePhaseController.getInstance().attack(matcher1);
                    }
                    selectedCards.get(turn).set(0, null);
                } else if (type == attack4Button) {
                    setSelectedCard(turn, card, "My/OO/" + i + 1);
                    Pattern pattern1 = Pattern.compile("^attack (\\d+)$");
                    Matcher matcher1 = pattern1.matcher("attack 4");
                    if (matcher1.find()) {
                        BattlePhaseController.getInstance().attack(matcher1);
                    }
                    selectedCards.get(turn).set(0, null);
                } else if (type == attack5Button) {
                    setSelectedCard(turn, card, "My/OO/" + i + 1);
                    Pattern pattern1 = Pattern.compile("^attack (\\d+)$");
                    Matcher matcher1 = pattern1.matcher("attack 5");
                    if (matcher1.find()) {
                        BattlePhaseController.getInstance().attack(matcher1);
                    }
                    selectedCards.get(turn).set(0, null);
                } else if (type == attackDirectButton) {
                    setSelectedCard(turn, card, "My/OO/" + i + 1);
                    Pattern pattern1 = Pattern.compile("^attack direct$");
                    Matcher matcher1 = pattern1.matcher("attack direct");
                    if (matcher1.find()) {
                        System.out.println(BattlePhaseController.getInstance().directAttack(matcher1));
                    }
                }
                try {
                    checkEndGame(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void checkEndGame(MouseEvent event) throws IOException {
        if (lifePoints.get(0) <= 0 || lifePoints.get(1) <= 0) {
            DuelView.stage.close();
            DeckController.getInstance().showSceneEndGame(event, checkWinner());
        }
    }


    public String checkWinner() {
        User secondUser = User.getUserByUsername(usernames.get(1));
        User firstUser = User.getUserByUsername(usernames.get(0));
        if (getLifePoint(0) <= 0) {
            secondUser.setScore(1000);
            secondUser.increaseCoins(1000 + getLifePoint(1));
            firstUser.increaseCoins(100);
            LoginAndSignUpController.saveChangesToFileByUser(secondUser);
            LoginAndSignUpController.saveChangesToFileByUser(firstUser);
            return (secondUser.getUsername() + " won the game and the score is: 1000 - 0");
        } else if (getLifePoint(1) <= 0) {
            firstUser.setScore(1000);
            firstUser.increaseCoins(1000 + getLifePoint(1));
            secondUser.increaseCoins(100);
            LoginAndSignUpController.saveChangesToFileByUser(secondUser);
            LoginAndSignUpController.saveChangesToFileByUser(firstUser);
            return (firstUser.getUsername() + " won the game and the score is: 1000 - 0");
        } else if (turn == 0) {
            secondUser.setScore(1000);
            secondUser.increaseCoins(1000 + getLifePoint(1));
            firstUser.increaseCoins(100);
            LoginAndSignUpController.saveChangesToFileByUser(secondUser);
            LoginAndSignUpController.saveChangesToFileByUser(firstUser);
            return (secondUser.getUsername() + " won the game and the score is: 1000 - 0");
        } else {
            firstUser.setScore(1000);
            firstUser.increaseCoins(1000 + getLifePoint(1));
            secondUser.increaseCoins(100);
            LoginAndSignUpController.saveChangesToFileByUser(secondUser);
            LoginAndSignUpController.saveChangesToFileByUser(firstUser);
            return (firstUser.getUsername() + " won the game and the score is: 1000 - 0");
        }
    }

    public void showCard(ImageView image, Card card) {
        String descriptionOfCard = card.getDescription();
        image.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DuelView.showCardImage.setImage(getCardImage(card).getImage());
                if (card.getCategory().equals("Monster")) {
                    DuelView.specificationsOfCard.setText("Level: " + card.getLevel() + "\n" +
                            "AttackPower: " + card.getAttackPower() + "\n" + "DefensePower: "
                            + card.getDefensePower() + "\n" + descriptionOfCard);
                } else {
                    DuelView.specificationsOfCard.setText(descriptionOfCard);
                }
            }
        });

        image.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DuelView.showCardImage.setImage(null);
                DuelView.specificationsOfCard.setText("");
            }
        });
    }

    public ImageView getEmptyCardForBoard() {
        URL url = null;
        try {
            url = new File("src/main/resource/Icons/100201Parts1.dds.png").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = new Image(Objects.requireNonNull(url).toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(110);
        imageView.setFitWidth(90);
        return imageView;
    }

    public void showGraveYard() {
        URL url = null;
        try {
            url = new File("src/main/java/FXMLFiles/GraveYard.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Parent root = null;
        try {
            assert url != null;
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage mainMenuStage = new Stage();
        mainMenuStage.setTitle("graveyard");
        assert root != null;
        mainMenuStage.setScene(new Scene(root, 1320, 700));
        mainMenuStage.show();
    }

    public ImageView getEmptyCardForHand() {
        URL url = null;
        try {
            url = new File("src/main/resource/Icons/100401Parts1.dds.png").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = new Image(Objects.requireNonNull(url).toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(110);
        imageView.setFitWidth(90);
        return imageView;
    }

    public void normalSummonGraphic(Card card) {
        if (DuelView.currentPhase.equals("mainPhase1") || DuelView.currentPhase.equals("mainPhase2")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Choose Set Or Summon");
            alert.setContentText("Do You Want Set Or Summon This Card?");
            ButtonType summonButton = new ButtonType("Summon");
            ButtonType setButton = new ButtonType("Set");
            ButtonType cancelButton = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(summonButton, setButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == summonButton) {
                    setSelectedCard(turn, card, "Hand");
                    String response = MainPhaseController.getInstance().summon();
                    errorOrSuccessLBLForSetAndSummon(response);
                } else if (type == setButton) {
                    setSelectedCard(turn, card, "Hand");
                    String response = MainPhaseController.getInstance().set();
                    errorOrSuccessLBLForSetAndSummon(response);
                }
            });
        }
    }

    public void activeSpellCard(Card card, int i) {
        if (DuelView.currentPhase.equals("mainPhase1") || DuelView.currentPhase.equals("mainPhase2")
                || DuelView.currentPhase.equals("battlePhase")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Choose Active");
            alert.setContentText("Do You Want Active This Card?");
            ButtonType activeButton = new ButtonType("Active");
            ButtonType cancelButton = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(activeButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == activeButton) {
                    setSelectedCard(turn, card, "My/H/" + i);
                    spellsAndTrapsInFiled.get(turn).set(i - 1, selectedCards.get(turn).get(0));
                    int i1 = i - 1;
                    spellAndTrapCondition.get(turn).set(i - 1, "O/" + i1);
                    errorOrSuccessLBLForSetAndSummon("activated successfully!");
                    getBoard();
                }
            });
        }
    }

    public void setAndActivateSpellGraphic(Card card) {
        if (DuelView.currentPhase.equals("mainPhase1") || DuelView.currentPhase.equals("mainPhase2")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Choose Set Or Activate");
            alert.setContentText("Do You Want Set Or Activate This Card?");
            ButtonType setButton = new ButtonType("Set");
            ButtonType activateButton = new ButtonType("Activate");
            ButtonType cancelButton = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(setButton, activateButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == setButton) {
                    setSelectedCard(turn, card, "Hand");
                    String response = MainPhaseController.getInstance().set();
                    errorOrSuccessLBLForSetAndSummon(response);
                } else if (type == activateButton) {
                    setSelectedCard(turn, card, "Hand");
                    String response = MainPhaseController.getInstance().activeSpellFromHand();
                    errorOrSuccessLBLForSetAndSummon(response);
                }
            });
        }
    }

    public void setTrapGraphic(Card card) {
        if (DuelView.currentPhase.equals("mainPhase1") || DuelView.currentPhase.equals("mainPhase2")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Choose Set");
            alert.setContentText("Do You Want Set This Card?");
            ButtonType setButton = new ButtonType("Set");
            ButtonType cancelButton = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(setButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == setButton) {
                    setSelectedCard(turn, card, "Hand");
                    String response = MainPhaseController.getInstance().set();
                    errorOrSuccessLBLForSetAndSummon(response);
                }
            });
        }
    }


    public void flipSummonGraphic(Card card, int i) {
        if (DuelView.currentPhase.equals("mainPhase1") || DuelView.currentPhase.equals("mainPhase2")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do You Want FlipSummon This Card?");
            ButtonType summonButton = new ButtonType("FlipSummon");
            ButtonType cancelButton = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(summonButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == summonButton) {
                    setSelectedCard(turn, card, "My/DH/" + i);
                    String response = MainPhaseController.getInstance().flipSummon();
                    errorOrSuccessLBLForSetAndSummon(response);
                    getBoard();
                }
            });
        }
    }

    public void errorOrSuccessLBLForSetAndSummon(String text) {
        if (!text.equals("summoned successfully") && !text.equals("set successfully")
                && !text.equals("flip summoned successfully") && !text.equals("activated successfully!")) {
            DuelView.informationLBL.setTextFill(Color.RED);
        }
        DuelView.informationLBL.setText(text);
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(3));
        fadeTransition.setNode(DuelView.informationLBL);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    public void showUnKnownCard(ImageView image) {
        image.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DuelView.showCardImage.setImage(image.getImage());
            }
        });
        image.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DuelView.showCardImage.setImage(null);
            }
        });
    }

    public ImageView getCardImage(Card card) {
        if (card.getCategory().equals("Monster")) {
            ImageView imageView;

            URL url = null;
            try {
                url = new File("src/main/resource/Monsters/" + card.getName() + ".jpg").toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            imageView = new ImageView(new Image(Objects.requireNonNull(url).toString()));
            imageView.setFitWidth(90);
            imageView.setFitHeight(110);
            return imageView;
        } else {
            ImageView imageView;

            URL url = null;
            try {
                url = new File("src/main/resource/SpellTrap/" + card.getName() + ".jpg").toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            imageView = new ImageView(new Image(Objects.requireNonNull(url).toString()));
            imageView.setFitWidth(90);
            imageView.setFitHeight(110);

            return imageView;
        }

    }

    public ImageView getUnknownCard() {
        ImageView imageView;

        URL url = null;
        try {
            url = new File("src/main/resource/Monsters/Unknown.jpg").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        imageView = new ImageView(new Image(Objects.requireNonNull(url).toString()));
        imageView.setFitWidth(90);
        imageView.setFitHeight(110);
        return imageView;
    }


    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public ArrayList<ArrayList<Card>> getMonstersInField() {
        return monstersInField;
    }

    public ArrayList<ArrayList<Card>> getSpellsAndTrapsInFiled() {
        return spellsAndTrapsInFiled;
    }

    public void setMonsterSetOrSummonInThisTurn(Card monsterSetOrSummonInThisTurn, int place) {
        this.monsterSetOrSummonInThisTurn = monsterSetOrSummonInThisTurn;
        this.thePlaceOfMonsterSetOrSummonInThisTurn = place;
    }

    public Card getMonsterSetOrSummonInThisTurn() {
        return monsterSetOrSummonInThisTurn;
    }

    public void deleteMonsterSetOrSummonInThisTurn() {
        monsterSetOrSummonInThisTurn = null;
    }

    public void flipSummon(int place) {
        monsterCondition.get(turn).add(place, "OO/" + place + 1);
        deSelectedCard();
    }

    public void changeSpellAndTrapCondition(int turn, int place, String condition) {
        spellAndTrapCondition.get(turn).set(place - 1, condition);
    }

    public void deleteCardFromHand(Card card) {
        handCards.get(turn).remove(card);
    }

    public void deleteCardFromOpponentHand(Card card) {
        handCards.get(1 - turn).remove(card);
        Card.getCardByName(card.name).getDefensePower();
        DuelView.getInstance().showBoard();
    }

    public void deleteCardFromHandWithIndex(int index) {
        handCards.get(turn).remove(index);
        DuelView.getInstance().showBoard();
    }


    public void addMonsterFromGraveyardToGame(String condition, Card card, int index) {
        monstersInField.get(turn).set(index, card);
        monsterCondition.get(turn).set(index, condition);
        DuelView.getInstance().showBoard();
    }

    public void addCertainMonsterFromGraveyardToGame(int turn, String condition, int index, Card card) {
        monstersInField.get(turn).set(index, card);
        monsterCondition.get(turn).set(index, condition);
        DuelView.getInstance().showBoard();
    }

    public void addCertainSpellOrTrapFromGraveyardToGame(String condition, int index, Card card) {
        monstersInField.get(turn).set(index, card);
        monsterCondition.get(turn).set(index, condition);
    }

    public void deleteCardFromGraveyard(int turn, int indexOfCard) {
        graveyard.get(turn).remove(indexOfCard);
    }

    public void addCardFromDeckToHandInMiddleOfGame(int turn, Card card) {
        handCards.get(turn).add(card);
        playersCards.get(turn).remove(card);
        DuelView.getInstance().showBoard();
    }

    public void setSwordsCard(int turn, Card swordCard) {
        swordsCard.get(turn).put(swordCard, 0);
    }

    public void deleteSwordsCard(int turn, Card swordCard) {
        swordsCard.get(turn).remove(swordCard);
        int index = spellsAndTrapsInFiled.get(turn).indexOf(swordCard);
        deleteSpellAndTrap(turn, index);
        addCardToGraveyard(turn, swordCard);
    }

    public ArrayList<HashMap<Card, Integer>> getSwordsCard() {
        return swordsCard;
    }

    public ArrayList<ArrayList<Card>> getMonsterDestroyedInThisTurn() {
        return monsterDestroyedInThisTurn;
    }

    public void deleteMonstersDestroyedInThisTurn() {
        for (Card card : new ArrayList<>(monsterDestroyedInThisTurn.get(turn))) {
            monsterDestroyedInThisTurn.get(turn).remove(card);
        }
    }

    public void setSupplySquad(int turn, Card card) {
        supplySquadCards.get(turn).add(card);
    }

    public ArrayList<ArrayList<Card>> getSupplySquadCards() {
        return supplySquadCards;
    }

    public void deleteSupplySquadCard(int turn, Card card) {
        supplySquadCards.get(turn).remove(card);
        int index = spellsAndTrapsInFiled.get(turn).indexOf(card);
        deleteSpellAndTrap(turn, index);
        addCardToGraveyard(turn, card);
    }

    public void setSpellAbsorptionCards(int turn, Card card) {
        spellAbsorptionCards.get(turn).add(card);
    }

    public void deleteSpellAbsorptionCards(int turn, Card card) {
        spellAbsorptionCards.get(turn).remove(card);
        int index = spellsAndTrapsInFiled.get(turn).indexOf(card);
        deleteSpellAndTrap(turn, index);
        addCardToGraveyard(turn, card);
    }

    public void effectOfSpellAbsorptionCards() {
        if (spellAbsorptionCards.get(turn).size() > 0) {
            increaseLifePoint(500 * spellAbsorptionCards.get(turn).size(), turn);
        } else if (spellAbsorptionCards.get(1 - turn).size() > 0) {
            increaseLifePoint(500 * spellAbsorptionCards.get(1 - turn).size(), 1 - turn);
        }
    }

    public void setMessengerOfPeace(int turn, Card card) {
        messengerOfPeace.get(turn).add(card);
    }

    public void deleteMessengerOfPeaceCards(int turn, Card card) {
        messengerOfPeace.get(turn).remove(card);
        int index = spellsAndTrapsInFiled.get(turn).indexOf(card);
        deleteSpellAndTrap(turn, index);
        addCardToGraveyard(turn, card);
    }

    public ArrayList<ArrayList<Card>> getMessengerOfPeace() {
        return messengerOfPeace;
    }

    public void setSpellsAndTrapsSetInThisTurn(int turn, Card spellOrTrapCard) {
        spellsAndTarpsSetInThisTurn.get(turn).add(spellOrTrapCard);
    }

    public ArrayList<ArrayList<Card>> getSpellsAndTarpsSetInThisTurn() {
        return spellsAndTarpsSetInThisTurn;
    }

    public void deleteSpellAndTrapsSetInThisTurn() {
        for (Card card : new ArrayList<>(spellsAndTarpsSetInThisTurn.get(turn))) {
            spellsAndTarpsSetInThisTurn.get(turn).remove(card);
        }
    }

    public void addActivatedMonsterEffect(Card card, int turn) {
        activatedMonsterEffects.get(turn).add(card);
    }

    public ArrayList<Card> getActivatedMonsterEffect(int turn) {
        return activatedMonsterEffects.get(turn);
    }

    public void ritualSummon(String condition, int index, Card card) {
        monstersInField.get(turn).set(index, card);
        monsterCondition.get(turn).set(index, condition);
        deleteCardFromHand(getMonster(turn, index + 1));
        DuelView.getInstance().showBoard();
    }

    public void deleteCardFromDeck(int turn, int index, Card card) {
        playersCards.get(turn).remove(index);
        addCardToGraveyard(turn, card);
    }

    public void setCardsInsteadOfScanners(Card card, int placeOfCard) {
        cardsInsteadOfScanners.put(card, placeOfCard);
    }

    public HashMap<Card, Integer> getCardsInsteadOfScanners() {
        return cardsInsteadOfScanners;
    }

    public void deleteCardsInsteadOfScanners() {
        cardsInsteadOfScanners.clear();
    }

    public ArrayList<HashMap<Card, Card>> getEquipSpells() {
        return equipSpells;
    }

    public void activeEquip(Card monster, Card spell) {
        equipSpells.get(turn).put(spell, monster);
    }

    public Integer findEmptyPlaceOfSpellField() {
        if (spellsAndTrapsInFiled.get(turn).get(0) == null)
            return 0;
        else if (spellsAndTrapsInFiled.get(turn).get(1) == null)
            return 1;
        else if (spellsAndTrapsInFiled.get(turn).get(2) == null)
            return 2;
        else if (spellsAndTrapsInFiled.get(turn).get(3) == null)
            return 3;
        else if (spellsAndTrapsInFiled.get(turn).get(4) == null)
            return 4;
        return null;
    }

}