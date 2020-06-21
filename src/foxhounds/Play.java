package foxhounds;

//import javaboard.Executor;
import javaboard.Player;
import javaboard.PlayerCPUEval;
import javaboard.ExecutorRecorder;
import javaboard.PlayerHumanTerminal;
import java.util.Scanner;

public class Play {

    public static void main(String[] args) {
        FoxAndHounds game = new FoxAndHounds();

        String User;
        Scanner re = new Scanner(System.in);
        System.out.println("Ingress your username: ");
        User = re.nextLine();

        Player[] players = {new PlayerHumanTerminal(User), new PlayerCPUEval()};

        ExecutorRecorder exec = new ExecutorRecorder();
        exec.runGame(game,players);
        re.close();
    }
}
