package fr.gouv.esante.pml.smt.business;

import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.SKOSVocabulary;

import fr.gouv.esante.pml.smt.Main;
import fr.gouv.esante.pml.smt.data.GetDomainDataFromAPI;
import fr.gouv.esante.pml.smt.data.GetTermsDataFromAPI;
import fr.gouv.esante.pml.smt.model.Domain;
import fr.gouv.esante.pml.smt.model.DomainList;
import fr.gouv.esante.pml.smt.model.Term;
import fr.gouv.esante.pml.smt.model.TermsList;
import fr.gouv.esante.pml.smt.utils.EDQMVocabulary;
import fr.gouv.esante.pml.smt.utils.SkosVocabulary;
import fr.gouv.esante.pml.smt.utils.DCTVocabulary;
import fr.gouv.esante.pml.smt.utils.DCVocabulary;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationPropertyImpl;

public class CreateTerms {

	public void creatTerms(OWLOntology onto, OWLOntologyManager manager, OWLDataFactory fact, String clazz)
			throws Exception {

		GetTermsDataFromAPI getTermsDataFromAPI = new GetTermsDataFromAPI();

		TermsList termsList = getTermsDataFromAPI.getTerms(clazz);
		for (Term tr : termsList.getContent()) {
			OWLClass owlClass = null;
			String about = "http://data.esante.gouv.fr/coe/standardterms/" + tr.getCode();
			owlClass = fact.getOWLClass(IRI.create(about));
			OWLAxiom declare = fact.getOWLDeclarationAxiom(owlClass);
			manager.applyChange(new AddAxiom(onto, declare));

			String sup = "http://data.esante.gouv.fr/coe/standardterms/" + tr.getClazz();
			OWLSubClassOfAxiom ax1 = fact.getOWLSubClassOfAxiom(owlClass, fact.getOWLClass(IRI.create(sup)));
			manager.applyChange(new AddAxiom(onto, ax1));
			
			OWLAnnotationProperty classKind = new OWLAnnotationPropertyImpl(DCVocabulary.type.getIRI());
			OWLAnnotation annotclassKind = fact.getOWLAnnotation(classKind, fact.getOWLLiteral("terms"));
			OWLAxiom axiomclassKind = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotclassKind);
			manager.applyChange(new AddAxiom(onto, axiomclassKind));

			String dom = Main.domainList.get(tr.getDomain());
			OWLAnnotationProperty domain = new OWLAnnotationPropertyImpl(EDQMVocabulary.domain.getIRI());
			String uri = "http://data.esante.gouv.fr/coe/standardterms/" + dom;
			OWLAnnotation domainannot = fact.getOWLAnnotation(domain, IRI.create(uri));
			OWLAxiom domainaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), domainannot);
			manager.applyChange(new AddAxiom(onto, domainaxiom));

			OWLAnnotation annot = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral(tr.getEnglish(), "en"));
			OWLAxiom axiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot);
			manager.applyChange(new AddAxiom(onto, axiom));
			if (null != tr.getFr_fr()) {
				OWLAnnotation annot2 = fact.getOWLAnnotation(fact.getRDFSLabel(),
						fact.getOWLLiteral(tr.getFr_fr(), "fr"));
				OWLAxiom axiom2 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot2);
				manager.applyChange(new AddAxiom(onto, axiom2));
			}

			OWLAnnotationProperty classCode = new OWLAnnotationPropertyImpl(SkosVocabulary.notation.getIRI());
			OWLAnnotation annot1 = fact.getOWLAnnotation(classCode, fact.getOWLLiteral(tr.getCode()));
			OWLAxiom axiom1 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot1);
			manager.applyChange(new AddAxiom(onto, axiom1));

			OWLAnnotationProperty DEFINITION = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
			OWLAnnotation DEFINITIONannot = fact.getOWLAnnotation(DEFINITION,
					fact.getOWLLiteral(tr.getDefinition(), "en"));
			OWLAxiom DEFINITIONaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), DEFINITIONannot);
			manager.applyChange(new AddAxiom(onto, DEFINITIONaxiom));
			
			OWLAnnotationProperty dctIssued = fact.getOWLAnnotationProperty(DCTVocabulary.issued.getIRI());
			OWLAnnotation annotIssued = fact.getOWLAnnotation(dctIssued,
					fact.getOWLLiteral(tr.getCreation_date()));
			OWLAxiom IssuedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotIssued);
			manager.applyChange(new AddAxiom(onto, IssuedNaxiom));
			
			OWLAnnotationProperty modified = fact.getOWLAnnotationProperty(DCTVocabulary.modified.getIRI());
			OWLAnnotation annotmodified = fact.getOWLAnnotation(modified,
					fact.getOWLLiteral(tr.getModification_date()));
			OWLAxiom modifiedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotmodified);
			manager.applyChange(new AddAxiom(onto, modifiedNaxiom));
			
//			Main.csvWriter.append(tr.getCode()).append("§").append(tr.getFr_fr()).append("§").append(tr.getEnglish()).append("§").append(tr.getDefinition()).append("§");
//			Main.csvWriter.append(tr.getClazz()).append("§").append(tr.getDomain()).append("§").append(tr.getCreation_date()).append("§").append(tr.getModification_date());
//			Main.csvWriter.append("\n");
		}
	}

}
