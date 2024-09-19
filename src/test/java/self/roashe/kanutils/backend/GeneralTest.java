package self.roashe.kanutils.backend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.model.Kanji;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;

public class GeneralTest {

    @Test
    public void testCollections(){
        System.out.println(Set.copyOf(List.of("A", "B")).equals(Set.copyOf(List.of("B", "A"))));
        System.out.println(List.of("A", "B").equals(List.of("B", "A")));
        System.out.println(Set.copyOf(List.of("A", "B")).hashCode());
        System.out.println(Set.copyOf(List.of("A", "B")).hashCode());
        System.out.println(Set.copyOf(List.of("B", "A")).hashCode());
    }

    @Test
    public void testGet() throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("127.0.0.1:8080/api/kanji/all"))
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//
//        HttpResponse<String> response =
//                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//        Kanji kanji;
//        try {
//            JSONObject json = new JSONObject(response.body());
//            System.out.println(json.toJSONArray(new JSONArray()).getJSONObject(0).getString("kanji");
//        } catch (JSONException e) {
//            throw new IOException("Kanji not found.");
//        }
//        return kanji;
    }
}
