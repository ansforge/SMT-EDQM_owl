package fr.gouv.esante.pml.smt;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gouv.esante.pml.smt.business.CreateClass;
import fr.gouv.esante.pml.smt.business.CreateDomain;
import fr.gouv.esante.pml.smt.business.CreateFullClass;
import fr.gouv.esante.pml.smt.business.CreateFullTerms;
import fr.gouv.esante.pml.smt.business.CreateHierarchyTerminolgy;
import fr.gouv.esante.pml.smt.business.CreateMAPTerms;
import fr.gouv.esante.pml.smt.utils.EDQMVocabulary;
import fr.gouv.esante.pml.smt.utils.PropertiesUtil;
import fr.gouv.esante.pml.smt.utils.SkosVocabulary;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationPropertyImpl;

public class Main2 {

	private static final Logger log = LoggerFactory.getLogger(Main2.class);
	private static OWLOntologyManager manager;
	private static OWLOntology onto;
	private static OWLDataFactory fact;
	public static Map<String, String> domainList = new HashMap<String, String>();
	public static Map<String, String> BasicDoseList = new HashMap<String, String>();
	public static Map<String, String> StateOfMatter = new HashMap<String, String>();
//	public static Writer csvWriter = null;

	public static void main(final String... args) throws Exception {

		OutputStream edqmOWL = new FileOutputStream(PropertiesUtil.getProperties("edqmOWLFile"));
//		csvWriter = new OutputStreamWriter(new FileOutputStream(PropertiesUtil.getProperties("edqmcsvFile")), StandardCharsets.UTF_8);
//		csvWriter.append("Code").append("§").append("FR_Label").append("§").append("EN_Label").append("§").append("Definition").append("§");
//		csvWriter.append("Classe").append("§").append("Domain").append("§").append("Creation_date").append("§").append("Modification_date");
//        csvWriter.append("\n");
		
		manager = OWLManager.createOWLOntologyManager();
		log.info("creation ontologie structure");
		onto = manager.createOntology();
		log.info("ajout ontologie IRI");
		IRI cimIRI = IRI.create("http://data.esante.gouv.fr/coe/standardterms#");
		manager.applyChange(new SetOntologyID(onto, new OWLOntologyID(cimIRI)));
		fact = onto.getOWLOntologyManager().getOWLDataFactory();
		
		
		
		OWLClass owlClass = null;
		String about = "http://data.esante.gouv.fr/coe/standardterms";
		owlClass = fact.getOWLClass(IRI.create(about));
		OWLAxiom declare = fact.getOWLDeclarationAxiom(owlClass);
		manager.applyChange(new AddAxiom(onto, declare));
		
		OWLAnnotation annot = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral("European Directorate for the Quality of Medicines & HealthCare (EDQM)", "en"));
		OWLAxiom axiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot);
		manager.applyChange(new AddAxiom(onto, axiom));
		
		OWLAnnotationProperty classCode = new OWLAnnotationPropertyImpl(SkosVocabulary.notation.getIRI());
		OWLAnnotation annot1 = fact.getOWLAnnotation(classCode, fact.getOWLLiteral("edqm"));
		OWLAxiom axiom1 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot1);
		manager.applyChange(new AddAxiom(onto, axiom1));
		
		CreateDomain createDomain = new CreateDomain();
		createDomain.creatDomain(onto, manager, fact);
		
		//CreateFullClass createClass = new CreateFullClass();
		//createClass.creatClasses(onto, manager, fact);
		
//		CreateHierarchyTerminolgy createTerms = new CreateHierarchyTerminolgy();
//		createTerms.createHierarchyTerminolgy(onto, manager, fact);
//		
		//CreateMAPTerms createMAPTerms = new CreateMAPTerms();
		//createMAPTerms.creatTerms(onto, manager, fact);
		
		//for(String cd : BasicDoseList.keySet()) {
			//String basicCode = BasicDoseList.get(cd);
			//String somCode = StateOfMatter.get(basicCode);
			
			//OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Has_Pharmaceutical_State_Of_Matter.getIRI());
			//String lin = "http://data.esante.gouv.fr/coe/standardterms/" + somCode;
			//OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
			//OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(IRI.create(cd), linksannot);
			//manager.applyChange(new AddAxiom(onto, linksaxiom));
		//}
//		
//		CreateFullTerms createFullTerms = new CreateFullTerms();
//		createFullTerms.creatTerms(onto, manager, fact, "CON");
//		createFullTerms.creatTerms(onto, manager, fact, "CLO");
//		createFullTerms.creatTerms(onto, manager, fact, "DEV");
//		createFullTerms.creatTerms(onto, manager, fact, "BDF");
		
		RDFXMLDocumentFormat ontologyFormat = new RDFXMLDocumentFormat();
		ontologyFormat.setPrefix("dct", "http://purl.org/dc/terms/");
		
		manager.saveOntology(onto, ontologyFormat, edqmOWL);
//		csvWriter.flush();
//	    csvWriter.close();

	}

}
