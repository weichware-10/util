package github.weichware10.util.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import github.weichware10.util.ToolType;
import javafx.geometry.Rectangle2D;
import org.junit.Test;

/**
 * Testet {@link TrailData}.
 */
public class TrialDataTest {
    /**
     * Testet, ob ToolType richtig gesetzt wurde.
     */
    @Test
    public void toolTypeShouldBeSet() {
        TrialData zoomdata = new TrialData(ToolType.ZOOMMAPS, "1", "1");
        assertEquals("zommdata sollte vom Typ ZOOMMAPS sein", ToolType.ZOOMMAPS, zoomdata.toolType);

        TrialData eyetrackingdata = new TrialData(ToolType.EYETRACKING, "1", "1");
        assertEquals("zommdata sollte vom Typ EYETRACKING sein",
                ToolType.EYETRACKING, eyetrackingdata.toolType);

        TrialData codechartsdata = new TrialData(ToolType.CODECHARTS, "1", "1");
        assertEquals("zommdata sollte vom Typ CODECHARTS sein",
                ToolType.CODECHARTS, codechartsdata.toolType);
    }

    /**
     * Testet ob ConfigID richtig gesetzt wurde.
     */
    @Test
    public void configIdShouldBeCorrect() {
        String id1 = String.valueOf((int) Math.random() * 1000);
        TrialData data1 = new TrialData(ToolType.ZOOMMAPS, "1", id1);
        assertEquals(("configId von data1 sollte " + id1 + " sein"), id1, data1.configId);

        String id2 = String.valueOf((int) Math.random() * 1000);
        TrialData data2 = new TrialData(ToolType.EYETRACKING, "1", id2);
        assertEquals(("configId von data2 sollte " + id2 + " sein"), id2, data2.configId);

        String id3 = String.valueOf((int) Math.random() * 1000);
        TrialData data3 = new TrialData(ToolType.CODECHARTS, "1", id3);
        assertEquals(("configId von data3 sollte " + id3 + " sein"), id3, data3.configId);
    }

    /**
     * Testet ob TrialID richtig gesetzt wurde.
     */
    @Test
    public void trialIdShouldBeCorrect() {
        String id1 = String.valueOf((int) Math.random() * 1000);
        TrialData data1 = new TrialData(ToolType.ZOOMMAPS, id1, "1");
        assertEquals(("trialId von data1 sollte " + id1 + " sein"), id1, data1.trialId);

        String id2 = String.valueOf((int) Math.random() * 1000);
        TrialData data2 = new TrialData(ToolType.EYETRACKING, id2, "1");
        assertEquals(("trialId von data2 sollte " + id2 + " sein"), id2, data2.trialId);

        String id3 = String.valueOf((int) Math.random() * 1000);
        TrialData data3 = new TrialData(ToolType.CODECHARTS, id2, "1");
        assertEquals(("trialId von data3 sollte " + id3 + " sein"), id3, data3.trialId);
    }

    /**
     * Testet ob answer richtig gesetzt wurde.
     */
    @Test
    public void answerShouldBeCorrect() {
        TrialData data1 = new TrialData(ToolType.ZOOMMAPS, "1", "1");
        data1.setAnswer("nichts");
        assertEquals(("answer von data1 sollte 'nichts' sein"), "nichts", data1.getAnswer());
    }

    /**
     * Testet ob toString stringy Strings zurückgibt.
     */
    @Test
    public void stringShouldStringStringy() {
        TrialData data1 = new TrialData(ToolType.CODECHARTS, "1", "2");
        assertEquals(String.format("""
                TrialData: {
                    toolType: CODECHARTS
                    trialId: 1
                    configId: 2
                    startTime: %s
                    answer: null
                    dataPoints: dataPoints[0]
                }""", data1.startTime.toString()),
                data1.toString());
        data1.addDataPoint(new Rectangle2D(1, 2, 3, 4), 12);
        assertEquals(String.format("""
                TrialData: {
                    toolType: CODECHARTS
                    trialId: 1
                    configId: 2
                    startTime: %s
                    answer: null
                    dataPoints: dataPoints[1]
                }""", data1.startTime.toString()),
                data1.toString());

        TrialData data2 = new TrialData(ToolType.ZOOMMAPS, "1", "2");
        assertEquals(String.format("""
                TrialData: {
                    toolType: ZOOMMAPS
                    trialId: 1
                    configId: 2
                    startTime: %s
                    answer: null
                    dataPoints: dataPoints[0]
                }""", data2.startTime.toString()),
                data2.toString());
        data2.addDataPoint(new Rectangle2D(1, 2, 3, 4));
        assertEquals(String.format("""
                TrialData: {
                    toolType: ZOOMMAPS
                    trialId: 1
                    configId: 2
                    startTime: %s
                    answer: null
                    dataPoints: dataPoints[1]
                }""", data2.startTime.toString()),
                data2.toString());
    }

