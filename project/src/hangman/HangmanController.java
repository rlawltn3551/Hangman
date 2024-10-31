package hangman;

import java.util.Scanner;

public class HangmanController {
	private final WordRepository wordRepository; // 단어 저장소
	private final HangmanPlayer player; // 플레이어 정보
	private GameStatus gameStatus; // 게임 상태
	private final Scanner scanner; // 사용자 입력을 위한 스캐너
	
	public HangmanController(WordRepository wordRepository, HangmanPlayer player) {
		this.wordRepository = wordRepository;
		this.player = player;
		this.scanner = new Scanner(System.in);
	}
	
	public void startGame() {
		int difficultyLevel = selectDifficulty(); // 난이도 선택
        String answerWord = wordRepository.getRandomWord(difficultyLevel); // 난이도에 맞는 랜덤 단어 선택
        
        if (answerWord.isEmpty()) {
        	System.out.println("해당 길이의 단어가 없습니다. 게임을 종료합니다.");
        	return ;
        }
        
        initializeGame(answerWord); // 게임 초기화
        
        System.out.println("행맨 게임 시작하기 (난이도: " + difficultyLevel + ", 단어 길이: " + answerWord.length() + ")" );
        scanner.nextLine(); // 사용자가 준비되기를 기다림
        play();
	}
	private void initializeGame(String answerWord) {
        gameStatus = new GameStatus(answerWord); // 게임 상태 초기화
        player.resetPlayer(); // 플레이어 초기화
    }
	
	private int selectDifficulty() {
		System.out.println("-----난이도를 선택하세요-----");
        System.out.println("1: 쉬움 (3~4글자)");
        System.out.println("2: 중간 (5~7글자)");
        System.out.println("3: 어려움 (8글자 이상)");
	
        int difficultyLevel;
        while (true) {
            System.out.print("난이도를 입력하세요 (1-3): ");
            difficultyLevel = getValidDifficulty();
            if (difficultyLevel >= 1 && difficultyLevel <= 3)
                break;
            System.out.println("유효하지 않은 난이도입니다. 다시 입력하세요.");
        }
        return difficultyLevel; // 선택된 난이도 반환
    }

    private int getValidDifficulty() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // 잘못된 입력을 무시하고 다음 입력을 받음
            return 0; // 유효하지 않은 경우 기본값으로 0 반환
        }
    }
    
	private void play() {
	    while (player.isAlive() && !gameStatus.isWordGuessed()) {
	    	printGameStatus(); // 게임 상태 출력
            char guessedLetter = getGuessedLetter(); // 입력 대기

            if (guessedLetter == '\0' || player.hasGuessedLetter(guessedLetter)) {
                if (guessedLetter != '\0') {
                    System.out.println("이미 사용한 알파벳입니다: " + guessedLetter);
                } else {
                	System.out.println("유효하지 않은 입력입니다.");
                }
                continue; // 유효하지 않은 입력이나 이미 사용한 알파벳일 경우 반복
            }
            
	        player.addGuessedLetter(guessedLetter); // 사용한 알파벳 리스트에 추가
	        processGuess(guessedLetter); // 알파벳 추측 처리
	        
	    }

	    printGameStatus(); // 최종 게임 상태 출력
        printGameOverMessage(); // 게임 종료 메시지
        askForRestart(); // 게임 재시작 여부 묻기
	}
	
	// 현재 게임 상태 출력 메소드
	private void printGameStatus() {
        printHangman();
        printLives();
        System.out.println(gameStatus); 
        printGuessedLetters();
    }

	// 알파벳 추측 처리 메소드
    private void processGuess(char guessedLetter) {
    	// 추측한 알파벳 처리
        if (!gameStatus.isLetterInWord(guessedLetter)) {
            player.decreaseLives(); // 알파벳이 단어에 없으면 생명 감소
            System.out.println(guessedLetter + "은 단어에 포함되지 않습니다.");
        } else {
            gameStatus.guessLetter(guessedLetter); // 알파벳 추측
            System.out.println(guessedLetter + "은 단어에 포함됩니다!");
        }
    }
	
    // 남은 목숨 출력 메소드
	private void printLives() {
        System.out.print("남은 목숨: ");
        for (int i = 0; i < GameConstants.INITIAL_LIVES; i++) {
            System.out.print(i < player.getLives() ? "🖤" : "❤️");
        }
        System.out.println();
    }

	// 사용한 알파벳 출력 메소드
    private void printGuessedLetters() {
        System.out.print("사용한 알파벳: ");
        for (char letter : player.getGuessedLetters()) {
            System.out.print(letter + " ");
        }
    }

    // 알파벳 입력 메소드
    private char getGuessedLetter() {
        System.out.print("\n알파벳을 입력하세요: ");
        try {
            String input = scanner.nextLine().toLowerCase();
            if (input.isEmpty() || input.charAt(0) < 'a' || input.charAt(0) > 'z') {
                System.out.println("알파벳(a-z)을 입력하세요.");
                return '\0'; // 유효하지 않은 입력
            }
            return input.charAt(0); // 첫 번째 문자 반환
        } catch (Exception e) {
            System.out.println("유효한 입력이 아닙니다. 다시 시도하세요.");
            return '\0'; // 예외 처리
        }
    }

    // 게임 종료 메시지 출력 메소드
    private void printGameOverMessage() {
        if (player.isAlive() && gameStatus.isWordGuessed()) {
            System.out.println("\n축하합니다! 단어를 맞췄습니다! " + gameStatus.getAnswerWord());
        } else {
            System.out.println("\n게임 오버! 정답은: " + gameStatus.getAnswerWord());
        }
    }

    
	// 게임 재시작 여부를 묻는 메소드
	private void askForRestart() {
		while (true) {
	        System.out.print("다시 게임을 시작하시겠습니까? (예/아니오): ");
	        String response = scanner.next(); // 사용자 입력 받기
	        if (response.equalsIgnoreCase("예")) {
	            startGame(); // 게임 시작
	            break; // 게임이 시작되면 루프 탈출
	        } else if (response.equalsIgnoreCase("아니오")) {
	            System.out.println("게임을 종료합니다. 감사합니다!");
	            break; // 종료 메시지 후 루프 탈출
	        } else {
	            System.out.println("예 또는 아니오를 입력해주세요."); // 유효하지 않은 입력
	        }
	    }
	}
	
	// 행맨 상태 출력 메소드
	private void printHangman() {
	    // 각 단계별 머리, 몸통, 팔, 다리 여부를 정의
	    String head = " ";
	    String body = " ";
	    String leftArm = " ";
	    String rightArm = " ";
	    String leftLeg = " ";
	    String rightLeg = " ";

	    // 남은 생명 수 가져오기
	    int lives = player.getLives();

	 // 생명 수에 따른 설정 (6이 남았을 때 아무것도 보이지 않음)
	    head = (lives <= 5) ? "O" : " ";
	    body = (lives <= 4) ? "|" : " ";
	    leftArm = (lives <= 3) ? "/" : " ";
	    rightArm = (lives <= 2) ? "\\" : " ";
	    leftLeg = (lives <= 1) ? "/" : " ";
	    rightLeg = (lives == 0) ? "\\" : " ";

	    // 남은 생명 수에 따른 출력 구성
	    System.out.println("  _______  ");
	    System.out.println(" |       | ");
	    System.out.println(" |       " + head);
	    System.out.println(" |      " + leftArm + body + rightArm);
	    System.out.println(" |      " + leftLeg + " " + rightLeg);
	    System.out.println(" |_________ ");
	}



}
