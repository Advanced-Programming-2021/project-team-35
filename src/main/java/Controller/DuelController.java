package Controller;

import Model.*;
import View.DuelView;
import View.MainPhaseView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class DuelController {
    protected static DuelController duelController = null;
    protected DuelModel duelModel;
    protected DuelView duelView;
    int playerActiveCloseForest;
    int attackaddedForClosedForest = 0;
    protected boolean isAi;

    protected DuelController() {

    }

    public static DuelController getInstance() {
        if (duelController == null)
            duelController = new DuelController();
        return duelController;
    }

    public void setDuelModel(DuelModel duelModel, DuelView duelView, DuelController duelController, boolean isAi) {
        this.isAi = isAi;
        this.duelModel = duelModel;
        this.duelView = duelView;
        DuelController.duelController = duelController;
    }

    public void selectFirstPlayer() {

    }

    //این تابع حین بازی صدا زده میشه تا کارت های ورودی شامل میدان شوند
    public void activeFieldInGameInGame() {
        if (duelModel.getField().get(duelModel.turn).get(0) != null) {
            Spell spell = (Spell) duelModel.getField().get(duelModel.turn).get(0);
            if (spell.getName().equals("Yami"))
                effectOfYami(1);
            else if (spell.getName().equals("Forest"))
                effectOfForest(1);
            else if (spell.getName().equals("Closed Forest"))
                effectOfClosedForest(1);
            else if (spell.getName().equals("UMIIRUKA"))
                effectOfUmiiruka(1);
        } else if (duelModel.getField().get(1 - duelModel.turn).get(0) != null) {
            Spell spell = (Spell) duelModel.getField().get(1 - duelModel.turn).get(0);
            if (spell.getName().equals("Yami"))
                effectOfYami(1);
            else if (spell.getName().equals("Forest"))
                effectOfForest(1);
            else if (spell.getName().equals("Closed Forest"))
                effectOfClosedForest(1);
            else if (spell.getName().equals("UMIIRUKA"))
                effectOfUmiiruka(1);

        }

    }

    public String deselect() {
        if (duelModel.getSelectedCards().get(duelModel.turn).get(0) == null) {
            return "no card is selected yet";
        } else {
            duelModel.deSelectedCard();
            return "card deselected";
        }
    }

    public String activateEffect(int placeOfSpell) {
        Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
        Spell spell = (Spell) card;
        if (spell.getName().equals("Monster Reborn"))
            return effectOfMonsterReborn(placeOfSpell);
        else if (spell.getName().equals("Terraforming"))
            return effectOfTerraforming(placeOfSpell);
        else if (spell.getName().equals("Pot of Greed"))
            return effectOfPotOfGreed(placeOfSpell);
        else if (spell.getName().equals("Raigeki"))
            return effectOfRaigeki(placeOfSpell);
        else if (spell.getName().equals("Change of Heart"))
            return effectOfChangeOfHeart(placeOfSpell);
        else if (spell.getName().equals("Harpie’s Feather Duster"))
            return effectOfHarpiesFeatherDuster(placeOfSpell);
        else if (spell.getName().equals("Swords of Revealing Light")) {
            return effectOfSwordsOfRevealingLight(placeOfSpell);
        } else if (spell.getName().equals("Dark Hole")) {
            return effectOfDarkHole(placeOfSpell);
        } else if (spell.getName().equals("Supply Squad")) {
            return effectOfSupplySquad(placeOfSpell);
        } else if (spell.getName().equals("Spell Absorption")) {
            return effectOfSpellAbsorption(placeOfSpell);
        } else if (spell.getName().equals("Messenger of peace")) {
            return effectOfMessengerOfPeace(placeOfSpell);
        } else if (spell.getName().equals("Twin Twisters")) {
            return effectOfTwinTwisters(placeOfSpell);
        } else if (spell.getName().equals("Mystical space typhoon")) {
            return effectOfMysticalSpaceTyphoon(placeOfSpell);
        } else if (spell.getName().equals("Ring of Defense")) {
            return effectOfRingOfDefense(placeOfSpell);
        } else if (spell.getName().equals("Advanced Ritual Art")) {
            return effectOfAdvancedRitualArt(placeOfSpell);
        } else if (spell.getCardType().equals("Field")) {
            if (placeOfSpell == -1)
                activeZoneFromHand();
            if (placeOfSpell == -2)
                activeSetZone();
        } else if (spell.getName().equals("SwordOfDarkDestruction"))
            return effectOfSwordOfDarkstraction(placeOfSpell);
        else if (spell.getName().equals("Black Pendant"))
            return effectOfBlackPendant(placeOfSpell);
        else if (spell.getName().equals("UnitedWeStand"))
            return effectOfUnitedWeStand(placeOfSpell);
        else if (spell.getName().equals("Magnum Shield"))
            return effectOfMagnumShield(placeOfSpell);
        return "";
    }

    public void deActiveOldField() {
        if (duelModel.getField().get(duelModel.turn).get(0) != null) {
            Spell spell = (Spell) duelModel.getField().get(duelModel.turn).get(0);
            if (spell.getName().equals("Yami"))
                effectOfYami(-1);
            else if (spell.getName().equals("Forest"))
                effectOfForest(-1);
            else if (spell.getName().equals("Closed Forest"))
                effectOfClosedForest(-1);
            else if (spell.getName().equals("UMIIRUKA"))
                effectOfUmiiruka(-1);
        } else if (duelModel.getField().get(1 - duelModel.turn).get(0) != null) {
            Spell spell = (Spell) duelModel.getField().get(1 - duelModel.turn).get(0);
            if (spell.getName().equals("Yami"))
                effectOfYami(-1);
            else if (spell.getName().equals("Forest"))
                effectOfForest(-1);
            else if (spell.getName().equals("Closed Forest"))
                effectOfClosedForest(-1);
            else if (spell.getName().equals("UMIIRUKA"))
                effectOfUmiiruka(-1);
        }

    }

    public String activeSetZone() {
        deActiveOldField();
        duelModel.activeField(duelModel.getSelectedCards().get(duelModel.turn).get(0));
        Spell spell = (Spell) duelModel.getSelectedCards().get(duelModel.turn).get(0);
        duelModel.getField().get(duelModel.turn).set(1, null);
        deselect();
        if (spell.getName().equals("Yami"))
            effectOfYami(1);
        else if (spell.getName().equals("Forest"))
            effectOfForest(1);
        else if (spell.getName().equals("Closed Forest")) {
            playerActiveCloseForest = duelModel.turn;
            effectOfClosedForest(1);

        } else if (spell.getName().equals("UMIIRUKA"))
            effectOfUmiiruka(1);

        return "spell zone activate";
    }

    public String activeZoneFromHand() {
        deActiveOldField();
        duelModel.activeField(duelModel.getSelectedCards().get(duelModel.turn).get(0));
        Spell spell = (Spell) duelModel.getSelectedCards().get(duelModel.turn).get(0);
        duelModel.getHandCards().get(duelModel.turn).remove(duelModel.getSelectedCards().get(duelModel.turn).get(0));
        if (spell.getName().equals("Yami"))
            effectOfYami(1);
        else if (spell.getName().equals("Forest"))
            effectOfForest(1);
        else if (spell.getName().equals("Closed Forest")) {
            playerActiveCloseForest = duelModel.turn;
            effectOfClosedForest(1);
        } else if (spell.getName().equals("UMIIRUKA"))
            effectOfUmiiruka(1);
        return "spell zone activate";
    }


    public void isOpponentHasAnySpellOrTrapForActivate() {
        boolean hasAnySpellOrTrap = false;
        for (int i = 1; i <= 5; i++) {
            if (duelModel.getSpellAndTrap(1 - duelModel.turn, i) != null) {
                if (duelModel.getSpellAndTrap(1 - duelModel.turn, i).getCardType()
                        .equals("Quick-play") || duelModel.getSpellAndTrap(1 - duelModel.turn, i)
                        .getCategory().equals("Trap")) {
                    hasAnySpellOrTrap = true;
                    break;
                }
            }
        }
        duelView.opponentActiveEffect(hasAnySpellOrTrap);
    }

    public String opponentActiveSpellOrTrap() {
        Matcher matcher = duelView.scanCommandForActiveSpell();
        if (matcher.find()) {
            String response = selectSpellOrTrap(matcher);
            System.out.println(response);
            if (response.equals("card selected")) {
                if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getCategory().equals("Trap")) {
                    return opponentActiveTrap();
                } else {
                    if (!duelModel.getSelectedCards().get(duelModel.turn).get(0).getCardType()
                            .equals("Quick-play")) {
                        return "you cant active this spell int this turn";
                    }
                    String[] detailsOfSelectedCard = duelModel.getDetailOfSelectedCard().get(duelModel.turn).
                            get(duelModel.getSelectedCards().get(duelModel.turn).get(0)).split("/");
                    String stateOfSelectedCard = detailsOfSelectedCard[1];
                    if (stateOfSelectedCard.equals("O")) {
                        return "Card already Activated";
                    } else {
                        return opponentActiveSpell(Integer.parseInt(matcher.group(1)));
                    }
                }
            }
        }
        return "it’s not your turn to play this kind of moves";
    }

    public String opponentActiveSpell(int placeOfSpell) {
        Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
        Spell spell = (Spell) card;
        if (spell.getName().equals("Twin Twisters")) {
            return effectOfTwinTwisters(placeOfSpell);
        } else if (spell.getName().equals("Mystical space typhoon")) {
            return effectOfMysticalSpaceTyphoon(placeOfSpell);
        } else if (spell.getName().equals("Ring of Defense")) {
            return effectOfRingOfDefense(placeOfSpell);
        }
        return "";
    }

    public String effectOfMonsterReborn(int placeOfSpell) {
        String kindOfGraveyard;
        int numberOfCard;
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        MainPhaseView mainPhaseView = MainPhaseView.getInstance();
        if (!isAi) {
            kindOfGraveyard = duelView.scanKindOfGraveyardForActiveEffect();
            numberOfCard = duelView.scanNumberOfCardForActiveEffect();
        } else if (!duelModel.getUsernames().get(duelModel.turn).equals("ai")) {
            kindOfGraveyard = duelView.scanKindOfGraveyardForActiveEffect();
            numberOfCard = duelView.scanNumberOfCardForActiveEffect();
        } else {
            MainPhaseController mainPhaseController = MainPhaseController.getInstance();
            NewCardToHandController newCardToHandController = NewCardToHandController.getInstance();
            if (mainPhaseController.hasMonsterInGraveyard(duelModel.turn)) {
                kindOfGraveyard = "My";
                numberOfCard = duelModel.getGraveyard(duelModel.turn).indexOf(newCardToHandController
                        .getBestMonsterFromGraveyard(duelModel.turn));
            } else {
                kindOfGraveyard = "Opponent";
                numberOfCard = duelModel.getGraveyard(1 - duelModel.turn).indexOf(newCardToHandController
                        .getBestMonsterFromGraveyard(1 - duelModel.turn));
            }
        }
        if (!kindOfGraveyard.equals("My") && !kindOfGraveyard.equals("Opponent")) {
            return "you must enter correct state of card for summon";
        } else if (kindOfGraveyard.equals("My")) {
            if (numberOfCard > duelModel.getGraveyard(duelModel.turn).size()) {
                return "card with this number not available";
            } else if (!duelModel.getGraveyard(duelModel.turn).get(numberOfCard - 1).getCategory().equals("Monster")) {
                return "you cant summon this card";
            } else {
                String state;
                if (!isAi) {
                    state = mainPhaseView.getStateOfCardForSummon();
                } else if (!duelModel.getUsernames().get(duelModel.turn).equals("ai")) {
                    state = mainPhaseView.getStateOfCardForSummon();
                } else {
                    if (duelModel.getGraveyard(duelModel.turn).get(numberOfCard - 1).getAttackPower() >=
                            duelModel.getGraveyard(duelModel.turn).get(numberOfCard - 1).getDefensePower()) {
                        state = "Attack";
                    } else {
                        state = "Defence";
                    }
                }
                if (!state.equals("Defence") && !state.equals("Attack")) {
                    return "please enter the appropriate state (Defence or Attack)";
                } else {
                    if (isMonsterZoneFull(duelModel.turn)) {
                        return "monster zone is full";
                    }
                    return specialEffectOfMonsterReborn(placeOfSpell, state, numberOfCard);
                }
            }
        } else {
            if (numberOfCard > duelModel.getGraveyard(1 - duelModel.turn).size()) {
                return "card with this number not available";
            } else if (!duelModel.getGraveyard(1 - duelModel.turn).get(numberOfCard - 1).getCategory()
                    .equals("Monster")) {
                return "you cant summon this card";
            } else {
                String state;
                if (!isAi) {
                    state = mainPhaseView.getStateOfCardForSummon();
                } else if (!duelModel.getUsernames().get(duelModel.turn).equals("ai")) {
                    state = mainPhaseView.getStateOfCardForSummon();
                } else {
                    if (duelModel.getGraveyard(1 - duelModel.turn).get(numberOfCard - 1).getAttackPower() >=
                            duelModel.getGraveyard(1 - duelModel.turn).get(numberOfCard - 1).getDefensePower()) {
                        state = "Attack";
                    } else {
                        state = "Defence";
                    }
                }
                if (!state.equals("Defence") && !state.equals("Attack")) {
                    return "please enter the appropriate state (Defence or Attack)";
                } else {
                    if (isMonsterZoneFull(duelModel.turn)) {
                        return "monster zone is full";
                    }
                    return specialEffectOfMonsterReborn(placeOfSpell, state, numberOfCard);
                }
            }
        }
    }


    public String specialEffectOfMonsterReborn(int placeOfSpell, String state, int numberOfCard) {
        if (placeOfSpell != -1) {
            Card card;
            card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
            duelController.isOpponentHasAnySpellOrTrapForActivate();
            duelModel.monsterSummonForEffectOfSomeTraps = null;
            if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                specialSummonMonsterOnFieldFromGraveyard(1 - duelModel.turn
                        , state, numberOfCard - 1);
                duelModel.effectOfSpellAbsorptionCards();
                duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                duelModel.addCardToGraveyard(duelModel.turn, card);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                return "spell activated";
            }
        } else {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            String response1 = activeSpellFromHand();
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
            duelController.isOpponentHasAnySpellOrTrapForActivate();
            duelModel.monsterSummonForEffectOfSomeTraps = null;
            if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                specialSummonMonsterOnFieldFromGraveyard(1 - duelModel.turn
                        , state, numberOfCard - 1);
                duelModel.effectOfSpellAbsorptionCards();
                int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card);
                duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                duelModel.addCardToGraveyard(duelModel.turn, card);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                return response1;
            }
        }
        return "";
    }

    public String effectOfTerraforming(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        Card card = null;
        ArrayList<Card> cardsInDeckOfPlayer = duelModel.getPlayersCards().get(duelModel.turn);
        for (Card card1 : cardsInDeckOfPlayer) {
            if (card1 != null) {
                if (card1.getCategory().equals("Spell")) {
                    if (card1.getCardType().equals("Field")) {
                        card = card1;
                        break;
                    }
                }
            }
        }
        if (card == null) {
            return "you dont have any FieldCard for add to your hand";
        } else {
            Card card1 = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card1, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card1)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card1);
                    duelModel.addCardFromDeckToHandInMiddleOfGame(duelModel.turn, card);
                    duelModel.effectOfSpellAbsorptionCards();
                    duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                    duelModel.addCardToGraveyard(duelModel.turn, card1);
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card1, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card1)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card1);
                    duelModel.addCardFromDeckToHandInMiddleOfGame(duelModel.turn, card);
                    duelModel.effectOfSpellAbsorptionCards();
                    int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card1);
                    duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                    duelModel.addCardToGraveyard(duelModel.turn, card1);
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card1);
            return "";
        }
    }

    public String effectOfPotOfGreed(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        ArrayList<Card> cardsInDeckOfPlayer = duelModel.getPlayersCards().get(duelModel.turn);
        if (cardsInDeckOfPlayer.size() < 2) {
            return "you dont have enough card for add to your hand";
        } else {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    specialEffectOfPotOfGreed();
                    duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                    duelModel.addCardToGraveyard(duelModel.turn, card);
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    specialEffectOfPotOfGreed();
                    int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card);
                    duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                    duelModel.addCardToGraveyard(duelModel.turn, card);
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
            return "";
        }
    }

    public void specialEffectOfPotOfGreed() {
        duelModel.addCardFromDeckToHandInMiddleOfGame(duelModel.turn
                , duelModel.getPlayersCards().get(duelModel.turn).get(duelModel.
                        getPlayersCards().get(duelModel.turn).size() - 1));
        duelModel.addCardFromDeckToHandInMiddleOfGame(duelModel.turn
                , duelModel.getPlayersCards().get(duelModel.turn).get(duelModel.
                        getPlayersCards().get(duelModel.turn).size() - 1));
        duelModel.effectOfSpellAbsorptionCards();
    }

    public String effectOfRaigeki(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        boolean opponentHasAnyMonster = false;
        ArrayList<Card> monstersInFieldOfPlayer = duelModel.getMonstersInField().get(1 - duelModel.turn);

        for (Card card : monstersInFieldOfPlayer) {
            if (card != null) {
                opponentHasAnyMonster = true;
            }
        }
        if (opponentHasAnyMonster) {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    deleteAllMonsters(1 - duelModel.turn);
                    duelModel.effectOfSpellAbsorptionCards();
                    duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                    duelModel.addCardToGraveyard(duelModel.turn, card);
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    deleteAllMonsters(1 - duelModel.turn);
                    duelModel.effectOfSpellAbsorptionCards();
                    int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card);
                    duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                    duelModel.addCardToGraveyard(duelModel.turn, card);
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
            return "";
        } else {
            return "your opponent dont have any monster";
        }
    }

    public void deleteAllMonsters(int turn) {
        ArrayList<Card> monstersInFieldOfPlayer = duelModel.getMonstersInField().get(turn);
        int i = 0;
        for (Card card : monstersInFieldOfPlayer) {
            if (card != null) {
                duelModel.deleteMonster(turn, i);
                duelModel.addCardToGraveyard(turn, card);
            }
            i++;
        }
    }

    public String effectOfChangeOfHeart(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        int numberOfMonsterCard = duelView.scanNumberOfCardForActiveEffect();
        if (numberOfMonsterCard > 5) {
            return "you cant get card with this address";
        } else if (duelModel.getMonstersInField().get(1 - duelModel.turn).get(numberOfMonsterCard - 1) == null) {
            return "you cant get card with this address";
        } else {
            if (isMonsterZoneFull(duelModel.turn)) {
                return "monster zone full";
            } else {
                Card borrowCard = duelModel.getMonstersInField().get(1 - duelModel.turn).get(numberOfMonsterCard - 1);
                String detailsOfBorrowCard = duelModel.getMonsterCondition(1 - duelModel.turn, numberOfMonsterCard);
                Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
                if (placeOfSpell != -1) {
                    duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                    duelController.isOpponentHasAnySpellOrTrapForActivate();
                    if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                        duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                        duelModel.deleteMonster(1 - duelModel.turn, numberOfMonsterCard - 1);
                        specialEffectOfChangeOfHeart(borrowCard, detailsOfBorrowCard);
                        duelModel.effectOfSpellAbsorptionCards();
                        duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                        duelModel.addCardToGraveyard(duelModel.turn, card);
                        return "spell activated";
                    }
                } else {
                    String response = activeSpellFromHand();
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                    duelController.isOpponentHasAnySpellOrTrapForActivate();
                    if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                        duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                        duelModel.deleteMonster(1 - duelModel.turn, numberOfMonsterCard - 1);
                        specialEffectOfChangeOfHeart(borrowCard, detailsOfBorrowCard);
                        duelModel.effectOfSpellAbsorptionCards();
                        int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card);
                        duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                        duelModel.addCardToGraveyard(duelModel.turn, card);
                        return response;
                    }
                }
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                return "";
            }
        }
    }

    public void specialEffectOfChangeOfHeart(Card borrowCard, String detailsOfBorrowCard) {
        String[] details = detailsOfBorrowCard.split("/");
        String stateOfBorrowCard = details[0];
        if (duelModel.getMonstersInField().get(duelModel.turn).get(0) == null) {
            duelModel.getMonstersInField().get(duelModel.turn).add(0, borrowCard);
            duelModel.addMonsterCondition(duelModel.turn, 0, stateOfBorrowCard + "/1");
            duelModel.addBorrowCard(borrowCard, detailsOfBorrowCard + "/" + 1);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(1) == null) {
            duelModel.getMonstersInField().get(duelModel.turn).add(1, borrowCard);
            duelModel.addMonsterCondition(duelModel.turn, 1, stateOfBorrowCard + "/2");
            duelModel.addBorrowCard(borrowCard, detailsOfBorrowCard + "/" + 2);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(2) == null) {
            duelModel.getMonstersInField().get(duelModel.turn).add(2, borrowCard);
            duelModel.addMonsterCondition(duelModel.turn, 2, stateOfBorrowCard + "/3");
            duelModel.addBorrowCard(borrowCard, detailsOfBorrowCard + "/" + 3);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(3) == null) {
            duelModel.getMonstersInField().get(duelModel.turn).add(3, borrowCard);
            duelModel.addMonsterCondition(duelModel.turn, 3, stateOfBorrowCard + "/4");
            duelModel.addBorrowCard(borrowCard, detailsOfBorrowCard + "/" + 4);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(4) == null) {
            duelModel.getMonstersInField().get(duelModel.turn).add(4, borrowCard);
            duelModel.addMonsterCondition(duelModel.turn, 4, stateOfBorrowCard + "/5");
            duelModel.addBorrowCard(borrowCard, detailsOfBorrowCard + "/" + 5);
        }
    }

    public void refundsTheBorrowCards() {
        ArrayList<Card> borrowCards = duelModel.getBorrowCards();
        ArrayList<String> conditionsOfBorrowCards = duelModel.getConditionOfBorrowCards();
        int i = 0;
        for (Card borrowCard : borrowCards) {
            String[] detailsOfBorrowCard = conditionsOfBorrowCards.get(i).split("/");
            String stateOfBorrowCard = detailsOfBorrowCard[0];
            int placeOfBorrowCard = Integer.parseInt(detailsOfBorrowCard[1]);
            int placeOfFieldThatBorrowCardSet = Integer.parseInt(detailsOfBorrowCard[2]);
            duelModel.deleteMonster(1 - duelModel.turn, placeOfFieldThatBorrowCardSet - 1);
            duelModel.getMonstersInField().get(duelModel.turn).add(placeOfBorrowCard - 1, borrowCard);
            duelModel.addMonsterCondition(duelModel.turn, placeOfBorrowCard - 1, stateOfBorrowCard
                    + "/" + placeOfBorrowCard);
            i++;
        }
        duelModel.deleteBorrowCard();
    }

    public String effectOfHarpiesFeatherDuster(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        ArrayList<Card> spellAndTraps = duelModel.getSpellsAndTrapsInFiled().get(1 - duelModel.turn);
        boolean hasSpellOrTrapCard = false;
        for (Card card : spellAndTraps) {
            if (card != null) {
                hasSpellOrTrapCard = true;
                break;
            }
        }
        if (!hasSpellOrTrapCard) {
            return "dont have any spell or trap";
        } else {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    specialEffectOfHarpiesFeatherDuster();
                    duelModel.effectOfSpellAbsorptionCards();
                    duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                    duelModel.addCardToGraveyard(duelModel.turn, card);
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    specialEffectOfHarpiesFeatherDuster();
                    duelModel.effectOfSpellAbsorptionCards();
                    int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card);
                    duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                    duelModel.addCardToGraveyard(duelModel.turn, card);
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
            return "";
        }
    }

    public void specialEffectOfHarpiesFeatherDuster() {
        ArrayList<Card> spellAndTraps = duelModel.getSpellsAndTrapsInFiled().get(1 - duelModel.turn);
        int i = 0;
        for (Card card : spellAndTraps) {
            if (card != null) {
                if (card.getName().equals("Swords of Revealing Light")) {
                    duelModel.deleteSwordsCard(1 - duelModel.turn, card);
                } else if (card.getName().equals("Supply Squad")) {
                    duelModel.deleteSupplySquadCard(1 - duelModel.turn, card);
                } else if (card.getName().equals("Spell Absorption")) {
                    duelModel.deleteSpellAbsorptionCards(1 - duelModel.turn, card);
                } else if (card.getName().equals("Messenger of peace")) {
                    duelModel.deleteMessengerOfPeaceCards(1 - duelModel.turn);
                } else {
                    duelModel.deleteSpellAndTrap(1 - duelModel.turn, i);
                    duelModel.addCardToGraveyard(1 - duelModel.turn, card);
                }
                for (Map.Entry<Card, Boolean> entry : duelModel.getSpellOrTrapActivated().get(1 - duelModel.turn).entrySet()) {
                    Card spellCardActivated = entry.getKey();
                    if (spellCardActivated == card) {
                        duelModel.getSpellOrTrapActivated().get(1 - duelModel.turn).put(card, true);
                    }
                }
            }
            i++;
        }
    }

    public String effectOfSwordsOfRevealingLight(int placeOfSpell) {
        // not complete it needs a change in battlePhase
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        } else {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    duelModel.setSwordsCard(duelModel.turn, card);
                    changeStateOfMonsterWithSwordsCard(1 - duelModel.turn);
                    duelModel.effectOfSpellAbsorptionCards();
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    duelModel.setSwordsCard(duelModel.turn, card);
                    changeStateOfMonsterWithSwordsCard(1 - duelModel.turn);
                    duelModel.effectOfSpellAbsorptionCards();
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
        }
        return "";
    }

    public void changeStateOfMonsterWithSwordsCard(int turn) {
        ArrayList<Card> monstersInField = duelModel.getMonstersInField().get(turn);
        int i = 1;
        for (Card card : monstersInField) {
            if (card != null) {
                String[] detailsOfMonsterCard = duelModel.getMonsterCondition(turn, i)
                        .split("/");
                if (detailsOfMonsterCard[0].equals("DH")) {
                    duelModel.addMonsterCondition(turn, i - 1, "DO" + i);
                }
            }
            i++;
        }
    }

    public String effectOfDarkHole(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        boolean opponentHasAnyMonster = false;
        boolean youHaveAnyMonster = false;
        ArrayList<Card> monstersInField1 = duelModel.getMonstersInField().get(1 - duelModel.turn);
        for (Card card : monstersInField1) {
            if (card != null) {
                opponentHasAnyMonster = true;
                break;
            }
        }
        ArrayList<Card> monstersInField2 = duelModel.getMonstersInField().get(duelModel.turn);
        for (Card card : monstersInField2) {
            if (card != null) {
                youHaveAnyMonster = true;
                break;
            }
        }
        if (opponentHasAnyMonster || youHaveAnyMonster) {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    deleteAllMonsters(duelModel.turn);
                    deleteAllMonsters(1 - duelModel.turn);
                    duelModel.effectOfSpellAbsorptionCards();
                    duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                    duelModel.addCardToGraveyard(duelModel.turn, card);
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    deleteAllMonsters(duelModel.turn);
                    deleteAllMonsters(1 - duelModel.turn);
                    duelModel.effectOfSpellAbsorptionCards();
                    int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card);
                    duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                    duelModel.addCardToGraveyard(duelModel.turn, card);
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
            return "";
        } else {
            return "you and your opponent dont have any monsters";
        }
    }

    public String effectOfSupplySquad(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        } else {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    duelModel.setSupplySquad(duelModel.turn, card);
                    duelModel.effectOfSpellAbsorptionCards();
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    duelModel.setSupplySquad(duelModel.turn, card);
                    duelModel.effectOfSpellAbsorptionCards();
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
            return "";
        }
    }

    public String effectOfSpellAbsorption(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        } else {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    duelModel.effectOfSpellAbsorptionCards();
                    duelModel.setSpellAbsorptionCards(duelModel.turn, card);
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    duelModel.effectOfSpellAbsorptionCards();
                    duelModel.setSpellAbsorptionCards(duelModel.turn, card);
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
        }
        return "";
    }

    public String effectOfMessengerOfPeace(int placeOfSpell) {
        // not complete it needs a change in battlePhase
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        } else {
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    duelModel.setMessengerOfPeace(duelModel.turn, card);
                    duelModel.effectOfSpellAbsorptionCards();
                    return "spell activated";
                }
            } else {
                String response = activeSpellFromHand();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                    duelModel.setMessengerOfPeace(duelModel.turn, card);
                    duelModel.effectOfSpellAbsorptionCards();
                    return response;
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
            return "";
        }
    }

    public String effectOfTwinTwisters(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        if (hasSpellSetInThisTurn()) {
            return "preparations of this spell are not done yet";
        }
        Card card1 = duelModel.getSelectedCards().get(duelModel.turn).get(0);
        if (placeOfSpell != -1) {
            duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
        } else {
            activeSpellFromHand();
        }
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card1, false);
        duelController.isOpponentHasAnySpellOrTrapForActivate();
        if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card1)) {
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card1);
            int placeOfCardInHand = duelView.scanNumberOfCardForDeleteFromHand();
            if (placeOfCardInHand > duelModel.getHandCards().get(duelModel.turn).size()) {
                return "you cant delete card with this address from your hand";
            } else {
                Card card = duelModel.getHandCards().get(duelModel.turn).get(placeOfCardInHand - 1);
                duelModel.deleteCardFromHand(card);
                duelModel.addCardToGraveyard(duelModel.turn, card);
                int numberOfCardsPlayerWantToDestroyed = duelView.scanNumberOfCardThatWouldBeDelete();
                if (numberOfCardsPlayerWantToDestroyed == 0) {
                    if (placeOfSpell != -1) {
                        duelModel.effectOfSpellAbsorptionCards();
                        duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                        duelModel.addCardToGraveyard(duelModel.turn, card1);
                        return "spell activated";
                    } else {
                        duelModel.effectOfSpellAbsorptionCards();
                        int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card1);
                        duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                        duelModel.addCardToGraveyard(duelModel.turn, card1);
                        return "spell activated";
                    }
                } else if (numberOfCardsPlayerWantToDestroyed == 1) {
                    String response = duelView.scanPlaceOfCardWantToDestroyed();
                    String[] splitResponse = response.split(" ");
                    int placeOfSpellOrTrapCard = Integer.parseInt(splitResponse[1]);
                    if (splitResponse[0].equals("my")) {
                        return deleteASpellCardInActiveSpell(duelModel.turn, placeOfSpell,
                                placeOfSpellOrTrapCard);
                    } else if (splitResponse[0].equals("opponent")) {
                        return deleteASpellCardInActiveSpell(1 - duelModel.turn, placeOfSpell,
                                placeOfSpellOrTrapCard);
                    } else {
                        return "you must enter my/opponent";
                    }
                } else if (numberOfCardsPlayerWantToDestroyed == 2) {
                    String response1 = duelView.scanPlaceOfCardWantToDestroyed();
                    String response2 = duelView.scanPlaceOfCardWantToDestroyed();
                    String[] splitResponse1 = response1.split(" ");
                    String[] splitResponse2 = response2.split(" ");
                    int placeOfSpellOrTrapCard1 = Integer.parseInt(splitResponse1[1]);
                    int placeOfSpellOrTrapCard2 = Integer.parseInt(splitResponse2[1]);
                    if (splitResponse1[0].equals("my") && splitResponse2[0].equals("my")) {
                        return deleteTwoSpellCardInActiveSpell(duelModel.turn, duelModel.turn, placeOfSpell,
                                placeOfSpellOrTrapCard1, placeOfSpellOrTrapCard2);
                    } else if (splitResponse1[0].equals("opponent") && splitResponse2[0].equals("opponent")) {
                        return deleteTwoSpellCardInActiveSpell(1 - duelModel.turn, 1 - duelModel.turn, placeOfSpell,
                                placeOfSpellOrTrapCard1, placeOfSpellOrTrapCard2);
                    } else if (splitResponse1[0].equals("my") && splitResponse2[0].equals("opponent")) {
                        return deleteTwoSpellCardInActiveSpell(duelModel.turn, 1 - duelModel.turn, placeOfSpell,
                                placeOfSpellOrTrapCard1, placeOfSpellOrTrapCard2);
                    } else if (splitResponse1[0].equals("opponent") && splitResponse2[0].equals("my")) {
                        return deleteTwoSpellCardInActiveSpell(1 - duelModel.turn, duelModel.turn, placeOfSpell,
                                placeOfSpellOrTrapCard1, placeOfSpellOrTrapCard2);
                    } else {
                        return "you must enter my/opponent";
                    }
                } else {
                    return "you cant destroy spell in this number";
                }
            }
        }
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card1);
        return "";
    }

    public String effectOfMysticalSpaceTyphoon(int placeOfSpell) {
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        if (hasSpellSetInThisTurn()) {
            return "preparations of this spell are not done yet";
        }
        Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
        if (placeOfSpell != -1) {
            duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
        } else {
            activeSpellFromHand();
        }
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
        duelController.isOpponentHasAnySpellOrTrapForActivate();
        if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
            String response = duelView.scanPlaceOfCardWantToDestroyed();
            String[] splitResponse = response.split(" ");
            int placeOfSpellOrTrapCard = Integer.parseInt(splitResponse[1]);
            if (splitResponse[0].equals("my")) {
                return deleteASpellCardInActiveSpell(duelModel.turn, placeOfSpell, placeOfSpellOrTrapCard);
            } else if (splitResponse[0].equals("opponent")) {
                return deleteASpellCardInActiveSpell(1 - duelModel.turn, placeOfSpell, placeOfSpellOrTrapCard);
            } else {
                return "you must enter my/opponent";
            }
        }
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
        return "";
    }

    public String effectOfRingOfDefense(int placeOfSpell) {
        // not complete it needs a change in trap
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        if (hasSpellSetInThisTurn()) {
            return "preparations of this spell are not done yet";
        }
        return "";
    }


    public boolean hasSpellSetInThisTurn() {
        ArrayList<Card> spellsAndTrapsSetInThisTurn = duelModel.getSpellsAndTarpsSetInThisTurn()
                .get(duelModel.turn);
        for (Card card : spellsAndTrapsSetInThisTurn) {
            if (card.getCategory().equals("Spell")) {
                if (card == duelModel.getSelectedCards().get(duelModel.turn).get(0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String deleteTwoSpellCardInActiveSpell(int turn1, int turn2, int placeOfSpell, int placeOfSpellOrTrapCard1, int placeOfSpellOrTrapCard2) {
        if (placeOfSpellOrTrapCard1 > 5 || placeOfSpellOrTrapCard2 > 5) {
            return "you must enter correct address";
        } else {
            Card card1 = duelModel.getSpellsAndTrapsInFiled().get(turn1).get(placeOfSpellOrTrapCard1 - 1);
            Card card2 = duelModel.getSpellsAndTrapsInFiled().get(turn2).get(placeOfSpellOrTrapCard2 - 1);
            if (card1 == null || card2 == null) {
                return "you cant destroyed card with this address";
            } else {
                Card spellCard = duelModel.getSelectedCards().get(duelModel.turn).get(0);
                if (placeOfSpell != -1) {
                    deleteASpell(turn1, placeOfSpellOrTrapCard1, card1);
                    deleteASpell(turn2, placeOfSpellOrTrapCard2, card2);
                    duelModel.effectOfSpellAbsorptionCards();
                    duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                    duelModel.addCardToGraveyard(duelModel.turn, spellCard);
                    return "spell activated";
                } else {
                    deleteASpell(turn1, placeOfSpellOrTrapCard1, card1);
                    deleteASpell(turn2, placeOfSpellOrTrapCard2, card2);
                    duelModel.effectOfSpellAbsorptionCards();
                    int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(spellCard);
                    duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                    duelModel.addCardToGraveyard(duelModel.turn, spellCard);
                    return "spell activated";
                }
            }

        }
    }

    public String deleteASpellCardInActiveSpell(int turn, int placeOfSpell, int placeOfSpellOrTrapCard) {
        if (placeOfSpellOrTrapCard > 5) {
            return "you must enter correct address";
        } else {
            Card card1 = duelModel.getSpellsAndTrapsInFiled().get(turn).get(placeOfSpellOrTrapCard - 1);
            if (card1 == null) {
                return "you cant destroyed card with this address";
            } else {
                Card spellCard = duelModel.getSelectedCards().get(duelModel.turn).get(0);
                if (placeOfSpell != -1) {
                    deleteASpell(turn, placeOfSpellOrTrapCard, card1);
                    duelModel.effectOfSpellAbsorptionCards();
                    duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                    duelModel.addCardToGraveyard(duelModel.turn, spellCard);
                    return "spell activated";
                } else {
                    deleteASpell(turn, placeOfSpellOrTrapCard, card1);
                    duelModel.effectOfSpellAbsorptionCards();
                    int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(spellCard);
                    duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                    duelModel.addCardToGraveyard(duelModel.turn, spellCard);
                    return "spell activated";
                }
            }
        }
    }

    public void deleteASpell(int turn, int placeOfSpellOrTrapCard, Card card) {
        if (card.getName().equals("Swords of Revealing Light")) {
            duelModel.deleteSwordsCard(turn, card);
        } else if (card.getName().equals("Supply Squad")) {
            duelModel.deleteSupplySquadCard(turn, card);
        } else if (card.getName().equals("Spell Absorption")) {
            duelModel.deleteSpellAbsorptionCards(turn, card);
        } else if (card.getName().equals("Messenger of peace")) {
            duelModel.deleteMessengerOfPeaceCards(turn);
        } else {
            duelModel.deleteSpellAndTrap(turn, placeOfSpellOrTrapCard - 1);
            duelModel.addCardToGraveyard(turn, card);
        }
        for (Map.Entry<Card, Boolean> entry : duelModel.getSpellOrTrapActivated().get(turn).entrySet()) {
            Card spellCardActivated = entry.getKey();
            if (spellCardActivated == card) {
                duelModel.getSpellOrTrapActivated().get(turn).put(card, true);
            }
        }
    }

    public boolean isSpellZoneFull(int turn) {
        boolean spellZoneFull = true;
        ArrayList<Card> spellsAndTraps = duelModel.getSpellsAndTrapsInFiled().get(turn);
        for (Card card : spellsAndTraps) {
            if (card == null) {
                spellZoneFull = false;
                break;
            }
        }
        return spellZoneFull;
    }

    public String activeSpellFromHand() {
        if (duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).get(0) == null) {
            duelModel.addSpellAndTrapFromHandToGame("O/1", 0);
        } else if (duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).get(1) == null) {
            duelModel.addSpellAndTrapFromHandToGame("O/2", 1);
        } else if (duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).get(2) == null) {
            duelModel.addSpellAndTrapFromHandToGame("O/3", 2);
        } else if (duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).get(3) == null) {
            duelModel.addSpellAndTrapFromHandToGame("O/4", 3);
        } else if (duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).get(4) == null) {
            duelModel.addSpellAndTrapFromHandToGame("O/5", 4);
        }
        return "spell activated";
    }

    public String effectOfAdvancedRitualArt(int placeOfSpell) {
        boolean hasAnyRitualMonster = false;
        if (placeOfSpell == -1 && isSpellZoneFull(duelModel.turn)) {
            return "spell card zone is full";
        }
        ArrayList<Card> handCards = duelModel.getHandCards().get(duelModel.turn);
        ArrayList<Card> cardsInDeck = duelModel.getPlayersCards().get(duelModel.turn);
        Monster monster = null;
        for (Card card : handCards) {
            if (card.getCategory().equals("Monster")) {
                monster = (Monster) card;
                if (monster.getCardType().equals("Ritual")) {
                    hasAnyRitualMonster = true;
                    break;
                }
            }
        }
        if (!hasAnyRitualMonster) {
            return "there is no way you could ritual summon a monster";
        } else {
            if (isMonsterZoneFull(duelModel.turn)) {
                return "there is no way you could ritual summon a monster";
            }
            Card card = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            if (placeOfSpell != -1) {
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, placeOfSpell);
            } else {
                activeSpellFromHand();
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(card, false);
            duelController.isOpponentHasAnySpellOrTrapForActivate();
            if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(card)) {
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
                String[] addressOfCardForTribute = duelView.scanAddressForTributeForRitualSummon().split(" ");
                if (addressOfCardForTribute.length < 2) {
                    return "you must enter two address for tribute from your deck";
                } else {
                    int addressOfCard1 = Integer.parseInt(addressOfCardForTribute[0]);
                    int addressOfCard2 = Integer.parseInt(addressOfCardForTribute[1]);
                    if (addressOfCard1 > cardsInDeck.size() || addressOfCard2 > cardsInDeck.size()) {
                        return "you must enter correct address";
                    } else {
                        Card card1 = cardsInDeck.get(addressOfCard1 - 1);
                        Card card2 = cardsInDeck.get(addressOfCard2 - 1);
                        if (!card1.getCategory().equals("Monster") || !card2.getCategory().equals("Monster")) {
                            return "you must tribute monster";
                        } else {
                            Monster monster1 = (Monster) card1;
                            Monster monster2 = (Monster) card2;
                            if (monster1.getLevel() + monster2.getLevel() != monster.getLevel()) {
                                return "selected monsters levels don’t match with ritual monster";
                            } else {
                                duelModel.deleteCardFromDeck(duelModel.turn, addressOfCard1 - 1, card1);
                                duelModel.deleteCardFromDeck(duelModel.turn, addressOfCard2 - 1, card2);
                                MainPhaseView mainPhaseView = MainPhaseView.getInstance();
                                String stateOfCardForSummon = mainPhaseView.getStateOfCardForSummon();
                                if (!stateOfCardForSummon.equals("Attack")
                                        && !stateOfCardForSummon.equals("Defence")) {
                                    return "you must Highlight the state of card for summon correctly";
                                }
                                ritualSummonMonsterOnField(stateOfCardForSummon, monster);
                                duelModel.effectOfSpellAbsorptionCards();
                                if (placeOfSpell != -1) {
                                    duelModel.deleteSpellAndTrap(duelModel.turn, placeOfSpell - 1);
                                } else {
                                    int indexOfSpell = duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(card);
                                    duelModel.deleteSpellAndTrap(duelModel.turn, indexOfSpell);
                                }
                                duelModel.addCardToGraveyard(duelModel.turn, card);
                                return "spell activated";
                            }
                        }
                    }
                }
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(card);
            return "";
        }
    }


    public void ritualSummonMonsterOnField(String state, Card card) {
        String stateOfCard = "OO";
        if (state.equals("Attack")) {
            stateOfCard = "OO";
        } else if (state.equals("Defence")) {
            stateOfCard = "DO";
        }
        if (duelModel.getMonstersInField().get(duelModel.turn).get(0) == null) {
            duelModel.ritualSummon(stateOfCard + "/1", 0, card);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(1) == null) {
            duelModel.ritualSummon(stateOfCard + "/2", 1, card);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(2) == null) {
            duelModel.ritualSummon(stateOfCard + "/3", 0, card);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(3) == null) {
            duelModel.ritualSummon(stateOfCard + "/4", 0, card);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(4) == null) {
            duelModel.ritualSummon(stateOfCard + "/5", 0, card);
        }
        duelModel.monsterSummonForEffectOfSomeTraps = card;
    }

    public String effectOfMagnumShield(int placeOfSpellInField) {
        boolean isWarriorExist = false;
        ArrayList<Integer> placeOfWarriorCard = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = duelModel.getMonstersInField().get(duelModel.turn).get(i);
            if (card != null)
                //check it
                if (card.getCategory().equals("Warrior")) {
                    if (duelModel.getMonsterCondition(duelModel.turn, i).equals("DO") || duelModel.getMonsterCondition(duelModel.turn, i).equals("OO"))
                        isWarriorExist = true;
                    placeOfWarriorCard.add(i);
                }
        }
        if (isWarriorExist) {
            int place = duelView.scanForChoseMonsterForEquip(placeOfWarriorCard);
            //فرض میکنیم که عدد درست وارد شده
            Monster monster = (Monster) duelModel.getMonstersInField().get(duelModel.turn).get(place);
            if (duelModel.getMonsterCondition(duelModel.turn, place).startsWith("D")) {
                monster.setAttackPower(monster.getAttackPower() + monster.getDefensePower());

                duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).set(placeOfSpellInField, duelModel.getSelectedCards().get(duelModel.turn).get(0));
                //پر شود
            } else {
                monster.setDefensePower(monster.getAttackPower() + monster.getDefensePower());

            }
            return "spell activated";
        } else return "you don't have any Warrior monster to equip ";
    }

    public String effectOfUnitedWeStand(int placeOfSpell) {
        boolean isMonster = false;
        ArrayList<Integer> placeOfMonsterCard = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = duelModel.getMonstersInField().get(duelModel.turn).get(i);
            if (card != null) {
                //check it
                if (duelModel.getMonsterCondition(duelModel.turn, i).equals("DO") || duelModel.getMonsterCondition(duelModel.turn, i).equals("OO"))
                    isMonster = true;
                placeOfMonsterCard.add(i);
            }
        }
        if (isMonster) {
            int place;
            while (true) {
                place = duelView.scanForChoseMonsterForEquip(placeOfMonsterCard);
                if(placeOfMonsterCard.contains(place))
                    break;
            }
            Monster monster = (Monster) duelModel.getMonstersInField().get(duelModel.turn).get(place);
            if (placeOfSpell == -1) {
                if (isSpellZoneFull(duelModel.turn))
                    return "spellZone full!";
                else {
                    unitedWeStand(monster, 1);
                    Spell spell = (Spell) duelModel.getSelectedCards().get(duelModel.turn).get(0);
                    duelModel.activeEquip(monster, spell);
                    duelModel.addSpellAndTrapFromHandToGame("O", duelModel.findEmptyPlaceOfSpellField());
                }
            } else {
                unitedWeStand(monster, 1);
                Spell spell = (Spell) duelModel.getSelectedCards().get(duelModel.turn).get(0);
                duelModel.activeEquip(monster, spell);
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(duelModel.getSelectedCards().get(duelModel.turn).get(0)));

            }
            return "spell activated";
        }
        return "you don't have any monster to equip";
    }

    public void unitedWeStand(Monster monster, int activeOrDeactive) {
        monster.setDefensePower(monster.getDefensePower() + 800 * activeOrDeactive);
        monster.setAttackPower(monster.getAttackPower() + 800 * activeOrDeactive);
    }

    public String effectOfBlackPendant(int placeOfSpell) {
        boolean isMonster = false;
        ArrayList<Integer> placeOfMonsterCard = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = duelModel.getMonstersInField().get(duelModel.turn).get(i);
            if (card != null) {
                //check it
                if (duelModel.getMonsterCondition(duelModel.turn, i).equals("DO") || duelModel.getMonsterCondition(duelModel.turn, i).equals("OO"))
                    isMonster = true;
                placeOfMonsterCard.add(i);
            }
        }

        if (isMonster) {
            int place;
            while (true) {
                 place= duelView.scanForChoseMonsterForEquip(placeOfMonsterCard);
                 if(placeOfMonsterCard.contains(place))
                     break;
            }
            Card monster =  duelModel.getMonstersInField().get(duelModel.turn).get(place);
            if (placeOfSpell == -1) {
                if (isSpellZoneFull(duelModel.turn))
                    return "spellZone full!";
                else {
                    blackPendant(monster, 1);
                    Spell spell = (Spell) duelModel.getSelectedCards().get(duelModel.turn).get(0);
                    duelModel.activeEquip(monster, spell);
                    duelModel.addSpellAndTrapFromHandToGame("O", duelModel.findEmptyPlaceOfSpellField());
                }
            } else {
                blackPendant(monster, 1);
                Spell spell = (Spell) duelModel.getSelectedCards().get(duelModel.turn).get(0);
                duelModel.activeEquip(monster, spell);
                duelModel.changePositionOfSpellOrTrapCard(duelModel.turn, duelModel.getSpellsAndTrapsInFiled().get(duelModel.turn).indexOf(duelModel.getSelectedCards().get(duelModel.turn).get(0)));

            }
            return "spell activated";
        }
        return "you don't have any monster to equip";
    }

    public void blackPendant(Card monster, int activeOrdeActive) {
        monster.setAttackPower(monster.getAttackPower() + 500 * activeOrdeActive);
    }

    public String effectOfSwordOfDarkstraction(int placeOfSpell) {
        boolean isMonster = false;
        ArrayList<Integer> placeOfMonsterCard = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = duelModel.getMonstersInField().get(duelModel.turn).get(i);
            if (card != null) {
                //check it
                if (duelModel.getMonsterCondition(duelModel.turn, i).equals("DO") || duelModel.getMonsterCondition(duelModel.turn, i).equals("OO"))
                    if (card.getCategory().equals("Fiend") || card.getCategory().equals("Spellcaster")) {
                        isMonster = true;
                        placeOfMonsterCard.add(i);
                    }
            }
        }
        if (isMonster) {
            int place;
            while (true) {
                place = duelView.scanForChoseMonsterForEquip(placeOfMonsterCard);
                if (placeOfMonsterCard.contains(place))
                    break;
            }
            Card monster =  duelModel.getMonstersInField().get(duelModel.turn).get(place);
            SwordOfDarkstraction(monster, 1);
            return "spell activated";
        }
        return "you don't have any monster to equip";
    }

    private void SwordOfDarkstraction(Card monster, int activeOrdeactive) {
        monster.setAttackPower(monster.getAttackPower() + 400 * activeOrdeactive);
        monster.setDefensePower(monster.getDefensePower() - 200 * activeOrdeactive);
    }

    public String effectOfUmiiruka(int activeOrdeActive) {
        for (int i = 0; i < 5; i++) {
            Monster monster = (Monster) duelModel.getMonstersInField().get(duelModel.turn).get(i);
            Monster monster1 = (Monster) duelModel.getMonstersInField().get(duelModel.turn).get(i);
            if (monster != null)
                if (monster.getCardType().equals("Aqua"))
                    if (duelModel.getSpellZoneActivate().get(duelModel.turn).get(i)) {
                        duelModel.getSpellZoneActivate().get(duelModel.turn).add(i, true);
                        monster.setDefensePower(monster.getDefensePower() - 400 * activeOrdeActive);
                        monster.setAttackPower(monster.getAttackPower() + 500 * activeOrdeActive);
                    }
            if (monster1 != null)
                if (monster1.getCardType().equals("Aqua"))
                    if (!duelModel.getSpellZoneActivate().get(1 - duelModel.turn).get(i)) {
                        duelModel.getSpellZoneActivate().get(1 - duelModel.turn).add(i, true);
                        monster1.setDefensePower(monster1.getDefensePower() - 400 * activeOrdeActive);
                        monster1.setAttackPower(monster1.getAttackPower() + 500 * activeOrdeActive);
                    }
        }
        deselect();
        return "spellZone activated";
    }

    public String effectOfClosedForest(int activeOrdeActive) {

        for (int i = 0; i < 5; i++) {
            Monster monster = (Monster) duelModel.getMonstersInField().get(playerActiveCloseForest).get(i);
            if (monster != null)
                if (monster.getMonsterType().equals("Beast-Type")) {
                    if (!duelModel.getSpellZoneActivate().get(playerActiveCloseForest).get(i))
                        duelModel.getSpellZoneActivate().get(playerActiveCloseForest).add(i, true);
                    if (activeOrdeActive == 1)
                        monster.setAttackPower(monster.getAttackPower() + duelModel.getGraveyard(playerActiveCloseForest).size() * 100 - attackaddedForClosedForest);
                    else
                        monster.setAttackPower(monster.getAttackPower() - attackaddedForClosedForest);
                }
            attackaddedForClosedForest = duelModel.getGraveyard(playerActiveCloseForest).size() * 100;

        }
        return "spellZone activated";
    }

    public String effectOfForest(int activeOrdeActive) {

        for (int i = 0; i < 5; i++) {
            Monster monster = (Monster) duelModel.getMonstersInField().get(duelModel.turn).get(i);
            Monster monster1 = (Monster) duelModel.getMonstersInField().get(1 - duelModel.turn).get(i);
            if (monster != null)
                if (monster.getMonsterType().equals("Beast-Warrior") || monster.getMonsterType().equals("Beast") || monster.getMonsterType().equals("Insect")) {
                    forest(i, monster, activeOrdeActive);
                }
            if (monster1 != null)
                if (monster1.getMonsterType().equals("Beast-Warrior") || monster.getMonsterType().equals("Beast") || monster.getMonsterType().equals("Insect"))
                    forest(i, monster1, activeOrdeActive);
        }
        return "spellZone activated";
    }

    private void forest(int i, Monster monster, int activeOrdeActive) {
        if (!duelModel.getSpellZoneActivate().get(duelModel.turn).get(i)) {
            duelModel.getSpellZoneActivate().get(duelModel.turn).add(i, true);
            Monster monster2 = Monster.getMonsterByName(monster.getName());
            monster.setAttackPower(monster2.getAttackPower() + 200 * activeOrdeActive);
            monster.setDefensePower(monster2.getDefensePower() + 200 * activeOrdeActive);
        }
    }

    public String effectOfYami(int activeOrdeActive) {
        for (int i = 0; i < 5; i++) {
            Monster monster = (Monster) duelModel.getMonstersInField().get(duelModel.turn).get(i);
            Monster monster1 = (Monster) duelModel.getMonstersInField().get(1 - duelModel.turn).get(i);
            if (monster != null) {
                if (monster.getMonsterType().equals("Fiend") || monster.getMonsterType().equals("Spellcaster")) {
                    forest(i, monster, activeOrdeActive);
                    forest(i, monster1, activeOrdeActive);
                } else if (monster.getMonsterType().equals("Fairy")) {
                    forest(i, monster, activeOrdeActive * -1);
                    forest(i, monster1, activeOrdeActive * -1);
                }
            }
            if (monster1 != null) {
                if (monster1.getMonsterType().equals("Fiend") || monster1.getMonsterType().equals("Spellcaster")) {
                    if (!duelModel.getSpellZoneActivate().get(1 - duelModel.turn).get(i)) {
                        duelModel.getSpellZoneActivate().get(1 - duelModel.turn).add(i, true);
                        monster1.setAttackPower(Monster.getMonsterByName(monster1.getName()).getAttackPower() + 200);
                        monster1.setDefensePower(Monster.getMonsterByName(monster1.getName()).getDefensePower() + 200);

                    }
                } else if (monster1.getMonsterType().equals("Fairy")) {
                    if (!duelModel.getSpellZoneActivate().get(1 - duelModel.turn).get(i)) {
                        duelModel.getSpellZoneActivate().get(1 - duelModel.turn).add(i, true);
                        Monster monster2 = Monster.getMonsterByName(monster.getName());
                        monster1.setAttackPower(monster2.getAttackPower() - 200);
                        monster1.setDefensePower(monster2.getDefensePower() - 200);
                    }
                }
            }
        }
        return "spellZoneActivate";
    }

    public boolean isMonsterZoneFull(int turn) {
        boolean isMonsterZoneFull = true;
        ArrayList<Card> monsterInFiledPlayer = duelModel.getMonstersInField().get(turn);
        for (Card card : monsterInFiledPlayer) {
            if (card == null) {
                isMonsterZoneFull = false;
                break;
            }
        }
        return isMonsterZoneFull;
    }


    public void specialSummonMonsterOnFieldFromGraveyard(int turn, String state, int indexOfCardOfGraveyard) {
        String stateOfCard = "OO";
        if (state.equals("Attack")) {
            stateOfCard = "OO";
        } else if (state.equals("Defence")) {
            stateOfCard = "DO";
        }
        Card card = duelModel.getGraveyard(duelModel.turn).get(indexOfCardOfGraveyard);
        if (duelModel.getMonstersInField().get(duelModel.turn).get(0) == null) {
            duelModel.addMonsterFromGraveyardToGame(stateOfCard + "/1", card, 0);
            duelModel.deleteCardFromGraveyard(turn, indexOfCardOfGraveyard);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(1) == null) {
            duelModel.addMonsterFromGraveyardToGame(stateOfCard + "/2", card, 1);
            duelModel.deleteCardFromGraveyard(turn, indexOfCardOfGraveyard);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(2) == null) {
            duelModel.addMonsterFromGraveyardToGame(stateOfCard + "/3", card, 2);
            duelModel.deleteCardFromGraveyard(turn, indexOfCardOfGraveyard);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(3) == null) {
            duelModel.addMonsterFromGraveyardToGame(stateOfCard + "/4", card, 3);
            duelModel.deleteCardFromGraveyard(turn, indexOfCardOfGraveyard);
        } else if (duelModel.getMonstersInField().get(duelModel.turn).get(4) == null) {
            duelModel.addMonsterFromGraveyardToGame(stateOfCard + "/5", card, 4);
            duelModel.deleteCardFromGraveyard(turn, indexOfCardOfGraveyard);
        }
        duelModel.monsterSummonForEffectOfSomeTraps = card;
    }

    public String opponentActiveTrap() {
        if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Magic Cylinder")) {
            return activeOtherTraps();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Mirror Force")) {
            return activeOtherTraps();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Mind Crush")) {
            return activeTrapMindCrush();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Trap Hole")) {
            return effectOfTrapHole();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Torrential Tribute")) {
            return effectOfTorrentialTribute();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Time Seal")) {
            return activeOtherTraps();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Negate Attack")) {
            return activeOtherTraps();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Call of the Haunted")) {
            return activeTrapCallOfTheHaunted();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Magic Jammer")) {
            return MagicJammer();
        } else if (duelModel.getSelectedCards().get(duelModel.turn).get(0).getName().equals("Solemn Warning")) {
            return effectOfSolemnWarning();
        }
        return "trap activated";
    }

    public String activeOtherTraps() {
        Card trap = duelModel.getSelectedCards().get(duelModel.turn).get(0);
        activeNormalTraps();
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(trap, false);
        duelController.isOpponentHasAnySpellOrTrapForActivate();
        int place = Integer.parseInt(duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap).split("/")[2]);
        if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(trap)) {
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
            return "trap activated";
        } else {
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
            return "";
        }
    }

    public String effectOfSolemnWarning() {
        if (duelModel.monsterSummonForEffectOfSomeTraps == null) {
            return "no monster summon";
        } else {
            Card trap = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            activeNormalTraps();
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(trap, false);
            duelController.isOpponentHasAnySpellOrTrapForActivate();
            if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(trap)) {
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
                Card card = duelModel.monsterSummonForEffectOfSomeTraps;
                int indexOfMonsterSummoned = duelModel.getMonstersInField().get(1 - duelModel.turn).indexOf(card);
                duelModel.decreaseLifePoint(2000, duelModel.turn);
                duelModel.deleteMonster(1 - duelModel.turn, indexOfMonsterSummoned);
                duelModel.addCardToGraveyard(1 - duelModel.turn, card);
                int place = Integer.parseInt(duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap).split("/")[2]);
                duelModel.deleteSpellAndTrap(duelModel.turn, place - 1);
                duelModel.addCardToGraveyard(duelModel.turn, trap);
                return "trap activated";
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
            return "";
        }
    }

    public String effectOfTorrentialTribute() {
        if (duelModel.monsterSummonForEffectOfSomeTraps == null) {
            return "no monster summon";
        } else {
            boolean opponentHasAnyMonster = false;
            boolean youHaveAnyMonster = false;
            ArrayList<Card> monstersInFieldOfOpponent = duelModel.getMonstersInField().get(1 - duelModel.turn);
            ArrayList<Card> monstersInFieldOfYou = duelModel.getMonstersInField().get(duelModel.turn);
            for (Card card : monstersInFieldOfOpponent) {
                if (card != null) {
                    opponentHasAnyMonster = true;
                    break;
                }
            }
            for (Card card : monstersInFieldOfYou) {
                if (card != null) {
                    youHaveAnyMonster = true;
                    break;
                }
            }
            if (!youHaveAnyMonster && !opponentHasAnyMonster) {
                return "no monster exist in field";
            } else {
                Card trap = duelModel.getSelectedCards().get(duelModel.turn).get(0);
                activeNormalTraps();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(trap, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(trap)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
                    deleteAllMonsters(duelModel.turn);
                    deleteAllMonsters(1 - duelModel.turn);
                    int place = Integer.parseInt(duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap).split("/")[2]);
                    duelModel.deleteSpellAndTrap(duelModel.turn, place - 1);
                    duelModel.addCardToGraveyard(duelModel.turn, trap);
                    return "trap activated";
                }
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
                return "";
            }
        }
    }

    public String effectOfTrapHole() {
        // check...
        if (duelModel.monsterFlipSummonOrNormalSummonForTrapHole == null) {
            return "your opponent didnt summon or flipSummon card with property for destroy";
        } else {
            Card trap = duelModel.getSelectedCards().get(duelModel.turn).get(0);
            activeNormalTraps();
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(trap, false);
            duelController.isOpponentHasAnySpellOrTrapForActivate();
            if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(trap)) {
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
                Card card = duelModel.monsterFlipSummonOrNormalSummonForTrapHole;
                int indexOfMonsterSummonOrFlipSummon = duelModel.getMonstersInField().get(1 - duelModel.turn).indexOf(card);
                duelModel.deleteMonster(1 - duelModel.turn, indexOfMonsterSummonOrFlipSummon);
                duelModel.addCardToGraveyard(1 - duelModel.turn, card);
                int place = Integer.parseInt(duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap).split("/")[2]);
                duelModel.deleteSpellAndTrap(duelModel.turn, place - 1);
                duelModel.addCardToGraveyard(duelModel.turn, trap);
                return "trap activated";
            }
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
            return "";
        }
    }

    public String MagicJammer() {
        if (duelModel.getSpellOrTrapActivated().get(1 - duelModel.turn).size() == 0) {
            return "no card activated";
        } else {
            Card cardActivated = null;
            for (Map.Entry<Card, Boolean> entry : duelModel.getSpellOrTrapActivated().get(1 - duelModel.turn).entrySet()) {
                cardActivated = entry.getKey();
            }
            assert cardActivated != null;
            if (!cardActivated.getCategory().equals("Spell")) {
                return "no spell activated";
            } else {
                Card trap = duelModel.getSelectedCards().get(duelModel.turn).get(0);
                activeNormalTraps();
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(trap, false);
                duelController.isOpponentHasAnySpellOrTrapForActivate();
                if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(trap)) {
                    duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
                    int placeOfCardDeleteFromHand = duelView.scanNumberOfCardForDeleteFromHand();
                    if (placeOfCardDeleteFromHand > duelModel.getHandCards().get(duelModel.turn).size()) {
                        return "you must enter address that has card (correct address)";
                    } else {
                        Card card = duelModel.getHandCards().get(duelModel.turn).get(placeOfCardDeleteFromHand - 1);
                        duelModel.deleteCardFromHand(card);
                        duelModel.addCardToGraveyard(duelModel.turn, card);
                        int indexOfCardActivated = duelModel.getSpellsAndTrapsInFiled().get(1 - duelModel.turn).
                                indexOf(cardActivated);
                        deleteASpell(1 - duelModel.turn, indexOfCardActivated + 1, cardActivated);
                        int place = Integer.parseInt(duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap).split("/")[2]);
                        duelModel.deleteSpellAndTrap(duelModel.turn, place - 1);
                        duelModel.addCardToGraveyard(duelModel.turn, trap);
                        return "trap activated";
                    }
                }
                duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
                return "";
            }
        }
    }


    public void activeNormalTraps() {
        Card trap = duelModel.getSelectedCards().get(duelModel.turn).get(0);
        int place = Integer.parseInt(duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap).split("/")[2]);
        String condition = duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap);
        duelModel.changeSpellAndTrapCondition(duelModel.turn, place, condition.replaceAll("H", "O"));
    }

    public String activeTrapMindCrush() {
        Card trap = duelModel.getSelectedCards().get(duelModel.turn).get(0);
        activeNormalTraps();
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(trap, false);
        duelController.isOpponentHasAnySpellOrTrapForActivate();
        int place = Integer.parseInt(duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap).split("/")[2]);
        if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(trap)) {
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
            duelModel.deleteSpellAndTrap(duelModel.turn, place - 1);
            duelModel.addCardToGraveyard(duelModel.turn, trap);
//        String condition = duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap);
//        duelModel.changeSpellAndTrapCondition(duelModel.turn, place,condition.replaceAll("H","O"));
            String cardName = duelView.getCardNameForTrapMindCrush();
            ArrayList<ArrayList<Card>> cardsInHand = duelModel.getHandCards();
            boolean isCardExistInOpponentHand = false;
            for (Card card : cardsInHand.get(duelModel.turn - 1)) {
                if (card.getName().equals(cardName)) {
                    duelModel.deleteCardFromOpponentHand(card);
                    isCardExistInOpponentHand = true;
                }
            }
            if (!isCardExistInOpponentHand) {
                ArrayList<Integer> number = new ArrayList<>();
                for (int i = 0; i < duelModel.getHandCards().get(duelModel.turn).size(); i++) {
                    number.add(i + 1);
                }
                Collections.shuffle(number);
                Card card = duelModel.getHandCards().get(duelModel.turn).get(number.get(0));
                duelModel.deleteCardFromHand(card);
                duelModel.addCardToGraveyard(duelModel.turn, card);
            }
            return "trap activated";
        }
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
        return "";
    }

    public String activeTrapCallOfTheHaunted() {
        Card trap = duelModel.getSelectedCards().get(duelModel.turn).get(0);
        activeNormalTraps();
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).put(trap, false);
        duelController.isOpponentHasAnySpellOrTrapForActivate();
        int place = Integer.parseInt(duelModel.getDetailOfSelectedCard().get(duelModel.turn).get(trap).split("/")[2]);
        if (!duelModel.getSpellOrTrapActivated().get(duelModel.turn).get(trap)) {
            duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
            duelModel.deleteSpellAndTrap(duelModel.turn, place - 1);
            duelModel.addCardToGraveyard(duelModel.turn, trap);
            ArrayList<Card> graveyard = duelModel.getGraveyard(duelModel.turn);
            for (int i = 0; i < graveyard.size() - 1; i++) {
                Card card = graveyard.get(i);
                if (card.getCategory().equals("Monster")) {
                    ArrayList<Card> monsters = duelModel.getMonstersInField().get(duelModel.turn);
                    for (int j = 0; j < 5; j++) {
                        if (monsters.get(j) == null) {
                            duelModel.addCertainMonsterFromGraveyardToGame(duelModel.turn, "OO/" + j + 1, j, card);
                            duelModel.deleteCardFromGraveyard(duelModel.turn, i);
                            return "trap activated";
                        }
                    }
                }
            }
        }
        duelModel.getSpellOrTrapActivated().get(duelModel.turn).remove(trap);
        return "";
    }

    public ArrayList<String> showGraveYard(int turn) {
        ArrayList<Card> graveyardCards = duelModel.getGraveyard(turn);
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < graveyardCards.size(); i++) {
            output.add(i + 1 + ". " + graveyardCards.get(i).getName() + ": " + graveyardCards.get(i).getDescription());
        }
        if (graveyardCards.size() == 0) {
            output.add("graveyard empty");
        }
        return output;
    }

    public ArrayList<String> checkCard(Matcher matcher) {
        ArrayList<String> output = new ArrayList<>();
        if (Card.getCardByName(matcher.group(1)) == null) {
            output.add("card with name " + matcher.group(1) + " does not exist");
        } else {
            Card card = Card.getCardByName(matcher.group(1));
            if (card.getCategory().equals("Monster")) {
                output = showMonster(matcher.group(1));
            } else if (card.getCategory().equals("Spell")) {
                output = showSpell(matcher.group(1));
            } else {
                output = showTrap(matcher.group(1));
            }
        }
        return output;
    }

    public ArrayList<String> checkSelectedCard(Matcher matcher) {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<ArrayList<Card>> selectedCards = duelModel.getSelectedCards();
        ArrayList<HashMap<Card, String>> detailsOfSelectedCards = duelModel.getDetailOfSelectedCard();
        if (selectedCards.get(duelModel.turn).get(0) == null) {
            output.add("no card is selected yet");
        } else {
            Card card = selectedCards.get(duelModel.turn).get(0);
            if (detailsOfSelectedCards.get(duelModel.turn).get(card).equals("Opponent/H")) {
                output.add("card is not visible");
            } else {
                if (card.getCategory().equals("Monster")) {
                    output = showMonster(card.getName());
                } else if (card.getCategory().equals("Spell")) {
                    output = showSpell(card.getName());
                } else {
                    output = showTrap(card.getName());
                }
            }
        }
        return output;
    }

    private ArrayList<String> showMonster(String cardName) {
        Monster monster = Monster.getMonsterByName(cardName);
        ArrayList<String> output = new ArrayList<>();
        output.add("Name: " + monster.getName());
        output.add("Level: " + monster.getLevel());
        output.add("Type: " + monster.getMonsterType());
        output.add("ATK: " + monster.getAttackPower());
        output.add("DEF: " + monster.getDefensePower());
        output.add("Description: " + monster.getDescription());
        return output;
    }

    private ArrayList<String> showSpell(String cardName) {
        Spell spell = Spell.getSpellByName(cardName);
        ArrayList<String> output = new ArrayList<>();
        output.add("Name: " + spell.getName());
        output.add("Spell");
        output.add("Type: " + spell.getCardType());
        output.add("Description: " + spell.getDescription());
        return output;
    }

    private ArrayList<String> showTrap(String cardName) {
        Trap trap = Trap.getTrapByName(cardName);
        ArrayList<String> output = new ArrayList<>();
        output.add("Name: " + trap.getName());
        output.add("Trap");
        output.add("Type: " + trap.getCardType());
        output.add("Description: " + trap.getDescription());
        return output;
    }

    public String surrender() {
        return null;
    }

    public String selectMonster(Matcher matcher) {
        if (duelModel.getMonster(duelModel.turn, Integer.parseInt(matcher.group(1))) == null) {
            return "no card found in the given position";
        } else {
            String condition = "My/";
            condition = condition + duelModel.getMonsterCondition(duelModel.turn, Integer.parseInt(matcher.group(1)));
            duelModel.setSelectedCard(duelModel.turn, duelModel.getMonster(duelModel.turn,
                    Integer.parseInt(matcher.group(1))), condition);
            return "card selected";
        }
    }


    public String selectOpponentMonster(Matcher matcher) {
        if (duelModel.getMonster(duelModel.turn - 1, Integer.parseInt(matcher.group(1))) == null) {
            return "no card found in the given position";
        } else {
            String condition = "Opponent/";
            condition = condition + duelModel.getMonsterCondition(duelModel.turn - 1, Integer.parseInt(matcher.group(1)));
            duelModel.setSelectedCard(duelModel.turn, duelModel.getMonster(duelModel.turn - 1,
                    Integer.parseInt(matcher.group(1))), condition);
            return "card selected";
        }
    }


    public String selectSpellOrTrap(Matcher matcher) {
        if (duelModel.getSpellAndTrap(duelModel.turn, Integer.parseInt(matcher.group(1))) == null) {
            return "no card found in the given position";
        } else {
            String condition = "My/";
            condition = condition + duelModel.getMonsterCondition(duelModel.turn, Integer.parseInt(matcher.group(1)));
            duelModel.setSelectedCard(duelModel.turn, duelModel.getSpellAndTrap(duelModel.turn,
                    Integer.parseInt(matcher.group(1))), condition);
            return "card selected";
        }
    }

    public String selectOpponentSpellOrTrap(Matcher matcher) {
        if (duelModel.getSpellAndTrap(duelModel.turn - 1, Integer.parseInt(matcher.group(1))) == null) {
            return "no card found in the given position";
        } else {
            String condition = "Opponent/";
            condition = condition + duelModel.getMonsterCondition(duelModel.turn - 1, Integer.parseInt(matcher.group(1)));
            duelModel.setSelectedCard(duelModel.turn, duelModel.getSpellAndTrap(duelModel.turn - 1,
                    Integer.parseInt(matcher.group(1))), condition);
            return "card selected";
        }
    }

    public String selectFieldZone(int place) {
        if (duelModel.getField().get(place) == null) {
            return "no card found in the given position";
        } else {
            duelModel.setSelectedCard(duelModel.turn, duelModel.getFieldZoneCard(duelModel.turn), "My/Filed/" + place);
            return "card selected";
        }
    }

    public String selectOpponentFieldZone(int place) {
        if (duelModel.getFieldZoneCard(duelModel.turn - 1) == null) {
            return "no card found in the given position";
        } else {
            duelModel.setSelectedCard(duelModel.turn, duelModel.getFieldZoneCard(duelModel.turn - 1), "Opponent/Field/" + place);
            return "card selected";
        }
    }


    public String selectHand(Matcher matcher) {
        if (duelModel.getHandCards().get(duelModel.turn).size() < Integer.parseInt(matcher.group(1))) {
            return "invalid selection";
        } else {
            duelModel.setSelectedCard(duelModel.turn, duelModel.getHandCards().get(duelModel.turn).
                    get(Integer.parseInt(matcher.group(1)) - 1), "Hand");
            return "card selected";
        }
    }

    public void hasSwordCard() {
        for (Map.Entry<Card, Integer> entry : duelModel.getSwordsCard().get(duelModel.turn).entrySet()) {
            Card swordCard = entry.getKey();
            int numberOfTurnExist = entry.getValue();
            if (numberOfTurnExist >= 5) {
                duelModel.deleteSwordsCard(duelModel.turn, swordCard);
            } else {
                entry.setValue(numberOfTurnExist + 1);
            }
        }
        for (Map.Entry<Card, Integer> entry : duelModel.getSwordsCard().get(1 - duelModel.turn).entrySet()) {
            Card swordCard = entry.getKey();
            int numberOfTurnExist = entry.getValue();
            if (numberOfTurnExist >= 5) {
                duelModel.deleteSwordsCard(1 - duelModel.turn, swordCard);
            } else {
                entry.setValue(numberOfTurnExist + 1);
            }
        }
    }

    public void hasSupplySquadCard() {
        ArrayList<Card> monsterDestroyedInThisTurn1 = duelModel.getMonsterDestroyedInThisTurn()
                .get(duelModel.turn);
        ArrayList<Card> monsterDestroyedInThisTurn2 = duelModel.getMonsterDestroyedInThisTurn()
                .get(1 - duelModel.turn);
        ArrayList<Card> supplyCards1 = duelModel.getSupplySquadCards().get(duelModel.turn);
        ArrayList<Card> supplyCards2 = duelModel.getSupplySquadCards().get(1 - duelModel.turn);
        if (monsterDestroyedInThisTurn1.size() > 0) {
            if (supplyCards1.size() > 0) {
                for (int i = 0; i < supplyCards1.size(); i++) {
                    duelModel.addCardFromDeckToHandInMiddleOfGame(duelModel.turn
                            , duelModel.getPlayersCards().get(duelModel.turn).get(duelModel.
                                    getPlayersCards().get(duelModel.turn).size() - 1));
                }
            }
        } else if (monsterDestroyedInThisTurn2.size() > 0) {
            if (supplyCards2.size() > 0) {
                for (int j = 0; j < supplyCards2.size(); j++) {
                    duelModel.addCardFromDeckToHandInMiddleOfGame(duelModel.turn
                            , duelModel.getPlayersCards().get(duelModel.turn).get(duelModel.
                                    getPlayersCards().get(duelModel.turn).size() - 1));
                }
            }
        }
    }

    public void hasSomeCardsInsteadOfScanner() {
        HashMap<Card, Integer> cardsInsteadOfScanner = duelModel.getCardsInsteadOfScanners();
        if (cardsInsteadOfScanner.size() > 0) {
            for (Map.Entry<Card, Integer> entry : cardsInsteadOfScanner.entrySet()) {
                Monster monster = new Monster("Scanner", "Once per turn, you can select 1 of your opponent's monsters" +
                        " that is removed from play. Until the End Phase, this card's name is treated" +
                        " as the selected monster's name, and this card has the same Attribute, Level, ATK," +
                        " and DEF as the selected monster. If this card is removed from the field while this effect" +
                        " is applied, remove it from play.", "Effect", 8000, "Monster",
                        0, 0, "Machine", "LIGHT", 1, false);
                int placeOfScanner = entry.getValue();
                duelModel.getMonstersInField().get(duelModel.turn).add(placeOfScanner - 1, monster);
            }
        }
    }


}