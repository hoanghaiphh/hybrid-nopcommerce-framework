package commons;

import java.util.Random;

public class BaseTest {
    protected long getRandomNumber() {
        return new Random().nextInt(999999);
    }
}
