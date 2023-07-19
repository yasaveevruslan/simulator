package cases;

public class CmoduleSensors implements IState
{

    private String commandSensors;

    public CmoduleSensors(String commandSensors)
    {
        this.commandSensors = commandSensors;
    }

    @Override
    public boolean execute()
    {
        return true;
    }
}
