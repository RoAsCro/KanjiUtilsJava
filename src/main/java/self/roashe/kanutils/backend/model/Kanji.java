package self.roashe.kanutils.backend.model;

import java.util.List;
import java.util.Objects;

public class Kanji {
    private int id;
    private char kanji;
    private List<String> kunReadings;
    private List<String> onReadings;
    private List<String> english;

    public char getKanji() {
        return kanji;
    }

    public void setKanji(char kanji) {
        this.kanji = kanji;
    }

    public List<String> getKunReadings() {
        return kunReadings;
    }

    public void setKunReadings(List<String> kunReadings) {
        this.kunReadings = kunReadings;
    }

    public List<String> getOnReadings() {
        return onReadings;
    }

    public void setOnReadings(List<String> onReadings) {
        this.onReadings = onReadings;
    }

    public List<String> getEnglish() {
        return english;
    }

    public void setEnglish(List<String> english) {
        this.english = english;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Kanji) {
            Kanji kanji = (Kanji) o;
            return kanji.kanji == this.kanji &&
                    kanji.english.equals(this.english) &&
                    kanji.kunReadings.equals(this.kunReadings) &&
                    kanji.onReadings.equals(this.onReadings);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.kanji, this.english, this.kunReadings, this.onReadings);
    }

}
