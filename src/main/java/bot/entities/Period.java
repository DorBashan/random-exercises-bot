package bot.entities;

public enum Period
{
    SHORT(15),
    MID(30),
    LONG(45);

    private int minutes;

    Period(int minutes) {
        this.minutes = minutes;
    }

    public int minutes() {
        return minutes;
    }
}
