package rubiktabla;

import static java.lang.Math.sqrt;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Burian Sándor
 */
public class Game {
    private int size;           //the size of one edge
    private Vector gameTable;   //the matrix of the game table
    public int steps;           //nr of steps
    
    /** 
     * Create new game
     * generate also the game table with this size
     * @param n - the size of game table
     */ 
    public Game(int n) {
//System.out.println("LOG Game ON");
        size = n;
        gameTable = new Vector();
        generateGameTable();
        steps = 0;
    }

    /**
     * create game table
     * init the game vector with size and values.
     */
    private void generateGameTable() {
       int idx = 0;
       int rowIdx = 0;
       int generatedValueIdx;
       
       /**
        * The possible values can be:
        * COLOR CODE
        * 1-red
        * 2-green
        * 3-yellow
        * 4-orange
        * 5-blue
        * 6-white
        */
        Vector possibleValues;
        possibleValues = new Vector();
        for(int fidx=1; fidx<size+1; ++fidx){
            possibleValues.add(fidx);
        }
//System.out.println("LOG possibleValues:"+possibleValues);

        /**
         * The nr of this value in table to be set only one row from one kind of color.
         */
        Vector valuesNrInTable;
        valuesNrInTable = new Vector();
        for(int fidx=1; fidx<size+1; ++fidx){
            valuesNrInTable.add(0);
        }
//System.out.println("LOG valuesNrInTable:"+valuesNrInTable);

        while(rowIdx<(size*size)){
            generatedValueIdx = randomGenerator(0,size);
            while((int)valuesNrInTable.get(generatedValueIdx)>(size-1)){
                generatedValueIdx = randomGenerator(0,size);
            }
            gameTable.add( possibleValues.get(generatedValueIdx) );
            int tmpValue;
            tmpValue = (int) valuesNrInTable.get(generatedValueIdx);
            valuesNrInTable.set(generatedValueIdx, tmpValue+1);
//System.out.println("LOG value added:"+possibleValues.get(generatedValueIdx));            
            ++rowIdx;
        }
System.out.println("LOG gameTable:"+gameTable);        
//System.out.println("LOG value nr:"+valuesNrInTable);
        
        /**
         * Again if the generated game is already completed
         */
        if(isCompleted()){
            gameTable.removeAllElements();
            generateGameTable();
        }
    }
    
    /**
     * Random number generator with parameters
     * @param Low int the lowest inclusive value of generated number
     * @param High int the highest exclusive value of generated number
     * @return an int, the gereated random number
     */
    private int randomGenerator(int Low, int High){
        Random r = new Random();
        return r.nextInt(High-Low) + Low;
    }
    
    /**
     * Translate a number to color
     * @param value A number between [1-6]
     * @return the color in a String
     */
    public String gameTableValueToColor(int value){
        String tmp ="";
        switch (value){
            case 1:
                    tmp = "red";
                    break;
            case 2:
                    tmp = "green";
                    break;
            case 3:
                    tmp = "yellow";
                    break;
            case 4:
                    tmp = "orange";
                    break;
            case 5:
                    tmp = "blue";
                    break;
            case 6:
                    tmp = "white";
                    break;
        }
        return tmp;
    }
    
    /**
     * Get values with given index, returned by color.
     * 
     * @param value - index of the element in game table
     * @return color value of this element as a String 
     *
     * @see gameTableValueToColor
     */
    public String gameTableIdxToColor(int value){
        return gameTableValueToColor((int) gameTable.get(value));
    }

    /**
     * Get the the game table as an Vector.
     * @return an Vector 
     */
    public Vector getGameTable() {
        return gameTable;
    }

    /**
     * Get the size of game Table.
     * This is only one row/column size, not the size of the gameTable vector!
     * @return the size in an int value
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Get the game status
     * 
     * @return boolean value, true if is completed by row or column, false if isn't
     */
    public boolean isCompleted(){
        boolean completed = true;
        int size = (int) sqrt(gameTable.size());
        
        //SOR EGYSZÍNŰ
        boolean sameRowElems = true;
        int rIdx=0;
        while((rIdx<gameTable.size()) && (completed)){
        //for(int rIdx = 0; rIdx<gameTable.size(); ++rIdx){
            int eIdx = 1;
            while((eIdx<size)&&(sameRowElems)){
            //for(int eIdx = 1; eIdx<size; ++eIdx){
                if( !gameTable.get(eIdx).equals(gameTable.get(eIdx-1)) )
                    sameRowElems = false;
                ++eIdx;
            }
            if(!sameRowElems)
                completed = false;
            ++rIdx;
        }
        
        //System.out.println("LOG\tsize: "+size);
        //System.out.println("LOG\tsorok szerint egyszínű: "+completed);
        if(completed)
            return completed;
        
        //OSZLOPOK EGYSZÍNŰ
        completed = true;
        int cIdx=0;
        boolean sameColumnElems = true;
        while((sameColumnElems)&&(cIdx<gameTable.size())){
            int eIdx=0+size;
            while((eIdx<gameTable.size()) && (sameColumnElems)){
                if( !gameTable.get(eIdx).equals(gameTable.get(eIdx-size)) )
                    sameColumnElems = false;
                eIdx += size;
            }
            ++cIdx;
        }
        if(!sameColumnElems)
            completed = false;
        
        //System.out.println("LOG\toszlopok szerint egyszínű: "+completed);
        return completed;
    }
    
