package net.peakgames.libgdx.stagebuilder.core.xml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import net.peakgames.libgdx.stagebuilder.core.builder.ActorBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class XmlHelper {

    private XmlHelper() {
        
    }
    
	public static float readFloatAttribute(XmlPullParser parser, String attributeName, float defaultValue) {
		String sValue = parser.getAttributeValue(null, attributeName);
		if (sValue == null) {
			return defaultValue;
		}
		return Float.parseFloat(sValue);
	}

	public static int readIntAttribute(XmlPullParser parser, String attributeName, int defaultValue) {
		String sValue = parser.getAttributeValue(null, attributeName);
		if (sValue == null) {
			return defaultValue;
		}
		return Integer.parseInt(sValue);
	}

	public static String readStringAttribute(XmlPullParser parser, String attributeName) {
		return parser.getAttributeValue(null, attributeName);
	}
	
	public static String readStringAttribute(XmlPullParser parser, String attributeName, String defaultValue) {
		String sValue = readStringAttribute(parser, attributeName);
		if(sValue == null) {
			return defaultValue;
		}
		return sValue;
	}

    public static boolean readBooleanAttribute(XmlPullParser parser, String attributeName, boolean defaultValue) {
        String sValue = readStringAttribute(parser, attributeName);
        if(sValue == null) {
            return defaultValue;
        }
        return Boolean.valueOf(sValue);
    }

	public static int readAlignmentAttribute(XmlPullParser parser, String attributeName, int defaultAlignment) {
		String sValue = readStringAttribute(parser, attributeName);
		return ActorBuilder.calculateAlignment(sValue, defaultAlignment);
	}

    public static FileHandle readFileAttribute(XmlPullParser parser, String attributeName, String filePath) {
		String sValue = parser.getAttributeValue(null, attributeName);
		if(sValue == null) {
			return Gdx.files.internal(filePath);
		}
		return Gdx.files.internal(sValue);
	}
	
	public static XmlPullParser getXmlParser(FileHandle fileHandle) {
        InputStream is = null;
        try {
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            is = fileHandle.read();
            parser.setInput(is, null);

			return parser;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
    }

    public static XmlPullParser getXmlParser(InputStream is) {
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is, null);
            return parser;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public static float[] readFloatArrayAttribute(XmlPullParser xmlParser, String key, int length, float defaultVal) {
		String sVal = readStringAttribute(xmlParser, key);
		if (sVal == null) return null;
		String[] splitted = sVal.split(" ");
		float[] result = new float[length];

		for (int i = 0; i < length; i++) {
			if (i >= splitted.length) {
				result[i] = defaultVal;
				continue;
			}
			result[i] = Float.valueOf(splitted[i]);
		}
		
		return result;
	}
}
