
import java.io.*;
import java.security.PublicKey;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS 340 - Assignment 1
 * Date: 1/14/13
 * Time: 10:03 PM
 * Java Class: WordCollection
 *
 */

public class WordCollection {
               ArrayList<Word> words;
                int bigCounter = 0;


  public WordCollection() {
      words = new ArrayList<Word>();
      try {

          FileReader fileReader = new FileReader("C:\\ics340\\words.txt");
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          String strLine;

          while ((strLine = bufferedReader.readLine()) != null){


              bigCounter++;
              addWord(strLine);

          }

      } catch (FileNotFoundException e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      } catch (IOException e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
  }
  public WordCollection(String a_file_name){


      if (a_file_name == "clear"){
          words = new ArrayList<Word>();
      }   else{

          words = new ArrayList<Word>();

      try {

          FileReader fileReader = new FileReader(a_file_name);
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          String strLine;

          while ((strLine = bufferedReader.readLine()) != null){


              bigCounter++;
              addWord(strLine);

          }

      } catch (FileNotFoundException e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      } catch (IOException e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
      }
  }

  private void addWord(String a_line) throws IOException {
      String currentWordKey = null;
      int counter = 1;
      Word newWord;
      String englishInEnglish =null;
      String englishInLang =null;
      String langInLang=null;
      String langInEnglish=null;
      String hintInEnglish=null;
      String hintInLang=null;
      String soundURIOfEnglish=null;
      String soundURIOfLang=null;
      String imageOneURI=null;
      String imageTwoURI=null;
      String descriptionInEnglish=null;
      String descriptionInLang=null;
      ArrayList<String> themesinEnglish=new ArrayList<String>();
      ArrayList<String> themesinLang=new ArrayList<String>();
      String reservedOne=null;
      String reservedTwo=null;
      String currentString = null;

      String newLine = System.getProperty("line.separator");
      boolean hasNewLine = a_line.contains(newLine);
      Scanner parser = new Scanner(a_line);
     //Sets the delimiters to space and comma.  Tells scanner to match a comma and zero or more spaces as delimiters.
      //
      if (hasNewLine){
          parser.useDelimiter("=|\\n");
         currentString = parser.next();
      }  else{
          parser.useDelimiter(", *|\\n");
      }

     while (parser.hasNext()){
         currentString = parser.next();
         //currentString = currentString.replaceAll("\t","");


             switch (counter) {
                 case 1:
                     englishInEnglish = currentString;
                     break;
                 case 2:
                     englishInLang = currentString;
                     break;
                 case 3:
                     langInLang = currentString;
                     break;
                 case 4:
                     langInEnglish = currentString;
                     break;
                 case 5:
                     hintInEnglish = currentString;
                     break;
                 case 6:
                     hintInLang = currentString;
                     break;
                 case 7:
                     soundURIOfEnglish = currentString;
                     break;
                 case 8:
                     soundURIOfLang = currentString;
                     break;
                 case 9:
                     imageOneURI = currentString;
                     break;
                 case 10:
                     imageTwoURI = currentString;
                     break;
                 case 11:
                     descriptionInEnglish = currentString;
                     break;
                 case 12:
                     descriptionInLang = currentString;
                     break;
                 case 13:
                          if (hasNewLine){
                              currentString = currentString.replaceAll(",","\t");
                              currentString = currentString.replaceAll("\\[","\t");
                              currentString = currentString.replaceAll("\\]","\t");
                          }
                     ArrayList<String> ethemes = new ArrayList<String>();
                     StringTokenizer stringTokenizer = new StringTokenizer(currentString);
                     while (stringTokenizer.hasMoreTokens()) {
                         String token = stringTokenizer.nextToken();
                         ethemes.add(token);
                     }
                     themesinEnglish = ethemes;
                     break;
                 case 14:
                     if (hasNewLine){
                         currentString = currentString.replaceAll(",","\t");
                         currentString = currentString.replaceAll("\\[","\t");
                         currentString = currentString.replaceAll("\\]","\t");
                     }
                     ArrayList<String> lthemes = new ArrayList<String>();
                     stringTokenizer = new StringTokenizer(currentString, "\t\n\r\f,");
                     while (stringTokenizer.hasMoreTokens()) {
                         String token = stringTokenizer.nextToken();
                         lthemes.add(token);
                     }
                     themesinLang = lthemes;
                     break;
                 case 15:
                     reservedOne = currentString;
                     break;
                 case 16:
                     reservedTwo = currentString;
                     break;
                 default:
                     break;

             }
             //Throw error for no space and double consecutive space


             counter++;
             if (hasNewLine  && parser.hasNext()){

                 currentString = parser.next();
             }
         }
     //}
      if(counter <= 16){
          throw new IOException("Test file has error on Line " + bigCounter +".  Line is missing a field/comma.");
      }
      if(counter >=18){
          throw new IOException("Test file has error on Line " + bigCounter +".  Line has too many fields/commas.");
      }
      newWord = new Word(englishInEnglish, englishInLang, langInLang, langInEnglish, hintInEnglish, hintInLang, soundURIOfEnglish, soundURIOfLang, imageOneURI, imageTwoURI, descriptionInEnglish, descriptionInLang, themesinEnglish, themesinLang, reservedOne, reservedTwo);
      parser.close();
       if (!hasNewLine){
         String newWordKey = newWord.getEnglishInEnglish() + newWord.getLangInLang();
      newWordKey.replaceAll("\\s", "");
        for(Word currentWord: words){
             currentWordKey= currentWord.getEnglishInEnglish() + currentWord.getLangInLang();
              currentWordKey.replaceAll("\\s","");
            boolean checkValue = newWordKey.equals(currentWordKey);
            if (checkValue == true){
                throw new IOException("Line " + bigCounter + " is a duplicate entry.");
            }
        }


       }
      this.words.add(newWord);


      }
     private ArrayList<String> getAllThemesInEnglish(){
         ArrayList<String> themesInEnglish = new ArrayList<String>();
         for (Word currentWord : words) {
             themesInEnglish.addAll(currentWord.getThemesinEnglish());

         }
         HashSet<String> hashSet = new HashSet<String>();
         hashSet.addAll(themesInEnglish);
         themesInEnglish.clear();
         themesInEnglish.addAll(hashSet);

         return themesInEnglish;
     }
    protected WordCollection getWordCollectionforEnglishTheme(String a_english_theme) {
        WordCollection themeWords = new WordCollection("clear");

        for (Word currentWord: words){
            ArrayList<String> themes = currentWord.getThemesinEnglish();
            for (String currentTheme : themes){
                if (currentTheme.equals(a_english_theme)){
                    try {
                        themeWords.addWord(String.valueOf(currentWord));
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        }
        return themeWords;
    }

    protected WordCollection getSomeRandomWords(int some_number) {
        WordCollection randomCollection = new WordCollection("clear");
        Random randomGenerator = new Random();
        if (some_number > words.size()){
        randomCollection.words = words;
    }    else{
            for (int randomCounter=0; randomCounter < some_number; randomCounter++){
            int index = randomGenerator.nextInt(words.size());
            Word word = words.get(index);
                try {
            randomCollection.addWord(String.valueOf(word));
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
    }
        }
        return randomCollection;
    }

    protected ArrayList<String> getEnglishInEnglishCollection() {
        ArrayList<String> englishInEnglish= new ArrayList<String>();
        for (Word currentWord: words){
               englishInEnglish.add(currentWord.getEnglishInEnglish());
        }

        return englishInEnglish;
    }


    public String toString(){
        String temp = "\nE_in_E\t\t\t\t\tE_in_L\t\t\t\t\tL_in_L\t\t\t\t\tL_in_E";
        for (Word currentWord: words){
            temp = temp + "\n"+currentWord.getEnglishInEnglish()+"\t"+currentWord.getEnglishInLang()+"\t"+currentWord.getLangInLang()+"\t"+currentWord.getLangInEnglish()+"\t";
        }

        return temp;
    }

    public static void main(String args[]){
        System.out.println("Starting Program");
         WordCollection collection = new WordCollection("testwordsPA3.txt");
        System.out.println("Done adding words to collection");
        System.out.println("Get all English Themes");
        ArrayList<String> englishThemes = collection.getAllThemesInEnglish();
        System.out.println("English Themes: " + englishThemes);
        WordCollection englishThemeCollection = collection.getWordCollectionforEnglishTheme("etheme1");
        System.out.println(englishThemeCollection);
        englishThemeCollection = collection.getWordCollectionforEnglishTheme("etheme2");
        System.out.println(englishThemeCollection);
        englishThemeCollection = collection.getWordCollectionforEnglishTheme("etheme3");
        System.out.println(englishThemeCollection);
        englishThemeCollection = collection.getWordCollectionforEnglishTheme("etheme4");
        System.out.println(englishThemeCollection);
        englishThemeCollection = collection.getWordCollectionforEnglishTheme("ethemex");
        System.out.println(englishThemeCollection);
        WordCollection randomWordCollection = collection.getSomeRandomWords(23);
        System.out.println(randomWordCollection);
        randomWordCollection = collection.getSomeRandomWords(150);
        System.out.println(randomWordCollection);
        ArrayList<String> englishInEnglishCollection = collection.getEnglishInEnglishCollection();
        System.out.println("English In English: " + englishInEnglishCollection);
        System.out.println("Exiting Program.");
    }




}