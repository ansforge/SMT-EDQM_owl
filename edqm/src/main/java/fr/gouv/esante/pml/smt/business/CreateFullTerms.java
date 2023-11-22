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
import fr.gouv.esante.pml.smt.data.GetTermsDataFromAPI;
import fr.gouv.esante.pml.smt.model.AME;
import fr.gouv.esante.pml.smt.model.BDF;
import fr.gouv.esante.pml.smt.model.CDF;
import fr.gouv.esante.pml.smt.model.CLASS;
import fr.gouv.esante.pml.smt.model.CLO;
import fr.gouv.esante.pml.smt.model.CMP;
import fr.gouv.esante.pml.smt.model.CMT;
import fr.gouv.esante.pml.smt.model.CONN;
import fr.gouv.esante.pml.smt.model.Content;
import fr.gouv.esante.pml.smt.model.DEV;
import fr.gouv.esante.pml.smt.model.Domain;
import fr.gouv.esante.pml.smt.model.DomainList;
import fr.gouv.esante.pml.smt.model.ISI;
import fr.gouv.esante.pml.smt.model.PAC;
import fr.gouv.esante.pml.smt.model.PDF;
import fr.gouv.esante.pml.smt.model.PFT;
import fr.gouv.esante.pml.smt.model.RCA;
import fr.gouv.esante.pml.smt.model.Root;
import fr.gouv.esante.pml.smt.model.SOM;
import fr.gouv.esante.pml.smt.model.TRA;
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

public class CreateFullTerms {

