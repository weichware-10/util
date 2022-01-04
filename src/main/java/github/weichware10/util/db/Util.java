package github.weichware10.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

class Util {

    // wandelt eine als String gespeicherte Liste um
    protected static List<String> stringsToList(String strings) {
        return Arrays.asList(strings.substring(1, strings.length() - 1).split(", "));
    }

    /**
     * Generiert eine ID. Muss nicht eindeutig sein!
     *
     * @param prefix - Präfix vor ID
     * @param length - Länge der generierten ID (nur zufälliger Teil)
     * @return generierte ID
     */
    protected static String generateId(String prefix, int length) {
        String alphanumerics = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String id = prefix;
        for (int i = 0; i < length; i++) {
            id += alphanumerics.charAt((int) Math.floor(Math.random() * alphanumerics.length()));
        }
        return id;
    }

    /**
     * Schließt eine Connection, ohne Fehler zu werfen.
     *
     * @param conn - die zu schließende Connection
     */
    protected static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            /* Ignored */
        }
    }

    /**
     * Schließt eine Statement, ohne Fehler zu werfen.
     *
     * @param st - die zu schließende Statement
     */
    protected static void closeQuietly(Statement st) {
        try {
            st.close();
        } catch (Exception e) {
            /* Ignored */
        }
    }

    /**
     * Schließt eine ResultSet, ohne Fehler zu werfen.
     *
     * @param rs - die zu schließende ResultSet
     */
    protected static void closeQuietly(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
            /* Ignored */
        }
    }
}
