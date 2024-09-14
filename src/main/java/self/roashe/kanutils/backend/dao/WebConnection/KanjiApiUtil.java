package self.roashe.kanutils.backend.dao.WebConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KanjiApiUtil {

    private static final int OKAY = 200;
    private static final String ENDPOINT = "https://kanjiapi.dev/v1/kanji/";
    private static final String GET = "GET";

    public static String getKanji(char kanji) throws IOException, InterruptedException, JSONException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT + kanji))
                .method(GET, HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != OKAY) {
            throw new IOException(response.toString());
        }

        JSONObject json = new JSONObject(response.body());

        return response.toString();

    }
}
