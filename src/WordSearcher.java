
import java.util.*;


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

            //check for bounds
           for (String word: wordList){

               if ( (word.length()>grid_height) ||  (word.length() > grid_width)){
                   throw new  ArrayStoreException("Word is too big for word search grid");
               }
           }  //start adding words
             for(String word : wordList){
                 boolean firstWord = false;
                 if (word.equals(wordList.get(0))){
                     firstWord = true;
                 }else{
                     firstWord = false;
                 }

                 add(word, puzzle);

            }
        //make a copy
        solution = copyPuzzle();
        //fill in the empty spaces
        fill(puzzle);
    }


    private boolean add(String word, String [][] puzzle) {


        String[][] origPuzzle = new String[grid_width][grid_height];
        for(int i=0; i<grid_width; i++)
            for(int j=0; j<grid_height; j++)
                origPuzzle[i][j] = puzzle[i][j];

        for(int tries=0; tries<100; tries++) {
            Random r = new Random();
            //direction and orientation logic
            int orientation = r.nextInt(2); // 0 = Forwards,   1 = Backwards
            if(orientation == 1) word = flip(word);

            int direction = r.nextInt(3); // 0 = Horizontal, 1 = Vertical,  2 = Diagonal

            int row	= r.nextInt(grid_width - word.length());
            int col	= r.nextInt(grid_height - word.length());

            int i;
            for(i=0; i<word.length(); i++) {
                boolean isEmpty = false;
                boolean isSameLetter = false;
                if (can_overlap = true){
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
                } else{
                    String puzzleWord = String.valueOf(word.charAt(i));
                    if (puzzle[row][col] == null){
                        isEmpty = true;
                    }else{
                        isEmpty = false;
                        isSameLetter = false;
                    }
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



    private String[][] copyPuzzle (){
        String[][] backup = new String[grid_width][grid_height];
        for (int row = 0; row < grid_width; row++) {
            for (int col = 0; col < grid_height; col++) {
            String middle = String.valueOf(puzzle[row][col]);
                if (middle == "null"){
                     middle = null;
                    backup[row][col] = middle;
                } else{
                backup[row][col] = middle;
                }

            }
        }
      return backup;
    }

    private void fill(String[][] puzzle) {
        String[][] ret = new String[grid_width][grid_height];
        RandomCharacter r = new RandomCharacter();

        for (int row = 0; row < grid_width; row++) {
            for (int col = 0; col < grid_height; col++) {
               if (puzzle [row][col] == null){
                   puzzle [row][col] = r.nextCharacter();
               }
            }
        }

    }


    //Start working on different recursion approaches with a varying results.  Some more accurate than others. The main issue that I couldn't get the find you go
    //in a straight line. Currently not working.
    public String[][] solvePuzzle(String[][] somePuzzle, ArrayList<String> someStrings){
        int ROWS = somePuzzle.length;
        int COLS = somePuzzle[0].length;
        puzzle = somePuzzle;
        solution = new String[ROWS][COLS];
        int counter = 0;

        for (String word: someStrings){
            for (int r = 0; r < puzzle.length; r++){
                for (int c = 0; c < puzzle[0].length; c++){
                    String currentChar = String.valueOf(word.charAt(counter));
                    searchArray(counter, word, ROWS, COLS, r, c);

                        solution [r][c] = puzzle [r][c];

                }
            }
        }
        return solution;

    }
    public boolean searchArray(int counter, String word, int ROWS, int COLS, int row, int col){

        if (row < 0 || row > ROWS - 1 || col < 0 || col > COLS - 1){
            return false;
        }
        //if(!(puzzle[row][col].equals(String.valueOf(word.charAt(counter+1))))){
        /*
        if (counter + 1 == word.length()){
        if ((puzzle[row][col].equals(String.valueOf(word.charAt(counter)))) || (String.valueOf(word.charAt(counter)).equals(String.valueOf(word.charAt(word.length()))))){
            //if(!(puzzle[row][col].equals(String.valueOf(word.charAt(counter))))){
            solution[row][col]=puzzle[row][col];
            return;
        }//else {
                //solution[row][col]=puzzle[row][col];
                //return;
            // }
       } */
        counter++;
        String currentChar = String.valueOf(word.charAt(counter));

        if (counter == word.length()-1){
            if (!(puzzle[row][col].equals(String.valueOf(word.charAt(counter))))){
                return false;
            } else{
                solution[row][col]=puzzle[row][col];
                return true; }
        }

        /*     return;
        }else if ((String.valueOf(word.charAt(counter))) == null){
         return;
        }
        }*/else{
            if (!(puzzle[row][col].equals(String.valueOf(word.charAt(counter))))){
                return false;
            }//else{
        }

             searchArray(counter, word, ROWS, COLS, row, col + 1);   // check right

                searchArray(counter, word, ROWS, COLS, row + 1, col);   // check bottom

                searchArray(counter, word, ROWS, COLS,row, col - 1);   // check left

                searchArray(counter, word, ROWS, COLS, row - 1, col);   // check top

               searchArray(counter, word, ROWS, COLS,row + 1, col + 1);   // check bottomright

                searchArray(counter, word, ROWS, COLS,row + 1, col - 1 );   // check bottomleft

                searchArray(counter, word, ROWS, COLS,row - 1, col - 1);   // check top left

                searchArray(counter, word, ROWS, COLS,row - 1, col + 1);   // check top right

                /*
                if (puzzle[row][col].equals(String.valueOf(word.charAt(counter)))){
                    solution[row][col]=puzzle[row][col];
                    return true;
                }
                */
                //return true;


        return false;
    }
    public String toString(){
        String temp = "\nWord Search\n";
        for (int counter = 0; counter < miniWordCollection.words.size(); counter++){
            if (counter%4==0){
                temp = temp + "\n" + miniWordCollection.words.get(counter).getEnglishInEnglish();
            } else {
                temp = temp + "\t"+ miniWordCollection.words.get(counter).getEnglishInEnglish();
            }
        }
        temp = temp + "\n\nPuzzle\n\n";

        for (int counter = 0; counter < this.grid_width+2; counter++){
            temp = temp + "----";
        }

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
        temp = temp +"\n";
        for (int counter = 0; counter < this.grid_width+2; counter++){
            temp = temp + "----";
        }
        temp = temp + "\n\nSolution\n";


        temp = temp +"\n";
        for (int counter = 0; counter < this.grid_width+2; counter++){
            temp = temp + "----";
        }
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

        temp = temp +"\n";
        for (int counter = 0; counter < this.grid_width+2; counter++){
        temp = temp + "----";
        }

        return temp;
    }

    public static void main(String args[]){
        // get the entire word collection
        WordCollection x = new WordCollection("SmallRealWords.txt");
        // Test case 1
        WordCollection y1 = x.getSomeUniqueRandomWords(10,4,null);
        WordSearcher z1 = new WordSearcher(y1);
        System.out.println(z1);
        // Test case 2
        WordCollection y2 = x.getSomeUniqueRandomWords(15,5, "animals");
        WordSearcher z2 = new WordSearcher(y2,20,24,false);
        System.out.println(z2);
        // Test case 3
        WordCollection y3 = x.getSomeUniqueRandomWords(8,4,"flowers");  //originally 8 ,4
        WordSearcher z3 = new WordSearcher(y3,20,20,true);     //Originally 20, 20
        System.out.println(z3);
        z3.solution = null;
        ArrayList<String> word_list = y3.getEnglishInEnglishCollection();
        z3.solution = z3.solvePuzzle(z3.puzzle,word_list);
        System.out.println(z3);


    }


}
