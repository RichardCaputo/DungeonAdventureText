public class Stats extends Battle {

    Stats() {
        System.out.println("--------------------------------------------------");
        System.out.println("Game Over!");
        System.out.println("--------------------------------------------------");
        System.out.println("STATS:");
        System.out.println("--------------------------------------------------");
        System.out.printf("You reached: LEVEL %s.\n", player.getPlayerLevel());
        System.out.printf("You encountered: %s enemies.\n", player.getNumEnemiesEncountered());
        System.out.printf("You used: %s health potions.\n", player.getNumPotionsUsed());
        System.out.printf("You ran away: %s time(s).\n", player.getTotalRun());
        System.out.printf("You dealt a total of: %s damage.\n", player.getTotalDamageDealt());
        System.out.printf("You received a total of: %s damage.\n", player.getTotalDamageTaken());
        System.out.println();
        System.out.println("########################");
        System.out.println("# THANKS FOR PLAYING!! #");
        System.out.println("########################");

        System.exit(0);
    }
}
