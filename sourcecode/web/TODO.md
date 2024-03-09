# TODOリスト

</br>

## 頑張っていきましょ〜☺️

- ~~calMineはopenCell、putNormalはremoveFlagなどなど変数をきちんと定義する~~

- ~~マス目を増やす5×5とか~~

- ~~地雷をランダムに設置~~

- ~~サービスにおける地雷の裏表示のところの省略(セッションから取ってくるのもあり)~~

  - ~~現在コントローラーに書いてます~~

- ~~1度目のクリックで地雷を配置~~

- ~~JavaScriptのファイル作成~~

- ~~Serviceの戻り値を一つにする/引数を必要なもののみにする~~

- ~~Serviceの「地雷と数字の画面表示/旗を立てる/旗をとる」の共通部分を関数化しまとめる~~

- ~~失敗判定(×を踏んだら)~~

- ~~クリア判定((全体数-×)の数分だけ空いたら)~~

- ~~裏画面の作成(数字を適切なものに)~~

  - ~~地雷の上下左右斜めに+1する~~
  - ~~枠外の配列要素を作る？~~

- ~~多過ぎず少な過ぎないように閾値決める(少なかったらもう一度決め直す)~~

- ~~クリア判定：外周をカウントされてしまっている~~

- ~~ボタンの場所~~

  - ~~65px × 65pxだと、6 × 6のサイズが限界。可変になるようにしたい~~

- ~~ランダムでpushedField[i][j] = "×"にする。~~

- ~~押した場所に応じた地雷の場所の作成。~~

- ~~最初にpushedになったときに、firstClickをtrueにする~~

- ~~クリアから、ゲームオーバーにしない~~

- ~~空白マスが空いた時、周りの8マスを開ける処理(pushedに)：再帰処理~~

  - ~~開いてあるマスを再び開ける時も考慮~~

- マインスイーパーがどういうアルゴリズムの生成なのか。空白マスが単品で存在するのを許すのか。研究。

  - 空白は単体でない。必ず3個以上、二次元にあること

- gitの使い方を学びましょう!(issue/branch/merge)

- デバックモードのmvn-springboot-runができるの？？確認

- 描画どうにかする

- ブランチを分ける

- 中身を見れる(開けない)

- ライフ生成

- ~~ステージ~~

- スコア/タイムとライフで生成

- おまけ!!：ピクセルアートを作ろう

- おまけ2!!：お絵かきロジック(色付き)
  - 任意の画像をドット絵にする

</br>

## 質問リスト

- session.fieldSizeでなぜ取れるのか？

- forUpdateMineDto.setIsGameFinished()を書こうとしたら、　→　結論：使用していない
  forUpdateMineDto.setGameFinished()に置換された。なぜIsがなくてsetされるのか？
  (もしかして、ランボックにsetterの命名させているせい？)

- isGameFinishedはDto？それともsession？　→　結論：Dtoで良いと判断
- そもそも...sessionからhtmlで直接、値をとれるのであれば、Dtoいらないのでは？
  - 毎回変化するもの(計算して返すもの)はDto？
    であれば、pushedFieldはDtoに含めなくて良いのでは → Dtoでなくて良い
  - 画面に必要な要素でなければ、sessionでいい？(isFirstClickCompletedなど)
    それとも、isFirstClickCompletedは1回しか使わないからDtoに詰めないのか？

</br>

## リンクリスト

MarkDownの書き方

> <https://qiita.com/tbpgr/items/989c6badefff69377da7>

Bootstrap(CSS使わずに書くやつ)

> <https://getbootstrap.com/docs/5.3/components/buttons/>

HTMX(部分的に更新できる)

> <https://qiita.com/twrcd1227/items/7bce18167fb02ec22729#イベントの指定>

開発ガイド

> <https://github.com/Fintan-contents/spring-sample-project/blob/main/開発ガイド/PGUT工程/pg/web/view-template.md>

Java SE Bronze

> <https://www.alloneslife-0to1work.jp/code/java/bronze-qa#no1>
