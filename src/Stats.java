public class Stats {

    Stats(int playerLevel, int numEnemiesEncountered, int numPotionsUsed, int totalRun, int totalDamageDealt, int totalDamageTaken) {
        System.out.println("--------------------------------------------------");
        System.out.println("Game Over!");
        System.out.println("--------------------------------------------------");
        System.out.println("STATS:");
        System.out.println("--------------------------------------------------");
        System.out.printf("You reached: LEVEL %s.\n", playerLevel);
        System.out.printf("You encountered: %s enemies.\n", numEnemiesEncountered);
        System.out.printf("You used: %s health potions.\n", numPotionsUsed);
        System.out.printf("You ran away: %s time(s).\n", totalRun);
        System.out.printf("You dealt a total of: %s damage.\n", totalDamageDealt);
        System.out.printf("You received a total of: %s damage.\n", totalDamageTaken);
        System.out.println();
        System.out.println("########################");
        System.out.println("# THANKS FOR PLAYING!! #");
        System.out.println("########################");

        System.exit(0);
    }
}
