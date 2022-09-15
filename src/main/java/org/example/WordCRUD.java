package org.example;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;
    final String fileName = "Dictionary.txt";
    /*
     * => 난이도(1,2,3)& 새 단어 입력:1 driveway
     * 뜻 입력: 차고 진입로
     * 새 단어가 단어장에 입력되었습니다.
     */

    WordCRUD(Scanner s){
        list= new ArrayList<>();
        this.s=s;
    }
    @Override
    public Object add() {
        System.out.print("=>난이도(1,2,3) & 새 단어 입력: ");
        int level = s.nextInt();
        String word=s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning =s.nextLine();

        return new Word(0,level, word, meaning);
    }
    public void addItem() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다. ");
    }

    @Override
    public int update(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void selectOne(int id) {
        // TODO Auto-generated method stub

    }

    public void listAll() {
        System.out.println("------------------------------");
        for(int i = 0;i<list.size();i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("------------------------------");
    }
    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist=new ArrayList<>();
        int j=0;
        System.out.println("------------------------------");
        for(int i = 0;i<list.size();i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("------------------------------");
        return idlist;
    }

    public void updateItem() {
        System.out.print("==> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist=this.listAll(keyword);
        System.out.print("==> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("==> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMenaing(meaning);
        System.out.println("단어가 수정되었습니다.");
    }

    public void deleteItem() {
        System.out.print("==> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist=this.listAll(keyword);
        System.out.print("==> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("정말로 삭제하시겠습니까?(Y/n) ");
        String answer = s.next();
        if(answer.equalsIgnoreCase("y")) {
            list.remove((int)idlist.get(id - 1));
            System.out.println("단어가 삭제되었습니다.");
        }else{
            System.out.println("취소되었습니다. ");
        }
    }
    public void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int count=0;
            while(true) {
                line = br.readLine();
                //System.out.println(line+count);
                if (line == null) {
                    System.out.print("break");
                    break;
                }else {
                    String data[] = line.split("\\|");
                    int level = Integer.parseInt(data[0]);
                    //System.out.println(level);
                    String word = data[1];
                    //System.out.println(data[1]);
                    String meaning = data[2];
                    //System.out.println(meaning);
                    list.add(new Word(0, level, word, meaning));
                    count++;
                }
            }
            br.close();
            System.out.println("==> "+count+ "개 단어 로딩 완료!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("test.txt"));
            for(Word one : list){
                pr.write(one.toFileString()+ "\n");
            }
            pr.close();
            System.out.println("===> 데이터 저장 완료!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

