package uk.co.turingatemyhamster.sbols.module

import java.util.Properties
import uk.co.turingatemyhamster.sbols.core.VocabularyBase

/**
 *
 *
 * @author Matthew Pocock
 */
object Vocabulary extends Vocabulary({
  val props = new Properties()
  props.load(classOf[Vocabulary].getResourceAsStream("Vocabulary.properties"))
  props
})

class Vocabulary(props: Properties) extends VocabularyBase(props) {
  val modulePrefix = "modulePrefix"
  val modulePrefix_value = lookupRaw(modulePrefix)

  val moduleNamespace = "moduleNamespace"
  val moduleNamespace_uri = lookup(moduleNamespace)

  object module {
    val `type` = "Module.type"
    val type_uri = lookup(`type`)

    val signal = "Module.signal"
    val signal_uri = lookup(signal)

    val subModule = "Module.subModule"
    val subModule_uri = lookup(subModule)

    val interaction = "Module.interaction"
    val interaction_uri = lookup(interaction)

    val port = "Module.port"
    val port_uri = lookup(port)

    val context = "Module.context"
    val context_uri = lookup(context)

    val model = "Module.model"
    val model_uri = lookup(model)
  }

  object signal {
    val `type` = "Signal.type"
    val type_uri = lookup(`type`)
  }

  object subModule {
    val `type` = "SubModule.type"
    val type_uri = lookup(`type`)
  }
  
  object interaction {
    val `type` = "Interaction.type"
    val type_uri = lookup(`type`)
    
    val participation = "Interaction.participation"
    val participation_uri = lookup(participation)
  }
  
  object participation {
    val `type` = "Participation.type"
    val type_uri = lookup(`type`)
    
    val role = "Participation.role"
    val role_uri = lookup(role)
    
    val participant = "Participation.participant"
    val participant_uri = lookup(participant)
  }
}