    /**
     * Testet, ob bei dem Versuch die Daten für den falschen ToolType
     * hinzuzufügen ein Error geworfen wird.
     */
    @Test
    public void wrongToolTypeShouldThrow() {
        TrialData data1 = new TrialData(ToolType.ZOOMMAPS, "1", "2");
        assertThrows(IllegalArgumentException.class, () -> {
            data1.addDataPoint(new Rectangle2D(1, 2, 3, 4), 12);
        });

        TrialData data2 = new TrialData(ToolType.CODECHARTS, "1", "2");
        assertThrows(IllegalArgumentException.class, () -> {
            data2.addDataPoint(new Rectangle2D(1, 2, 3, 4));
        });
    }

    @Test
    public void shouldGetDataCorrectly() {
        TrialData data1 = new TrialData(ToolType.ZOOMMAPS, "1", "2");
        data1.addDataPoint(new Rectangle2D(1, 2, 3, 4));
        DataPoint dataPoint1 = data1.getData().get(0);
        assertTrue(data1.getData().get(0).equals(dataPoint1));
    }

    @Test
    public void toJsonShouldWork() {
        TrialData data1 = new TrialData(ToolType.ZOOMMAPS, "trialId", "configId");
        data1.setAnswer("answer");
        data1.addDataPoint(new Rectangle2D(1, 2, 3, 4));
        data1.addDataPoint(new Rectangle2D(1, 2, 3, 4));
        assertTrue(TrialData.toJson("target/TD-ZOOMMAPS.json", data1));

        TrialData data2 = new TrialData(ToolType.CODECHARTS, "trialId", "configId");
        data2.setAnswer("answer");
        data2.addDataPoint(new Rectangle2D(1, 2, 3, 4), 12);
        data2.addDataPoint(new Rectangle2D(1, 2, 3, 4), 12);
        assertTrue(TrialData.toJson("target/TD-CODECHARTS.json", data2));

        assertFalse(TrialData.toJson("target/TD-ZOOMMAPS.jpg", data1));
        assertFalse(TrialData.toJson("target/TD-CODECHARTS.jpg", data2));
    }

    @Test
    public void fromJsonShouldWork() {
        assertEquals(TrialData.class,
                TrialData.fromJson("src/test/resources/testtrial-ZOOMMAPS.json").getClass());
        assertEquals(TrialData.class,
                TrialData.fromJson("src/test/resources/testtrial-CODECHARTS.json").getClass());

        TrialData dataZm = TrialData.fromJson("src/test/resources/testtrial-ZOOMMAPS.json");

        assertEquals(ToolType.ZOOMMAPS, dataZm.toolType);
        assertEquals("TRI_ID", dataZm.trialId);
        assertEquals("CON_ID", dataZm.configId);
        assertEquals("Wrigley, denke ich", dataZm.getAnswer());
        assertEquals(161, dataZm.getData().size());
        assertEquals(5148, dataZm.getData().get(0).timeOffset);
        assertEquals(41.42133035743848, dataZm.getData().get(0).viewport.getMinY(), 0.0001f);
        assertEquals(1, dataZm.getData().get(1).dataId);
        assertEquals(2207.555860812834, dataZm.getData().get(1).viewport.getWidth(), 0.0001f);
        assertNull(dataZm.getData().get(0).depth);

        TrialData dataCc = TrialData.fromJson("src/test/resources/testtrial-CODECHARTS.json");

        assertEquals(ToolType.CODECHARTS, dataCc.toolType);
        assertEquals("TRI_ID", dataCc.trialId);
        assertEquals("CON_ID", dataCc.configId);
        assertNull(dataCc.getAnswer());
        assertEquals(10, dataCc.getData().size());
        assertEquals(9886, dataCc.getData().get(0).timeOffset);
        assertEquals(new Rectangle2D(1280.0000000000002, 216.0, 640.0000000000001, 216.0),
                dataCc.getData().get(0).viewport);
        assertEquals(1, dataCc.getData().get(1).dataId);
        assertEquals(new Rectangle2D(640.0000000000001, 648.0, 640.0000000000001, 216.0),
                dataCc.getData().get(1).viewport);
    }
}
