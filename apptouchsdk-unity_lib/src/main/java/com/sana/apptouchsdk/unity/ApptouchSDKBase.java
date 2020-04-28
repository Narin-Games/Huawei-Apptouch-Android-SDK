package com.sana.apptouchsdk.unity;


import android.app.Activity;

import com.sana.apptouchsdk.unity.wrapper.ApptouchUnitySender;
import com.unity3d.player.UnityPlayer;

public class ApptouchSDKBase {
    protected static Activity getCurrentActivity() {
        return UnityPlayer.currentActivity;
    }
}
