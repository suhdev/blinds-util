package test.com.suh.blinds.util;

import com.suh.blinds.util.ByteConverter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

public class ByteConverterTestSuite {
    public static class MockClass1 {
        public byte[] toBytes(){
            return new byte[]{0x01,0x02};
        }
    }
    public @Test void  itShouldCalculateBigEndianByteArrayOfLongValue(){
        byte []b = ByteConverter.toByte(0x1212101210121010L);
        Assert.assertEquals(b[0],0x12);
    }

    public @Test void itShouldCalculateLittleEndianByteArrayOfLongValue(){
        byte []b = ByteConverter.toByte(0x1212101210121010L,ByteConverter.MODE_LITTLE_ENDIAN);
        Assert.assertEquals(b[0],0x10);
    }

    public @Test void  itShouldCalculateBigEndianByteArrayOfIntegerValue(){
        byte []b = ByteConverter.toByte(0x12121010);
        Assert.assertEquals(b[0],0x12);
    }

    public @Test void itShouldCalculateLittleEndianByteArrayOfIntegerValue(){
        byte []b = ByteConverter.toByte(0x12121010,ByteConverter.MODE_LITTLE_ENDIAN);
        Assert.assertEquals(b[0],0x10);
    }

    public @Test void  itShouldCalculateBigEndianByteArrayOfShortValue(){
        byte []b = ByteConverter.toByte((short)0x1210);
        Assert.assertEquals(b[0],0x12);
    }

    public @Test void itShouldCalculateLittleEndianByteArrayOfShortValue(){
        byte []b = ByteConverter.toByte((short)0x1210,ByteConverter.MODE_LITTLE_ENDIAN);
        Assert.assertEquals(b[0],0x10);
    }

    public @Test void  itShouldCalculateBigEndianByteArrayOfCharValue(){
        byte []b = ByteConverter.toByte('ي');
        Assert.assertEquals(b[0],6);
    }

    public @Test void itShouldCalculateLittleEndianByteArrayOfChartValue(){
        byte []b = ByteConverter.toByte('ي',ByteConverter.MODE_LITTLE_ENDIAN);
        Assert.assertEquals(b[0],74);
    }


    public @Test void itShouldConvertObjectToByte() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        byte []b= ByteConverter.toByte(new MockClass1());
        Assert.assertEquals(b[0],0x01);
        Assert.assertEquals(b[1],0x02);
    }

}
