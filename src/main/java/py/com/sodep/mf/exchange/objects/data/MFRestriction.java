package py.com.sodep.mf.exchange.objects.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

//FIXME no parece adecuado el nombre Restriction
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface MFRestriction {

	public static enum OPERATOR {
		EQUALS, GT, GTEQUAL, LT, LTEQUAL, REGEX, IN, NOT_EQUAL, NOT_IN
	};

	public static enum OPERATOR_MODIF {
		BEGIN, END, ANYWHERE, ABS, CASE_INSENSITIVE
	};
}
