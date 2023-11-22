package fr.gouv.esante.pml.smt.business;

import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import fr.gouv.esante.pml.smt.data.GetClassesDataFromAPI;
import fr.gouv.esante.pml.smt.data.GetDomainDataFromAPI;
import fr.gouv.esante.pml.smt.model.ClassList;
import fr.gouv.esante.pml.smt.model.Clazz;
import fr.gouv.esante.pml.smt.model.Domain;
import fr.gouv.esante.pml.smt.model.DomainList;
import fr.gouv.esante.pml.smt.utils.DCVocabulary;
import fr.gouv.esante.pml.smt.utils.SkosVocabulary;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationPropertyImpl;

public class CreateClass {
	
	public void creatClasses(OWLOntology onto, OWLOntologyManager manager, OWLDataFactory fact) throws Exception {
		
		GetClassesDataFromAPI getClassesDataFromAPI = new GetClassesDataFromAPI();
		
		String classesabout = "http://data.esante.gouv.fr/coe/standardterms/classes";
		OWLClass classesOwlClass = fact.getOWLClass(IRI.create(classesabout));
		OWLAxiom classesDeclare = fact.getOWLDeclarationAxiom(classesOwlClass);
		manager.applyChange(new AddAxiom(onto, classesDeclare));
		
		String supEDQM = "http://data.esante.gouv.fr/coe/standardterms";
		OWLSubClassOfAxiom ax = fact.getOWLSubClassOfAxiom(classesOwlClass, fact.getOWLClass(IRI.create(supEDQM)));
		manager.applyChange(new AddAxiom(onto, ax));
		
		OWLAnnotation annotdomain = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral("Classes", "en"));
		OWLAxiom axiomdomain = fact.getOWLAnnotationAssertionAxiom(classesOwlClass.getIRI(), annotdomain);
		manager.applyChange(new AddAxiom(onto, axiomdomain));
		
		OWLAnnotationProperty domainclassCode = new OWLAnnotationPropertyImpl(SkosVocabulary.notation.getIRI());
		OWLAnnotation annot1domain = fact.getOWLAnnotation(domainclassCode, fact.getOWLLiteral("classes"));
		OWLAxiom axiom1domain = fact.getOWLAnnotationAssertionAxiom(classesOwlClass.getIRI(), annot1domain);
		manager.applyChange(new AddAxiom(onto, axiom1domain));
		
		ClassList classList = getClassesDataFromAPI.getClassesList();
		for(Clazz cl : classList.getContent()) {
			OWLClass owlClass = null;
			String about = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
			owlClass = fact.getOWLClass(IRI.create(about));
			OWLAxiom declare = fact.getOWLDeclarationAxiom(owlClass);
			manager.applyChange(new AddAxiom(onto, declare));
			
			OWLAnnotationProperty classKind = new OWLAnnotationPropertyImpl(DCVocabulary.type.getIRI());
			OWLAnnotation annotclassKind = fact.getOWLAnnotation(classKind, fact.getOWLLiteral("classe"));
			OWLAxiom axiomclassKind = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotclassKind);
			manager.applyChange(new AddAxiom(onto, axiomclassKind));
			
			String sup = "http://data.esante.gouv.fr/coe/standardterms/classes";
			OWLSubClassOfAxiom ax1 = fact.getOWLSubClassOfAxiom(owlClass, fact.getOWLClass(IRI.create(sup)));
			manager.applyChange(new AddAxiom(onto, ax1));
			
			OWLAnnotation annot = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral(cl.getName(), "en"));
			OWLAxiom axiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot);
			manager.applyChange(new AddAxiom(onto, axiom));
			
			OWLAnnotationProperty classCode = new OWLAnnotationPropertyImpl(SkosVocabulary.notation.getIRI());
			OWLAnnotation annot1 = fact.getOWLAnnotation(classCode, fact.getOWLLiteral(cl.getCode()));
			OWLAxiom axiom1 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot1);
			manager.applyChange(new AddAxiom(onto, axiom1));
			
			CreateTerms createTerms = new CreateTerms();
			createTerms.creatTerms(onto, manager, fact, cl.getCode());
		}
	}

}
