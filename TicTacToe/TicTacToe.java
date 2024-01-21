import java.util.Scanner;
public class TicTacToe{
    static String [][]board=new String[3][3];
    public static int round=0; 
    public static String currentPlayer = "X";
    public static boolean validMove=true;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(true){
            board();
            promptMove(scanner);
            if(validMove){
                if (hasWon(currentPlayer)) {
                    System.out.println("Player"+currentPlayer+"wins! Congratulations!");
                    System.out.println("Play again? (yes/no): ");
                    String playAgain = scanner.nextLine().toLowerCase();

                    if(!playAgain.equals("yes")) {
                        System.out.println("Thanks for playing!");
                        break;
                    }else{
                        resetGame();
                    }
                    board();

                } else {
                    if(round == 9){
                        System.out.println("Game over. No player wins.");
                        break;
                    }
                    switchPlayer();
                }

            }

        }
        scanner.nextLine();
    }

    public static void board(){
        System.out.println("Round"+round);
        for(int row=0; row<3;row++){
            for(int col=0;col<3;col++){
                if(board[row][col]==null)
                    board[row][col] = " ";  
                System.out.print("["+board[row][col]+"]");
            }
            System.out.println();
        }
    }

    public static void switchPlayer(){
        if (currentPlayer.equals("X")) {
            currentPlayer="O";
        }else{
            currentPlayer="X";
        }
    }

    public static void promptMove(Scanner scanner){
        int row=0;
        int col=0;
        validMove = false;
        System.out.println("Player  "+currentPlayer+" ,make your move (row,col): ");
        String move=scanner.nextLine();

        //extract row/col numbers
        String[] moveArray = move.split(",");
        if((moveArray != null) && (moveArray.length==2)){    
            row=Integer.parseInt(moveArray[0])-1;
            col=Integer.parseInt(moveArray[1])-1;
            //check validity of move
            if (isValidMove(row, col)) {
                validMove = true;
                round++;
                board[row][col]=currentPlayer;
            }
        }
        if(!validMove)
            System.out.println("Invalid move.Try again");  

    }

    public static boolean isValidMove(int row,int col){
        boolean valid = row>=0&&row<3&&col>=0&&col<3&&board[row][col]==" ";
        return valid;
    }

    public static boolean hasWon(String player) {
        //rows columns check
        for(int i=0;i<3;i++) {
            if((board[i][0]!=null&&board[i][0].equals(player)&&board[i][1].equals(player)&&board[i][2].equals(player)) ||
            (board[0][i]!=null&&board[0][i].equals(player)&&board[1][i].equals(player)&&board[2][i].equals(player))){
                return true;
            }
        }

        //diagonals check
        if ((board[0][0]!= null && board[0][0].equals(player)&&board[1][1].equals(player)&&board[2][2].equals(player))||
        (board[0][2]!=null&&board[0][2].equals(player)&&board[1][1].equals(player)&&board[2][0].equals(player))){
            return true;
        }

        return false;
    }

    public static void resetGame() {
        round = 0;
        currentPlayer = "X";
        validMove = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
    }
}

