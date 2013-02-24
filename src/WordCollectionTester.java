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
        //Test Case 1: check for etheme
            WordCollection etheme1RandomWords = collection.getSomeUniqueRandomWords(7,3,"etheme1");
        //Test Case 2: check for ltheme
            WordCollection ltheme1RandomWords = collection.getSomeUniqueRandomWords(7,3,"ltheme1");
        //Test Case 3: check for no theme and go to random words
            WordCollection blankRandomWords = collection.getSomeUniqueRandomWords(7,3, "");
        //Test Case 4: check for null and go to random words
            WordCollection nullRandomWords = collection.getSomeUniqueRandomWords(7,3,null);
        //Test Case 5: check for large collection request and throw exception
            //WordCollection largeRandomWords = collection.getSomeUniqueRandomWords(150,3,"ltheme1");
        //Test Case 6: check for less than word length parameter and throw exception.
            //WordCollection characterRandomWords = collection.getSomeUniqueRandomWords(7,1,"ltheme1");
        //Test Case 7: request a collection larger than what is available
        WordCollection largeLThemeRandomWords = collection.getSomeUniqueRandomWords(15,3,"ltheme1");
    }

    public static void main(String args[]){
              WordCollectionTester wordCollectionTester = new WordCollectionTester();
    }
}
