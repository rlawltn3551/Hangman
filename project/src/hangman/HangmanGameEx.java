package hangman;

public class HangmanGameEx {

	public static void main(String[] args) {
		WordRepository wordRepository = new WordRepository(); // 단어 저장소 초기화
        HangmanPlayer player = new HangmanPlayer(); // 플레이어 초기화
        HangmanController controller = new HangmanController(wordRepository, player); // 컨트롤러 초기화

        controller.startGame(); // 게임 시작
    }

}
