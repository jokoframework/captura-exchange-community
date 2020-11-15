package py.com.sodep.mf.exchange;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import py.com.sodep.mf.exchange.MFField.FIELD_TYPE;

/**
 * This class encapusulates useful methods related to how we handle data related
 * to Documents and Lookup tables
 * 
 * @author danicricco
 * 
 */
public class MFDataHelper {

	private static final String NUMBER_FORMAT = "#.#";

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";

	private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			return sdf;
		}
	};

	private static final ThreadLocal<DecimalFormat> NUMBER_FORMATTER = new ThreadLocal<DecimalFormat>() {
		@Override
		protected DecimalFormat initialValue() {
			return new DecimalFormat(NUMBER_FORMAT);
		}
	};

	/**
	 * This method assign a data type to a meta column. It is useful in order to
	 * have code that works either with "data fields" or "meta fields"
	 * 
	 * @param column
	 * @return
	 */
	public static FIELD_TYPE whatTypeIsThisMetaData(String column) {
		if (column.equals(MFIncomingDataI.META_FIELD_FORM_ID)) {
			return FIELD_TYPE.NUMBER;
		} else if (column.equals(MFIncomingDataI.META_FIELD_LOCATION)) {
			return FIELD_TYPE.LOCATION;
		} else if (column.equals(MFIncomingDataI.META_FIELD_MAIL)) {
			return FIELD_TYPE.STRING;
		} else if (column.equals(MFIncomingDataI.META_FIELD_RECEIVED_AT) || column.equals(MFIncomingDataI.META_FIELD_SAVED_AT)) {
			return FIELD_TYPE.DATE;
		} else if (column.equals(MFIncomingDataI.META_FIELD_USER_ID)) {
			return FIELD_TYPE.NUMBER;
		} else if (column.equals(MFIncomingDataI.META_FIELD_DOCUMENT_ID)) {
			return FIELD_TYPE.NUMBER;
		} else if (column.equals(MFIncomingDataI.META_FIELD_DEVICE_ID)) {
			return FIELD_TYPE.STRING;
		}
		throw new RuntimeException("Unkowwn meta column " + column);
	}

	public static Date unserializeDate(String str) {
		SimpleDateFormat sdf = DATE_FORMATTER.get();

		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			throw new IllegalStringFormatException(str + " is no a valid Date string "
					+ ". The Temporal type pattern is " + DATE_FORMAT);
		}
	}

	public static Date unserializeDate(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			throw new IllegalStringFormatException(str + " is no a valid Date string "
					+ ". The Temporal type pattern is " + format);
		}
	}

	
	
	/**
	 * Some values are transported as Strings. This method will cast it to the
	 * expected Java Type according to the provided data type
	 * 
	 * @return
	 */
	public static Object unserialize(FIELD_TYPE type, String str) {
		switch (type) {
		case BOOLEAN:
			return unserializeBoolean(str);
		case NUMBER:
			return unserializeNumber(str);
		case DATE:
			return unserializeDate(str);
		case STRING:
			return str;
		case LOCATION:
			return unserializeLocation(str);
		}
		return null;
	}

	public static Boolean unserializeBoolean(String str) {
		if (!str.toLowerCase().equals("true") && !str.toLowerCase().equals("false")) {
			throw new IllegalStringFormatException("Valid boolean values are: [true || false] ");
		}
		return Boolean.parseBoolean(str);
	}

	public static Number unserializeNumber(String str) {
		// FIXME Is DecimalFormat thread Safe?
		// This should be a singleton or a ThreadLocalVariable
		DecimalFormat formatter = NUMBER_FORMATTER.get();
		try {
			return formatter.parse(str);
		} catch (ParseException e) {
			throw new IllegalStringFormatException(str + " is not a valid Number. The number pattern is "
					+ NUMBER_FORMAT);
		}
	}

	public static MFLocationData unserializeLocation(String location) {
		MFLocationData loc = new MFLocationData();
		String[] coordinates = location.split(",");
		if (coordinates == null || coordinates.length < 2) {
			throw new IllegalStringFormatException(
					"Coordindates must be of type (latitude,longitude[,altitude[,accuracy]])");
		}
		loc.setLatitude(new Double(coordinates[0]));
		loc.setLongitude(new Double(coordinates[1]));
		// altitude and accuracy are optional
		if (coordinates.length >= 3) {
			loc.setAltitude(new Double(coordinates[2]));
		}

		if (coordinates.length == 4) {
			loc.setAccuracy(new Double(coordinates[3]));
		}

		return loc;
	}

	public static String serialize(Object val) {
		if (val == null) {
			return null;
		}

		if (val instanceof Boolean) {
			return serializeBoolean((Boolean) val);
		} else if (val instanceof Number) {
			return serializeNumber((Number) val);
		} else if (val instanceof Date) {
			return serializeDate((Date) val);
		} else if (val instanceof MFLocationData) {
			throw new RuntimeException("NOT YET IMPLEMENTED");
		} else if (val instanceof String) {
			return (String) val;
		}
		throw new IllegalValueException();
	}

	/**
	 * Since some values are transported as Strings. This method will convert
	 * those objects to String. In order to covert them back to their original
	 * value use the method
	 * {@link #castValue(py.com.sodep.mf.exchange.MFField.FIELD_TYPE, String, String)}
	 * 
	 * @return
	 */
	public static String serialize(FIELD_TYPE type, Object val) {
		if (val == null) {
			return null;
		}
		switch (type) {
		case BOOLEAN:
			if (val instanceof Boolean) {
				return serializeBoolean((Boolean) val);
			} else {
				throw new IllegalValueException("An object of type " + val.getClass().getName()
						+ " can't be serialized as a Boolean");
			}
		case NUMBER:
			if (val instanceof Number) {
				return serializeNumber((Number) val);
			} else {
				throw new IllegalValueException("An object of type " + val.getClass().getName()
						+ " can't be serialized as a Number");
			}
		case DATE:
			if (val instanceof Date) {
				return serializeDate((Date) val);
			} else {
				throw new IllegalValueException("An object of type " + val.getClass().getName()
						+ " can't be serialized as a Date");
			}
		case STRING:
			return (String) val;
		case LOCATION:
			throw new RuntimeException("Not yet implemented!");
		}
		return null;
	}

	private static String serializeNumber(Number val) {
		DecimalFormat formatter = NUMBER_FORMATTER.get();
		return formatter.format(val);
	}

	private static String serializeBoolean(Boolean val) {
		return val.toString();
	}

	private static String serializeDate(Date d) {
		if (d == null) {
			return null;
		}
		SimpleDateFormat sdf = DATE_FORMATTER.get();
		return sdf.format(d);
	}

	/**
	 * Receives a map of String,String and cast each of the values (using
	 * {@link #castValue(py.com.sodep.mf.exchange.MFField.FIELD_TYPE, String, String)}
	 * ) to the adequate object in Java
	 * 
	 * @param def
	 * @param row
	 * @return
	 */
	public static Map<String, Object> unserializeValues(MFDataSetDefinition def, Map<String, String> row) {
		HashMap<String, Object> newRow = new HashMap<String, Object>();
		Map<String, MFField> fieldsMap = def.fieldsMappedByName();
		Set<String> expectedFields = fieldsMap.keySet();
		for (String f : expectedFields) {
			String strValue = row.get(f);
			MFField field = fieldsMap.get(f);
			Object value = null;
			if (strValue != null) {
				// danicricco: I decide to put this check here, because using it
				// in inside the method castValue might have undesire effect on
				// the form
				// Hello developer from the future! If you feel sure
				// enough to move this "nullability" check inside castValue
				// please feel free to do it. Just remember what uncle Ben told
				// us
				value = unserialize(field.getType(), strValue);
			}
			newRow.put(f, value);
		}
		return newRow;
	}

	/**
	 * This method will translate objects on the java space to the string
	 * representation that we use in the intermediate json
	 * 
	 * @param def
	 * @param row
	 * @return
	 */
	public static Map<String, String> serializeValues(MFDataSetDefinition def, Map<String, Object> row) {
		HashMap<String, String> newRow = new HashMap<String, String>();
		Map<String, MFField> fieldsMap = def.fieldsMappedByName();
		Set<String> expectedFields = fieldsMap.keySet();
		for (String f : expectedFields) {
			Object originalValue = row.get(f);
			MFField field = fieldsMap.get(f);
			String strValue = serialize(field.getType(), originalValue);
			newRow.put(f, strValue);
		}
		return newRow;
	}
}
