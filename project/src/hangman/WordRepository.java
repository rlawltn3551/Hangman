package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordRepository {
	private final List<String> words = new ArrayList<>(); // 단어 목록을 저장할 ArrayList
	private final Random random = new Random();

	 public WordRepository() {
	        loadWords(); // 단어 목록 초기화
	    }

    private void loadWords() {
    	// 단어 목록 추가
        String[] wordArray = {
            "cat", "dog", "sun", "moon", "star", "fish", "tree", "book",
            "apple", "grape", "banana", "orange", "kiwifruit", "pencil", "guitar",
            "friend", "computer", "education", "elephant", "adventure", "caterpillar",
            "basketball", "chocolate", "television", "butterfly", "strawberry", 
            "superhero", "notebook", "university", "happiness", "mountain", "paradise",
            "tiger", "dolphin", "rainbow", "ocean", "forest", "castle", "dragon", 
            "robot", "island", "pizza", "coffee", "snowflake", "chameleon", 
            "fireworks", "adventure", "mystery", "journey", "whisper", "explorer", 
            "freedom", "creativity", "imagination", "wonderland", "unicorn", 
            "fantasy", "galaxy", "puzzle", "seashell", "butterfly", "sunflower"
        };
        
        // 단어 목록을 추가
        for (String word : wordArray) {
            words.add(word);
        }
    }

    public String getRandomWord(int difficultyLevel) {
    	int length = getDifficultyLength(difficultyLevel); // 난이도에 따른 단어 길이 결정
        List<String> filteredWords = filterWordsByLength(length);
        return filteredWords.isEmpty() ? "" : filteredWords.get(random.nextInt(filteredWords.size())); // 랜덤으로 단어 선택
    }
    
    private List<String> filterWordsByLength(int length) {
        List<String> filteredWords = new ArrayList<>();
        // 단어를 길이에 따라 필터링
        for (String word : words) {
            if (isValidWordLength(word.length(), length)) {
                filteredWords.add(word);
            }
        }
        return filteredWords; // 필터링된 단어 목록 반환
    }

    private boolean isValidWordLength(int wordLength, int length) {
    	if (length <= 4)
    		return wordLength >= 3 && wordLength <= 4; // 난이도 1: 3~4글자
        else if (length <= 7)
            return wordLength >= 5 && wordLength <= 7; // 난이도 2: 5~7글자
        else
            return wordLength >= 8; // 난이도 3: 8글자 이상
    }
    
    // 난이도에 따라 단어 길이 결정
    public int getDifficultyLength(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1: return random.nextInt(2) + 3; // 쉬운 단어: 3~4글자
            case 2: return random.nextInt(3) + 5; // 중간 단어: 5~7글자
            case 3: return 8; // 어려운 단어: 8글자 이상
            default: return 0; // 잘못된 난이도 선택
        }
    }
	
}
