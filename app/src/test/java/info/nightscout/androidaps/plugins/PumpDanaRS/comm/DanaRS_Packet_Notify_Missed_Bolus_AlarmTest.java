package info.nightscout.androidaps.plugins.PumpDanaRS.comm;


import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;

import info.AAPSMocker;
import info.SPMocker;
import info.nightscout.androidaps.MainApp;
import info.nightscout.androidaps.db.DatabaseHelper;
import info.nightscout.androidaps.logging.L;
import info.nightscout.androidaps.plugins.ConfigBuilder.ConfigBuilderPlugin;
import info.nightscout.androidaps.plugins.NSClientInternal.NSUpload;
import info.nightscout.androidaps.plugins.PumpDanaR.DanaRPump;
import info.nightscout.androidaps.plugins.Treatments.TreatmentService;
import info.nightscout.androidaps.plugins.Treatments.TreatmentsPlugin;
import info.nightscout.utils.SP;
import info.nightscout.utils.ToastUtils;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rumen Georgiev on 8/9/2018.
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest({MainApp.class, SP.class, L.class})
public class DanaRS_Packet_Notify_Missed_Bolus_AlarmTest {

    @Test
    public void runTest() {
        AAPSMocker.mockMainApp();
        AAPSMocker.mockApplicationContext();
        AAPSMocker.mockSP();
        AAPSMocker.mockL();
        DanaRS_Packet_Notify_Missed_Bolus_Alarm packet = new DanaRS_Packet_Notify_Missed_Bolus_Alarm();

        // test params
        assertEquals(null, packet.getRequestParams());

        // test message decoding
        packet.handleMessage(createArray(6, (byte) 0));
        assertEquals(false, packet.failed);
        // everything ok :)
        packet.handleMessage(createArray(6, (byte) 1));
        assertEquals(true, packet.failed);

        assertEquals("NOTIFY__MISSED_BOLUS_ALARM", packet.getFriendlyName());
    }

    byte[] createArray(int length, byte fillWith){
        byte[] ret = new byte[length];
        for(int i = 0; i<length; i++){
            ret[i] = fillWith;
        }
        return ret;
    }

    double[] createArray(int length, double fillWith){
        double[] ret = new double[length];
        for(int i = 0; i<length; i++){
            ret[i] = fillWith;
        }
        return ret;
    }
}