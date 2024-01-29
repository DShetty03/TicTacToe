import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class TicTacToe{

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main (String[] args){


        char[][] gameBoard = {
            {' ', '|', ' ', '|', ' '},
            {'-', '*', '-', '*', '-' },
            {' ', '|', ' ', '|', ' '},
            {'-', '*', '-', '*', '-'},
            {' ', '|', ' ', '|', ' '}
        };

        printBoard(gameBoard);

    

        while(true){
            Scanner scan = new Scanner(System.in); 
            //player move
            System.out.println("Enter your move (1-9): ");
            int playerPos = scan.nextInt();
            //input a correct position (ie; cpu can't place over ours)
            while(playerPositions.contains(playerPos)|| cpuPositions.contains(playerPositions)){
                System.out.println("Position already taken. Enter a correct spot.");
                playerPos = scan.nextInt();
            }
    

            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
 
        
            //cpu move
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos)|| cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9)+1;
            }
            placePiece(gameBoard, cpuPos, "cpu");
        
            //print the board
            printBoard(gameBoard);   
            
            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }


        }
      

    }

    public static void printBoard(char[][] gameBoard){
        for(char[] row: gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user){
        
        char symbol = ' ';

        if (user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        }else if(user.equals("cpu")){
            symbol = '0';
            cpuPositions.add(pos);

        }
        
          //placing x or o
          switch(pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8: 
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }


    public static String checkWinner() {
        
        //rows
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9); 

        //cols
        List leftCol = Arrays.asList(1, 2, 3);
        List midCol = Arrays.asList(4, 5, 6);
        List rightCol = Arrays.asList(7, 8, 9);

        //diagonals
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow); 
        winning.add(bottomRow);
        winning.add(leftCol); 
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l: winning){
            if(playerPositions.containsAll(l)){
                return "Congrats. You Won!";

            }else if(cpuPositions.containsAll(l)){
                return "You lost to the CPU";

            }else if(playerPositions.size()+cpuPositions.size() ==9 ){
                return "Board is full";
            }

        }
        return("");
    }
}
 
