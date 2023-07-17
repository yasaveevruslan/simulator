import training.RobotContainer;
import training.StateMachine;


public class Main {

    static StateMachine sm = new StateMachine();



    public static void main(String[] args) {

        new Thread( () ->{
            while (!Thread.interrupted()){
                try {
                    new RobotContainer();
                    RobotContainer.el.calculateCoordinates();
                    RobotContainer.el.calculateSensors();
                    sm.Update();
                    Thread.sleep(20);
                    System.out.println("array: " + StateMachine.currentArray + " index: " + StateMachine.currentIndex);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

}