    /**
     * Move by an row left
     * @param rowIdx the first element index of the row
     */
    void moveByRowLeft(int rowIdx){
        Vector tmp = new Vector();
        int tIdx = rowIdx;
        int tmpRowSizeIdx = 0;
        while(tmpRowSizeIdx<size){
            tmp.add(gameTable.get(tIdx));
            ++tIdx;     
            ++tmpRowSizeIdx;
        }
        
        tIdx = 1;
        int vIdx = rowIdx;
        while(tIdx<tmp.size()){
            gameTable.set(vIdx, tmp.get(tIdx));
            ++tIdx;
            ++vIdx;
        }
        gameTable.set(vIdx, tmp.get(0));
        //System.out.println("LOG\ttmp vector: "+tmp);
        System.out.println("LOG\tgameTable after mBRL: "+gameTable);
    }
    
    /**
     * Move by an row right
     * @param rowIdx the first element index of the row
     */
    void moveByRowRight(int rowIdx){
        Vector tmp = new Vector();
        int tIdx = rowIdx;
        int tmpRowSizeIdx = 0;
        while(tmpRowSizeIdx<size){
            tmp.add(gameTable.get(tIdx));
            ++tIdx;     
            ++tmpRowSizeIdx;
        }
        
        tIdx = 0;
        int vIdx = rowIdx+1;
        while(tIdx<tmp.size()-1){
            gameTable.set(vIdx, tmp.get(tIdx));
            ++tIdx;
            ++vIdx;
        }
        gameTable.set(rowIdx, tmp.get(tIdx));
        //System.out.println("LOG\ttmp vector: "+tmp);
        System.out.println("LOG\tgameTable after mBRR: "+gameTable);
    }
    
    /**
     * Move by a column up
     * @param rowIdx the first element index of the column
     */
    void moveByColumnUp(int colIdx){
        Vector tmp = new Vector();
        int tIdx = colIdx;
        int tmpColSizeIdx = 0;
        while(tmpColSizeIdx<size){
            tmp.add(gameTable.get(tIdx));
            tIdx+=size;     
            ++tmpColSizeIdx;
        }
        
        tIdx = 1;
        int vIdx = colIdx;
        while(tIdx<tmp.size()){
            gameTable.set(vIdx, tmp.get(tIdx));
            ++tIdx;
           vIdx+=size;
        }
        gameTable.set(vIdx, tmp.get(0));
        System.out.println("LOG\ttmp vector: "+tmp);
        System.out.println("LOG\tgameTable after mBCU: "+gameTable);
    }
    
    /**
     * Move by a column down
     * @param rowIdx the first element index of the column
     */
    void moveByColumnDown(int colIdx){
        Vector tmp = new Vector();
        int tIdx = colIdx;
        int tmpColSizeIdx = 0;
        while(tmpColSizeIdx<size){
            tmp.add(gameTable.get(tIdx));
            tIdx+=size;     
            ++tmpColSizeIdx;
        }
        
        tIdx = 0;
        int vIdx = colIdx+size;
        while(tIdx<tmp.size()-1){
            gameTable.set(vIdx, tmp.get(tIdx));
            ++tIdx;
           vIdx+=size;
        }
        gameTable.set(colIdx, tmp.get(tIdx));
        System.out.println("LOG\ttmp vector: "+tmp);
        System.out.println("LOG\tgameTable after mBCU: "+gameTable);
    }

    /**
     * Get if the element is on the edge of game table. An elem is on edge if is in first row or in
     * last row, or in first column, or in last column.
     * 
     * @param idx the index of elem. The index is from gameTable.
     * @return String value, "up" if is the first row, "down" if is the last row, "left" if is the left edge of the game table, "right if is the right edge of game table.
     */
    String isEdge(int idx) {
        String is = "";
        boolean found = false;
        
        //first row
        if (idx < size) {
            is = "up";
            found = true;
        }

        //right side
        if ((!found) && ((idx % size) == 0.0)) {
            is = "right";
            found = true;
        }

        //left side
        if(!found)
            for (int tIdx = 0; tIdx < size; ++tIdx){
                if ((!found) && (tIdx * size - 1 == idx)) {
                    is = "left";
                    found = true;
                }
            }
        
        //last row 
        if ((!found) && (idx >= gameTable.size() - size)) {
            is = "down";
            found = true;
        }
        System.out.println(idx + "is on edge: " + is);
        return is;
    }
}