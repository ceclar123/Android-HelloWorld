package org.bond.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-6-24.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object pdu : pdus) {
            SmsMessage smsMessage = SmsMessage.createFromPdu(((byte[]) pdu));
            //发送人
            String sender = smsMessage.getOriginatingAddress();
            //短信内容
            String body = smsMessage.getMessageBody();

            Toast.makeText(context, sender + body, Toast.LENGTH_SHORT).show();

            if ("110".equals(sender)) {
                //拦截掉
                this.abortBroadcast();
            }
        }
    }
}
