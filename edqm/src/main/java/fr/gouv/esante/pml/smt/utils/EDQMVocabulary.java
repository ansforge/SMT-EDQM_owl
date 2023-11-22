package fr.gouv.esante.pml.smt.utils;

import static org.semanticweb.owlapi.model.EntityType.ANNOTATION_PROPERTY;
import static org.semanticweb.owlapi.model.EntityType.CLASS;
import static org.semanticweb.owlapi.model.EntityType.DATA_PROPERTY;
import static org.semanticweb.owlapi.model.EntityType.OBJECT_PROPERTY;
import static org.semanticweb.owlapi.util.OWLAPIStreamUtils.asSet;
import java.util.Set;
import java.util.stream.Stream;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.HasIRI;
import org.semanticweb.owlapi.model.HasPrefixedName;
import org.semanticweb.owlapi.model.HasShortForm;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.providers.AnnotationPropertyProvider;
import org.semanticweb.owlapi.vocab.Namespaces;


public enum EDQMVocabulary implements HasShortForm, HasIRI, HasPrefixedName {
//@formatter:off
    /** domain.         */ domain            ("domain",            ANNOTATION_PROPERTY),
    /** class.         */ clazz            ("class",            ANNOTATION_PROPERTY),
    /** Has_Pharmaceutical_Transformation.  */ Has_Pharmaceutical_Transformation          ("Has_Pharmaceutical_Transformation",            ANNOTATION_PROPERTY),
    /** replacement.      */ replacement          ("replacement",            ANNOTATION_PROPERTY),
    /** mapping.      */ mapping          ("mapping",            ANNOTATION_PROPERTY),
    /** Has_Pharmaceutical_State_Of_Matter.      */ Has_Pharmaceutical_State_Of_Matter          ("Has_Pharmaceutical_State_Of_Matter",            ANNOTATION_PROPERTY),
    /** Has_Pharmaceutical_Release_Characteristics.      */ Has_Pharmaceutical_Release_Characteristics          ("Has_Pharmaceutical_Release_Characteristics",            ANNOTATION_PROPERTY),
    /** Has_Pharmaceutical_Intended_Site.      */ Has_Pharmaceutical_Intended_Site          ("Has_Pharmaceutical_Intended_Site",            ANNOTATION_PROPERTY),
    /** Has_Pharmaceutical_Basic_Dose_Form.      */ Has_Pharmaceutical_Basic_Dose_Form          ("Has_Pharmaceutical_Basic_Dose_Form",            ANNOTATION_PROPERTY),
    /** Has_Pharmaceutical_Administration_Method.      */ Has_Pharmaceutical_Administration_Method          ("Has_Pharmaceutical_Administration_Method",            ANNOTATION_PROPERTY),
    /** Linked_Pharmaceutical_Dose_Forms.      */ Linked_Pharmaceutical_Dose_Forms          ("Linked_Pharmaceutical_Dose_Forms",            ANNOTATION_PROPERTY),
    /** Linked_Patient_Friendly_Terms.      */ Linked_Patient_Friendly_Terms          ("Linked_Patient_Friendly_Terms",            ANNOTATION_PROPERTY),
    /** Linked_Combined_Pharmaceutical_Dose_Form.      */ Linked_Combined_Pharmaceutical_Dose_Form          ("Linked_Combined_Pharmaceutical_Dose_Form",            ANNOTATION_PROPERTY),
    /** Linked_Combined_Term.      */ Linked_Combined_Term          ("Linked_Combined_Term",            ANNOTATION_PROPERTY),
    /** Linked_Combination_Pack.      */ Linked_Combination_Pack          ("Linked_Combination_Pack",            ANNOTATION_PROPERTY),
    /** Linked_Packaging_Category      */ Linked_Packaging_Category          ("Linked_Packaging_Category",            ANNOTATION_PROPERTY),
    /** Linked_Container      */ Linked_Container          ("Linked_Container",            ANNOTATION_PROPERTY),
    /** Linked_Closure      */ Linked_Closure          ("Linked_Closure",            ANNOTATION_PROPERTY),
    /** Linked_Administration_Device      */ Linked_Administration_Device          ("Linked_Administration_Device",            ANNOTATION_PROPERTY),
    

    /** @deprecated No longer used */
    DOCUMENT("Document", CLASS),
    /** @deprecated No longer used */
    IMAGE("Image", CLASS),
    /** @deprecated No longer used */
    COLLECTABLEPROPERTY("CollectableProperty", ANNOTATION_PROPERTY),
    /** @deprecated No longer used */
    RESOURCE("Resource", CLASS),
    /** @deprecated No longer used */
    COMMENT("comment", ANNOTATION_PROPERTY);
//@formatter:on
  /**
   * All IRIs.
   */
  public static final Set<IRI> ALL_IRIS = asSet(stream().map(v -> v.getIRI()));
  private final String localName;
  private final IRI iri;
  private final EntityType<?> entityType;
  private final String prefixedName;

  EDQMVocabulary(final String localname, final EntityType<?> entityType) {
    this.localName = localname;
    this.prefixedName = NameSpace.STANDARDTERMS.getPrefixName() + ':' + localname;
    this.entityType = entityType;
    this.iri = IRI.create(NameSpace.STANDARDTERMS.toString(), localname);
  }

  private static Stream<EDQMVocabulary> stream() {
    return Stream.of(values());
  }

  /**
   * @param dataFactory data factory to use
   * @return set of SCHEMA annotation properties
   */
  public static Set<OWLAnnotationProperty> getAnnotationProperties(
      final AnnotationPropertyProvider dataFactory) {
    return asSet(stream().filter(v -> v.entityType.equals(ANNOTATION_PROPERTY))
        .map(v -> dataFactory.getOWLAnnotationProperty(v.iri)));
  }

  /**
   * @param dataFactory data factory to use
   * @return set of SCHEMA object properties
   */
  public static Set<OWLObjectProperty> getObjectProperties(final OWLDataFactory dataFactory) {
    return asSet(stream().filter(v -> v.entityType.equals(OBJECT_PROPERTY))
        .map(v -> dataFactory.getOWLObjectProperty(v.iri)));
  }

  /**
   * @param dataFactory data factory to use
   * @return set of SCHEMA data properties
   */
  public static Set<OWLDataProperty> getDataProperties(final OWLDataFactory dataFactory) {
    return asSet(stream().filter(v -> v.entityType.equals(DATA_PROPERTY))
        .map(v -> dataFactory.getOWLDataProperty(v.iri)));
  }

  /**
   * @param dataFactory data factory to use
   * @return set of SCHEMA classes
   */
  public static Set<OWLClass> getClasses(final OWLDataFactory dataFactory) {
    return asSet(
        stream().filter(v -> v.entityType.equals(CLASS)).map(v -> dataFactory.getOWLClass(v.iri)));
  }

  /**
   * @return entity type
   */
  public EntityType<?> getEntityType() {
    return this.entityType;
  }

  /**
   * @return local name
   */
  public String getLocalName() {
    return this.localName;
  }

  @Override
  public IRI getIRI() {
    return this.iri;
  }

  @Override
  public String getShortForm() {
    return this.localName;
  }

  @Override
  public String getPrefixedName() {
    return this.prefixedName;
  }
}
