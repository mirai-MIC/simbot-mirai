package love.simbot.example.Utils_;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CQID {
    public long foo(String str) {
        Pattern pattern = Pattern.compile("([0-9]+)");
        Matcher matcher = pattern.matcher(str);
        long qID = 0;
        while (matcher.find()) {
            qID = Long.parseLong(matcher.group(0));
        }
        return qID;
    }
}
//解析类