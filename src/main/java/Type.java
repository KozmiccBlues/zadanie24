import java.util.Optional;

public enum Type {

    INCOME("wpłata"),
    OUTCOME("wydatek");

    public final String translation;

    Type(String translation) {
        this.translation = translation;
    }

}
