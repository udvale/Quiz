import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

/**
 * The Client class represents a client for the Hangman game. It connects to the server, 
 * sends and receives messages, and interacts with the user to play the game.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        String host = args[0];
        int port = Integer.parseInt(args[1]);      
        
        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);
        String line = "";
        String input = "";

        while(!input.equalsIgnoreCase("quit")){
            line = in.readLine();
            //print response from client
            if (line.trim().equals("SETPHRASE")){
                System.out.print("Enter a phrase for your opponent to guess: ");
                input = scanner.nextLine();
                //send the phrase to the server
                out.println(input);
                out.flush();
                System.out.println("The hidden phrase is: " + input);
                // System.out.println("Waiting for your opponent to guess.");
            } else if (line.trim().equals("GUESS")) {
                    System.out.print("Guess a letter: ");
                    input = scanner.nextLine();
                    out.println(input);
                    out.flush();
            } else if (line.trim().equals("GUESSAGAIN")) {
                    System.out.print("You already guessed that letter. Guess again: ");
                    input = scanner.nextLine();
                    out.println(input);
                    out.flush();
            }else if (line.trim().startsWith("HINT")) {
                System.out.println("The other player has requested a hint. What's the hint? " );
                System.out.print(">>>  ");
                input = scanner.nextLine();
                out.println(input);
                out.flush();
            } else if (line.trim().equals("USERNAME")) {
                System.out.print("Enter your username: ");
                input = scanner.nextLine();
                out.println(input);
                out.flush();
            } else if (line.trim().equals("PASSWORD")) {
                System.out.print("Enter your password: ");
                input = scanner.nextLine();
                out.println(input);
                out.flush();
            }else if (line.trim().equals("SIGNIN")) {
                System.out.print("Do you have an account (yes/no)? ");
                input = scanner.nextLine();
                out.println(input);
                out.flush();
            }else if (line.trim().equals("ENDGAME")) {
                    System.exit(0);
            } else {
                    System.out.println(line);
            }
        }
        scanner.close();
        socket.close();
    }
}
