import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA3
 * Date: 2/24/13
 * Time: 2:38 AM
 * Java Class: PACKAGE_NAME
 */
public class WordSearcher {
    WordCollection miniWordCollection;
    int grid_height = 10;
    int grid_width = 10;
    boolean can_overlap = true;
    String [] [] puzzle;
    String [] [] solution;

    public WordSearcher(WordCollection some_word_collection, int a_grid_width, int a_grid_height, boolean a_can_overlap){
        miniWordCollection = some_word_collection;
        grid_height = a_grid_height;
        grid_width = a_grid_width;
        can_overlap = a_can_overlap;
        puzzle = new String[grid_width][grid_height];
        solution = new String[grid_width][grid_height];

        generatePuzzleAndSolution();

    }
    public WordSearcher(WordCollection some_word_collection){
        this(some_word_collection,10,10,true);
    }

    private void generatePuzzleAndSolution(){
           ArrayList<String> wordList = miniWordCollection.getEnglishInEnglishCollection();
           for (String word: wordList){

               if ( (word.length()>grid_height) ||  (word.length() > grid_width)){
                   throw new  ArrayStoreException("Word is too big for word search grid");
               }
           }
             for(String word : wordList){
                 boolean firstWord = false;
                 if (word.equals(wordList.get(0))){
                     firstWord = true;
                 }else{
                     firstWord = false;
                 }
                 //char[] characters = word.toCharArray();
                 //addCharacters(characters, firstWord);
                 add(word, this.puzzle);
                 solution = puzzle;
            }
    }
    /*
    private void addCharacters (char[] letters, boolean first ){
        if (first){

        }

    } */

    private boolean add(String word, String [][] puzzle) {
        //word = word.toUpperCase();

        String[][] origPuzzle = new String[grid_width][grid_height];
        for(int i=0; i<grid_width; i++)
            for(int j=0; j<grid_height; j++)
                origPuzzle[i][j] = puzzle[i][j];

        for(int tries=0; tries<100; tries++) {
            Random r = new Random();

            int orientation = r.nextInt(2); // 0 = Forwards,   1 = Backwards
            if(orientation == 1) word = flip(word);

            int direction   = r.nextInt(3); // 0 = Horizontal, 1 = Vertical,  2 = Diagonal

            int row			= r.nextInt(grid_width - word.length());
            int col			= r.nextInt(grid_height - word.length());

            int i;
            for(i=0; i<word.length(); i++) {
                boolean isEmpty = false;
                boolean isSameLetter = false;
                String puzzleWord = String.valueOf(word.charAt(i));
                if (puzzle[row][col] == null){
                      isEmpty = true;
                }else if ((puzzle[row][col].equals(puzzleWord))){
                    isSameLetter = true;
                }
                else{
                    isEmpty = false;
                    isSameLetter = false;
                }


                if((isEmpty) || (isSameLetter))  {
                    puzzle[row][col] = Character.toString(word.charAt(i));

                    if(direction == 0) col++;
                    if(direction == 1) row++;
                    if(direction == 2) { col++; row++; }
                } else {
                    for(int j=i; j>0; j--) {
                        if(direction == 0) col--;
                        if(direction == 1) row--;
                        if(direction == 2) { col--; row--; }

                        puzzle[row][col] = origPuzzle[row][col];
                    }
                    break;
                }
            }
            if(--i > 0) return true;
        }
        return false;
    }

    private String flip(String in) {
        StringBuilder ret = new StringBuilder();
        for(int i=in.length()-1; i>=0; i--)
            ret.append(in.charAt(i));
        return ret.toString();
    }



    private ArrayList<int[]> searchMatrix(char value){
        ArrayList<int[]> valueFoundList = new ArrayList<int[]>();
        for (int row = 0; row < grid_width; row++) {
            for (int col = 0; col < grid_height; col++) {
                if (puzzle[row][col].equals(value)){
                    int[] coordinates = {row, col};
                    valueFoundList.add(coordinates);
                }
        }
    }
        return valueFoundList;
    }

    private char[][] fill(char[][] puzzle) {
        char[][] ret = new char[puzzle.length][puzzle.length];
        RandChar r = new RandChar();

        for(int i=0; i<ret.length; i++) {
            for(int j=0; j<ret.length; j++) {
                if(puzzle[i][j] != ' ') {
                    ret[i][j] = puzzle[i][j];
                } else {
                    ret[i][j] = r.nextChar();
                }
            }
        }

        return ret;
    }
    /*
     public static String [] [] solvePuzzle( String [] [] somePuzzle, ArrayList<String> someStrings){


     }
      */
    public String toString(){
        String temp = "\nWord Search\n";
        for (int counter = 0; counter < miniWordCollection.words.size(); counter++){
            if (counter%4==0){
                temp = temp + "\n" + miniWordCollection.words.get(counter).getEnglishInEnglish();
            } else {
                temp = temp + "\t"+ miniWordCollection.words.get(counter).getEnglishInEnglish();
            }
        }
        temp = temp + "\n\nPuzzle\n\n---------------------------------------";
        for (int row = 0; row < grid_width; row++) {
            for (int col = 0; col < grid_height; col++) {
                String slot = null;
                if (puzzle[row][col] == null){
                      slot = " ";
                } else{
                    slot = puzzle[row][col];
                }

                if (col == grid_height-1){
                     temp = temp + ", " + slot + " |";
                }else if (col == 0){
                    temp = temp + "\n| " + slot + " ";
                }
                else{
                    temp = temp + ", " + slot + " ";

            }

    }
        }
        temp = temp + "\n---------------------------------------\n\nSolution\n";

        temp = temp + "\n\n---------------------------------------";
        for (int row = 0; row < grid_width; row++) {
            for (int col = 0; col < grid_height; col++) {
                String slot = null;
                if (solution[row][col] == null){
                    slot = " ";
                } else{
                    slot = solution[row][col];
                }

                if (col == grid_height-1){
                    temp = temp + ", " + slot + " |";
                }else if (col == 0){
                    temp = temp + "\n| " + slot + " ";
                }
                else{
                    temp = temp + ", " + slot + " ";

                }

            }
        }
        temp = temp + "\n---------------------------------------";



        return temp;
    }

    public static void main(String args[]){
        WordCollection collection = new WordCollection("SmallRealWords.txt");
       WordCollection y1 = collection.getSomeUniqueRandomWords(10,4,null);
        WordSearcher z1 = new WordSearcher(y1);
        System.out.println(z1);

    }
}
