# java 프로젝트 : HangmanGame
## 프로젝트 기능
난이도(글자 수에 따라 쉬움(3-4글자), 중간(5-7), 어려움(8글자 이상))를 선택하면 난이도에 따라 

랜덤 영단어가 주어지며 그 영단어를 맞추어 가는 게임. 정답 혹은 목숨(초기 목숨 : 6) 소진 시 게임 종료

게임 종료 후 예를 입력 시 다시 난이도 선택으로 이동. 아니요 입력 시 게임을 완전히 종료.

## UML
![hangman UML](https://github.com/user-attachments/assets/15c0e528-6177-4334-856d-571fdd9b12cd)

## 구현 화면

+ 숫자 1-3 중 하나를 입력하여 난이도 선택
  
![image](https://github.com/user-attachments/assets/f49c8070-adf3-45ec-ba3d-e661a518384e)

---
+ 1-3을 벗어난 입력 시 재입력
  
![image](https://github.com/user-attachments/assets/c2fba7d7-8acb-47c3-b794-61a1c3ddde31)

![image](https://github.com/user-attachments/assets/86a2ade6-340f-479d-bac4-505872c826a0)

---
+ 알파벳 하나를 입력하여 단어 맞추기 (시작 목숨 6. 단어에 포함되지 않는 알파벳 사용 시 목숨 -1)
  
![image](https://github.com/user-attachments/assets/152adbff-83da-4616-b334-4af0efceed77)

---
+ 알파벳이 아닌 글자 입력 시 재입력
  
![image](https://github.com/user-attachments/assets/bc562425-a272-4d6a-a79b-5b38d4f85ae5)

---
+ 사용한 알파벳 재사용 시 재입력

![image](https://github.com/user-attachments/assets/708e6be3-adde-4940-82cb-45184ddb8a54)

---
+ 목숨 소진 시 게임 종료

![image](https://github.com/user-attachments/assets/2b7c8d7f-c40d-4843-b807-0a59ea7bd771)

---
+ 예 입력 시 게임 다시시작
  
![image](https://github.com/user-attachments/assets/e13ae1f7-65c8-41b7-b434-35c7ae75ca59)

---
+ 단어 정답 시 게임 종료

![image](https://github.com/user-attachments/assets/e5e7fb90-fc98-4cfa-8818-c21a12ff820b)

---
+ 아니오 입력 시 프로그램 종료

![image](https://github.com/user-attachments/assets/374295bd-f99e-4312-beca-a36fb505efbe)
