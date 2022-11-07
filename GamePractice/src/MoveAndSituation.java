import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MoveAndSituation {

	public static int[] rooms = {0,1,2,3};
	public static int[][] links = {{1,2,3},{2,3,0},{3,0,1},{0,1,2}};
	
	
	public static String BAT = "Bat";
	public static String PIT = "Pit";
	public static String WUMPUS = "Wumpus";
	public static String NOTHING = "Nothing";
	
	public static String[] hazards = {NOTHING,BAT,PIT,WUMPUS};
	
	public static int currentRoom = 0;
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("현재 [ "+currentRoom+" ]번 방에 있습니다.");
			
			int[] nextRooms = links[currentRoom];
			
			System.out.println("다음 중 이동할 방의 번호를 입력해 주세요 :");
			System.out.println(Arrays.toString(nextRooms));
			
			int roomNumber = scanner.nextInt();
			
			move(roomNumber);
			
		}
	}	
		
		/*플레이어가 이동했을 때, --> [이동]과 [이동 후의 이벤트]를 처리해주는 메소드
		 * 이동 한 방에 있는 위험요소에 따라서 해당되는 이벤트를 처리함
		 * */
		
		private static void move(int room) {
			//입력한 방으로 이동합니다
			currentRoom = room;
			
			String hazardInRoom = hazards[currentRoom];
			
			//움퍼스
			if(hazardInRoom.equals(WUMPUS)) {
				System.out.println("움퍼스에게 잡아먹혔습니다!");
				//TODO. game over
			}
			
			//구덩이
			else if(hazardInRoom.equals(PIT)){
				System.out.println("구덩이에 빠졌습니다");
				//TODO. game over
			}
			
			//박쥐
			
			else if(hazardInRoom.equals(BAT)) {
				System.out.println("박쥐가 당신을 잡아 다른 방에 떨어뜨렸습니다.");
				
				Random random = new Random();
				
				do {
					currentRoom = random.nextInt(rooms.length);
				}while(hazards[currentRoom].equals(BAT)); // <--do를 실행했을때의 결과가 박쥐(true)라면 반복문을 다시 한번 실행
				
				
				hazards[room] = NOTHING; //박쥐로 일단 플레이어를 옮겨놓고, 박쥐가 있던 자리를 비워둠
				
				while(true) {
					int newBatRoom = random.nextInt(rooms.length);
					
					if(newBatRoom == currentRoom) { //currentRoom == 박쥐로 인해 플레이어가 이동된 위치.
						continue;
					}
					
					if(hazards[newBatRoom].equals(NOTHING)) {
						hazards[newBatRoom] = BAT;
						break;
					}
				}
				
				move(currentRoom);
			}
			
		}
		
	}

