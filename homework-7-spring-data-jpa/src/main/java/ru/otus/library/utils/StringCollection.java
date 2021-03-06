package ru.otus.library.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class StringCollection {

    public static List<Long> splitLong(String string) {
        List<Long> longList = new ArrayList<>();
        String value = StringUtils.strip(string, "\t\n ");
        if (StringUtils.isEmpty(value)) {
            return longList;
        }
        for (String s : value.split(",")) {
            String stringLong = StringUtils.strip(s, "\t\n ");
            if (StringUtils.isEmpty(stringLong)) {
                continue;
            }
            long longValue;
            try {
                longValue = Long.parseLong(s);
            } catch (NumberFormatException e) {
                continue;
            }
            longList.add(longValue);
        }

        return longList;
    }
}
