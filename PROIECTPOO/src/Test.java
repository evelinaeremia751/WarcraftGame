import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Test {
    public static List<Account> parseAccounts() throws IOException, InformationIncompleteException {
        List<Account> gameAccounts = new ArrayList<>();

        String data = new String(Files.readAllBytes(Paths.get("accounts.json")));
        JSONArray accounts = new JSONObject(data).getJSONArray("accounts");

        for (int i = 0; i < accounts.length(); i++) {
            JSONObject jsonObject = accounts.getJSONObject(i);
            JSONObject credentials = jsonObject.getJSONObject("credentials");

            JSONArray characters = jsonObject.getJSONArray("characters");
            JSONArray favoriteGames = jsonObject.getJSONArray("favorite_games");

            List<Character> gameCharacters = new ArrayList<>();
            List<String> gameFavoriteGames = new ArrayList<>();

            for (int j = 0; j < characters.length(); j++) {
                String name = characters.getJSONObject(j).getString("name");
                String profession = characters.getJSONObject(j).getString("profession");

                int level = characters.getJSONObject(j).getInt("level");
                int experience = characters.getJSONObject(j).getInt("experience");

                gameCharacters.add(CharactersFactory.getType(profession, name, level, experience));
            }

            for (int j = 0; j < favoriteGames.length(); j++)
                gameFavoriteGames.add(favoriteGames.getString(j));

            Collections.sort(gameFavoriteGames);

            String email = credentials.getString("email");
            String password = credentials.getString("password");

            String name = jsonObject.getString("name");
            String country = jsonObject.getString("country");

            int noOfGames = jsonObject.getInt("maps_completed");

            Account.Information information = new Account.Information.InformationBuilder().
                    setCredentials(new Credentials(email, password)).
                    setFavoriteGames(gameFavoriteGames).
                    setCountry(country).
                    setName(name).
                    build();

            gameAccounts.add(new Account(information, gameCharacters, noOfGames));
        }

        return gameAccounts;
    }

    public static Map<Cell.Type, List<String>> parseStories() throws IOException {
        Map<Cell.Type, List<String>> gameStories = new HashMap<>();

        String data = new String(Files.readAllBytes(Paths.get("stories.json")));
        JSONArray stories = new JSONObject(data).getJSONArray("stories");

        for (int i = 0; i < stories.length(); i++) {
            JSONObject jsonObject = stories.getJSONObject(i);
            String key = jsonObject.getString("type");

            gameStories.putIfAbsent(Cell.getTypeFromString(key), new ArrayList<>());
            gameStories.get(Cell.getTypeFromString(key)).add(jsonObject.getString("value"));
        }

        return gameStories;
    }

    public static void main(String[] args) throws InformationIncompleteException, IOException, InvalidCommandException {
        Game.getInstance().run();
    }
}
