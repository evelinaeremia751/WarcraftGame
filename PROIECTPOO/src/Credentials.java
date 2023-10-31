public class Credentials {
    private String email, password;

    public Credentials(final String email, final String password) {
        this.email = email; this.password = password;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
