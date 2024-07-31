package self.roashe.kanutils.backend.model;

import java.util.List;

public class Word {
    private String japanese;
    private List<String> english;

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
}
