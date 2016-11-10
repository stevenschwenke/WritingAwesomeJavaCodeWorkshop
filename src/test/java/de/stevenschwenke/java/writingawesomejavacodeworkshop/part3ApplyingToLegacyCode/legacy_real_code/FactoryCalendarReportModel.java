package de.stevenschwenke.java.writingawesomejavacodeworkshop.part3ApplyingToLegacyCode.legacy_real_code;


public class FactoryCalendarReportModel {

    private BitSet factoryNames;
    private BitSet linienNamen;
    private BitSet produktNamen;
    private String markeName;
    private boolean quartal;
    private boolean clientFactoryLinieModell;
    private boolean clientFactoryModell;
    private boolean clientFactory;
    private boolean clientFactoryLine;

    public BitSet getFactoryNames() {
        return factoryNames;
    }

    public void setFactoryNames(BitSet factoryNames) {
        this.factoryNames = factoryNames;
    }

    public BitSet getLinienNamen() {
        return linienNamen;
    }

    public void setLinienNamen(BitSet linienNamen) {
        this.linienNamen = linienNamen;
    }

    public BitSet getProduktNamen() {
        return produktNamen;
    }

    public void setProduktNamen(BitSet produktNamen) {
        this.produktNamen = produktNamen;
    }

    public String getClientName() {
        return markeName;
    }

    public void setMarkeName(String markeName) {
        this.markeName = markeName;
    }

    public boolean isQuartal() {
        return quartal;
    }

    public void setQuartal(boolean quartal) {
        this.quartal = quartal;
    }

    public boolean isClientFactoryLinieModell() {
        return clientFactoryLinieModell;
    }

    public void setClientFactoryLinieModell(boolean clientFactoryLinieModell) {
        this.clientFactoryLinieModell = clientFactoryLinieModell;
    }

    public boolean isClientFactoryModell() {
        return clientFactoryModell;
    }

    public void setClientFactoryModell(boolean clientFactoryModell) {
        this.clientFactoryModell = clientFactoryModell;
    }

    public boolean isClientFactory() {
        return clientFactory;
    }

    public void setClientFactory(boolean clientFactory) {
        this.clientFactory = clientFactory;
    }

    public boolean isClientFactoryLinie() {
        return clientFactoryLine;
    }

    public void setClientFactoryLine(boolean clientFactoryLine) {
        this.clientFactoryLine = clientFactoryLine;
    }
}
