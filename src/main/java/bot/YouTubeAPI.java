package bot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class YouTubeAPI
{
    private static final String YOUTUBE_PREFIX = "http://www.youtube.com/embed/";
    private final String APIKey = "AIzaSyDbm9NwuV8QxYnzuEfhBw7J3-utAg6BZjA";
    private String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=%s&type=video&key=" + APIKey;

    public String getStringVideoBySearchParameter(String parameter) {
        try {
            JSONObject json = getJSONFromURL(String.format(url, parameter.replaceAll(" ", "%20")));
            return YOUTUBE_PREFIX + extractVideoIDFromJSON(json);
        }
        // TODO: throw exception and not show the video to the user
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private Object extractVideoIDFromJSON(JSONObject json) {
        return ((JSONObject)((JSONObject)((JSONArray)json.get("items")).get(0)).get("id")).get("videoId");
    }

    private JSONObject getJSONFromURL(String urlString) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return new JSONObject(result.toString());
    }
}
