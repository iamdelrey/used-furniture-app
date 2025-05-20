import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class FuzzTester {
    private static final String BASE = "http://localhost:8080";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Random RND = new Random();

    public static void main(String[] args) throws Exception {
        System.out.println("=== Fuzzing /register ===");
        fuzzRegister(20);

        System.out.println("\n=== Fuzzing /login ===");
        fuzzLogin(20);
    }

    private static void fuzzRegister(int runs) {
        for (int i = 0; i < runs; i++) {
            String user = randomString( RND.nextInt(1, 16) );
            String pass = randomString( RND.nextInt(1, 16) );
            String email = randomEmail();

            String json = String.format(
                    "{\"username\":\"%s\",\"password\":\"%s\",\"email\":\"%s\"}",
                    escape(user), escape(pass), escape(email)
            );
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(BASE + "/register"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            sendAndPrint(req);
        }
    }

    private static void fuzzLogin(int runs) {
        for (int i = 0; i < runs; i++) {
            String user = randomString( RND.nextInt(1, 16) );
            String pass = randomString( RND.nextInt(1, 16) );

            String json = String.format(
                    "{\"username\":\"%s\",\"password\":\"%s\"}",
                    escape(user), escape(pass)
            );
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(BASE + "/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            sendAndPrint(req);
        }
    }

    private static void sendAndPrint(HttpRequest req) {
        try {
            HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            System.out.printf("%s %s → %d %n%s%n%n",
                    req.method(),
                    req.uri().getPath(),
                    resp.statusCode(),
                    trim(resp.body(), 200)
            );
        } catch (IOException | InterruptedException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private static String randomString(int len) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(RND.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private static String randomEmail() {
        return randomString( RND.nextInt(3, 8) )
                + "@"
                + randomString( RND.nextInt(3, 5) )
                + "."
                + (RND.nextBoolean() ? "com" : "ru");
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String trim(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max) + "...";
    }
}
