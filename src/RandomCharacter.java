import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA3
 * Date: 2/24/13
 * Time: 6:01 PM
 * Java Class: PACKAGE_NAME
 */
public class RandomCharacter {

        private Random r = new Random();

        private final String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z" };
    public String nextCharacter() {
        int index = r.nextInt(26);

        return alphabet[index];

    }
}
