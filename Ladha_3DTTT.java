import javafx.event.*;
import java.util.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
public class Ladha_3DTTT extends Application {
    int lookAheadCounter = 0;
    int totalLooksAhead = 8;
    char mainboard[][][];
    int[] finalWin = new int[3];
    boolean player = true;
    Pane panel = new Pane();
    char humanPiece = 'X';
    char computerPiece = 'O';
    static Boolean[] spotFilled = new Boolean[27];
    public boolean win = false;
    Button[] buttons = new Button[27];
	int converter[][][] = {{{0,1,2},{3,4,5},{6,7,8}},{{9,10,11},{12,13,14},{15,16,17}},{{18,19,20},{21,22,23},{24,25,26}}};
	int[][] wins = {
    {0,1,2},{3,4,5},{6,7,8},{9,10,11},{12,13,14},{15,16,17},{18,19,20},{21,22,23},{24,25,26},{0,3,6},
	{1,4,7},{2,5,8},{9,12,15},{10,13,16},{11,14,17},{18,21,24},{19,22,25},{20,23,26},{0,4,8},{2,4,6},
	{9,13,17},{11,13,15},{18,22,26},{20,22,24},{0,9,18},{1,10,19},{2,11,20},{3,12,21},{4,13,22},{5,14,23},
	{6,15,24},{7,16,25},{8,17,26},{0,12,24},{1,13,25},{2,14,26},{6,12,18},{7,13,19},{8,14,20},{0,10,20},
	{3,13,23},{6,16,26},{2,10,18},{5,13,21},{8,16,24},{0,13,26},{2,13,24},{6,13,20},{8,13,18},};    
	public void start(Stage primaryStage){
        finalWin = new int[3];
        mainboard = new char[3][3][3];
        Button btStart = new Button("Reset");
        ImageView hashemi = new ImageView("hashemi_icon.png");
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        hBox.getChildren().addAll(btStart);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    mainboard[i][j][k] = '-';
                }
            }
        }
        for(int i = 0; i < 27; i++){
            buttons[i] = new Button("",new ImageView("white_icon.png"));
			spotFilled[i] = Boolean.FALSE;
		}
        VBox[] vboxes = new VBox[9];
        for(int i = 0; i < 9; i++){
            vboxes[i] = new VBox();
            vboxes[i].getChildren().addAll(buttons[3*i],buttons[3*i + 1],buttons[3*i+2]);
        }
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        HBox hb3 = new HBox();
        hb1.getChildren().addAll(vboxes[0],vboxes[1],vboxes[2]);
        hb2.getChildren().addAll(vboxes[3],vboxes[4],vboxes[5]);
        hb3.getChildren().addAll(vboxes[6],vboxes[7],vboxes[8]);
        VBox fv = new VBox(16);
        fv.getChildren().addAll(hb1,hb2,hb3);
        panel.getChildren().addAll(fv);
        vBox.getChildren().addAll(panel,hBox);
        Scene scene = new Scene(vBox);
        vBox.setStyle("-fx-background-color: #00FFFF");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Abrahim Ladha 3D tictactoe");
        primaryStage.show();
        btStart.setOnMouseClicked(e -> {
            clearBoard();
            player = true;
            win = false;
        });
        buttons[0].setOnMouseClicked(e-> {doEverything(0);});
        buttons[1].setOnMouseClicked(e-> {doEverything(1);});
        buttons[2].setOnMouseClicked(e-> {doEverything(2);});
        buttons[3].setOnMouseClicked(e-> {doEverything(3);});
        buttons[4].setOnMouseClicked(e-> {doEverything(4);});
        buttons[5].setOnMouseClicked(e-> {doEverything(5);});
        buttons[6].setOnMouseClicked(e-> {doEverything(6);});
        buttons[7].setOnMouseClicked(e-> {doEverything(7);});
        buttons[8].setOnMouseClicked(e-> {doEverything(8);});
        buttons[9].setOnMouseClicked(e-> {doEverything(9);});
        buttons[10].setOnMouseClicked(e-> {doEverything(10);});
        buttons[11].setOnMouseClicked(e-> {doEverything(11);});
        buttons[12].setOnMouseClicked(e-> {doEverything(12);});
        buttons[13].setOnMouseClicked(e-> {doEverything(13);});
        buttons[14].setOnMouseClicked(e-> {doEverything(14);});
        buttons[15].setOnMouseClicked(e-> {doEverything(15);});
        buttons[16].setOnMouseClicked(e-> {doEverything(16);});
        buttons[17].setOnMouseClicked(e-> {doEverything(17);});
        buttons[18].setOnMouseClicked(e-> {doEverything(18);});
        buttons[19].setOnMouseClicked(e-> {doEverything(19);});
        buttons[20].setOnMouseClicked(e-> {doEverything(20);});
        buttons[21].setOnMouseClicked(e-> {doEverything(21);});
        buttons[22].setOnMouseClicked(e-> {doEverything(22);});
        buttons[23].setOnMouseClicked(e-> {doEverything(23);});
        buttons[24].setOnMouseClicked(e-> {doEverything(24);});
		buttons[25].setOnMouseClicked(e-> {doEverything(25);});
        buttons[26].setOnMouseClicked(e-> {doEverything(26);});
}
private void doEverything(int n){
	OneMove newMove = new OneMove();
	if(!spotFilled[n] && player){
	buttons[n].setGraphic(new ImageView("hashemi_icon.png"));
	spotFilled[n] = Boolean.TRUE;
	player = false;
	newMove = intToMove(n);
		if(isWin(humanPiece,newMove)){
            win = true;
            System.out.println("you won");
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					System.out.println(mainboard[i][j][0] +" "+ mainboard[i][j][1] + " " + mainboard[i][j][2]);
				}
			}
	        buttons[converter[newMove.board][newMove.row][newMove.column]].setGraphic(new ImageView("hashemi_icon_blue.png"));
			disableBoard();
        }
		else playComp();
	}
}
private OneMove intToMove(int n){
int spot = n;
OneMove newMove = new OneMove();
        if(spot < 9){
            if(spot % 9 < 3){
                mainboard[0][0][spot % 3] = humanPiece;
                newMove.board = 0;
                newMove.row = 0;
                newMove.column = spot % 3;
            } 
            else if(spot % 9 < 6){
                mainboard[0][1][spot % 3] = humanPiece;
                newMove.board = 0;
                newMove.row = 1;
                newMove.column = spot % 3;
            } 
            else if(spot % 9 < 9){
                mainboard[0][2][spot % 3] = humanPiece;
                newMove.board = 0;
                newMove.row = 2;
                newMove.column = spot % 3;
            }               
        }
        else if(spot < 18){
            if(spot % 9 < 3){
                mainboard[1][0][spot % 3] = humanPiece;
                newMove.board = 1;
                newMove.row = 0;
                newMove.column = spot % 3;
            } 
            else if(spot % 9 < 6){
                mainboard[1][1][spot % 3] = humanPiece;
                newMove.board = 1;
                newMove.row = 1;
                newMove.column = spot % 3;
            } 
            else if(spot % 9 < 9){
                mainboard[1][2][spot % 3] = humanPiece;
                newMove.board = 1;
                newMove.row = 2;
                newMove.column = spot % 3;
            }
        }
        else if(spot < 36){

            if(spot % 9 < 3){
                mainboard[2][0][spot % 3] = humanPiece;
                newMove.board = 2;
                newMove.row = 0;
                newMove.column = spot % 3;
            } 
		    else if(spot % 9 < 6){
                mainboard[2][1][spot % 3] = humanPiece;
                newMove.board = 2;
                newMove.row = 1;
                newMove.column = spot % 3;
            } 
            else if(spot % 9 < 9){
                mainboard[2][2][spot % 3] = humanPiece;
                newMove.board = 2;
                newMove.row = 2;
                newMove.column = spot % 3;
            }
        }
	return newMove;
}
private void clearBoard(){
    panel.getChildren().removeAll();
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 3; k++){
                mainboard[i][j][k] = '-';
                spotFilled[9 * i + 3 * j + k] = false;
            }
        }
    }
	for(int i = 0; i < 27; i++){
		buttons[i].setDisable(false);
        buttons[i].setGraphic(new ImageView("white_icon.png"));
    }
}
private void disableBoard(){
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 3; k++){
                mainboard[i][j][k] = 'A';
            }
        }
    }
    for(int i = 0; i < 27; i++)
        buttons[i].setDisable(true);
    finalWin = new int[3];
}
private void drawComputerPiece(int i,int j,int k){
	buttons[converter[i][j][k]].setGraphic(new ImageView("robot_icon.png"));
	player = true;
	spotFilled[converter[i][j][k]] = true;
}
private void playComp(){
    int bestScore;
    int hValue;
    OneMove nextMove;
    int bestScoreBoard = -1;
    int bestScoreRow = -1;
    int bestScoreColumn = -1;
    bestScore = -1000;
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 3; k++){
                if(mainboard[i][j][k] == '-'){
                    nextMove = new OneMove();
                    nextMove.board = i;
                    nextMove.row = j;
                    nextMove.column = k;
                    if(isWin(computerPiece,nextMove)){
                        mainboard[i][j][k] = computerPiece;
                        drawComputerPiece(i,j,k);
                        win = true;
						buttons[finalWin[0]].setGraphic(new ImageView("robot_icon_red.png"));
						buttons[finalWin[1]].setGraphic(new ImageView("robot_icon_red.png"));
						buttons[finalWin[2]].setGraphic(new ImageView("robot_icon_red.png"));    
                        System.out.println("you lose");
                        disableBoard();
                        break;
                    }
					else{
                        hValue = lookAhead(humanPiece,-1000,1000);
                        lookAheadCounter = 0;
                        if(hValue >= bestScore){
                            bestScore = hValue;
                            bestScoreBoard = i;
                            bestScoreRow = j;
                            bestScoreColumn = k;
                            mainboard[i][j][k] = '-';
                        }
						else
                            mainboard[i][j][k] = '-';
                    }
                }
            }
        }
    }
    if(!win){
        mainboard[bestScoreBoard][bestScoreRow][bestScoreColumn] = computerPiece;
        drawComputerPiece(bestScoreBoard,bestScoreRow,bestScoreColumn);
    }
}
private int lookAhead(char c,int a,int b){
    int alpha = a;
    int beta = b;
    if(lookAheadCounter <= totalLooksAhead){
        lookAheadCounter++;
        if(c == computerPiece){
            int hValue;
            OneMove nextMove;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    for(int k = 0; k < 3; k++){
                        if(mainboard[i][j][k] == '-'){
                            nextMove = new OneMove();
                            nextMove.board = i;
                            nextMove.row = j;
                            nextMove.column = k;
                            if(isWin(computerPiece,nextMove)){
                                mainboard[i][j][k] = '-';
                                return 1000;
                            } 
                            else{
                                hValue = lookAhead(humanPiece,alpha,beta);
                                if(hValue > alpha){
                                    alpha = hValue;
                                }
                                mainboard[i][j][k] = '-';
                            }
                            if(alpha >= beta){
                                break;
                            }
                        }
                    }
                }
            }
                return alpha;
        }
        else{
            int hValue;
            OneMove nextMove;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    for(int k = 0; k < 3; k++){
                        if(mainboard[i][j][k] == '-'){
                            nextMove = new OneMove();
                            nextMove.board = i;
                            nextMove.row = j;
                            nextMove.column = k;
                            if(isWin(humanPiece,nextMove)){
                                mainboard[i][j][k] = '-';
                                return -1000;
                            } 
                            else{
                                hValue = lookAhead(computerPiece,alpha,beta);
                                if(hValue < beta)
                                    beta = hValue;
                                mainboard[i][j][k] = '-';
                            }
                            if(alpha >= beta){
                                break;
                            }
                        }
                    }
                }
            }
            return beta;
        }
    }
    else
        return heuristic();
}
private int heuristic(){
    return (whatsOpen(computerPiece) - whatsOpen(humanPiece));
}
private int whatsOpen(char c){
    int winCounter = 0;
    int[] board = new int[27];
    int counter = 0;
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 3; k++){
                if(mainboard[i][j][k] == c || mainboard[i][j][k] == '-')
                    board[counter] = 1; 
                else
                    board[counter] = 0;
                counter++;
            }
        }
    }
    for(int i = 0; i < 49; i++){
        counter = 0;
        for(int j = 0; j < 3; j++){
            if(board[wins[i][j]] == 1){
                counter++;
                finalWin[j] = wins[i][j];
                if(counter == 3)
                    winCounter++;
            }
        }
    }
    return winCounter;
}
private boolean isWin(char c,OneMove pos){
    mainboard[pos.board][pos.row][pos.column] = c;
    int[] gameBoard = new int[27];
    int counter = 0;
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 3; k++){
                if(mainboard[i][j][k] == c)
                    gameBoard[counter] = 1; 
				else
                    gameBoard[counter] = 0;
                counter++;
            }
        }
    }
    for(int i = 0; i < 49; i++){
        counter = 0;
        for(int j = 0; j < 3; j++){
            if(gameBoard[wins[i][j]] == 1){
                counter++;
                finalWin[j] = wins[i][j];
                if(counter == 3)
                   return true;
            }
        }
    }
    return false;
}
public class OneMove {
    int board;
    int row;
    int column;
}
public static void main(String[] args){
    launch(args);
}
}
