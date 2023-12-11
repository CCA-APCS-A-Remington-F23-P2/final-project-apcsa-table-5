import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File db = new File("Database.txt");
        FlappyRaven.main(args, db);
    }

}