package fr.gouv.esante.pml.smt.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.semanticweb.owlapi.vocab.SKOSVocabulary;

import fr.gouv.esante.pml.smt.Main;
import fr.gouv.esante.pml.smt.data.GetFullTermsDataFromAPI;
import fr.gouv.esante.pml.smt.model.CLASS;
import fr.gouv.esante.pml.smt.model.Content;
import fr.gouv.esante.pml.smt.model.Root;
import fr.gouv.esante.pml.smt.model.Translation;
import fr.gouv.esante.pml.smt.model.Us;
import fr.gouv.esante.pml.smt.utils.ADMSVocabulary;
import fr.gouv.esante.pml.smt.utils.DCTVocabulary;
import fr.gouv.esante.pml.smt.utils.DCVocabulary;
import fr.gouv.esante.pml.smt.utils.EDQMVocabulary;
import fr.gouv.esante.pml.smt.utils.SkosVocabulary;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationPropertyImpl;

public class CreateHierarchyTerminolgy {
	
	List<String> ignoredTerms = new ArrayList<String>(Arrays.asList("0004", "0005", "0006", "0007", "0008", "0009", "0010", "0011", "0012", "0013", "0014", "0015", "0017", "0018",
			"0019", "0020", "0038", "0039", "0040", "0041", "0042", "0043", "0044", "0045", "0046", "0047", "0111", "0112", "0113"));

	public void createHierarchyTerminolgy(OWLOntology onto, OWLOntologyManager manager, OWLDataFactory fact) throws Exception {
		String group1 = "ISI/PDF";
		String label1 = "Pharmaceutical Dose Forms by Intended Site";
		String def1 = "List of pharmaceutical dose forms, arranged according to their intended site of administration. Where a pharmaceutical dose form is associated with more than one intended site, it will appear under each of those headings.";
		getHierarchyByGroup(onto, manager, fact, group1, label1, def1);
		String group2 = "SOM/SOM";
		String label2 = "Pharmaceutical Dose Forms by State Of Matter";
		String def2 = "List of pharmaceutical dose forms, arranged according to their state of matter, and then their basic dose form.";
		getHierarchyByGroup(onto, manager, fact, group2, label2, def2);
		String group3 = "CDF";
		String label3 = "Combined Pharmaceutical Dose Forms";
		String def3 = "List of combined pharmaceutical dose forms, each of which represents a combination of two or more pharmaceutical dose forms that are intended to be reconstituted together to form a single administrable pharmaceutical dose form.";
		getHierarchyByClass(onto, manager, fact, group3, label3, def3);
		String group4 = "CMT";
		String label4 = "Combined Terms";
		String def4 = "List of combined terms, each of which is a combination of one or more pharmaceutical dose forms or combined pharmaceutical dose forms with one or more items of packaging.";
		getHierarchyByClass(onto, manager, fact, group4, label4, def4);
		String group5 = "ROA";
		String label5 = "Routes and Methods of Administration";
		String def5 = "List of routes and methods of administration.";
		getHierarchyByClass(onto, manager, fact, group5, label5, def5);
		String group6 = "PAC/PAC";
		String label6 = "Packaging";
		String def6 = "List of packaging terms, arranged according to their type of packaging.";
		getHierarchyByGroup(onto, manager, fact, group6, label6, def6);
		String group7 = "CMP";
		String label7 = "Combination Packs";
		String def7 = "List of combination packs, each of which represents a combination of two or more products that are packaged together but that are intended to be administered independently of each other.";
		getHierarchyByClass(onto, manager, fact, group7, label7, def7);
		String group8 = "ISI/PFT";
		String label8 = "Patient-Friendly Terms";
		String def8 = "List of patient-friendly terms, arranged according to their intended site of administration. These are for use only where authorised, and where labelling space on the immediate packaging is limited such that the full Standard Term cannot be used.";
		getHierarchyByGroup(onto, manager, fact, group8, label8, def8);
		String group9 = "UOP";
		String label9 = "Unit of Presentation";
		String def9 = "List of units of presentation.";
		getHierarchyByClass(onto, manager, fact, group9, label9, def9);

	}

