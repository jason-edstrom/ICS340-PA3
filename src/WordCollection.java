
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
          parser.useDelimiter("\\| *|\\n");
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
                     StringTokenizer stringTokenizer = new StringTokenizer(currentString, "\t\n\r\f,\"");
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
                     stringTokenizer = new StringTokenizer(currentString, "\t\n\r\f,\"");
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

    protected WordCollection getWordCollectionforLangTheme(String a_lang_theme) {
        WordCollection themeWords = new WordCollection("clear");

        for (Word currentWord: words){
            ArrayList<String> themes = currentWord.getThemesinLang();
            for (String currentTheme : themes){
                if (currentTheme.equals(a_lang_theme)){
                    try {
                        themeWords.addWord(String.valueOf(currentWord));
                        //themeWords.words.add(currentWord);
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
               englishInEnglish.add(currentWord.getEnglishInEnglish().replaceAll("\\s",""));
        }

        return englishInEnglish;
    }

    WordCollection getSomeUniqueRandomWords(int some_number, int min_length_of_word, String a_theme){
        if (min_length_of_word < 2){
            throw new NegativeArraySizeException("Minimum word length is under 2 characters");
        } else if (words.size() < some_number){
            throw new ArrayIndexOutOfBoundsException("There are not enough words in the collection to process this instruction");
        }
        boolean hasTheme = false;
        boolean hasEnough = true;
        if((a_theme != null) && !(a_theme.isEmpty()) && !(a_theme == "")){
            hasTheme = true;
        }
         WordCollection internalCollection = new WordCollection("clear");
            if (hasTheme){
             internalCollection = getWordCollectionforEnglishTheme(a_theme);
                   if (internalCollection.words.size() == 0){
                       internalCollection = getWordCollectionforLangTheme(a_theme);
                   }

               //Duplication Check and remove

            for (Word currentWord: internalCollection.words){
                     String engInEngCompare = currentWord.getEnglishInEnglish();
                if (!(engInEngCompare.length() >= min_length_of_word)){
                    int index = internalCollection.words.indexOf(engInEngCompare);
                    internalCollection.words.remove(index);
                }else{
                for (int compareCounter = internalCollection.words.indexOf(currentWord)+1; compareCounter < internalCollection.words.size(); compareCounter++){
                    Word compareToWord= internalCollection.words.get(compareCounter);

                    String compareToWordString = compareToWord.getEnglishInEnglish();

                    if (engInEngCompare.equals(compareToWordString)){
                        int index = internalCollection.words.indexOf(compareToWord);
                        internalCollection.words.remove(index);
                    }
                }

                }
            }

            if (internalCollection.words.size() == 0){
                hasEnough = false;
                int remainder = some_number - internalCollection.words.size();
            } else if(internalCollection.words.size() < some_number){
                hasEnough = false;
            }else {
                ArrayList<Word> tempHolderList = new ArrayList<Word>();

                Random randomGenerator = new Random();
                boolean tempEmpty = true;
                do{
                    int index = randomGenerator.nextInt(internalCollection.words.size());
                    Word currentWord = internalCollection.words.get(index);

                    boolean hasDuplicate = false;
                    for (Word checkWord: tempHolderList){

                        if (((currentWord.getEnglishInEnglish().replaceAll("\\s","")).equals(checkWord.getEnglishInEnglish().replaceAll("\\s","")))){
                            hasDuplicate = true;
                        }
                    }

                    if (!(hasDuplicate) || (tempEmpty)){
                       tempHolderList.add(currentWord);
                        tempEmpty = false;
                    }

                }   while (tempHolderList.size() < some_number);
                internalCollection.words = tempHolderList;
            }
        }

        if ((!hasEnough) ^ (!hasTheme) ) {


        Random randomGenerator = new Random();




       do{
           String currentString = null;
           Word currentWord = null;
           //word length check
           do{
           int index = randomGenerator.nextInt(words.size());

           currentWord = words.get(index);
           currentString = currentWord.getEnglishInEnglish();
           }while (currentString.length() < min_length_of_word);

           boolean duplicateWord = false;

            //Duplicate Check for random word non theme word pull

           for (Word checkWord: internalCollection.words){
               if (((currentWord.getEnglishInEnglish().replaceAll("\\s","")).equals(checkWord.getEnglishInEnglish().replaceAll("\\s","")))){
                duplicateWord = true;

           }
           }
            if (!(duplicateWord)){
               internalCollection.words.add(currentWord);
            }
          // }

       }   while (internalCollection.words.size() < some_number);

        }
        return  internalCollection;
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
         WordCollection collection = new WordCollection("testwordsPA3_2.txt");
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