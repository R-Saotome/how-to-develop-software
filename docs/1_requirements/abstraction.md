# 1. 要件定義

ソフトウェア開発で最初に行うことは要件定義です。要件定義はソフトウェア開発を行うにあたり「**何を**」する必要があるのかを明確にするプロセスです。要求定義ともいわれています。

## 目標

ここでのゴールはソフトウェアを開発するにあたり必要な情報を収集・分析し、それを文書化することです。特に私は**要求を必要十分な形でドキュメント化すること(Just Enough Documentation)** を目標としています。

## モデリングの利点と課題

要求をドキュメント化する方法はいくつかあります。全てを*文書*でまとめたり、スプレッドシートを使って一覧できるようにする方法もあります。もちろん全くまとめないという選択もあります。私はとりませんが・・・

いろいろ試した結果、私はモデリング言語を用いてモデルを作成するのが最適解だという結論に至りました。理由は**要求が簡潔に記載されていて全体が把握しやすいため**です。

結局ドキュメントが更新されないのは複雑でわかりづらく、更新するのに非常に手間がかかるからだと思われます。プロジェクトが大きくなればなるほど、複雑さはより大きくなるのは明らかです。**モデル化することで複雑さは大幅に改善され、概要の理解は簡単になるため管理がしやすくなります**。モデリングの最大の利点は相手に「示す」ことができるため、見せ方を工夫すれば関係者たちとの認識合わせが非常にスムーズに行えます。私の言う*Just Enough Documentation*の本質はモデリングにあります。

しかしモデリングにはいくつか課題もあります。

まず、**モデリングでは詳細を記載するには限界があります**。モデリングのみで全てを表現できるのが理想ですがなかなかそうはいきません。詳細は別のどこかにまとめて参照できるようにするのが望ましいといえます。

また、**モデルを読むのはそれほど難しくないですが、書く人は記法を理解しなければならないため学習コストがかかります**。書き方が俗人化してしまうと読みづらくなるのは全てのドキュメント化において言えることですが、モデリングを使った場合も例外ではありません。幸いモデリングの世界では記法についてそれぞれ意味や役割が定められているため、記法についておぼえられれば非常に保守はしやすいと思います。あとは書けるようになることと、チーム独自のルールがある場合はそれを明確にしておくことが必要です。

## 要求開発プロセス

ソフトウェアの仕様を固めていくにあたり特定のプロセスを反復的に行います。プロセスを反復することで要求がどんどん洗練され、開発レベルに落とし込みやすくします。

ざっくりですが私は 3 回くらい以下のプロセスを反復しています。

### 要求の収集

クライアントから要求を抽出するプロセスです。やり方はいろいろありますが、受託開発の場合は**ヒヤリング**が一般的でしょう。自社サービスを開発している場合は**ユーザーアンケート**、社内システムの開発なら**グループワークや実際の業務を観察する**といった方法が考えられます。

### 要求の分析・トリアージ

ヒヤリングの内容をもとに何を解決するべきか決め、それをシステムに落とし込んでいきます。このとき要求をすべて対応しようとすると要求が膨れ上がっていきます。そのため価値の高いものを優先的に対応してスコープを制限する、つまりトリアージをしています。

### 要求の文書化

抽出した要求を分析し、トリアージを行ったら要求を文書化します。ドキュメントはプロジェクト関係者があとで参照できるようにするために必要なものです。

私が作成しているものには以下のようなものがあります。

#### 1. [要求モデル](./1-1_business-analysis.md)

要求をすべてモデルにして関連付けています。全ての要求は「ビジネス要求」「ユーザー要求」「機能要求」「非機能要求」に分類され、各要求の派生元がたどれるようになっています。

#### 2. [ドメインモデル](./1-2_domain-modeling.md)

クライアントが使う言葉でやりとりができるように用語とその関係性をモデルにしています。これが設計時に活用され、クラス図や ER 図が出来上がります。

#### 3 その他

本資料では取り上げませんが私は必要に応じて以下の資料を作成することがあります。

- コンテキスト図

システム全体の構成が把握できる図です。外部システムとの連携があるようなプロダクトではそれぞれの関係や役割を示すために用意します。

- 業務フロー図

一言でユーザーといっても役割や権限の異なるユーザーが複数存在することがあります。これをユーザークラスと呼ぶのですが、ユーザークラスが多い際は業務の連携を整理するために作成します。

### 参考書籍
