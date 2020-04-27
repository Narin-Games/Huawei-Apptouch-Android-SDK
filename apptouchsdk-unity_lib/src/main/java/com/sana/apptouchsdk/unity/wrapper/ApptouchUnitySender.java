package com.sana.apptouchsdk.unity.wrapper;

import com.unity3d.player.UnityPlayer;

public class ApptouchUnitySender {
    private static final String UNITY_MANAGER_OBJECT = "ApptouchSDK.ApptouchEventManager";

    private static void unitySendMessage(String methodName, String parameter) {
        UnityPlayer.UnitySendMessage(UNITY_MANAGER_OBJECT, methodName, parameter);
    }

    public static void initCallback(int errorCode) {
        unitySendMessage("InitCallback", Integer.toString(errorCode));
    }
}