package org.solovyev.common.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: serso
 * Date: 27.04.2009
 * Time: 10:23:29
 */
public final class StringUtils {

    private static final String EMPTY = "";

    /**
     * <p>The maximum size to which the padding constant(s) can expand.</p>
     */
    private static final int PAD_LIMIT = 8192;

	// not intended for instantiation
	private StringUtils() {
		throw new AssertionError();
	}

	public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];

	public static String[] split(String source, String subString) {
		String[] params = source.split(subString);
		List<String> result = new ArrayList<String>();

		for (String s : params) {
			if (!isEmpty(s) && !s.equals(subString)) {
				result.add(s);
			}
		}

		return result.toArray(new String[result.size()]);
	}

	public static String convertStream(InputStream in) throws IOException {
		int i;
		StringBuilder sb = new StringBuilder();
		while ((i = in.read()) != -1) {
			sb.append((char) i);
		}
		return sb.toString();
	}

	public static boolean notEmpty(@Nullable String s) {
		return !isEmpty(s);
	}

	public static boolean isEmpty(@Nullable CharSequence s) {
		return s == null || s.length() == 0;
	}

	@NotNull
	public static String getNotEmpty(@Nullable CharSequence s, @NotNull String defaultValue) {
		return isEmpty(s) ? defaultValue : s.toString();
	}

	@NotNull
	public static <T> String getNotEmpty(@Nullable T t, @NotNull String defaultValue) {
		return t == null ? defaultValue : t.toString();
	}

	public static String[] toString(@NotNull Enum... enums) {
		String[] result = new String[enums.length];
		LoopData ld = new LoopData(enums);
		for (Enum anEnum : enums) {
			result[(int) ld.next()] = anEnum.name();
		}
		return result;
	}

	@NotNull
	public static Character[] toObject(char[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_CHARACTER_OBJECT_ARRAY;
		}

		final Character[] result = new Character[array.length];

		for (int i = 0; i < array.length; i++) {
			result[i] = new Character(array[i]);
		}
		return result;
	}

	@NotNull
	public static String fromStackTrace(@Nullable StackTraceElement... stackTraceElements) {
		final StringBuilder sb = new StringBuilder();

		if ( !CollectionsUtils.isEmpty(stackTraceElements) ){
			for (StackTraceElement stackTraceElement : stackTraceElements) {
				sb.append(" at ");
				sb.append(stackTraceElement.toString());
				sb.append(System.getProperty("line.separator"));
			}
		}

		return sb.toString();
	}

    @NotNull
    public static String repeat(@NotNull String str, int repeat) {
        // Performance tuned for 2.0 (JDK1.4)

        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    @NotNull
    public static String repeat(char ch, int repeat) {
        char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }
}