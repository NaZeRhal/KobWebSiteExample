package com.maxrzhe.kobweb.sections

import androidx.compose.runtime.*
import com.maxrzhe.kobweb.components.SectionTitle
import com.maxrzhe.kobweb.components.SkillBar
import com.maxrzhe.kobweb.models.Section
import com.maxrzhe.kobweb.models.Skill
import com.maxrzhe.kobweb.models.Theme
import com.maxrzhe.kobweb.styles.AboutImageStyle
import com.maxrzhe.kobweb.styles.AboutTextStyle
import com.maxrzhe.kobweb.util.Constants.FONT_FAMILY
import com.maxrzhe.kobweb.util.Constants.LOREM_IPSUM_SHORT
import com.maxrzhe.kobweb.util.Constants.SECTION_WIDTH
import com.maxrzhe.kobweb.util.ObserveViewportEntered
import com.maxrzhe.kobweb.util.Res
import com.maxrzhe.kobweb.util.animatePercentage
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun AboutMeSection() {

  Box(
    modifier = Modifier
      .id(Section.About.id)
      .maxWidth(SECTION_WIDTH.px)
      .padding(topBottom = 150.px),
    contentAlignment = Alignment.Center
  ) {

    AboutContent()
  }
}

@Composable
fun AboutContent() {
  val breakpoint = rememberBreakpoint()

  Column(
    modifier = Modifier
      .fillMaxWidth(if (breakpoint >= Breakpoint.MD) 100.percent else 90.percent)
      .maxWidth(1200.px),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    SimpleGrid(
      modifier = Modifier.fillMaxWidth(
        if (breakpoint >= Breakpoint.MD) 90.percent
        else 100.percent
      ),
      numColumns = numColumns(base = 1, md = 2)
    ) {
      if (breakpoint >= Breakpoint.MD) {
        AboutImage()
      }
      AboutTextAndSkills()
    }
  }
}

@Composable
fun AboutImage() {
  Box(
    modifier = Modifier.fillMaxWidth(),
    contentAlignment = Alignment.Center
  ) {
    Image(
      modifier = AboutImageStyle
        .toModifier()
        .fillMaxWidth(80.percent),
      src = Res.Image.aboutImage,
      desc = "About Image"
    )
  }
}

@Composable
fun AboutTextAndSkills() {
  val scope = rememberCoroutineScope()
  var viewportEntered by remember { mutableStateOf(false) }
  val skillPercentageList = remember { mutableStateListOf(0, 0, 0, 0, 0) }

  ObserveViewportEntered(
    sectionId = Section.About.id,
    distanceFromTop = 300.0
  ) {
    viewportEntered = true
    Skill.values().forEach { skill ->
      scope.launch {
        animatePercentage(
          percent = skill.percentage.value.toInt(),
          onUpdate = {
            skillPercentageList[skill.ordinal] = it
          }
        )
      }
    }
  }

  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.Center
  ) {
    SectionTitle(section = Section.About)
    P(
      attrs = AboutTextStyle
        .toModifier()
        .margin(topBottom = 25.px)
        .maxWidth(500.px)
        .fontFamily(FONT_FAMILY)
        .fontSize(18.px)
        .fontWeight(FontWeight.Normal)
        .color(Theme.Secondary.rgb)
        .fontStyle(FontStyle.Italic)
        .toAttrs()
    ) {
      Text(LOREM_IPSUM_SHORT)
    }
    Skill.values().forEach { skill ->
      SkillBar(
        index = skill.ordinal,
        name = skill.title,
        percentage = if (viewportEntered) skill.percentage else 0.percent,
        animatedPercentage = skillPercentageList[skill.ordinal]
      )
    }
  }
}