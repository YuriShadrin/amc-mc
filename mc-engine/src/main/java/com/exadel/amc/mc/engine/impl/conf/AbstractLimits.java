package com.exadel.amc.mc.engine.impl.conf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exadel.amc.mc.engine.conf.Limits;

public abstract class AbstractLimits implements Limits {

    private Pattern pattern = Pattern.compile("(\\d+?)([dhms]){1}");
    
    @Override
    public long getTimeFrameMillis() {
        long tfs = 0L;
        String atf[] = getTimeFrame().toLowerCase().split(" ");
        for (String tf : atf) {
            Matcher matcher = pattern.matcher(tf);
            if (matcher.matches()) {
                int n = Integer.parseInt(matcher.group(1));
                switch(matcher.group(2).charAt(0)) {
                case 'd':
                    tfs += n * 24 * 60 * 60;
                    break;
                case 'h':
                    tfs += n * 60 * 60;
                    break;
                case 'm':
                    tfs += n * 60;
                    break;
                case 's':
                    tfs += n;
                    break;
                }
            }
        }
        return tfs * 1000;
    }
}
