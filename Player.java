public class Player {
    private String name;
    private Mark mark;
    private int score;

    public Player(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
        this.score = 0;
    }

    // Constructor overloading
    public Player(Mark mark) {
        this("Player " + mark, mark);
    }

    public String getName() {
        return name;
    }

    public Mark getMark() {
        return mark;
    }

    public int getScore() {
        return score;
    }

    public void addPoint() {
        score++;
    }

    public void resetScore() {
        score = 0;
    }
}
