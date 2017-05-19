package client

import navigation.{NavigationService, NavigationState}
import org.scalajs.jquery.{jQuery => $}
import utils.TemplateUtils

object HomePanel {
  private[this] var initialized = false

  def update(navigation: NavigationService) = {
    if (!initialized) {
      TemplateUtils.clickHandler($("#home-link-play"), _ => navigation.navigate(NavigationState.Play))
      TemplateUtils.clickHandler($("#home-link-games"), _ => navigation.navigate(NavigationState.Games))
      initialized = true
    }
  }
}
