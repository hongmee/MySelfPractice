package Wumpus;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HuntTheWumpus {

	public static Integer[] rooms = {
			0,1,2,3,4,5,6,7,8,
			9,10,11,12,13,14,15,16,17,18,19
			};
	
	public static Integer[][] links = {
			{1,7,4},{0,9,2},{1,11,3},{2,13,4},{3,0,5},
			{4,14,6},{5,16,7},{6,0,8},{7,17,9},{8,1,10},
			{9,18,11},{10,2,12},{11,19,12},{12,3,14},{13,5,15},
			{14,19,16},{15,6,17},{6,8,18},{17,10,19},{18,12,15}
	};
	
	public static String BAT = "Bat";
	public static String PIT = "Pit";
	public static String WUMPUS = "Wumpus";
	public static String NOTHING = "Nothing";
	
	public static ArrayList<String> hazards = new ArrayList<>();
	public static HashMap<String, String> hazardMessages = new HashMap<>();
	
	public static Random random = new Random();
	public static Scanner scanner = new Scanner(System.in);
	
	public static boolean gameOver = true;
	
	public static int curentRoom;
	public static int arrowCount;
	public static int wumpusRoom;
	
	public static void main(String[] args) {
		
		showIntro();
		
		initializeStaticValues();
		
		while(true) { //게임플레이 1회 분량
			initializePlayVariables();
			
			setupHazards();
			
			delay(1000L);
			System.out.println("\"...\"");
			delay(1000L);
			System.out.println("\n....");
			delay(1000L);
			System.out.println("[동굴에 들어왔습니다.]");
			delay(1000L);
			System.out.println("\"섬뜩한 곳이군.\"");
			delay(1000L);
			
			game();
			
			selectReplay();
		}
		
	}//메인
	
	
	private static void showIntro() {
		try {
			FileInputStream inputStream = new FileInputStream("src/Wumpus/intro.txt");
			Scanner scanner = new Scanner(inputStream);
			while(scanner.hasNextLine()){
				System.out.println(scanner.nextLine());
				delay(500L);
			}
		}catch(FileNotFoundException e){
			System.out.println("인트로를 불러올 수 없어 생략합니다.");
		}
	}
	
	
	public static void initializeStaticValues() { //게임의 정적 요소들을 초기화해주는 기능. 왜 멤버변수에 끼워넣지 않은걸까??
		hazardMessages.put(WUMPUS, "\"어디선가 끔찍한 악취가 난다.\"");
		hazardMessages.put(BAT, "\"어디선가 부스럭거리는 소리가 들린다.\"");
		hazardMessages.put(PIT, "\"바람이 부는 소리가 들리는 것 같아.\"");
		hazardMessages.put(NOTHING, "\"이 방에는 아무 것도 없는 것 같군.\"");
	}
	
	
	public static void initializePlayVariables() {
		gameOver = false;
		
		curentRoom = random.nextInt(rooms.length);
		arrowCount = 5;
	}
	
	
	
	public static void setupHazards() {
		if(hazards.size()==0) {
			for(int i=0 ; i<rooms.length ; i=i+1) { //1회차때만 실행되는 기능
				hazards.add(NOTHING);
			}
		}
		
		for(int i=0 ; i<rooms.length ; i=i+1) { //게임이 재시작 되었을 때 
			hazards.set(i, NOTHING);
		}
		
		String[] ordinals = {WUMPUS,BAT,BAT,BAT,PIT,PIT};
		
		for(String hazard : ordinals) {
			int room;
			
			while(true) {
				room = random.nextInt(rooms.length);
				
				if(isTooCloseWithPlayer(room)) {
					continue;
				}
				
				if(hazards.get(room).equals(NOTHING)) {
					break;
				}
			}
			
			hazards.set(room, hazard);
			
			if(hazards.equals(WUMPUS)) {
				wumpusRoom = room;
			}
			
		}
	
	}
	
	
	public static boolean isTooCloseWithPlayer(int room) {
		if(curentRoom == room) {
			return true;
		}
		
		List<Integer> linkedRooms = Arrays.asList(links[curentRoom]);
		if(linkedRooms.contains(room)) {
			return true;
		}
		
			return false;
	}
	
	
	private static void game() {
		while(gameOver==false) {
			System.out.println("\n당신은 "+curentRoom+"번 방에 있습니다.");
			System.out.println("[1:이동][2:화살 쏘기][3:통로 목록][0:게임 종료]");
			
			String action = scanner.nextLine();
			
			if(action.equals("1")) {
				Integer[] nextRooms = links[curentRoom];
				
				System.out.println("\n몇 번 방으로 이동하시겠습니까?");
				System.out.println(Arrays.toString(nextRooms));
				
				String nextRoomInput = scanner.nextLine();
				int nextRoom = parseIntegerOrNegative1(nextRoomInput);
				
				if(Arrays.asList(nextRooms).contains(nextRoom)) {
					move(nextRoom);
				}else {
					System.out.println("선택한 방으로는 이동할 수 없습니다.");
				}
			}
			
			else if(action.equals("2")) {
				Integer[] nextRooms = links[curentRoom];
				
				System.out.println("\n몇 번 방에 화살을 쏘시겠습니까?");
				System.out.println(Arrays.toString(nextRooms));
				
				String targetInputRoom = scanner.nextLine();
				int targetRoom = parseIntegerOrNegative1(targetInputRoom);
				
				if(Arrays.asList(nextRooms).contains(targetRoom)) {
					shoot(targetRoom);
				}else {
					System.out.println("선택한 방에는 화살을 쏠 수 없습니다.");
				}
			}
			
			else if(action.equals("3")) {
				System.out.println("현재 방에서 이동할 수 있는 방 목록입니다.");
				System.out.println(Arrays.asList(links[curentRoom]));
			}
			
			else if(action.equals("0")) {
				System.out.println("게임을 종료합니다");
				gameOver=true;
			}
			
			else {
				System.out.println("잘못된 입력입니다");
			}
		}
	}
	
	public static void shoot(int room) {
		arrowCount = arrowCount-1;
		delay(500L);
		System.out.println("슈웅-");
		delay(200L);
		
		if(hazards.get(room).equals(WUMPUS)) {
			System.out.println("푹!");
			delay(100L);
			System.out.println("\"키에에엑!!!\"");
			delay(100L);
			System.out.println("**축하합니다. 당신은 움퍼스 사냥에 성공했습니다!!**");
			gameOver = true;
		}else {
			System.out.println("\"....\"");
			delay(200L);
			System.out.println("\"..화살만 낭비했군.\"");
			
			if(arrowCount==0) {
				delay(200L);
				System.out.println("[이런, 화살이 남지 않았다!]");
				delay(200L);
				System.out.println("당신은 움퍼스 사냥에 실패했습니다.");
				gameOver = false;
			}else if(random.nextInt(4)!=0) {
				System.out.println("[이런! 당신의 화살 소리에 움퍼스가 깨어났습니다]");
				
				Integer[] nextRooms = links[wumpusRoom];
				int nextRoom = nextRooms[random.nextInt(3)];
				
				if(hazards.get(nextRoom).equals(NOTHING)) {
					hazards.set(wumpusRoom, NOTHING); //일단 (기존)움퍼스방을 비워주고
					hazards.set(nextRoom, WUMPUS); //이사간 움퍼스룸인데 아직 문패가 안 붙음
					wumpusRoom = nextRoom; //(기존)움퍼스방의 문패를 이사간곳에 옮겨줌
				}
				//움퍼스 이동 후 이벤트
				if(nextRoom==curentRoom) {
					System.out.println("\"으아아아악!!!\"");
					delay(300L);
					System.out.println("움퍼스가 당신을 잡아먹었습니다.");
					delay(300L);
					System.out.println("G a m e O v e r");
					gameOver=true;
				}else if(wumpusRoom==nextRoom) {
					System.out.println("움퍼스가 도망갔습니다");
				}
			}
		}//화살을 빗맞췄을경우
	}//shoot
	
	
	
	public static int parseIntegerOrNegative1(String input) {
		try {
			return Integer.parseInt(input);
		}catch(NumberFormatException e) {
			return -1;
		}
	}
	
	
	public static void move(int room) {
		curentRoom = room;
		System.out.println(room+"번 방으로 이동했습니다.");
		
		String hazard = hazards.get(curentRoom);
		
		delay(1000L);
		
		if(hazard.equals(WUMPUS)) {
			System.out.println("\"으아아아악!!!\"");
			delay(300L);
			System.out.println("움퍼스가 당신을 잡아먹었습니다.");
			delay(300L);
			System.out.println("G a m e O v e r");
			gameOver=true;
		}
		
		else if(hazard.equals(PIT)) {
			System.out.println("[공기의 흐름이 바뀌었다...]");
			delay(300L);
			System.out.println("\"앗, 으아아악!!!!\"");
			delay(300L);
			System.out.println("[쿵!!]");
			System.out.println("[당신은 구덩이에 빠졌습니다.]");
			System.out.println("[더 이상 움퍼스를 사냥할 수 없습니다.]");
			System.out.println("G a m e O v e r");
			gameOver=true;
		}
		
		else if(hazard.equals(BAT)) {
			System.out.println("[휙-!]");
			delay(300L);
			System.out.println("[커다란 박쥐가 당신을 낚아채 다른 방으로 떨어뜨렸습니다.]");
			
			do {
				curentRoom = random.nextInt(rooms.length);
			}while(hazards.get(curentRoom).equals(BAT)); //반복 조건
			
			hazards.set(room, NOTHING); //일단 사용자가 선택한 방에 있던 박쥐를 비워주고
			while(true) {
				int newBatRoom = random.nextInt(rooms.length); //날아간 박쥐의 새 보금자리를 만들어줌
				
				if(newBatRoom==curentRoom) { //랜덤으로 뽑힌 새 보금자리가 현재 방(=사용자를 던져버린 방)이랑 같을 경우
					continue; 
				}
				
				if(hazards.get(newBatRoom).equals(NOTHING)) {//랜덤으로 배정해준 새 보금자리에 아무것도 없다면
					hazards.set(newBatRoom, BAT);
					break; //while문을 탈출 
				}
				
			}
			move(curentRoom);
		}
		
		else { //hazard가 없는 경우
			List<Integer> nextRooms = Arrays.asList(links[curentRoom]);
			
			System.out.println("\n연결되어 있는 통로를 살핀다.");
			for(int nextRoom : nextRooms) {
				delay(500L);
				String hazardAround = hazards.get(nextRoom);
				System.out.println(hazardMessages.get(hazardAround));
			}
			
			Collections.shuffle(nextRooms);
			
		}
	}
	
	
	private static void selectReplay() {
		System.out.println("[게임이 끝났습니다. 다시 시작하시겠습니까?]");
		
		while(true) {
			System.out.println("[ 0:종료하기 1:다시 플레이 ]");
			String action = scanner.nextLine();
			
			if(action.equals("0")) {
				System.out.println("게임을 종료합니다.");
				System.exit(0);
			}
			else if(action.equals("1")) {
				System.out.println("게임을 다시 플레이합니다!");
				break; //이 반복문을 빠져나가 메인에 있는 while루프의 첫 줄부터 다시 시작한다고 함
			}
			else {
				System.out.println("잘못된 입력입니다.");
			}
			
		}
	}
	
	public static void delay(long sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}