	public void getHierarchyByGroup(OWLOntology onto, OWLOntologyManager manager, OWLDataFactory fact, String group,
			String label, String def) throws Exception {

		OWLClass owlClassRacine = null;
		String aboutRacine = "http://data.esante.gouv.fr/coe/standardterms/terms";
		owlClassRacine = fact.getOWLClass(IRI.create(aboutRacine));
		OWLAxiom declareRacine = fact.getOWLDeclarationAxiom(owlClassRacine);
		manager.applyChange(new AddAxiom(onto, declareRacine));
		
		String supEDQMRacine = "http://data.esante.gouv.fr/coe/standardterms";
		OWLSubClassOfAxiom axRacine = fact.getOWLSubClassOfAxiom(owlClassRacine, fact.getOWLClass(IRI.create(supEDQMRacine)));
		manager.applyChange(new AddAxiom(onto, axRacine));

		OWLAnnotation annotRacine = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral("Terms", "en"));
		OWLAxiom axiomRacine = fact.getOWLAnnotationAssertionAxiom(owlClassRacine.getIRI(), annotRacine);
		manager.applyChange(new AddAxiom(onto, axiomRacine));
		
		OWLClass owlClassMain = null;
		String aboutMain = "http://data.esante.gouv.fr/coe/standardterms/lists/" + group;
		owlClassMain = fact.getOWLClass(IRI.create(aboutMain));
		OWLAxiom declareMain = fact.getOWLDeclarationAxiom(owlClassMain);
		manager.applyChange(new AddAxiom(onto, declareMain));
		
		String supEDQM = "http://data.esante.gouv.fr/coe/standardterms/terms";
		OWLSubClassOfAxiom ax = fact.getOWLSubClassOfAxiom(owlClassMain, fact.getOWLClass(IRI.create(supEDQM)));
		manager.applyChange(new AddAxiom(onto, ax));

		OWLAnnotation annotMain = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral(label, "en"));
		OWLAxiom axiomMain = fact.getOWLAnnotationAssertionAxiom(owlClassMain.getIRI(), annotMain);
		manager.applyChange(new AddAxiom(onto, axiomMain));

		OWLAnnotationProperty DEFINITIONMain = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
		OWLAnnotation DEFINITIONannotMain = fact.getOWLAnnotation(DEFINITIONMain, fact.getOWLLiteral(def, "en"));
		OWLAxiom DEFINITIONaxiomMain = fact.getOWLAnnotationAssertionAxiom(owlClassMain.getIRI(), DEFINITIONannotMain);
		manager.applyChange(new AddAxiom(onto, DEFINITIONaxiomMain));

		GetFullTermsDataFromAPI getTermsDataFromAPI = new GetFullTermsDataFromAPI();
		Root termsList = getTermsDataFromAPI.getTermsGroup(group);
		for (Content tr : termsList.getContent()) {
			if (tr.getCode().length() == 4) {
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

				OWLSubClassOfAxiom ax1 = fact.getOWLSubClassOfAxiom(owlClass, owlClassMain);
				manager.applyChange(new AddAxiom(onto, ax1));

				String dom = Main.domainList.get(tr.getDomain());
				OWLAnnotationProperty domain = new OWLAnnotationPropertyImpl(EDQMVocabulary.domain.getIRI());
				String uri = "http://data.esante.gouv.fr/coe/standardterms/" + dom;
				OWLAnnotation domainannot = fact.getOWLAnnotation(domain, IRI.create(uri));
				OWLAxiom domainaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), domainannot);
				manager.applyChange(new AddAxiom(onto, domainaxiom));

				OWLAnnotation annot = fact.getOWLAnnotation(fact.getRDFSLabel(),
						fact.getOWLLiteral(tr.getEnglish(), "en"));
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

				OWLAnnotationProperty DEFINITION = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
				OWLAnnotation DEFINITIONannot = fact.getOWLAnnotation(DEFINITION,
						fact.getOWLLiteral(tr.getDefinition(), "en"));
				OWLAxiom DEFINITIONaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), DEFINITIONannot);
				manager.applyChange(new AddAxiom(onto, DEFINITIONaxiom));

				OWLAnnotationProperty dctIssued = fact.getOWLAnnotationProperty(DCTVocabulary.issued.getIRI());
				OWLAnnotation annotIssued = fact.getOWLAnnotation(dctIssued, fact.getOWLLiteral(tr.getCreation_date()));
				OWLAxiom IssuedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotIssued);
				manager.applyChange(new AddAxiom(onto, IssuedNaxiom));

				OWLAnnotationProperty modified = fact.getOWLAnnotationProperty(DCTVocabulary.modified.getIRI());
				OWLAnnotation annotmodified = fact.getOWLAnnotation(modified,
						fact.getOWLLiteral(tr.getModification_date()));
				OWLAxiom modifiedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotmodified);
				manager.applyChange(new AddAxiom(onto, modifiedNaxiom));

