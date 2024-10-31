package hangman;

import java.util.LinkedHashSet;
import java.util.Set;

public class GameStatus {
	private final String answerWord;	// 정답 단어
	private Set<Character> guessedLetters; // 플레이어가 추측한 글자 집합
	private int correctGuesses; // 맞춘 글자 수
	
	public GameStatus(String answerWord) {
		this.answerWord = answerWord; // 정답 단어 초기화
		this.guessedLetters = new LinkedHashSet<>();
		this.correctGuesses = 0; // 맞춘 글자 수 초기화
	}

    public String getAnswerWord() {
        return answerWord; // 정답 단어 반환
    }

	public boolean isWordGuessed() {
        return correctGuesses == answerWord.length(); // 모든 글자를 맞췄는지 확인
    }

    public boolean isLetterInWord(char letter) {
        return answerWord.indexOf(letter) >= 0; // 글자가 정답 단어에 있는지 확인
    }

    public void guessLetter(char letter) {
    	if (!guessedLetters.contains(letter)) { 
            guessedLetters.add(letter);
            if (isLetterInWord(letter)) {
                correctGuesses += countOccurrences(letter); // 맞춘 글자 수 증가
            }
        }
    }

    private int countOccurrences(char letter) {
        int count = 0;
        for (char c : answerWord.toCharArray()) {
            if (c == letter) {
                count++;
            }
        }
        return count; // 글자의 개수를 반환
    }

    @Override
    public String toString() {
    	String currentStatus = ""; // 현재 상태를 저장할 문자열

        for (char c : answerWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                currentStatus += c; // 맞춘 글자는 그대로 추가
            } else {
                currentStatus += "_"; // 맞추지 못한 글자는 언더바로 표시
            }
            currentStatus += " "; // 글자 사이에 공백 추가
        }

        return currentStatus.trim(); // 현재 상태 반환
    }	
	
}
