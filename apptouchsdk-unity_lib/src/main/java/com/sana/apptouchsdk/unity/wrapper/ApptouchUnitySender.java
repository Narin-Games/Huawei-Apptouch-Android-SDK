package com.sana.apptouchsdk.unity.wrapper;

import com.google.gson.Gson;
import com.sdk.commplatform.entry.SkuDetail;
import com.unity3d.player.UnityPlayer;

import java.util.List;

public class ApptouchUnitySender {
    private static final String UNITY_MANAGER_OBJECT = "Sana.Apptouch.ApptouchEventManager";

    private static void unitySendMessage(String methodName, String parameter) {
        UnityPlayer.UnitySendMessage(UNITY_MANAGER_OBJECT, methodName, parameter);
    }

    public static void initCallback(int resultCode) {
        unitySendMessage("InitCallback", Integer.toString(resultCode));
    }

    public static void getSKUCallback(List<SkuDetail> skuDetails) {
        Gson gson = new Gson();
        unitySendMessage(
                "GetSKUCallback"
                ,(skuDetails == null)? null : gson.toJson(skuDetails)
        );
    }

    public static void purchaseProductCallback(int resultCode) {
        unitySendMessage("PurchaseProductCallback", Integer.toString(resultCode));
    }
}