package hangman;

import java.util.LinkedHashSet;
import java.util.Set;

public class HangmanPlayer {
	private int lives;	// 남은 목숨
	private final Set<Character> guessedLetters;
	
	public HangmanPlayer() {
		this.lives = GameConstants.INITIAL_LIVES;
		this.guessedLetters = new LinkedHashSet<>(); // 이미 사용한 알파벳을 저장할 리스트
	}
	
	public int getLives() {	// 남은 목숨 반환
		return lives;
	}
	
	public boolean isAlive() {	// 플레이어의 생존 여부 반환
		return lives > 0;	
	}
	
	public void decreaseLives() {	// 목숨 감소
		if (isAlive()) {
            lives--; // 생존 중이면 목숨 감소
        }
	}
	
	public void resetPlayer() { // 플레이어 초기화 메소드
        this.lives = GameConstants.INITIAL_LIVES;
        guessedLetters.clear(); // 사용한 알파벳 초기화
    }
	
	public Set<Character> getGuessedLetters() {	// 사용한 알파벳 리스트 반환
		return guessedLetters;
	}

	public void addGuessedLetter(char letter) { // 사용한 알파벳 추가
		guessedLetters.add(letter);
	}
	
	public boolean hasGuessedLetter(char letter) {	// 이미 사용한 알파벳인지 확인
		return guessedLetters.contains(letter);
	}
	
}
