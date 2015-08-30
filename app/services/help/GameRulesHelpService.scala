package services.help

import models.rules._
import play.api.i18n.Messages

object GameRulesHelpService {
  private[this] val descriptionLinkPattern = """\^([a-z0-9]+)\^""".r

  def description(desc: String, link: Boolean = true) = {
    val links = descriptionLinkPattern.findAllIn(desc).matchData.map(_.group(1))
    val linked = links.foldLeft(desc) { (desc, id) =>
      val rules = GameRulesSet.allByIdWithAliases(id)
      if (link) {
        val url = controllers.routes.GameController.help(rules.id).url
        desc.replaceAllLiterally(s"^$id^", s"""<a class="help-link" href="$url">${rules.title}</a>""")
      } else {
        desc.replaceAllLiterally(s"^$id^", rules.title)
      }
    }
    linked
  }

  def layout(rules: GameRules)(implicit messages: Messages) = {
    var foundationsProcessed = 0
    var tableausProcessed = 0
    var pyramidsProcessed = 0

    rules.layout.flatMap {
      case 's' => rules.stock.map(StockHelpService.stock)
      case 'w' => rules.waste.map(WasteHelpService.waste)
      case 'r' => rules.reserves.map(ReserveHelpService.reserve)
      case 'c' => rules.cells.map(CellHelpService.cell)
      case 'f' =>
        val fr = rules.foundations(foundationsProcessed)
        foundationsProcessed += 1
        if (fr.visible) {
          Some(FoundationHelpService.foundation(fr, rules.deckOptions))
        } else {
          None
        }
      case 't' =>
        val tr = rules.tableaus(tableausProcessed)
        tableausProcessed += 1
        Some(TableauHelpService.tableau(tr, rules.deckOptions))
      case 'p' =>
        val pr = rules.pyramids(pyramidsProcessed)
        pyramidsProcessed += 1
        Some(PyramidHelpService.pyramid(pr, rules.deckOptions))
      case x if x == '2' || x == '3' || x == '4' =>
        None
      case x if x == '|' || x == ':' || x == '.' =>
        None
    }
  }
}
