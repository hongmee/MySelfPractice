import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//메모장과 영단어 사전을 혼자서 만들어 보기
//메모장 
//1.메모 작성 2.메모 불러오기 3.메모 삭제 4.메모 수정(되나?)


public class Memo {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
		System.out.println("이용할 메뉴를 입력하세요");
		System.out.println("1.메모 작성하기\n2.메모 불러오기 \n3.메모 삭제 \n4.메모 수정");
		
		
		int task;
		
		try {
			String input = scanner.nextLine();
			task = Integer.parseInt(input);
		}catch(NumberFormatException e) {
			System.out.println("잘못된 입력입니다");
			continue;
		}
		
		// 1. 메모 작성하기
		
		if(task==1) {
			System.out.println("새로 작성할 메모의 파일명을 입력하세요(*경로 및 확장자까지) :");
			String memoName = scanner.nextLine();
			
			FileWriter newMemo = null;
			
			try {
				newMemo = new FileWriter(memoName);
			} catch (IOException e) {
				System.out.println("새 파일 생성에 실패했습니다.");
				continue;
			}
			
			System.out.println("작성할 내용을 입력하세요 : \n*종료하기:내용 없이 엔터");
			
			while(true) {
				String input = scanner.nextLine();
				
				if(input.equals("")) {
					System.out.println("메모 작성을 종료합니다");
					break;
				}else {
					try {
						newMemo.write(input);
						newMemo.write("\n");
					} catch (IOException e) {
						System.out.println("내용 작성에 실패하였습니다");
					}
				}
				
			}

			try {
				newMemo.close();
			} catch (IOException e) {
				System.out.println("파일 닫는것도 실패했습니다 ㅠㅠ");
			}
			
		}
		
		// 2. 메모 불러오기
		
		if(task==2) {
			System.out.println("불러올 파일명을 입력하세요(*경로 및 확장자까지)");
			
			String fileName = scanner.nextLine();
			
			FileInputStream view = null;
			
			try {
				view = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				System.out.println("파일 불러오기에 실패했습니다 :(");
				continue;
			}
			
			System.out.println(fileName+"의 내용은 아래와 같습니다 \n ========");
			
			Scanner reader = new Scanner(view);
			
			while(reader.hasNextLine()) {
				System.out.println(reader.nextLine());
			}
			
			System.out.println("\n");
			
			reader.close();
			
		}
		
		// 3. 메모 삭제하기
		
		if(task==3) {
			System.out.println("삭제할 파일명을 입력하세요(*경로부터 확장자까지)");
			
			String deleteFileName = scanner.nextLine();
			
			File deleteFile = new File(deleteFileName);
			
			if(deleteFile.exists()) {
				if(deleteFile.delete()) {
					System.out.println("메모 파일을 삭제했습니다");
					}else {
					System.out.println("파일삭제 실패");
						}
			}else {
				System.out.println("파일이 없습니다");
			}
			
			
		}
		
		
		
		// 4. 메모 수정하기(덮어쓰기네...ㅎㅎ;)
		
			if(task==4) {
				System.out.println("수정할 파일명을 입력하세요(*경로 및 확장자까지):");
			
			/*	
				
				String fileName = scanner.nextLine();
				
				FileInputStream modifyFile = null;
				
				try {
					modifyFile = new FileInputStream(fileName);
				} catch (FileNotFoundException e) {
					System.out.println("파일 불러오기에 실패했습니다");
				}
				
				System.out.println("수정할 내용을 입력하세요");
				System.out.println("아직 관리자 역량으로 전체 덮어쓰기밖에 안됩니다ㅠㅠ");
				System.out.println("*종료는 내용 없이 엔터");
				
				while(true) {
					
					String input = scanner.nextLine();
					if(input.equals("")) {
						System.out.println("메모 수정을 종료합니다");
						break;
					}else {
						modifyFile. 
					}
				} */
				
				String memoName = scanner.nextLine();
				
				FileWriter newMemo = null;
				
				try {
					newMemo = new FileWriter(memoName);
				} catch (IOException e) {
					System.out.println("파일 불러오기에 실패했습니다.");
					continue;
				}
				
				System.out.println("작성할 내용을 입력하세요 : \n*종료하기:내용 없이 엔터");
				
				while(true) {
					String input = scanner.nextLine();
					
					if(input.equals("")) {
						System.out.println("메모 수정을 종료합니다");
						break;
					}else {
						try {
							newMemo.write(input);
							newMemo.write("\n");
						} catch (IOException e) {
							System.out.println("내용 작성에 실패하였습니다");
						}
					}
					
				}

				try {
					newMemo.close();
				} catch (IOException e) {
					System.out.println("파일 닫는것도 실패했습니다 ㅠㅠ");
				}
				
				
				
				
			}
		
		
	}
}
	
}
