import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        //greeting
        Instructions.greeting();

        //main loop
        while(true) {
            command = scanner.nextLine();
            int end = Instructions.echo(command);
            if(end == 0) {
                return;
            }
        }
    }
}