//				if (null != tr.getLinks())
//					for (CLASS cl : tr.getLinks().allLinks()) {
//						String lin = "http://data.esante.gouv.fr/coe/edqm/terms/" + cl.getCode();
//						
//						OWLSubClassOfAxiom owlSubClassOfAxiom = fact.getOWLSubClassOfAxiom(fact.getOWLClass(IRI.create(lin)), owlClass);
//						manager.applyChange(new AddAxiom(onto, owlSubClassOfAxiom));
//					}

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
			} else {
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

				String dom = Main.domainList.get(tr.getDomain());
				OWLAnnotationProperty domain = new OWLAnnotationPropertyImpl(EDQMVocabulary.domain.getIRI());
				String uri = "http://data.esante.gouv.fr/coe/standardterms/" + dom;
				OWLAnnotation domainannot = fact.getOWLAnnotation(domain, IRI.create(uri));
				OWLAxiom domainaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), domainannot);
				manager.applyChange(new AddAxiom(onto, domainaxiom));

				OWLAnnotation annot = fact.getOWLAnnotation(fact.getRDFSLabel(),
						fact.getOWLLiteral(tr.getEnglish(), "en"));
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

				OWLAnnotationProperty DEFINITION = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
				OWLAnnotation DEFINITIONannot = fact.getOWLAnnotation(DEFINITION,
						fact.getOWLLiteral(tr.getDefinition(), "en"));
				OWLAxiom DEFINITIONaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), DEFINITIONannot);
				manager.applyChange(new AddAxiom(onto, DEFINITIONaxiom));

				OWLAnnotationProperty dctIssued = fact.getOWLAnnotationProperty(DCTVocabulary.issued.getIRI());
				OWLAnnotation annotIssued = fact.getOWLAnnotation(dctIssued, fact.getOWLLiteral(tr.getCreation_date()));
				OWLAxiom IssuedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotIssued);
				manager.applyChange(new AddAxiom(onto, IssuedNaxiom));

				OWLAnnotationProperty modified = fact.getOWLAnnotationProperty(DCTVocabulary.modified.getIRI());
				OWLAnnotation annotmodified = fact.getOWLAnnotation(modified,
						fact.getOWLLiteral(tr.getModification_date()));
				OWLAxiom modifiedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotmodified);
				manager.applyChange(new AddAxiom(onto, modifiedNaxiom));

