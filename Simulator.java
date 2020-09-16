import java.util.Random;

public class Simulator
{
    private Random random;
    private int totalTimeTakeOff;
    private int totalTimeLanding;
    private int totalPlanesTakenOff;
    private int totalPlanesLanded;
    //Counter to keep track of when to add a plane
    private int counter;
    private int longestTimeWaitedLanding = 0;
    private int longestTimeWaitedTakeOff = 0;
    private int planesCrashed;

    private QueueInterface<Airplane> takeOffQueue;
    private QueueInterface<Airplane> landingQueue;

    public Simulator()
    {
        takeOffQueue = new LinkedQueue<>();
        landingQueue = new LinkedPriorityQueue<>();
        random = new Random();
        //Random int counter between 3-6 units to keep track of when to add a plane into the simulation.
        counter = random.nextInt(4) + 3;
        totalTimeTakeOff = 0;
        totalTimeLanding = 0;
        totalPlanesTakenOff = 0;
        totalPlanesLanded = 0;
        planesCrashed = 0;
    } //end Simulator

    public void update(int currentTime)
    {
        //Decrement fuel and update waiting times of airplane objects in the queue.
        landingQueue.updateValues();
        takeOffQueue.updateValues();
        //Check counter for adding an airplane into simulation.
        //Counter is greater than 0, decrement the counter.
        if (counter > 0)
        {
            counter--;
        }

        //Counter is 0 or less, add a new airplane into simulation.
        else if (counter <= 0)
        {
            Airplane airplane = new Airplane();
            //Random number to decide if the airplane object is joining the take-off queue or the landing priority queue. 1 is landing, 2 is take-off.
            int rand = random.nextInt(2) + 1;
            //Random number is 1, add the airplane object into the landing queue.
            if (rand == 1)
            {
                landingQueue.enqueue(airplane);
            }
            //Random number is 2, add the airplane object into the take-off queue.
            else if (rand == 2)
            {
                takeOffQueue.enqueue(airplane);
            }
            //Reset counter
            counter = random.nextInt(4) + 3;
        }

        //Check landing queue before checking take-off queue.
        //Landing queue isn't empty, but the first airplane in the queue has run out of fuel.
        if(!landingQueue.isEmpty() && landingQueue.getFront().isCrashed())
        {
            landingQueue.dequeue();
            planesCrashed++;
            return;
        }

        //Landing queue isn't empty, and the first airplane in the queue has landing time greater than 0. Decrement fuel and landing time.
        else if (!landingQueue.isEmpty() && landingQueue.getFront().getLandingTime() > 0)
        {
            landingQueue.getFront().update();
            return;
        }

        //Landing queue isn't empty, and the first airplane in the queue has landing time of 0 or less.
        else if (!landingQueue.isEmpty() && landingQueue.getFront().getLandingTime() <= 0)
        {
            //Increment totalPlanesLanded
            totalPlanesLanded++;
            //Add time waited to totalTimeWaitedLanding
            totalTimeLanding += (currentTime - landingQueue.getFront().getTimeArrived());
            int timeWaitedLanding = (currentTime - landingQueue.getFront().getTimeArrived());
            //Longest time waited by an airplane for landing.
            if(timeWaitedLanding >= longestTimeWaitedLanding)
            {
                longestTimeWaitedLanding = timeWaitedLanding;
            }
            landingQueue.dequeue();
            return;
        }

        //Landing queue is empty, take-off queue isn't empty, and the first airplane in the take-off queue has take-off time less than or equal to 0.
        else if (landingQueue.isEmpty() && !takeOffQueue.isEmpty() && takeOffQueue.getFront().getTakeOffTime() <= 0)
        {
            totalPlanesTakenOff++;
            totalTimeTakeOff += (currentTime - takeOffQueue.getFront().getTimeArrived());
            int timeWaitedTakeOff = (currentTime - takeOffQueue.getFront().getTimeArrived());
            if (timeWaitedTakeOff > longestTimeWaitedTakeOff)
            {
                longestTimeWaitedTakeOff = timeWaitedTakeOff;
            }
            takeOffQueue.dequeue();
            return;
        }

        //Landing queue is empty, take-off queue isn't empty, and first airplane in the take-off queue has take-off time greater than 0.
        else if (landingQueue.isEmpty() == true && !takeOffQueue.isEmpty() && takeOffQueue.getFront().getTakeOffTime() > 0)
        {
            takeOffQueue.getFront().updateTakeOffTime();
            return;
        }
        return;
    } //end update

    //Getters
    public float getLongestTimeWaitedLanding()
    {
        return longestTimeWaitedLanding;
    } //end getTotalTimeWaitedLanding

    public float getLongestTimeWaitedTakeoff()
    {
        return longestTimeWaitedTakeOff;
    } //end getTotalTimeWaitedTakeOff

    public int getTotalPlanesTakenOff()
    {
        return totalPlanesTakenOff;
    }

    public int getTotalPlanesLanding()
    {
        return totalPlanesLanded;
    }

    public int getPlanesCrashed()
    {
        return planesCrashed;
    }

   public float getAverageTimeLanding()
    {
        return totalTimeLanding/totalPlanesLanded;
    }

    public float getAverageTimeTakeOff()
    {
        return totalTimeTakeOff/totalPlanesTakenOff;
    }
} //end Simulator class
