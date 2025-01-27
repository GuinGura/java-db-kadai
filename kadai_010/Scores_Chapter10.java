package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import  java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class Scores_Chapter10 {
    public static void main(String[] args) {

        Connection con;
        Statement updateStatement;
        Statement orderByStatement;

        try {
            //データベースに接続する
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/challenge_java",
                    "root",
                    "Yuma_2814"
            );

            System.out.println("データベース接続成功：" + con);

            //SQLクエリを準備する
            updateStatement = con.createStatement();
            String updateSql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";

            //データを更新する
            System.out.println("レコード更新を実行します");
            int rowCnt = updateStatement.executeUpdate(updateSql);
            System.out.println(rowCnt + "件のレコードが更新されました");

            //点数順に並べる
            System.out.println("数学・英語の点数が高い順に並び替えました");
            orderByStatement = con.createStatement();
            String orderBySql = "SELECT id, name, score_math, score_english FROM scores ORDER BY score_math DESC, score_english DESC;";

            ResultSet result = orderByStatement.executeQuery(orderBySql);

            //並び替えの結果を表示する
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int scoreMath = result.getInt("score_math");
                int scoreEnglish = result.getInt("score_english");
                System.out.println(result.getRow() + "件目：生徒ID=" + id
                                    + "／氏名=" + name + "／数学=" + scoreMath + "／英語" + scoreEnglish);
            }

        } catch (SQLException e) {
            System.out.println("入力が正しくありません");
        }
        }
    }
