package com.javasampleapproach.jaxb.create;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javasampleapproach.jaxb.pojo.Customer;
import com.javasampleapproach.jms.client.JmsClient;

@Service
public class JAXBXmlWR {

	@Autowired
	JmsClient jmsClient;
	
	public void  writeJAXB(){
		 Customer customer = new Customer();
		  customer.setId(100);
		  customer.setName("Hello");
		  customer.setAge(29);
		  File file = new File("C:\\Users\\sony\\Desktop\\multiThread\\JAXB\\in\\fin.xml");
		  try {

			
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

//			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(customer, file);
//			jaxbMarshaller.marshal(customer, System.out);
			
			BufferedReader br;
				br = new BufferedReader(new FileReader(file));
				String line;
				StringBuilder sb = new StringBuilder();

				while((line=br.readLine())!= null){
				    sb.append(line.trim());
				}
				jmsClient.send(sb.toString());
		  	  } catch (JAXBException e) {
		    	  e.printStackTrace();
		      } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    
	}
	
	
	public void  readJAXB(){
		  File file = new File("C:\\Users\\sony\\Desktop\\multiThread\\JAXB\\out\\fout.xml");
		  String xmlString = jmsClient.receive();
		  try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xmlString));
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(reader);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(customer, file);
	      } catch (JAXBException e) {
	    	  e.printStackTrace();
	      } catch (XMLStreamException e) {
			e.printStackTrace();
		  } catch (FactoryConfigurationError e) {
			e.printStackTrace();
		  }
	}
	
}
