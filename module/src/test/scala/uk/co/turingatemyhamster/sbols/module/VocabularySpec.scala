package uk.co.turingatemyhamster.sbols.module

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class VocabularySpec extends Specification {

  "Vocabulary" should {
    "have a non-null value for modulePrefix_value" in {
      Vocabulary.modulePrefix_value must not be null
    }

    "have a non-null value for moduleNamespace_uri" in {
      Vocabulary.moduleNamespace_uri must not be null
    }

    // Module
    "have a non-null value for module.type_uri" in {
      Vocabulary.module.type_uri must not be null
    }

    "have a valid documented.name_uri" in {
      Vocabulary.module.type_uri.toString must not endWith "#"
    }

    "have a non-null value for module.signal_uri" in {
      Vocabulary.module.signal_uri must not be null
    }

    "have a valid documented.signal_uri" in {
      Vocabulary.module.signal_uri.toString must not endWith "#"
    }

    "have a non-null value for module.subModule_uri" in {
      Vocabulary.module.subModule_uri must not be null
    }

    "have a valid documented.subModule_uri" in {
      Vocabulary.module.subModule_uri.toString must not endWith "#"
    }

    "have a non-null value for module.interaction_uri" in {
      Vocabulary.module.interaction_uri must not be null
    }

    "have a valid documented.interaction_uri" in {
      Vocabulary.module.interaction_uri.toString must not endWith "#"
    }

    "have a non-null value for module.port_uri" in {
      Vocabulary.module.port_uri must not be null
    }

    "have a valid documented.port_uri" in {
      Vocabulary.module.port_uri.toString must not endWith "#"
    }

    "have a non-null value for module.context_uri" in {
      Vocabulary.module.context_uri must not be null
    }

    "have a valid documented.context_uri" in {
      Vocabulary.module.context_uri.toString must not endWith "#"
    }

    "have a non-null value for module.model_uri" in {
      Vocabulary.module.model_uri must not be null
    }

    "have a valid documented.model_uri" in {
      Vocabulary.module.model_uri.toString must not endWith "#"
    }

    // signal
    "have a non-null value for signal.type_uri" in {
      Vocabulary.signal.type_uri must not be null
    }

    "have a valid signal.type_uri" in {
      Vocabulary.signal.type_uri.toString must not endWith "#"
    }

    // subModule
    "have a non-null value for subModule.type_uri" in {
      Vocabulary.subModule.type_uri must not be null
    }

    "have a valid subModule.type_uri" in {
      Vocabulary.subModule.type_uri.toString must not endWith "#"
    }

    // interaction
    "have a non-null value for interaction.type_uri" in {
      Vocabulary.interaction.type_uri must not be null
    }

    "have a valid interaction.type_uri" in {
      Vocabulary.interaction.type_uri.toString must not endWith "#"
    }

    "have a non-null value for interaction.interactionType_uri" in {
      Vocabulary.interaction.interactionType_uri must not be null
    }

    "have a valid interaction.interactionType_uri" in {
      Vocabulary.interaction.interactionType_uri.toString must not endWith "#"
    }

    "have a non-null value for interaction.participant_uri" in {
      Vocabulary.interaction.participation_uri must not be null
    }

    "have a valid interaction.participant_uri" in {
      Vocabulary.interaction.participation_uri.toString must not endWith "#"
    }

    // participation
    "have a non-null value for participation.type_uri" in {
      Vocabulary.participation.type_uri must not be null
    }

    "have a valid participation.type_uri" in {
      Vocabulary.participation.type_uri.toString must not endWith "#"
    }

    "have a non-null value for participation.role_uri" in {
      Vocabulary.participation.role_uri must not be null
    }

    "have a valid interaction.participant_uri" in {
      Vocabulary.participation.role_uri.toString must not endWith "#"
    }

    "have a non-null value for participation.participant_uri" in {
      Vocabulary.participation.participant_uri must not be null
    }

    "have a valid participation.participant_uri" in {
      Vocabulary.participation.participant_uri.toString must not endWith "#"
    }
  }
}
