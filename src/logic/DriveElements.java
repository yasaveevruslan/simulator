package logic;
public class DriveElements {

    private final String action;
    private final int positionLift;

    public DriveElements(String actionIn, int positionLiftIn){
        this.action = actionIn;
        this.positionLift = positionLiftIn;
    }

    public String getAction() {
        return action;
    }

    public int getPositionLift() {
        return positionLift;
    }
}