	public void creatTerms(OWLOntology onto, OWLOntologyManager manager, OWLDataFactory fact, String clazz)
			throws Exception {

		GetFullTermsDataFromAPI getTermsDataFromAPI = new GetFullTermsDataFromAPI();

		Root termsList = getTermsDataFromAPI.getTerms(clazz);
		for (Content tr : termsList.getContent()) {
			OWLClass owlClass = null;
			String about = "http://data.esante.gouv.fr/coe/standardterms/" + tr.getCode();
			owlClass = fact.getOWLClass(IRI.create(about));
			OWLAxiom declare = fact.getOWLDeclarationAxiom(owlClass);
			manager.applyChange(new AddAxiom(onto, declare));
			
			OWLAnnotationProperty classKind = new OWLAnnotationPropertyImpl(DCVocabulary.type.getIRI());
			OWLAnnotation annotclassKind = fact.getOWLAnnotation(classKind, fact.getOWLLiteral("terms"));
			OWLAxiom axiomclassKind = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotclassKind);
			manager.applyChange(new AddAxiom(onto, axiomclassKind));

			String claz = "http://data.esante.gouv.fr/coe/standardterms/" + tr.getClazz();
			OWLAnnotationProperty clazze = new OWLAnnotationPropertyImpl(EDQMVocabulary.clazz.getIRI());
			OWLAnnotation clazzannot = fact.getOWLAnnotation(clazze, IRI.create(claz));
			OWLAxiom clazzaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), clazzannot);
			manager.applyChange(new AddAxiom(onto, clazzaxiom));

			OWLSubClassOfAxiom ax1 = fact.getOWLSubClassOfAxiom(owlClass, fact.getOWLClass(IRI.create(claz)));
			manager.applyChange(new AddAxiom(onto, ax1));

			String dom = Main.domainList.get(tr.getDomain());
			OWLAnnotationProperty domain = new OWLAnnotationPropertyImpl(EDQMVocabulary.domain.getIRI());
			String uri = "http://data.esante.gouv.fr/coe/standardterms/" + dom;
			OWLAnnotation domainannot = fact.getOWLAnnotation(domain, IRI.create(uri));
			OWLAxiom domainaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), domainannot);
			manager.applyChange(new AddAxiom(onto, domainaxiom));

			OWLAnnotation annot = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral(tr.getEnglish(), "en"));
			OWLAxiom axiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot);
			manager.applyChange(new AddAxiom(onto, axiom));

			for (Translation tl : tr.getTranslations()) {
				if (tl.getLanguage().equals("fr") && !tl.getTerm().isEmpty() && !tl.getTerm().equals("-")) {
					OWLAnnotation annot2 = fact.getOWLAnnotation(fact.getRDFSLabel(),
							fact.getOWLLiteral(tl.getTerm(), "fr"));
					OWLAxiom axiom2 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot2);
					manager.applyChange(new AddAxiom(onto, axiom2));
				}
			}

			if (!tr.getComment().isEmpty()) {
				OWLAnnotation annot2 = fact.getOWLAnnotation(fact.getRDFSComment(),
						fact.getOWLLiteral(tr.getComment(), "en"));
				OWLAxiom axiom2 = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annot2);
				manager.applyChange(new AddAxiom(onto, axiom2));
			}

			if (null != tr.getStatus()) {
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
			
			if (!tr.getDefinition().isEmpty()) {
			OWLAnnotationProperty DEFINITION = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
			OWLAnnotation DEFINITIONannot = fact.getOWLAnnotation(DEFINITION,
					fact.getOWLLiteral(tr.getDefinition(), "en"));
			OWLAxiom DEFINITIONaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), DEFINITIONannot);
			manager.applyChange(new AddAxiom(onto, DEFINITIONaxiom));
			}

			OWLAnnotationProperty dctIssued = fact.getOWLAnnotationProperty(DCTVocabulary.issued.getIRI());
			OWLAnnotation annotIssued = fact.getOWLAnnotation(dctIssued, fact.getOWLLiteral(tr.getCreation_date()));
			OWLAxiom IssuedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotIssued);
			manager.applyChange(new AddAxiom(onto, IssuedNaxiom));

			OWLAnnotationProperty modified = fact.getOWLAnnotationProperty(DCTVocabulary.modified.getIRI());
			OWLAnnotation annotmodified = fact.getOWLAnnotation(modified,
					fact.getOWLLiteral(tr.getModification_date()));
			OWLAxiom modifiedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotmodified);
			manager.applyChange(new AddAxiom(onto, modifiedNaxiom));

			if (null != tr.getLinks()) {
				if (null != tr.getLinks().getaME()) {
					for (AME cl : tr.getLinks().getaME()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Has_Pharmaceutical_Administration_Method.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getbDF()) {
					for (BDF cl : tr.getLinks().getbDF()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Has_Pharmaceutical_Basic_Dose_Form.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
						
						Main.BasicDoseList.put(owlClass.getIRI().toString(), cl.getCode());
					}
				}
				if (null != tr.getLinks().getiSI()) {
					for (ISI cl : tr.getLinks().getiSI()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Has_Pharmaceutical_Intended_Site.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getpDF()) {
					for (PDF cl : tr.getLinks().getpDF()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Pharmaceutical_Dose_Forms.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getsOM()) {
					for (SOM cl : tr.getLinks().getsOM()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Has_Pharmaceutical_State_Of_Matter.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
						
						Main.StateOfMatter.put(tr.getCode(), cl.getCode());
					}
				}
				if (null != tr.getLinks().gettRA()) {
					for (TRA cl : tr.getLinks().gettRA()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Has_Pharmaceutical_Transformation.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getrCA()) {
					for (RCA cl : tr.getLinks().getrCA()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Has_Pharmaceutical_Release_Characteristics.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getpFT()) {
					for (PFT cl : tr.getLinks().getpFT()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Patient_Friendly_Terms.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getcMT()) {
					for (CMT cl : tr.getLinks().getcMT()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Combined_Term.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getcDF()) {
					for (CDF cl : tr.getLinks().getcDF()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Combined_Pharmaceutical_Dose_Form.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getcMP()) {
					for (CMP cl : tr.getLinks().getcMP()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Combination_Pack.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getpAC()) {
					for (PAC cl : tr.getLinks().getpAC()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Packaging_Category.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getcON()) {
					for (CONN cl : tr.getLinks().getcON()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Container.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getcLO()) {
					for (CLO cl : tr.getLinks().getcLO()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Closure.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
				if (null != tr.getLinks().getdEV()) {
					for (DEV cl : tr.getLinks().getdEV()) {
						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.Linked_Administration_Device.getIRI());
						String lin = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
						manager.applyChange(new AddAxiom(onto, linksaxiom));
					}
				}
			}

			if (null != tr.getReplacement())
				for (CLASS cl : tr.getReplacement().allReplacement()) {
					OWLAnnotationProperty replacement = new OWLAnnotationPropertyImpl(
							EDQMVocabulary.replacement.getIRI());
					String rempl = "http://data.esante.gouv.fr/coe/standardterms/" + cl.getCode();
					OWLAnnotation replacementannot = fact.getOWLAnnotation(replacement, IRI.create(rempl));
					OWLAxiom replacementaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(),
							replacementannot);
					manager.applyChange(new AddAxiom(onto, replacementaxiom));
				}

			if (null != tr.getMapping()) {
				if (null != tr.getMapping().iCH_E2B) {
					if (null != tr.getMapping().iCH_E2B.en) {
						for (Us us : tr.getMapping().iCH_E2B.en.us) {
							OWLAnnotationProperty mapping = new OWLAnnotationPropertyImpl(
									EDQMVocabulary.mapping.getIRI());
							String rempl = "http://data.esante.gouv.fr/coe/standardterms/" + us.code;
							OWLAnnotation mappingannot = fact.getOWLAnnotation(mapping, IRI.create(rempl));
							OWLAxiom mappingaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(),
									mappingannot);
							manager.applyChange(new AddAxiom(onto, mappingaxiom));
						}
					}
					if (null != tr.getMapping().iCH_E2B.ja) {
						for (Us us : tr.getMapping().iCH_E2B.ja.jp) {
							OWLAnnotationProperty mapping = new OWLAnnotationPropertyImpl(
									EDQMVocabulary.mapping.getIRI());
							String rempl = "http://data.esante.gouv.fr/coe/standardterms/" + us.code;
							OWLAnnotation mappingannot = fact.getOWLAnnotation(mapping, IRI.create(rempl));
							OWLAxiom mappingaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(),
									mappingannot);
							manager.applyChange(new AddAxiom(onto, mappingaxiom));
						}
					}
				}

				if (null != tr.getMapping().uSFDA_SPL) {
					if (null != tr.getMapping().uSFDA_SPL.en) {
						for (Us us : tr.getMapping().uSFDA_SPL.en.us) {
							OWLAnnotationProperty mapping = new OWLAnnotationPropertyImpl(
									EDQMVocabulary.mapping.getIRI());
							String rempl = "http://data.esante.gouv.fr/coe/standardterms/" + us.code;
							OWLAnnotation mappingannot = fact.getOWLAnnotation(mapping, IRI.create(rempl));
							OWLAxiom mappingaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(),
									mappingannot);
							manager.applyChange(new AddAxiom(onto, mappingaxiom));
						}
					}
					if (null != tr.getMapping().uSFDA_SPL.ja) {
						for (Us us : tr.getMapping().uSFDA_SPL.ja.jp) {
							OWLAnnotationProperty mapping = new OWLAnnotationPropertyImpl(
									EDQMVocabulary.mapping.getIRI());
							String rempl = "http://data.esante.gouv.fr/coe/standardterms/" + us.code;
							OWLAnnotation mappingannot = fact.getOWLAnnotation(mapping, IRI.create(rempl));
							OWLAxiom mappingaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(),
									mappingannot);
							manager.applyChange(new AddAxiom(onto, mappingaxiom));
						}
					}
				}

			}

//			System.out.println(tr.getMapping());
//			System.out.println(tr.getReplacement());
//			System.out.println(tr.getTags());
//			
//			Main.csvWriter.append(tr.getCode()).append("§").append(tr.getFr_fr()).append("§").append(tr.getEnglish()).append("§").append(tr.getDefinition()).append("§");
//			Main.csvWriter.append(tr.getClazz()).append("§").append(tr.getDomain()).append("§").append(tr.getCreation_date()).append("§").append(tr.getModification_date());
//			Main.csvWriter.append("\n");
		}
	}

}
