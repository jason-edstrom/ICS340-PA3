/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA3
 * Date: 2/23/13
 * Time: 4:48 PM
 * Java Class: PACKAGE_NAME
 */
public class WordCollectionTester {

    public WordCollectionTester(){
            WordCollection collection = new WordCollection("TestWordsPA3_correct.txt");
            collection.getSomeUniqueRandomWords(7,3, "etheme1");
    }

    public static void main(String args[]){
              WordCollectionTester wordCollectionTester = new WordCollectionTester();
    }
}
