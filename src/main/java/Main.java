
import model.Card;
import service.FileUtil;
import service.implementation.FileUtilImpl;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FileUtil fileUtil = new FileUtilImpl();
        List<List<List<Card>>> hands = fileUtil.readFile();

    }
}
