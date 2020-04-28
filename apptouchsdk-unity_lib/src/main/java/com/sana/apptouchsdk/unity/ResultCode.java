package com.sana.apptouchsdk.unity;

public final class ResultCode {

    // general result code 0 - 9
    public static final int SUCCESS             = 0;
    public static final int NOT_CONNECTED       = 1;
    public static final int FAILED              = 2;
    public static final int FAILED_FORCE_CLOSE  = 3;

    // payment result code 10 - 19
    public static final int PAY_SUCCESS         = 10;
    public static final int PAY_FAILURE         = 11;
    public static final int PAY_CANCEL          = 12;
    public static final int PAY_UNKNOWN_ERROR   = 13;
}
