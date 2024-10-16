package self.roashe.kanutils.backend.dto;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Word {
    private String japanese;
    private List<String> english;
    private List<String> readings;
    private int id;
    private List<String> tags;

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
                "English - " + this.english;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word w = (Word) obj;
            return w.japanese.equals(this.japanese)
                    && Set.copyOf(w.readings).equals(Set.copyOf(this.readings))
                    && Set.copyOf(w.english).equals(Set.copyOf(this.english));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.japanese, Set.copyOf(this.english), Set.copyOf(this.readings));
    }

}
