import java.io.IOException;
import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import edu.neu.coe.info6205.sort.counting.MSDStringSort;
import edu.neu.coe.info6205.sort.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sort.huskySort.utils.HuskyCoderFactory;


public class playground {

    public static void main(String[] args) {
        // 台 tái    \u0074\u00e1\u0069
        // 南 nán    \u006e\u00e1\u006e
        // 美 měi    \u006d\u011b\u0069
        // 食 shí    \u0073\u0068\u00ed
        // 最 zuì    \u007a\u0075\u00ec
        // 棒 bàng   \u0062\u00e0\u006e\u0067
        // 了 le     \u006c\u0065
        // 好 hǎo    \u0068\u01ce\u006f
        // 想 xiǎng  \u0078\u0069\u01ce\u006e\u0067
        // 逛 guàng  \u0067\u0075\u00e0\u006e\u0067
        // 水 shuǐ   \u0073\u0068\u0075\u01d0
        // 族 zú     \u007a\u00fa
        // 館 guǎn   \u0067\u0075\u01ce\u006e
        String[] originalString = {"台", "南", "美", "食", "最", "棒", "了", "好", "想", "逛", "水", "族", "館"};
        String[] MSDString = originalString.clone();
        MSDStringSort.sort(MSDString);
        System.out.println("MSD String sort result: ");
        for(String i: MSDString){
            System.out.print(i);
        }
        System.out.println("");

        String[] LSDString = originalString.clone();
        MSDStringSort.sort(LSDString);
        System.out.println("LSD String sort result: ");
        for(String i: LSDString){
            System.out.print(i);
        }
        System.out.println("");

        //String[] HuskyString = {"g", "a", "m", "e", "o", "f", "t", "h", "r", "o", "n", "e"};
        String[] HuskyString = originalString.clone();
        PureHuskySort huskySort = new PureHuskySort(HuskyCoderFactory.asciiCoder, false, false);
        huskySort.sort(HuskyString);
        System.out.println("HuskySort String sort result: ");
        for(String i: HuskyString){
            System.out.print(i);
        }
        System.out.println("");

        String words = String.join("", originalString);

        System.out.println("");

        String pinyin = Utils.wordToPinyin(words, true);
        System.out.println("Pinyin: " + pinyin);

        String unicode = Utils.pinyinToUnicode(pinyin);
        System.out.println("Unicode of Pinyin: " + unicode);

        String[] wordsArray = words.split("");
        Collator cmp = Collator.getInstance(Locale.CHINA);

        System.out.println("\nBefore sort: ");
        System.out.println("Words: " + Arrays.toString(wordsArray));
        System.out.println("CN Unicode: " + Utils.cnToUnicode(wordsArray));

        Arrays.sort(wordsArray, cmp);

        System.out.println("\nAfter sort: ");
        System.out.println("Words: " + Arrays.toString(wordsArray));
        System.out.println("CN Unicode: " + Utils.cnToUnicode(wordsArray));

        List<String> list = Utils.readFromFile("shuffledChinese.txt");
        String[] array = list.stream().toArray(String[]::new);
        huskySort.sort(array);
        Arrays.asList(array).stream().forEach(s -> System.out.println(s));

    }
}
