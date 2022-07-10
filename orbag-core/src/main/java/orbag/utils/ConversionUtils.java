package orbag.utils;

public class ConversionUtils {

	public static <T> T convertString(String source, Class<T> targetType) {

		if (source == null || source.isEmpty()) {
			return null;
		}

		if (targetType == String.class) {
			return targetType.cast(source);
		}

		if (targetType == Long.class) {
			return targetType.cast(Long.parseLong(source));
		}

		if (targetType == Integer.class) {
			return targetType.cast(Integer.parseInt(source));
		}

		throw new IllegalArgumentException("invalid target type " + targetType);
	}
}
