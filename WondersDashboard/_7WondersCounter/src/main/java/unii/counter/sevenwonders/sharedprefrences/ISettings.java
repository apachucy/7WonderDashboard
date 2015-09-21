package unii.counter.sevenwonders.sharedprefrences;


public interface ISettings {

	public boolean isExtensionSet();
    public void setExtension(boolean setExtension);
    public void setFirstRun(boolean isFirst);
    public boolean getFirstRun();
}