//				if (null != tr.getLinks())
//					for (CLASS cl : tr.getLinks().allLinks()) {
//						if(cl.getCode().length() == 4) {
//							if(!ignoredTerms.contains(cl.getCode())) {
//								String lin = "http://data.esante.gouv.fr/coe/edqm/terms/" + cl.getCode();
//								OWLSubClassOfAxiom owlSubClassOfAxiom = fact.getOWLSubClassOfAxiom(owlClass, fact.getOWLClass(IRI.create(lin)));
//								manager.applyChange(new AddAxiom(onto, owlSubClassOfAxiom));
//							}
//						}else {
//							OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.links.getIRI());
//							String lin = "http://data.esante.gouv.fr/coe/edqm/terms/" + cl.getCode();
//							OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
//							OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
//							manager.applyChange(new AddAxiom(onto, linksaxiom));
//						}
//					}

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
			}
		}
	}

	public void getHierarchyByClass(OWLOntology onto, OWLOntologyManager manager, OWLDataFactory fact, String clazz,
			String label, String def) throws Exception {

		OWLClass owlClassRacine = null;
		String aboutRacine = "http://data.esante.gouv.fr/coe/standardterms/terms";
		owlClassRacine = fact.getOWLClass(IRI.create(aboutRacine));
		OWLAxiom declareRacine = fact.getOWLDeclarationAxiom(owlClassRacine);
		manager.applyChange(new AddAxiom(onto, declareRacine));
		
		String supEDQMRacine = "http://data.esante.gouv.fr/coe/standardterms";
		OWLSubClassOfAxiom axRacine = fact.getOWLSubClassOfAxiom(owlClassRacine, fact.getOWLClass(IRI.create(supEDQMRacine)));
		manager.applyChange(new AddAxiom(onto, axRacine));

		OWLAnnotation annotRacine = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral("Terms", "en"));
		OWLAxiom axiomRacine = fact.getOWLAnnotationAssertionAxiom(owlClassRacine.getIRI(), annotRacine);
		manager.applyChange(new AddAxiom(onto, axiomRacine));
		
		OWLClass owlClassMain = null;
		String aboutMain = "http://data.esante.gouv.fr/coe/standardterms/lists/" + clazz;
		owlClassMain = fact.getOWLClass(IRI.create(aboutMain));
		OWLAxiom declareMain = fact.getOWLDeclarationAxiom(owlClassMain);
		manager.applyChange(new AddAxiom(onto, declareMain));
		
		String supEDQM = "http://data.esante.gouv.fr/coe/standardterms/terms";
		OWLSubClassOfAxiom ax = fact.getOWLSubClassOfAxiom(owlClassMain, fact.getOWLClass(IRI.create(supEDQM)));
		manager.applyChange(new AddAxiom(onto, ax));

		OWLAnnotation annotMain = fact.getOWLAnnotation(fact.getRDFSLabel(), fact.getOWLLiteral(label, "en"));
		OWLAxiom axiomMain = fact.getOWLAnnotationAssertionAxiom(owlClassMain.getIRI(), annotMain);
		manager.applyChange(new AddAxiom(onto, axiomMain));

		OWLAnnotationProperty DEFINITIONMain = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
		OWLAnnotation DEFINITIONannotMain = fact.getOWLAnnotation(DEFINITIONMain, fact.getOWLLiteral(def, "en"));
		OWLAxiom DEFINITIONaxiomMain = fact.getOWLAnnotationAssertionAxiom(owlClassMain.getIRI(), DEFINITIONannotMain);
		manager.applyChange(new AddAxiom(onto, DEFINITIONaxiomMain));

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

			OWLSubClassOfAxiom ax1 = fact.getOWLSubClassOfAxiom(owlClass, owlClassMain);
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

			OWLAnnotationProperty DEFINITION = new OWLAnnotationPropertyImpl(SKOSVocabulary.DEFINITION.getIRI());
			OWLAnnotation DEFINITIONannot = fact.getOWLAnnotation(DEFINITION,
					fact.getOWLLiteral(tr.getDefinition(), "en"));
			OWLAxiom DEFINITIONaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), DEFINITIONannot);
			manager.applyChange(new AddAxiom(onto, DEFINITIONaxiom));

			OWLAnnotationProperty dctIssued = fact.getOWLAnnotationProperty(DCTVocabulary.issued.getIRI());
			OWLAnnotation annotIssued = fact.getOWLAnnotation(dctIssued, fact.getOWLLiteral(tr.getCreation_date()));
			OWLAxiom IssuedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotIssued);
			manager.applyChange(new AddAxiom(onto, IssuedNaxiom));

			OWLAnnotationProperty modified = fact.getOWLAnnotationProperty(DCTVocabulary.modified.getIRI());
			OWLAnnotation annotmodified = fact.getOWLAnnotation(modified,
					fact.getOWLLiteral(tr.getModification_date()));
			OWLAxiom modifiedNaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), annotmodified);
			manager.applyChange(new AddAxiom(onto, modifiedNaxiom));

//			if (null != tr.getLinks())
//				for (CLASS cl : tr.getLinks().allLinks()) {
//					if(cl.getCode().length() == 4) {
//					String lin = "http://data.esante.gouv.fr/coe/edqm/terms/" + cl.getCode();
//					OWLSubClassOfAxiom owlSubClassOfAxiom = fact.getOWLSubClassOfAxiom(owlClass, fact.getOWLClass(IRI.create(lin)));
//					manager.applyChange(new AddAxiom(onto, owlSubClassOfAxiom));
//					}else {
//						OWLAnnotationProperty links = new OWLAnnotationPropertyImpl(EDQMVocabulary.links.getIRI());
//						String lin = "http://data.esante.gouv.fr/coe/edqm/terms/" + cl.getCode();
//						OWLAnnotation linksannot = fact.getOWLAnnotation(links, IRI.create(lin));
//						OWLAxiom linksaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(), linksannot);
//						manager.applyChange(new AddAxiom(onto, linksaxiom));
//					}
//				}

//			if (null != tr.getReplacement())
//				for (CLASS cl : tr.getReplacement().allReplacement()) {
//					OWLAnnotationProperty replacement = new OWLAnnotationPropertyImpl(
//							EDQMVocabulary.replacement.getIRI());
//					String rempl = "http://data.esante.gouv.fr/coe/edqm/terms/" + cl.getCode();
//					OWLAnnotation replacementannot = fact.getOWLAnnotation(replacement, IRI.create(rempl));
//					OWLAxiom replacementaxiom = fact.getOWLAnnotationAssertionAxiom(owlClass.getIRI(),
//							replacementannot);
//					manager.applyChange(new AddAxiom(onto, replacementaxiom));
//				}

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

		}
	}

}
