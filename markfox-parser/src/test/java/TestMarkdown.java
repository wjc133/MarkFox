/**
 * Created by lenovo on 2016/7/17.
 */
public class TestMarkdown {
    public static void main(String[] args){
        String str =new String();
        str="> * aaaaa";
        MarkdownProcessor str2 = new  MarkdownProcessor();
        String s=str2.markdown(str);
        System.out.println(s);
    }
}
