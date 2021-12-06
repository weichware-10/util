package github.weichware10.util.config;

/**
 * Koordinator des Config-Moduls.
 *
 * <p>- Lädt Einstellungen.
 *
 * <p>- Gibt Einstellungen aus.
 *
 * <p>- Schreibt Einstellungen.
 */
public class ConfigClient {
    protected Configuration configuration;

    /**
     * Gibt das komplette Einstellungs-Objekt zurück.
     *
     * @return die Einstellungen. Kann {@code null} sein.
     */
    public Configuration getConfig() {
        return this.configuration;
    }

    /**
     * Schreibt die Konfiguration in die spezifizierte JSON-Datei.
     * Der Inhalt wird dabei überschrieben / die Datei neu angelegt.
     *
     * @param location - Pfad zu JSON-Datei.
     * @return Erfolgsboolean
     * @since v0.2
     */
    public boolean writeToJson(String location) {
        // Kann nicht gespeichert werden, wenn nicht geladen
        if (this.configuration == null) {
            return false;
        }
        // gibt Erfolgsboolean von ConfigWriter weiter
        return ConfigWriter.toJson(location, configuration);
    }

    /**
     * Lädt den Inhalt einer JSON-Datei in die interne Konfiguration.
     *
     * @param location - Der Speicherort der JSON-Datei
     * @return Erfolgsboolean
     * @since v0.2
     */
    public boolean loadFromJson(String location) {
        configuration = ConfigLoader.fromJson(location);
        // Rückgabe von false, wenn keine Konfiguration geladen wurde
        return (configuration == null) ? false : true;
    }

    /**
     * Lädt eine Konfiguration in den internen Speicher.
     *
     * @param trialId - ID des Versuchs.
     * @return Erfolgsboolean
     */
    public boolean loadFromDataBase(String trialId) {
        if (trialId == "www.weichware10.com/config") {
            configuration = ConfigLoader.fromDataBase(trialId);
            return true;
        } else {
            return false;
        }
    }
}
