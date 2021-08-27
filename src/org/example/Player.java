package org.example;

import java.util.Random;
import java.util.Scanner;

public class Player {

    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    // Player Variables
    private String playerName;
    private int maxPlayerHealth;
    private int health;
    private int attackDamage;
    private int playerLevel;
    private int playerExp;
    private int numHealthPotions;
    private int healthPotionHealAmount;
    private int numPotionsUsed;
    private int totalDamageDealt;
    private int totalDamageTaken;
    private int totalRun;
    private int numEnemiesEncountered;

    Player(String playerName, int maxPlayerHealth, int health, int attackDamage, int playerLevel, int playerExp,
           int numHealthPotions, int healthPotionHealAmount, int numPotionsUsed, int totalDamageDealt,
           int totalDamageTaken, int totalRun, int numEnemiesEncountered) {
        this.playerName = playerName;
        this.maxPlayerHealth = maxPlayerHealth;
        this.health = health;
        this.attackDamage = rand.nextInt(attackDamage);
        this.playerLevel = playerLevel;
        this.playerExp = playerExp;
        this.numHealthPotions = numHealthPotions;
        this.healthPotionHealAmount = healthPotionHealAmount;
        this.numPotionsUsed = numPotionsUsed;
        this.totalDamageDealt = totalDamageDealt;
        this.totalDamageTaken = totalDamageTaken;
        this.totalRun = totalRun;
        this.numEnemiesEncountered = numEnemiesEncountered;
    }

    public String setPlayerName() {
        System.out.println("What is your name, warrior?");
        return this.playerName = scan.nextLine();
    }
    public String getPlayerName() {
        return this.playerName;
    }
    public int getMaxPlayerHealth() {
        return this.maxPlayerHealth;
    }
    public int setMaxPlayerHealth() {
        return this.maxPlayerHealth += (getMaxPlayerHealth() / 2);
    }
    public int getHealth() {
        return this.health;
    }
    public int decreaseHealth(int enemyAttackDamage) {
        return this.health -= enemyAttackDamage;
    }
    public int restoreHealth() {
        this.health += getHealthPotionHealAmount();
        if (getHealth() > getMaxPlayerHealth()) {
            return this.health = getMaxPlayerHealth();
        }
        else {
            return this.health;
        }
    }
    public int getAttackDamage() { return this.attackDamage; }
    public int setAttackDamage() { return attackDamage; }
    public int getPlayerLevel() { return this.playerLevel; }
    public int getPlayerExp() { return this.playerExp; }
    public int setPlayerExp() { return this.playerExp++; }
    public int getNumHealthPotions() { return this.numHealthPotions; }
    public int increaseNumHealthPotions() {
        return this.numHealthPotions++;
    }
    public void decreaseNumHealthPotions() {
        usePotion();
        setNumPotionsUsed();
    }
    public boolean usePotion() {
        if (getNumHealthPotions() > 0){
            if (getHealth() == getMaxPlayerHealth()) {
                System.out.println("--------------------------------------------------");
                System.out.println("\t> You have full health already!");
                return false;
            }
            else {
                this.numHealthPotions--;
                System.out.println("--------------------------------------------------");
                System.out.printf("""
                        \t> You drank a health potion, healing yourself for %s.
                        \t> You now have %s HP.
                        \t> You have %s health potions left.
                        """,
                        getHealthPotionHealAmount(), restoreHealth(), getNumHealthPotions());
                return true;
            }
        }
        else {
            System.out.println("--------------------------------------------------");
            System.out.println("\t> You have no health potions left!" +
                    " Keep fighting for a chance to get one!");
            return false;
        }
    }
    public int dropPotion(String enemyType, int enemyLevel) {
        int healthPotionDropChance = 75; // Percentage
        if (rand.nextInt(100) < healthPotionDropChance) {
            System.out.println("--------------------------------------------------");
            System.out.printf("\t> The %s LVL%s has dropped a health potion!", enemyType, enemyLevel);
            System.out.printf("\n\t> You now have %s health potion(s).\n", getNumHealthPotions() + 1);
            return increaseNumHealthPotions();
        }
        else {
            return getNumHealthPotions();
        }
    }
    public int getHealthPotionHealAmount() { return this.healthPotionHealAmount; }
    public int setHealthPotionHealAmount() { return this.healthPotionHealAmount += (this.healthPotionHealAmount / 2); }
    public int getNumPotionsUsed() { return this.numPotionsUsed; }
    public int setNumPotionsUsed() { return this.numPotionsUsed++; }
    public int getTotalDamageDealt() { return this.totalDamageDealt; }
    public int setTotalDamageDealt() { return this.totalDamageDealt += this.attackDamage; }
    public int getTotalDamageTaken() { return this.totalDamageTaken; }
    public int setTotalDamageTaken(int enemyAttack) { return this.totalDamageTaken += enemyAttack; }
    public int getTotalRun() { return this.totalRun; }
    public int setTotalRun() { return this.totalRun++; }
    public int getNumEnemiesEncountered() { return this.numEnemiesEncountered; }
    public int setNumEnemiesEncountered() { return this.numEnemiesEncountered++; }

    public int levelUp() {
        setHealthPotionHealAmount();
        this.attackDamage += (getAttackDamage() / 2);
        setMaxPlayerHealth();
        return this.playerLevel++;
    }

}
