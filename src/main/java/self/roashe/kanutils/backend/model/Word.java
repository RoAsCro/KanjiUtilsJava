package self.roashe.kanutils.backend.model;

import java.util.List;
import java.util.Objects;

public class Word {
    private String japanese;
    private List<String> english;
    private List<String> readings;
    private int id;

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public List<String> getEnglish() {
        return english;
    }

    public void setEnglish(List<String> english) {
        this.english = english;
    }

    public List<String> getReadings() {
        return readings;
    }

    public void setReadings(List<String> readings) {
        this.readings = readings;
    }

    @Override
    public String toString() {
        return "Japanese - " + this.japanese + "\n" +
                "Readings - " + this.readings + "\n" +
                "English" + this.english;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word w = (Word) obj;
            return w.japanese.equals(this.japanese)
                    && w.readings.equals(this.readings)
                    && w.english.equals(this.english);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.japanese, this.english, this.readings);
    }
}
