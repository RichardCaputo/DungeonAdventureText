public class Enemy {

    private String type;
    private String weapon;
    private int level;
    private boolean angry;
    private boolean scared;

    Enemy(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
