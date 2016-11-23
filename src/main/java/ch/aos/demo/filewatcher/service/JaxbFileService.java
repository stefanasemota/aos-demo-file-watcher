package ch.aos.demo.filewatcher.service;

import javax.xml.bind.JAXBException;

import ch.aos.demo.filewatcher.jaxb.MyDocumentXML;

public interface JaxbFileService {
    final String COMPONENT_NAME = "jJaxbFileService";

    MyDocumentXML getFileWithPath(String path) throws JAXBException;
}
