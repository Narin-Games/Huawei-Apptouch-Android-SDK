package com.sana.apptouchsdk.unity;

import com.sana.apptouchsdk.unity.wrapper.ApptouchUnitySender;
import com.sdk.commplatform.CallbackListener;
import com.sdk.commplatform.Commplatform;
import com.sdk.commplatform.ErrorCode;
import com.sdk.commplatform.entry.AppInfo;
import com.sdk.commplatform.entry.PayResult;
import com.sdk.commplatform.entry.Payment;
import com.sdk.commplatform.entry.SkuDetail;

import java.util.List;
import java.util.UUID;

public class ApptouchSDK extends ApptouchSDKBase {

    public static void initSDK(String appId, String appKey) {
        Commplatform.getInstance().onCreate(getCurrentActivity().getApplication().getBaseContext(), null);

        AppInfo appInfo = new AppInfo();
        appInfo.setAppId(appId);
        appInfo.setAppKey(appKey);
        appInfo.setCtx(getCurrentActivity());

        Commplatform.getInstance().Init(0, appInfo, new CallbackListener<Integer>() {
            @Override
            public void callback(int paramInt, Integer paramT) {
                if (paramInt == com.sdk.commplatform.ErrorCode.COM_PLATFORM_SUCCESS) {
                    ApptouchSDKLogger.logDebug("SDK Initialized Successfully");
                    ApptouchUnitySender.initCallback(ResultCode.SUCCESS);

                } else if (paramInt == com.sdk.commplatform.ErrorCode.COM_PLATFORM_ERROR_FORCE_CLOSE) {
                    //init failure
                    ApptouchSDKLogger.logDebug("SDK Failed Initialized");
                    ApptouchUnitySender.initCallback(ResultCode.FAILED_FORCE_CLOSE);

                } else if (paramInt == ErrorCode.COM_PLATFORM_ERROR_ONLINE_CHECK_FAILURE) {
                    //network not connect
                    ApptouchSDKLogger.logDebug("You're not connected!");
                    ApptouchUnitySender.initCallback(ResultCode.NOT_CONNECTED);
                }
            }
        });
    }

    public static void getSKUDetails() {
        Commplatform.getInstance().getSkuDetails(getCurrentActivity(), new CallbackListener<List<SkuDetail>>() {
            @Override
            public void callback(int errorCode, List<SkuDetail> skuDetails) {
                if (errorCode == ErrorCode.COM_PLATFORM_SUCCESS && skuDetails != null) {
                    ApptouchSDKLogger.logDebug("Get SKU Successfully: " + skuDetails.get(0).productId);
                    ApptouchUnitySender.getSKUCallback(skuDetails);
                }
                else {
                    ApptouchSDKLogger.logDebug("Get SKU Failed");
                    ApptouchUnitySender.getSKUCallback(null);
                }
            }
        });
    }

    public static void purchaseProduct(SkuDetail sku) {
        //Synchronous payment.
        Payment payment = new Payment();
        String uuid = UUID.randomUUID().toString();

        try {
            payment.setTradeNo(uuid); // UUID
            payment.setProductId(sku.productId);
            payment.setSubject(sku.title); // Product title
            payment.setAmount(sku.price_amount);
            payment.setNote("This is for test purchasing"); // Generate by developer
            payment.setCurrency(sku.price_currency_code);
            payment.setDesc(sku.description); // Description
            payment.setNotifyURL(""); // Reception - Optional
            payment.setThirdAppId(""); // Third-party feature - Optional
            payment.setThirdAppName(""); // Third-party feature - Optional
            payment.setThirdAppPkgname(""); // Third-party feature - Optional
        } catch (Exception e) {
            payment = null;
        }
        if (payment == null) {}

        final String tradeNo = payment.getTradeNo();
        final String productId = payment.getProductId();

        int aError = Commplatform.getInstance().UniPayExt(payment, getCurrentActivity(), new CallbackListener<PayResult>() {
            @Override
            public void callback(int paramInt, PayResult payResult) {
                if (paramInt == ErrorCode.COM_PLATFORM_SUCCESS) {
                    ApptouchSDKLogger.logDebug("Purchase Success");
                    ApptouchUnitySender.purchaseProductCallback(ResultCode.PAY_SUCCESS);
                }
                else if(paramInt == ErrorCode.COM_PLATFORM_ERROR_PAY_FAILURE) {
                    ApptouchSDKLogger.logDebug("Purchase Failure");
                    ApptouchUnitySender.purchaseProductCallback(ResultCode.PAY_FAILURE);
                }
                else if(paramInt == ErrorCode.COM_PLATFORM_ERROR_PAY_CANCEL) {
                    ApptouchSDKLogger.logDebug("Purchase Cancel");
                    ApptouchUnitySender.purchaseProductCallback(ResultCode.PAY_CANCEL);
                }
                else {
                    ApptouchSDKLogger.logDebug("Purchase Unknown Error");
                    ApptouchUnitySender.purchaseProductCallback(ResultCode.PAY_UNKNOWN_ERROR);
                }
            }
        });
        if(aError == 0) { }
    }
}
