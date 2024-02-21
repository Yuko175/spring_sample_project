package com.example.web.sample.service;

import com.example.web.sample.dto.CountDto;
import org.springframework.stereotype.Service;

//TODO：Javadoc
//TODO:文字数制限記入し、+-の文字数を表示

@Service
public class CountService {
  public CountDto calcCount(String text) {

    //行数を数える
    int lineNum = calcLineNum(text);

    //スペースあり
    int spaceCharCount = calcSpaceCharCount(text, lineNum);
    //スペースなし
    int noSpaceCharCount = calcNoSpaceCharCount(text, spaceCharCount);
    //行数
    int lineCount = calcLineCount(lineNum);
    //段落数
    int paragraphCount = calcParagraphCount(text);
    //ページ
    int pageCount = calcPageCount(text);
    return new CountDto(spaceCharCount, noSpaceCharCount, lineCount, paragraphCount, pageCount);

  }

  /**
   * 行数を数える。
   * @param text 入力テキスト
   * @return 行数
   */
  private int calcLineNum(String text) {
    int lineNum = 0;//改行定義

    for (int i = 0; i < text.length(); i++) {//1ずつ回す
      String str = String.valueOf(text.charAt(i));//1文字ずつ取り出す
      if (str.equals("\n"))//改行の数
        lineNum++;
    }
    return lineNum;
  }

  /**
   * スペースあり
   * @param text 入力テキスト
   * @param lineNum 行数
   * @return スペースあり文字数
   */
  private int calcSpaceCharCount(String text,int lineNum) {

    int charCount = 0;//スペースありの文字数定義
    int commentCount = 0;
    boolean isInComment = false; // コメント内にいるかどうか
    for (int i = 0; i < text.length(); i++) {//1ずつ回す
      char currentChar = text.charAt(i);//1文字ずつ取り出す
    
      // コメント内の場合はスキップ
      if (isInComment) {
        if (currentChar == '\n') { // コメント終了
          isInComment = false;
          commentCount++;
        }
        continue;
      }

      // コメント行の開始をチェック
      if (i < text.length() - 1) {
        char nextChar = text.charAt(i + 1);//1文字ずつ取り出す
        if (currentChar == '/' && nextChar == '/') {
          isInComment = true;
          continue; // コメント行の文字はカウントしないので、次の文字へ
        }
      }
      charCount++; // コメント外の文字数をカウントする
    }
    int spaceCharCount = charCount - ((lineNum - commentCount) * 2);//改行コードを数えない
    return spaceCharCount;
  }

  //スペースなし
  private int calcNoSpaceCharCount(String text, int spaceCharCount) {
    int spaceNum = 0;//スペース定義
    for (int i = 0; i < text.length(); i++) {//1ずつ回す
      char currentChar = text.charAt(i);//1文字ずつ取り出す
      if (currentChar == ' ' || currentChar == '　')//空白の数
        spaceNum++;
    }
    int noSpaceCharCount = spaceCharCount - spaceNum;
    return noSpaceCharCount;
  }

  //行数
  private int calcLineCount(int lineNum) {
    return lineNum + 1;//最初の１行を足す
  }
  
  //段落
  private int calcParagraphCount(String text) {
    String[] lines = text.split("\n"); //分割したのを配列に入れて要素数を取ってくる
    int paragraphCount = 1;
    for (String line : lines) {//lineの中を1つずつ見る
      if (line.trim().isEmpty()) {//lineの中の何もない配列を数えている
        paragraphCount++;
      }
    }
    return paragraphCount;
  }
  
  //ページ
  private int calcPageCount(String text) {
    //TODO:文字数制限記入し、+-の文字数を表示
    int pageCount = 0;
    return pageCount;
  }
  
}
