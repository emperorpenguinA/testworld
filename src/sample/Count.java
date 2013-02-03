package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Count {

    private static final String Q1 = "1";
    private static final String Q2 = "2";
    private static final String Q3 = "3";

    private static final String[] first  = {"soccer", "baseball", "jogging", "jogging", "soccer", "surfing", "surfing", "soccer", "surfing", "baseball"};
    private static final String[] second = {"boxing", "surfing", "soccer", "surfing", "jogging", "boxing", "jogging", "baseball", "soccer", "soccer"};

    // countMap に関してはこの問題で一番重要なデータ構造になるので
    // どういったデータを保持するマップなのかのコメントを入れといた方がベターかも
    // あるいは favoriteSportsCountMap とかいう名前にしちゃう、とか。
    public static Map<String, Integer> countMap = new HashMap<String, Integer>();

    public static String numberOneSports = "";
    public static Integer maxCount = 0;


    /**
     * args[0] : 1-第1問2重ループ、1-第2問、3-第3問ランキング
     * 問1
     *   アンケートの結果、1番人気のあったスポーツをあげてください。その際、何票獲得したかも合わせてあげてください。
     *   また、集計の際はfor文の二重ループを使用してください。
     * 問2
     *   問1をfor文のループ一回だけで行ってください。
     * 問3
     *   アンケート結果を降順で最後まで表示してください。表示内容は下記を参考にしてください。
     *      好きなスポーツランキング
     *      一位 ■■■ ○○票
     *      二位 ▽▽▽ ○○票
     *      三位 ××× ○○票
     * @param args
     */
    public static void main(String[] args) {

        //票をcountして結果を取得する
        count(args[0]);
        //結果を表示する
        showCount(args[0]);

        /**
         * 問１なのか２、３なのかを引数で渡せるようにしたのは面白いですね
         * また処理を
         * 　集計をする部分
         * 　表示する部分
         * にわけた点もいいと思います
         * 集計処理に関しては、２と３が
         * 出力処理に関しては、１と２が共通化できますからね。
         */

    }

    /**
     * それぞれのスポーツが何票獲得したかマップにセットする
     * 1位を表示する場合は1位の情報もセットする
     * どのルートに入るかで分岐
     * 引数が無いときはエラーを表示
     * @param select 何問目であるかのフラグ
     */
    private static void count(final String select){

        if(Q1.equals(select)){

    	/**
    	 * 出題者の意図としては問１はあえてマップを使わないという問題（全探索）
    	 * 全探索は、要素の数が増えると計算量が爆発的に増えるので
    	 * 気をつけましょう、、ということがいいたい
    	 *
    	 * 僕も思いつきませんでしたが
    	 * for(int j=0; j<second.length; j++){
    	 * のループの中で
    	 * firstの他の要素も見ていくのがミソっぽい
    	 */
        /*
       	 for (int i=0; i<first.length; i++) {
     	    String targetSports = first[i];
     	    int count = 1;
     	    for (int j=0; j<second.length; j++) {
	     	    String firstOtherSports = "";
	     	    String secondOtherSports = second[j];
	     	 	if (i!=j) {
	     	 		firstOtherSports = first[j];
	     		}
	     	 	if (targetSports.equals(firstOtherSports)) {
	     	 		count++;
	     	 	}
	     	    if (targetSports.equals(secondOtherSports)) {
	     	    	count++;
	     	    }
     	    }
     	    if (count > maxCount) {
     	    	maxCount = count;
     	    	numberOneSports = targetSports;
     	    }
       	 }
       	 */
       	 /**
       	  * 上記だけだと、secoundにしかないスポーツが考慮できてないので
       	  * さらにsecondを起点にして同じ事をやる必要がある
       	  * 上の２重ループのなかで一緒にやれなくもないかもしれないけど
       	  * 処理がやや複雑になるかと、、
       	  */

            //配列をまわしてマップを作成する
            for(int i=0; i<first.length; i++){
                String firstKey = first[i];
                String secondKey = "";
                Integer value = 1;
                boolean firstFlg = true;
                //すでにあるときは++
                if(countMap.containsKey(firstKey)){
                    value = countMap.get(firstKey);
                    value++;
                    countMap.put(firstKey, value);
                    firstFlg = false;

                }else{
                    countMap.put(firstKey, 1);
                }

                if(firstFlg){
                    for(int j=0; j<second.length; j++){
                        secondKey = second[j];
                        if(secondKey.equals(firstKey)){
                            if(countMap.containsKey(firstKey)){
                                value = countMap.get(firstKey);
                                value++;
                                countMap.put(secondKey, value);
                            }
                        }
                    }
                }

                //その時点の最大票なら値とスポーツ名をセット
                if(value > maxCount){
                    maxCount = value;
                    numberOneSports = firstKey;
                }
            }

        }else if(Q2.equals(select) || Q3.equals(select)){

            //配列を結合する
            String[] all = new String[first.length + second.length];
            System.arraycopy(first, 0, all, 0, first.length);
            System.arraycopy(second, 0, all, first.length, second.length);

            //配列をまわしてマップを作成する
            for(int i=0; i<all.length; i++){
                String key = all[i];
                // keySports とか targetSports とかのほうがいいかな
                Integer value = 1;

                if(countMap.containsKey(key)){
                    value = countMap.get(key);
                    value++;
                    countMap.put(key, value);
                }else{
                    countMap.put(key, 1);
                    // 折角　Integer value = 1;してるので
                    // countMap.put(key, value); の方がいいかな
                }

                if(value > maxCount){
                    maxCount = value;
                    numberOneSports = key;
                }
            }

        }else{
            System.out.println("問題を選んでください。");
        }
    }

    /**
     * 結果を表示する
     * どのルートに入るかで分岐
     * 引数が無いときはエラーを表示
     * @param select 何問目であるかのフラグ
     */
    private static void showCount(final String select){
        if(Q1.equals(select) || Q2.equals(select)){
            System.out.println("1番は" + numberOneSports  + "：" + maxCount + "票");
        }else if(Q3.equals(select)){
            ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String,Integer>>(countMap.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<String,Integer>>() {

                @Override
                public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                    Map.Entry<String,Integer> e1 = o1;
                    Map.Entry<String,Integer> e2 = o2;
                    Integer v1 = o1.getValue();
                    Integer v2 = o2.getValue();

                    return v2.compareTo(v1);
                }
            });

            Integer ranking = 1;
            for(Map.Entry<String,Integer> entry : entries){
                System.out.println(ranking + "位" + entry.getKey() + " " + entry.getValue() + "票");
                ranking++;
            }
        }
    }



}
