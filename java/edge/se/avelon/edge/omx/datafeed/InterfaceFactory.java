package se.avelon.edge.omx.datafeed;


public class InterfaceFactory {
	static CandleFeedInterface feeder = null;

	static public CandleFeedInterface getFeeder() {
		if(feeder == null) {
			try {
				ClassLoader loader = InterfaceFactory.class.getClassLoader();
				Class<?> aClass = loader.loadClass("se.avelon.edge.omx.datafeed.OmxCandleFeedAdapter");
				feeder = (CandleFeedInterface)aClass.newInstance();
			} 
			catch (InstantiationException e) {
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) {
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return feeder;
	}
}
