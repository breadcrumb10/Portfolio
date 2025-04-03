import java.io.IOException;
import java.util.*;

public class ClientExecutor implements AutoCloseable {

	public static int portnum = 4999;
	public static boolean running = true;
	
	public static void main(String[] args) {
		Scanner Scantato = new Scanner(System.in);
		//Exception needed to allow run.
		//IO catch for server and a General catch for close().
		
		try (PrinterClient ftw = new PrinterClient()){
			while (running == true) {
				ftw.Run("localhost",portnum);
				
				System.out.println("\t\t-What would you like to do?-");
				System.out.println();
				System.out.println("\t{1: Close all Clients\t\t\t\t2: Create new client}");
				
				int choice = Scantato.nextInt();
				switch (choice) {
				
					//Close all clients
					case 1:
						Scantato.close();
						running = false;
						break;
						
					//Create new client
					case 2:
						Scantato.close();
						running = false;
						break;
						
					//repeat
					default:
						break;
				}//Switch	
				}//OnceMore
			}//ftw
		
 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}//main
	

	@Override
	public void close() throws Exception {
		System.out.println("Client: Closing Resources");
	}
	
}
