package com.maxrzhe.kobweb.components

import androidx.compose.runtime.Composable
import com.maxrzhe.kobweb.models.Section
import com.maxrzhe.kobweb.models.Theme
import com.maxrzhe.kobweb.styles.LogoStyle
import com.maxrzhe.kobweb.styles.NavigationItemStyle
import com.maxrzhe.kobweb.util.Constants
import com.maxrzhe.kobweb.util.Constants.FONT_FAMILY
import com.maxrzhe.kobweb.util.Res
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun Header() {
  val breakpoint = rememberBreakpoint()
  Row(
    modifier = Modifier
      .fillMaxWidth(if (breakpoint > Breakpoint.MD) 80.percent else 90.percent)
      .margin(topBottom = 50.px),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    LeftSide(breakpoint = breakpoint)
    if (breakpoint > Breakpoint.MD) {
      RightSide()
    }
  }
}

@Composable
fun LeftSide(breakpoint: Breakpoint) {
  Row(
    modifier = Modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {

    if (breakpoint <= Breakpoint.MD) {
      FaBars(
        modifier = Modifier
          .margin(right = 15.px),
        size = IconSize.XL
      )
    }
    Image(
      modifier = LogoStyle.toModifier(),
      src = Res.Image.logo,
      desc = "Logo Image"
    )
  }
}

@Composable
fun RightSide() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .borderRadius(r = 50.px)
      .backgroundColor(color = Theme.LighterGray.rgb)
      .padding(all = 20.px),
    horizontalArrangement = Arrangement.End
  ) {
    Section.values().take(6).forEach { section ->
      Link(
        modifier = NavigationItemStyle
          .toModifier()
          .padding(right = 30.px)
          .fontFamily(FONT_FAMILY)
          .fontSize(18.px)
          .fontWeight(FontWeight.Normal)
          .textDecorationLine(TextDecorationLine.None),
        path = section.path,
        text = section.title
      )
    }
  }
}