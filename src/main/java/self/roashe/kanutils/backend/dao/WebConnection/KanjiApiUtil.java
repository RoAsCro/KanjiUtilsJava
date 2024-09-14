package self.roashe.kanutils.backend.dao.WebConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import self.roashe.kanutils.backend.model.Kanji;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;

public class KanjiApiUtil {

    private static final int OKAY = 200;
    private static final String ENDPOINT = "https://kanjiapi.dev/v1/kanji/";
    private static final String GET = "GET";
    private static final String JSON_ENG = "meanings";
    private static final String JSON_KANJI = "kanji";
    private static final String JSON_KUN = "kun_readings";
    private static final String JSON_ON = "on_readings";

    public static Kanji getKanji(char kanjiChar) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT + kanjiChar))
                .method(GET, HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != OKAY) {
            throw new IOException(response.toString());
        }

        Kanji kanji;
        try {
            JSONObject json = new JSONObject(response.body());
             kanji = mapJSON(json);
        } catch (JSONException e) {
            throw new IOException("Kanji not found.");
        }
        return kanji;

    }

    private static Kanji mapJSON(JSONObject json) throws JSONException {
        Kanji kanji = new Kanji();
        kanji.setKanji(json.getString(JSON_KANJI).charAt(0));
        kanji.setKunReadings(jsonIndexToList(JSON_KUN, json));
        kanji.setOnReadings(jsonIndexToList(JSON_ON, json));
        kanji.setEnglish(jsonIndexToList(JSON_ENG, json));
        return kanji;
    }

    private static List<String> jsonIndexToList(String index, JSONObject json) throws JSONException {
        JSONArray array = json.getJSONArray(index);
        List<String> stringList = new LinkedList<>();
        for (int i = 0 ; i < array.length() ; i++) {
            stringList.add(array.getString(i));
        }
        return stringList;
    }
}
