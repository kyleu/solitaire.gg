package menu

import navigation.{NavigationService, NavigationState}
import org.scalajs.jquery.{jQuery => $}
import settings.SettingsService
import utils.TemplateUtils

class MenuService(settings: SettingsService, navigation: NavigationService) {
  private[this] var priorState: NavigationState = NavigationState.Loading

  private[this] val title = $("#nav-title")
  if (title.length != 1) {
    throw new IllegalStateException(s"Found [${title.length}] menu titles.")
  }
  TemplateUtils.clickHandler(title, _ => toggleMenu())

  private[this] val toggle = $("#menu-toggle")
  if (toggle.length != 1) {
    throw new IllegalStateException(s"Found [${toggle.length}] menu toggles.")
  }
  TemplateUtils.clickHandler(toggle, _ => toggleMenu())

  NavigationState.values.foreach { s =>
    TemplateUtils.clickHandler($(s"#menu-link-$s"), _ => navigation.navigate(s))
  }

  def toggleMenu() = if (navigation.getState == NavigationState.Home) {
    navigation.navigate(priorState)
  } else {
    priorState = navigation.getState
    navigation.navigate(NavigationState.Home)
  }
}
