package org.example;

import java.util.Scanner;

public class WordManager {
    Scanner s= new Scanner(System.in);
    WordCRUD wordCRUD;
    /*
     *** 영단어 마스터 ***
     ******************
     *1. 모든 단어 보기
     *2. 수준별 단어 보기
     *3. 단어 검색
     *4. 단어 추가
     *5. 단어 수정
     *6. 단어 삭제
     *7. 파일 저장
     *0. 나가기
     ******************
     *=> 원하는 메뉴는? 4   이런식으로 나오게
     */

    WordManager(){
        wordCRUD = new WordCRUD(s);
    }
    public int selectMenu() {
        System.out.print(
                "*** 영단어 마스터 ***\n"
                        + "******************\n"
                        + "1. 모든 단어 보기\n"
                        + "2. 수준별 단어 보기\n"
                        + "3. 단어 검색\n"
                        + "4. 단어 추가\n"
                        + "5. 단어 수정\n"
                        + "6. 단어 삭제\n"
                        + "7. 파일 저장\n"
                        + "0. 나가기\n"
                        + "******************\n"
                        + "=> 원하는 메뉴는? "
        );
        return s.nextInt();
    }
    public void start() {
        System.out.println("WordManager!!!");
        while(true) {
            int menu = selectMenu();
            if(menu==0) {
                System.out.print("장비를 정지합니다");
                break;
            }
            if(menu==1) {
                wordCRUD.listAll();
            }else if(menu==2){

            }else if(menu==3) {

            }else if(menu==4) {
                wordCRUD.addWord();
                //create
            }else if(menu==5){
                //update
                wordCRUD.updateItem();
            }else if(menu==6){
                //delete

            }else if(menu==7){

            }else{
                System.out.println("다시 입력하세요.");
            }
        }
    }
}