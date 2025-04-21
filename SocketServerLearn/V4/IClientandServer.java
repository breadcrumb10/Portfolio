
//The "Workhorse" interface
//named to represent the Server & Client methods that do things.

public interface IClientandServer {
    
    final int port = 4999;
    boolean isrunning = false;

    boolean IsRunning();
    void stop();

    void run();
    
}
