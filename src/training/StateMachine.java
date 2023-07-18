package training;

public class StateMachine {

    public static int currentArray = 0;
    public static int currentIndex = 0;
    public static boolean isFirst = true;

    public static String commandLogic = "F";
    public static int numberPalace = 0;
    public static int indexElementLogic = 0;


    public void Update(){
        if (States.state[currentArray][currentIndex].execute()){
            isFirst = true;
            currentIndex++;
        }
    }
}
