package ch.aos.demo.filewatcher.service.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import ch.aos.demo.filewatcher.jaxb.MyDocumentXML;
import ch.aos.demo.filewatcher.service.JaxbFileService;

@Component(JaxbFileService.COMPONENT_NAME)
public class JaxbFileServiceImpl implements JaxbFileService {

    @Override
    public MyDocumentXML getFileWithPath(String path) throws JAXBException {
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(MyDocumentXML.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (MyDocumentXML) jaxbUnmarshaller.unmarshal(file);
    }
}
