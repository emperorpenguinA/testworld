package count;

import java.util.HashMap;
import java.util.Map;

public class Count {

    private static final String Q1 = "1";
    private static final String Q2 = "2";
    private static final String Q3 = "3";

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
        String[] first  = {"soccer", "baseball", "jogging", "jogging", "soccer", "surfing", "surfing", "soccer", "surfing", "baseball"};
        String[] second = {"boxing", "surfing", "soccer", "surfing", "jogging", "boxing", "jogging", "baseball", "soccer", "soccer"};

        Map<String, Integer> countMap = new HashMap<String, Integer>();

        /*
         * どのルートに入るか分岐
         * 引数が無いときはエラーを表示
         */
        if(Q1.equals(args[0])){
            String numberOneSports = "";
            Integer maxCount = 0;

            for(int i=0; i<first.length; i++){
                String firstKey = first[i];
                String secondKey = "";
                Integer value = 1;
                //すでにあるときは++
                if(countMap.containsKey(firstKey)){
                    value = countMap.get(firstKey);
                    value++;
                    countMap.put(firstKey, value);

                }else{
                    countMap.put(firstKey, 1);
                }

                if(value > maxCount){
                    maxCount = value;
                    numberOneSports = firstKey;
                }

                for(int j=0; j<second.length; j++){

                    if(countMap.containsKey(secondKey)){
                        value = countMap.get(secondKey);
                        value++;
                        countMap.put(secondKey, value);
                    }else{
                        countMap.put(secondKey, 1);
                    }

                    if(value > maxCount){
                        maxCount = value;
                        numberOneSports = secondKey;
                    }
                }
            }

            System.out.println("1番は" + numberOneSports  + "：" + maxCount + "票");

        }else if(Q2.equals(args[0])){
            String numberOneSports = "";
            Integer maxCount = 0;

            String[] all = new String[first.length + second.length];
            System.arraycopy(first, 0, all, 0, first.length);
            System.arraycopy(second, 0, all, all.length, second.length);

            for(int i=0; i<all.length; i++){
                String key = all[i];
                Integer value = 1;

                if(countMap.containsKey(key)){
                    value = countMap.get(key);
                    value++;
                    countMap.put(key, value);
                }else{
                    countMap.put(key, 1);
                }

                if(value > maxCount){
                    maxCount = value;
                    numberOneSports = key;
                }
            }

            System.out.println("1番は" + numberOneSports  + "：" + maxCount + "票");


        }else if(Q3.equals(args[0])){

        }else{
            System.out.println("問題を選んでください。");
        }


    }

}
