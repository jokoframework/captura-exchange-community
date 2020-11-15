package py.com.sodep.mf.exchange;

public class ExchangeConstants {

	public static class PATH {
		public static final String LOOKUPTABLE_GETLOOKUPTABLE = "/api/lookupTable/definition/{lookupTableId}";
		
		public static final String LOOKUPTABLE_FINDTABLES = "/api/lookupTable/definition/listAll";
		
		public static final String LOOKUPTABLE_CREATE = "/api/lookupTable/definition";

		public static final String LOOKUPTABLE_DATA = "/api/lookupTable/data/{lookupTableId}";

		public static final String LOGOUT = "/api/authentication/logout";
	}

}
