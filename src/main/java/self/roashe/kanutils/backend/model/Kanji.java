package self.roashe.kanutils.backend.model;

import java.util.List;

public class Kanji {
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
}
