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
import fr.gouv.esante.pml.smt.data.GetFullTermsDataFromAPI;
import fr.gouv.esante.pml.smt.data.GetMAPsTermsDataFromAPI;
import fr.gouv.esante.pml.smt.data.GetTermsDataFromAPI;
import fr.gouv.esante.pml.smt.model.CLASS;
import fr.gouv.esante.pml.smt.model.Content;
import fr.gouv.esante.pml.smt.model.ContentMAP;
import fr.gouv.esante.pml.smt.model.Domain;
import fr.gouv.esante.pml.smt.model.DomainList;
import fr.gouv.esante.pml.smt.model.Root;
import fr.gouv.esante.pml.smt.model.RootMAP;
import fr.gouv.esante.pml.smt.model.Term;
import fr.gouv.esante.pml.smt.model.TermsList;
import fr.gouv.esante.pml.smt.model.Translation;
import fr.gouv.esante.pml.smt.model.Us;
import fr.gouv.esante.pml.smt.utils.EDQMVocabulary;
import fr.gouv.esante.pml.smt.utils.SkosVocabulary;
import fr.gouv.esante.pml.smt.utils.ADMSVocabulary;
import fr.gouv.esante.pml.smt.utils.DCTVocabulary;
import fr.gouv.esante.pml.smt.utils.DCVocabulary;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationPropertyImpl;

public class CreateMAPTerms {

