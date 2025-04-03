import java.io.IOException;
import java.util.Scanner;

public class ServerExecutor implements AutoCloseable{

	public static int portnum = 4999;
	public static boolean running = true;
	
	//Main String
		public static void main(String[] args) {
			
			Scanner Scantastic = new Scanner(System.in);
			boolean prompt = true;
			
			while (running) {
				
				//Two exceptions needed to allow run.
				//IO catch for server and a General catch for close()
				try (StringServer wtf = new StringServer()){
					wtf.run();
					//wtf.close();
				} catch (IOException e) {
					System.out.println("Server Error: IOException");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Server Error: Could not close 'wtf'");
					e.printStackTrace();
				}
				
				if (running) {
					prompt = true;
				}
				
				while (prompt == true) {
					System.out.println("\t\t-What would you like to do?-");
					System.out.println();
					System.out.println("\t{1: Close all servers\t2: Start another server/t3: continue running server(s)}");
					
					int yesno = Scantastic.nextInt();
					switch (yesno) {
					//Close all servers
					case 1:
						running = false;
						prompt = false;
						Scantastic.close();
						break;
				//Start another Server
					case 2:
						portnum++;
						System.out.println("Created server at port:" +portnum);
						prompt = false;
						break;
				//Continue running current servers
					case 3:
						prompt = false;
						break;
						
				//Default - Run the Prompt again
					default:
						break;
					}
				}
				
				
			}//Running
			
		}//main
		
		@Override
		public void close() throws Exception {
			System.out.println("Server: Closing resources.");
			
		}

}
