package com.hxc.springbootweek08.sms;

public final class SmsRedisKey {

    private SmsRedisKey() {
    }

    public static final String SMS_CODE_PREFIX = "sms:code:";

    public static final String SMS_COOLDOWN_PREFIX = "sms:cooldown:";

    public static final String SMS_DAILY_COUNT_PREFIX = "sms:count:";
}
