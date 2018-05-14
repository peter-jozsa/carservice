package hu.unideb.inf.lev.carservice.datasource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A datasource loader class which can handle XML files.
 */
public class XmlDataSourceLoader {
    /**
     * Parses the provided XML file into a datasource.
     * @param filename The name of the XML file
     * @return A datasource instance.
     */
    public static CarserviceDataSource loadData(String filename) {
        try(FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr)) {
            JAXBContext ctx = JAXBContext.newInstance(XmlCarserviceDataSource.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();

            return (XmlCarserviceDataSource) unmarshaller.unmarshal(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
