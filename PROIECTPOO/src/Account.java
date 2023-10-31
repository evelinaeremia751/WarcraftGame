import java.util.LinkedList;
import java.util.List;

public class Account {
    public static class Information {
        private Credentials credentials;
        private String name, country;

        private Information(InformationBuilder informationBuilder) {
            this.credentials = informationBuilder.credentials;
            this.name = informationBuilder.name;
            this.country = informationBuilder.country;
        }

        public static class InformationBuilder {
            private Credentials credentials;
            private String name, country;
            private List<String> favoriteGames;

            public InformationBuilder setCredentials(final Credentials credentials) {
                this.credentials = credentials;
                return this;
            }

            public InformationBuilder setName(final String name) {
                this.name = name;
                return this;
            }

            public InformationBuilder setCountry(final String country) {
                this.country = country;
                return this;
            }

            public InformationBuilder setFavoriteGames(final List<String> favoriteGames) {
                this.favoriteGames = favoriteGames;
                return this;
            }

            public Information build() throws InformationIncompleteException {
                if (credentials == null || name == null)
                    throw new InformationIncompleteException("Missing credentials or name!");

                return new Information(this);
            }
        }
    }

    private Information information;
    public List<Character> characters;
    private int noOfGames;

    public Account(final Information information, final List<Character> characters, final int noOfGames) {
        this.information = information; this.characters = characters; this.noOfGames = noOfGames;
    }

    public Character getRandomCharacter() {
        return characters.get((int)Math.floor(characters.size() * Math.random()));
    }

    public String toString() {
        String s = new String();

        s += "Account\n";
        s += "email: " + information.credentials.getEmail() + "\n";
        s += "password: " + information.credentials.getPassword() + "\n";

        return s;
    }
}