	public void creatTerms(OWLOntology onto, OWLOntologyManager manager, OWLDataFactory fact)
			throws Exception {
		
		
//		OWLClass owlClassRacine = null;
//		String aboutRacine = "http://data.esante.gouv.fr/coe/edqm/terms";
//		owlClassRacine = fact.getOWLClass(IRI.create(aboutRacine));
//		OWLAxiom declareRacine = fact.getOWLDeclarationAxiom(owlClassRacine);
//		manager.applyChange(new AddAxiom(onto, declareRacine));
//		
//		String supEDQMRacine = "http://data.esante.gouv.fr/coe/edqm";
//		OWLSubClassOfAxiom axRacine = fact.getOWLSubClassOfAxiom(owlClassRacine, fact.getOWLClass(IRI.create(supEDQMRacine)));
//		manager.applyChange(new AddAxiom(onto, axRacine));
//
//		OWLAnnotation annotRacine = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral("Terms", "en"));
//		OWLAxiom axiomRacine = fact.getOWLAnnotationAssertionAxiom(owlClassRacine.getIRI(), annotRacine);
//		manager.applyChange(new AddAxiom(onto, axiomRacine));
		
		OWLClass owlClassMain = null;
		String aboutMain = "http://data.esante.gouv.fr/coe/standardterms/MAP";
		owlClassMain = fact.getOWLClass(IRI.create(aboutMain));
		OWLAxiom declareMain = fact.getOWLDeclarationAxiom(owlClassMain);
		manager.applyChange(new AddAxiom(onto, declareMain));
		
//		String supEDQM = "http://data.esante.gouv.fr/coe/edqm/terms";
//		OWLSubClassOfAxiom ax = fact.getOWLSubClassOfAxiom(owlClassMain, fact.getOWLClass(IRI.create(supEDQM)));
//		manager.applyChange(new AddAxiom(onto, ax));

//		OWLAnnotation annotMain = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral("Mapped Terms", "en"));
//		OWLAxiom axiomMain = fact.getOWLAnnotationAssertionAxiom(owlClassMain.getIRI(), annotMain);
//		manager.applyChange(new AddAxiom(onto, axiomMain));

		OWLAnnotationProperty DEFINITIONMain = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
		OWLAnnotation DEFINITIONannotMain = fact.getOWLAnnotation(DEFINITIONMain, fact.getOWLLiteral("List of mapped terms, arranged according to their source. These are not Standard Terms, but terms from external databases that are referenced here in order to help users identify potential equivalent Standard Terms. The EDQM is not responsible for the accuracy or completeness of any mapping. Questions about any terms should be directed to the owners of the external database.", "en"));
		OWLAxiom DEFINITIONaxiomMain = fact.getOWLAnnotationAssertionAxiom(owlClassMain.getIRI(), DEFINITIONannotMain);
		manager.applyChange(new AddAxiom(onto, DEFINITIONaxiomMain));

		GetMAPsTermsDataFromAPI getTermsDataFromAPI = new GetMAPsTermsDataFromAPI();

		RootMAP termsList = getTermsDataFromAPI.getTerms();
		for (ContentMAP tr : termsList.getContent()) {
			OWLClass owlClass = null;
			String about = "http://data.esante.gouv.fr/coe/standardterms/" + tr.getCode();
			owlClass = fact.getOWLClass(IRI.create(about));
			OWLAxiom declare = fact.getOWLDeclarationAxiom(owlClass);
			manager.applyChange(new AddAxiom(onto, declare));
			
			OWLAnnotationProperty classKind = new OWLAnnotationPropertyImpl(DCVocabulary.type.getIRI());
			OWLAnnotation annotclassKind = fact.getOWLAnnotation(classKind, fact.getOWLLiteral("terms"));
			OWLAxiom axiomclassKind = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotclassKind);
			manager.applyChange(new AddAxiom(onto, axiomclassKind));

			String claz = "http://data.esante.gouv.fr/coe/standardterms/" + tr.getMyclass();
			OWLAnnotationProperty clazze = new OWLAnnotationPropertyImpl(EDQMVocabulary.clazz.getIRI());
			OWLAnnotation clazzannot = fact.getOWLAnnotation(clazze, IRI.create(claz));
			OWLAxiom clazzaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), clazzannot);
			manager.applyChange(new AddAxiom(onto, clazzaxiom));

			OWLSubClassOfAxiom ax1 = fact.getOWLSubClassOfAxiom(owlClass, owlClassMain);
			manager.applyChange(new AddAxiom(onto, ax1));

			String dom = Main.domainList.get(tr.getDomain());
			OWLAnnotationProperty domain = new OWLAnnotationPropertyImpl(EDQMVocabulary.domain.getIRI());
			String uri = "http://data.esante.gouv.fr/coe/standardterms/" + dom;
			OWLAnnotation domainannot = fact.getOWLAnnotation(domain, IRI.create(uri));
			OWLAxiom domainaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), domainannot);
			manager.applyChange(new AddAxiom(onto, domainaxiom));

			OWLAnnotation annot = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral(tr.getTerm(), "en"));
			OWLAxiom axiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot);
			manager.applyChange(new AddAxiom(onto, axiom));
			
			
			
			if(!tr.getComment().isEmpty()) {
				OWLAnnotation annot2 = fact.getOWLAnnotation(fact.getRDFSComment(),
						fact.getOWLLiteral(tr.getComment(), "en"));
				OWLAxiom axiom2 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot2);
				manager.applyChange(new AddAxiom(onto, axiom2));
			}
			
			if(null != tr.getStatus()) {
				OWLAnnotationProperty status = new OWLAnnotationPropertyImpl(ADMSVocabulary.status.getIRI());
				OWLAnnotation annot1 = fact.getOWLAnnotation(status, fact.getOWLLiteral(tr.getStatus()));
				OWLAxiom axiom1 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot1);
				manager.applyChange(new AddAxiom(onto, axiom1));
			}
			
			OWLAnnotation annotVersion = fact.getOWLAnnotation(fact.getOWLVersionInfo(),
					fact.getOWLLiteral(tr.getVersion()));
			OWLAxiom axiomVersion = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotVersion);
			manager.applyChange(new AddAxiom(onto, axiomVersion));

			OWLAnnotationProperty classCode = new OWLAnnotationPropertyImpl(SkosVocabulary.notation.getIRI());
			OWLAnnotation annot1 = fact.getOWLAnnotation(classCode, fact.getOWLLiteral(tr.getCode()));
			OWLAxiom axiom1 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot1);
			manager.applyChange(new AddAxiom(onto, axiom1));
			
			if(!tr.getComment().isEmpty()) {
			OWLAnnotationProperty DEFINITION = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
			OWLAnnotation DEFINITIONannot = fact.getOWLAnnotation(DEFINITION,
					fact.getOWLLiteral(tr.getDefinition(), "en"));
			OWLAxiom DEFINITIONaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), DEFINITIONannot);
			manager.applyChange(new AddAxiom(onto, DEFINITIONaxiom));
			}
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
			
			
			
			if(null != tr.getMapping()) {
				if(null != tr.getMapping().getpDF()) {
					for(CLASS cl : tr.getMapping().getpDF()) {
							OWLAnnotationProperty mapping = new OWLAnnotationPropertyImpl(EDQMVocabulary.mapping.getIRI());
							String rempl = "http://data.esante.gouv.fr/coe/standardterms/" + cl.code;
							OWLAnnotation mappingannot = fact.getOWLAnnotation(mapping, IRI.create(rempl));
							OWLAxiom mappingaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), mappingannot);
							manager.applyChange(new AddAxiom(onto, mappingaxiom));
					
					}
				}
				
				if(null != tr.getMapping().getrOA()) {
					for(CLASS cl : tr.getMapping().getrOA()) {
							OWLAnnotationProperty mapping = new OWLAnnotationPropertyImpl(EDQMVocabulary.mapping.getIRI());
							String rempl = "http://data.esante.gouv.fr/coe/standardterms/" + cl.code;
							OWLAnnotation mappingannot = fact.getOWLAnnotation(mapping, IRI.create(rempl));
							OWLAxiom mappingaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), mappingannot);
							manager.applyChange(new AddAxiom(onto, mappingaxiom));
						}
				}
				
			}
			
		}
	}

}
