import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Shoot {
	
	
	public static int[] rooms = {0,1,2,3};
	public static int[][] links = {{1,2,3},{2,3,0},{3,0,1},{0,1,2}};
	
	
	public static String BAT = "Bat";
	public static String PIT = "Pit";
	public static String WUMPUS = "Wumpus";
	public static String NOTHING = "Nothing";
	
	public static String[] hazards = {NOTHING,NOTHING,NOTHING,WUMPUS};
	
	public static int currentRoom = 0;
	public static int wumpusRoom = 3;

	public static int arrowCount = 5;
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		
		while(true) {
			System.out.println("현재 [ "+currentRoom+" ]번 방에 있습니다");

			int[] nextRooms = links[currentRoom];
			
			System.out.println(Arrays.toString(nextRooms));
			System.out.println("어느 방에 화살을 쏘시겠습니까?");
			
			int roomNumber = scanner.nextInt();
			
			shoot(roomNumber);
			
		}	
	}
	
	public static void shoot(int room) {
		if(hazards[room].equals(WUMPUS)) {
			System.out.println("축하합니다! 당신은 움퍼스 사냥에 성공했습니다");
		}else { //움퍼스가 없는 곳에 화살을 쐈을 경우
			arrowCount = arrowCount-1;
			
			if(arrowCount==0) {
				System.out.println("화살을 모두 사용하였습니다. 무력합니다.");
				System.out.println("움퍼스 사냥 실패");
			}else if(random.nextInt(4)!=0) { //<-- 화살이 0이 아닐 때 75퍼센트의 뜻으로 발동한다는 뜻. 그럼 나머지 25퍼는? 그냥 카운트만 -1됨. 
				System.out.println("움퍼스가 깨어났습니다!");
				
				int[] nextRooms = links[wumpusRoom];
				int nextRoom = nextRooms[random.nextInt(3)]; //어차피 움퍼스 빠지고 남은 방 3개니까 3써줌.
				
				if(hazards[nextRoom].equals(NOTHING)) {
					hazards[wumpusRoom] = NOTHING; //일단 기존 움퍼스 자리부터 (이사갔으니)비워주고
					hazards[nextRoom] = WUMPUS;
					wumpusRoom = nextRoom; 
				}
				
				if(wumpusRoom==currentRoom) {
					System.out.println("움퍼스가 당신을 잡아먹었습니다. 으아악.");
					System.out.println("움퍼스 사냥 실패");
				}else if(wumpusRoom==nextRoom) { //움퍼스가 플레이어가 없는 곳으로 잘 이동했을 경우
					System.out.println("움퍼스가 도망갔습니다");
				}
			}
		}
	}
	

}



