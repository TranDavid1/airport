public class Main
{
    public static void main(String[] args)
    {
        Simulator simulator = new Simulator();
        for (int i = 0; i < 1000; i++)
        {
            simulator.update(i);
        }

        System.out.println("Average time waiting for take off: " + simulator.getAverageTimeTakeOff());
        System.out.println("Longest time waiting for take off: " + simulator.getLongestTimeWaitedTakeoff());
        System.out.println("Average time waiting to land: " + simulator.getAverageTimeLanding());
        System.out.println("Longest time waiting to land: " + simulator.getLongestTimeWaitedLanding());
        System.out.println("Planes crashed: " + simulator.getPlanesCrashed());
        System.out.println("Planes taken off: " + simulator.getTotalPlanesTakenOff());
        System.out.println("Planes landed: " + simulator.getTotalPlanesLanding());
    }
}
