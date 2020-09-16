import java.util.Random;

public class Airplane implements Updatable, Comparable<Airplane>
{
    //Units of time spent landing.
    private int landingTime;

    //Units of time spent taking off.
    private int takeOffTime;

    //Units of fuel left in the airplane, treated as priority in priority queue
    private int fuel;

    //Unit of time the airplane arrived in the simulation
    private int timeArrived;

    //Keep track of whether or not a plane is crashed.
    public boolean crashed;

    public Airplane()
    {
        this.timeArrived = timeArrived;
        //Each plane takes 2 units of time to land
        landingTime = 2;
        //Each plane takes 3 units of time to take off.
        takeOffTime = 3;
        //Random unit of fuel between 5 to 15.
        fuel = new Random().nextInt(10 + 1) + 5;
        crashed = false;
    } //end Airplane

    //Getter methods
    public int getTimeArrived() { return timeArrived; } //end getTimeArrived
    public int getFuel() { return fuel; } //end getFuel
    public int getLandingTime() { return landingTime; } //end getLandingTime
    public int getTakeOffTime() { return landingTime; } //end getTakeOffTime

    //Update takeOffTime of airplane object.
    public boolean updateTakeOffTime()
    {
        takeOffTime--;
        return takeOffTime <= 0;
    } //end updateTakeOffTime

    public boolean isCrashed()
    {
        return fuel == 0;
    }

    //Compares fuel of airplane objects
    @Override
    public int compareTo(Airplane other)
    {
        //Airplane objects are the same
        if(this.equals(other))
            return 0;
        //Airplane
        else if(getFuel() > other.getFuel())
            return 1;
        else
            return -1;
    } //end compareTo

    //Update method to decrement fuel and landingTime.
    @Override
    public void update()
    {
        fuel--;
        if(fuel == 0)
        {
            crashed = true;
        }
        landingTime--;
    } //end update
} //end Airplane
