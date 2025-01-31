public class Instructions {
    static void greeting() {
        System.out.println("--------------------------------");
        System.out.println("Hello, I am Martin!\nWhat can I do for you?");
        System.out.println("--------------------------------");
    }

    static int echo(String text) {
        if (text.equals("bye")) {
            System.out.println("Bye, see you next time!");
            System.out.println("--------------------------------");
            return 0;
        }
        System.out.println(text);
        System.out.println("--------------------------------");
        return 1;
    }
}
