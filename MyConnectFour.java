import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyConnectFour {
 
  private BufferedReader input;
  private char[][] board;
  String errorCode = null;
  
  public MyConnectFour()
  {
    errorCode = "CF-1";
    board = new char[7][6];
    try
    {
        input = new BufferedReader(new InputStreamReader(System.in));
        playGame();
    }
    catch(Exception e)
    {
       System.out.println("Error code :"+ errorCode +" For details check bug and omission list");
      System.exit(1);
    }
  }
  
  //Main function called from method
  private void playGame()
  {
    System.out.println("Welcome to Connect 4");
    System.out.println("There are 2 players red and yellow");
    System.out.println("Player 1 is Red, Player 2 is Yellow");
    System.out.println("To play the game type in the number of the column you want to drop you counter in");
    System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
    System.out.println("");
    printBoard();
    
    Boolean endOfGame = true;
    Boolean player_1 = true;
    Boolean player_2 = false;
    String inputColor = "R";
    String userInput = "";
    String player = "";
    
    while(endOfGame)   //This code block is run until the game is over.
    {
        if(player_1)
        {
          System.out.println("Player 1, please select to column :");
          userInput = getUserInput();
          inputColor = "R";    
          player = "Player-1";
        }
        if(player_2)
        {
           System.out.println("Player 2, please select to column : ");
           userInput = getUserInput();
           inputColor = "Y";  
           player = "Player-2";
        }
        
        Boolean controlUserInput = controlUserInput(userInput);
        while(controlUserInput)
        {
            System.out.println("You must write column number");
            userInput = getUserInput();
            controlUserInput = controlUserInput(userInput);
        }
        
        Boolean controlColumnStatus = controlColumnStatus(userInput);
        while(controlColumnStatus)
        {
            System.out.println("You can't choose this area, choose another place");
            userInput = getUserInput();
            controlColumnStatus = controlColumnStatus(userInput);
        }
        
        try
        {
            int column = updateBoard(userInput,inputColor);
            printBoard();
            endOfGame = checkEndOfGame(inputColor,Integer.parseInt(userInput)-1,column);
        }
        catch(Exception e)
        {
           errorCode = "CF-4";
           System.out.println("Error code :"+ errorCode +" For details check bug and omission list");
        }
        
        if(!endOfGame)
        {
          System.out.println(player +" won the game.Congratulations!");
        }
        
        if(player_1) 
        {
            player_1 = false;
            player_2 = true;   
        }
        else
        {
          player_1 = true;
          player_2 = false;  
        }
    }
  }
  
  //The part where the game is checked whether there is a winner or not.
  
  private Boolean checkEndOfGame(String color,Integer firstDimension,Integer secondDimension)
  {
   
   int colorNumber = 0;
   Boolean returnValue = true;
   
   char currentColor = color.charAt(0);
   
   int rightAndLeftMatchNumber = 0;
   int downMatchNumber = 0;
   int leftDownRightUpMatchNumber = 0;
   int leftUpRightDownMatchNumber = 0;
   
   Boolean leftCheck = true;
   Boolean rightCheck = true;  
   
   Boolean downCheck = true;
   
   Boolean leftDownCheck = true;
   Boolean rightUpCheck = true;
   
   Boolean leftUpCheck = true;
   Boolean rightDownCheck = true;
   
   for(int i=0;i<=board.length-1;i++)
   {
       for(int j=0;j<=board[i].length-1;j++)
       {
           if(currentColor == board[i][j])
           {
               colorNumber++;
           }
       }
   }

   if(colorNumber >=4)
   {  
     //Left check
     int leftFirstDimension = firstDimension-1;
     while(leftCheck)
     {
       try
       {
          if(currentColor == board[leftFirstDimension][secondDimension])
          {
            rightAndLeftMatchNumber++;
            leftFirstDimension--;
          }
          else
          {
            leftCheck = false;   
          }
       }
       catch(Exception e)
       {
         leftCheck = false; 
       }
     }
     
     if(rightAndLeftMatchNumber == 3)
     {
       return false;
     }
     
     //Right check
     int rightFirstDimension = firstDimension+1;
     while(rightCheck)
     {
       try
       {
          if(currentColor == board[rightFirstDimension][secondDimension])
          {
            rightAndLeftMatchNumber++;
            rightFirstDimension++;
          }
          else
          {
            rightCheck = false;   
          }
       }
       catch(Exception e)
       {
         rightCheck = false; 
       }
     }
     
     if(rightAndLeftMatchNumber == 3)
     {
       return false;
     }
     
     //Down check
     int downSecondDimension = secondDimension+1;
     while(downCheck)
     {
       try
       {
          if(currentColor == board[firstDimension][downSecondDimension])
          {
            downMatchNumber++;
            downSecondDimension++;
          }
          else
          {
            downCheck = false;   
          }
       }
       catch(Exception e)
       {
         downCheck = false; 
       }
     }
     
     if(downMatchNumber == 3)
     {
       return false;
     }
       
     //Left-Down check
     leftFirstDimension = firstDimension-1;
     downSecondDimension = secondDimension+1;
     while(leftDownCheck)
     {
       try
       {
          if(currentColor == board[leftFirstDimension][downSecondDimension])
          {
            leftDownRightUpMatchNumber++;
            leftFirstDimension--;
            downSecondDimension++;
          }
          else
          {
            leftDownCheck = false;   
          }
       }
       catch(Exception e)
       {
         leftDownCheck = false; 
       }
     } 
     
     if(leftDownRightUpMatchNumber == 3)
     {
       return false;
     }
     
     //Right-Up check
     rightFirstDimension = firstDimension+1;
     int upSecondDimension = secondDimension-1;
     while(rightUpCheck)
     {
       try
       {
          if(currentColor == board[rightFirstDimension][upSecondDimension])
          {
            leftDownRightUpMatchNumber++;
            rightFirstDimension++;
            upSecondDimension--;
          }
          else
          {
            rightUpCheck = false;   
          }
       }
       catch(Exception e)
       {
         rightUpCheck = false; 
       }
     } 
     
     if(leftDownRightUpMatchNumber == 3)
     {
       return false;
     }
     
     //Left-Up check
     leftFirstDimension = firstDimension-1;
     upSecondDimension = secondDimension-1;
     while(leftUpCheck)
     {
       try
       {
          if(currentColor == board[leftFirstDimension][upSecondDimension])
          {
            leftUpRightDownMatchNumber++;
            leftFirstDimension--;
            upSecondDimension--;
          }
          else
          {
            leftUpCheck = false;   
          }
       }
       catch(Exception e)
       {
         leftUpCheck = false; 
       }
     } 
     
     if(leftUpRightDownMatchNumber == 3)
     {
       return false;
     }
     
     //Right-Down check
     rightFirstDimension = firstDimension+1;
     downSecondDimension = secondDimension+1;
     while(rightDownCheck)
     {
       try
       {
          if(currentColor == board[rightFirstDimension][downSecondDimension])
          {
            leftUpRightDownMatchNumber++;
            rightFirstDimension++;
            downSecondDimension++;
          }
          else
          {
            rightDownCheck = false;   
          }
       }
       catch(Exception e)
       {
         rightDownCheck = false; 
       }
     } 
   }
   return true;
  }
  
  //The received input is thrown into the array.
  private int updateBoard(String input,String color)
  {
    int column = board[0].length-1;
    int row = Integer.parseInt(input)-1;
    char value;
    
    for(;column>=0;column--)
    {  
       if((int)board[row][column]!=0)
       {
           ;
       }
       else
       {
         board[row][column] = color.charAt(0);
         return column;
       }
    }
   return column; 
  }
  
  //Checking if the column is empty is done here.
  private Boolean controlColumnStatus(String input)
  {
    Boolean returnValue = false;
    int column = 1;
    errorCode = "CF-3";
    
    try
    {
       column = Integer.parseInt(input)-1;
    }
    catch(Exception e)
    {
        System.out.println("Error code :"+ errorCode +" For details check bug and omission list");
    }
    
    if(board[column][0] != 0)
    {
      returnValue =true;
    }
    
    return returnValue;
  }
  
  //The control of the value received from the user is done here.
  private Boolean controlUserInput(String input)
  {
    Boolean returnValue = false;
    
    if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") 
       || input.equals("5") || input.equals("6") ||input.equals("7"))
    {
      returnValue =false;
    }
    else
    {
     returnValue =true;   
    }
    
    return returnValue;
  }
  
  //The value is taken from the user.
  private String getUserInput(){
    String toReturn = null;
    errorCode = "CF-2";
    try
    {      
       toReturn = input.readLine();
    }
    catch(Exception e)
    {
        System.out.println("Error code :"+ errorCode +" For details check bug and omission list");
    }
    return toReturn;
  }
  
  //The current state of the board is printed on the screen. 
  private void printBoard()
  {  
    try
    {
     for(int i=0; i<board.length-1; i++)
      {
        for(int j=0; j<=board[i].length; j++)
        {
          if(board[j][i] == 'R')
          {
            System.out.print("| R ");
          }
          else if(board[j][i] == 'Y')
          {

            System.out.print("| Y ");
          }
          else
          {
            System.out.print("|   ");
          }
        }
        System.out.println("|");
      }
       System.out.println("  1   2   3   4   5   6   7");
    }
    catch(Exception e)
    {
      errorCode = "CF-5";
      System.out.println("Error code :"+ errorCode +" For details check bug and omission list");  
    }
  }  
}
