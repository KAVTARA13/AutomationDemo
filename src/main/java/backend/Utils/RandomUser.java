package backend.Utils;

import java.util.Random;

public class RandomUser {
    public String getUniqueUsername() {
        Random rand = new Random();

        return "user000000000" + rand.nextInt(Integer.MAX_VALUE);
    }
}
