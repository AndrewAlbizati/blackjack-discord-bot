package com.github.AndrewAlbizati;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck extends ArrayList<Card> {
    public Deck() {

    }

    public Deck(int size) {
        createDeck(size);
    }

    public void createDeck(int size) {
        clear();
        ArrayList<Card> temp = new ArrayList<>();
        for (int value = 0; value < 13; value++) {
            for (Suit suit: Suit.values()) {
                temp.add(new Card(value + 1, suit));
            }
        }

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int index = rand.nextInt(temp.size());
            add(temp.get(index));
            temp.remove(index);
        }
    }

    public void shuffleDeck() {
        ArrayList<Card> temp = new ArrayList<>();
        for (int i = 0; i < super.size(); i++) {
            temp.add(super.get(i));
        }
        clear();
        Collections.shuffle(temp);
        for (Card c : temp) {
            super.add(c);
        }
    }

    public void printDeck() {
        for (int i = 0; i < super.size(); i++) {
            Card c = super.get(i);
            System.out.println(c.getName());
        }
    }

    public void sortDeck() {
        Object[] cards = this.toArray();
        boolean sorted = false;
        Card temp;

        while(!sorted) {
            sorted = true;
            for (int i = 0; i < cards.length - 1; i++) {
                Card c = (Card) cards[i];
                Card nextC = (Card) cards[i + 1];
                if (compare(c, nextC) == cards[i]) {
                    temp = c;
                    cards[i] = cards[i+1];
                    cards[i+1] = temp;
                    sorted = false;
                }
            }
        }
        clear();
        for (int i = 0; i < cards.length; i++) {
            add((Card) cards[i]);
        }
    }

    private static Card compare(Card c1, Card c2) {
        if (c1.getValue() > c2.getValue()) {
            return c1;
        } else if (c2.getValue() > c1.getValue()) {
            return c2;
        }
        return null;
    }

    public void sortDeckWithSuits() {
        ArrayList<Integer> spades = new ArrayList<>();
        ArrayList<Integer> hearts = new ArrayList<>();
        ArrayList<Integer> diamonds = new ArrayList<>();
        ArrayList<Integer> clubs = new ArrayList<>();

        for (int i = 0; i < super.size(); i++) {
            Card c = super.get(i);
            switch(c.getSuit()) {
                case SPADE:
                    spades.add(c.getValue());
                    break;
                case HEART:
                    hearts.add(c.getValue());
                    break;
                case DIAMOND:
                    diamonds.add(c.getValue());
                    break;
                case CLUB:
                    clubs.add(c.getValue());
                    break;
            }
        }
        Collections.sort(spades);
        Collections.sort(hearts);
        Collections.sort(diamonds);
        Collections.sort(clubs);


        clear();

        ArrayList[] cardsBySuit = new ArrayList[]{spades, hearts, diamonds, clubs};
        for (int i = 0; i < cardsBySuit.length; i++) {
            ArrayList<Integer> currentSuitDeck = cardsBySuit[i];
            for (Integer value : currentSuitDeck) {
                add(new Card(value, Suit.values()[i]));
            }
        }
    }

    public Card getRandomCard() {
        Card c = remove(new Random().nextInt(size()));
        return c;
    }

    public void reverseDeck() {
        Collections.reverse(this);
    }

    public Deck[] deal(int pCount, int cardsPerP) {
        Deck[] dealtHands = new Deck[pCount];
        if (pCount * cardsPerP > super.size()) {
            return null;
        }

        for (int i = 0; i < pCount; i++) {
            Deck tempDeck = new Deck();
            for (int j = 0; j < cardsPerP; j++) {
                tempDeck.add(super.get(0));
                super.remove(0);
            }
            dealtHands[i] = tempDeck;
        }

        return dealtHands;
    }
}
