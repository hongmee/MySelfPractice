import java.util.Arrays;
import java.util.Scanner;

public class RoomsInCave {

	public static void main(String[] args) {
		
		int[] rooms = {0,1,2,3}; // 방 넘버
		int[][] links = {{1,2,3},{2,3,0},{3,0,1},{0,1,2}}; //방과 연결되는 통로
		
		int currentRoom = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("현재 [ "+currentRoom+" ]번 방에 있습니다.");
			System.out.println("다음 방 중에서 이동할 방의 번호를 입력해주세요.");
			System.out.println(Arrays.toString(links[currentRoom]));
			
			int roomNumber = scanner.nextInt();
			
			currentRoom = roomNumber;
			
		}
		
	}
	
}
