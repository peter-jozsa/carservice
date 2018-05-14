package hu.unideb.inf.lev.carservice.utility;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

/**
 * An adapter class to parse {@link LocalDateTime} values in XMLs.
 */
public class LocalDateTimeAdapter extends XmlAdapter<String,LocalDateTime> {
    /* (non-Javadoc)
     * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
     */
    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v);
    }

    /* (non-Javadoc)
     * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
     */
    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }
}
