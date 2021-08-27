import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {

    private Scanner scan = new Scanner(System.in);

    @Override
    public String receiveInput() {
        return scan.nextLine();
    }

    @Override
    public void displayText(Object text) {

    }

    @Override
    public void displayNewLine() {
        System.out.println();
    }

    @Override
    public void displayPrompt(String prompt) {
        System.out.println(prompt);
    }

    @Override
    public void displayBreak() {
        System.out.println("--------------------------------------------------");
    }
}
