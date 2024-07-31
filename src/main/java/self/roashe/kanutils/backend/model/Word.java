package self.roashe.kanutils.backend.model;

import java.util.List;

public class Word {
    private String japanese;
    private List<String> english;
    private List<String> readings;

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
}
