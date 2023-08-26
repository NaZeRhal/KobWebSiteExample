package com.maxrzhe.kobweb.sections

import androidx.compose.runtime.Composable
import com.maxrzhe.kobweb.components.Header
import com.maxrzhe.kobweb.components.SocialBar
import com.maxrzhe.kobweb.models.Section
import com.maxrzhe.kobweb.models.Theme
import com.maxrzhe.kobweb.styles.MainButtonStyle
import com.maxrzhe.kobweb.styles.MainImageStyle
import com.maxrzhe.kobweb.util.Constants
import com.maxrzhe.kobweb.util.Constants.FONT_FAMILY
import com.maxrzhe.kobweb.util.Constants.SECTION_WIDTH
import com.maxrzhe.kobweb.util.Res
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toAttrs
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun MainSection() {
  val breakpoint = rememberBreakpoint()
  Box(
    modifier = Modifier
      .id(Section.Home.id)
      .maxWidth(SECTION_WIDTH.px),
    contentAlignment = Alignment.TopCenter
  ) {
    MainBackground()
    MainContent(breakpoint = breakpoint)
  }
}

@Composable
fun MainBackground() {
  Image(
    modifier = Modifier.fillMaxSize().objectFit(ObjectFit.Cover),
    src = Res.Image.background,
    desc = "Background image"
  )
}

@Composable
fun MainContent(breakpoint: Breakpoint) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Header()
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Bottom,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      SimpleGrid(
        modifier = Modifier.fillMaxWidth(
          if (breakpoint > Breakpoint.MD)
            80.percent
          else
            90.percent
        ),
        numColumns = numColumns(base = 1, md = 2),
      ) {
        MainText(breakpoint = breakpoint)
        MainImage()
      }
    }

  }
}

@Composable
fun MainText(breakpoint: Breakpoint) {
  Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    if (breakpoint > Breakpoint.MD) {
      SocialBar()
    }
    Column {
      P(
        attrs = Modifier
          .margin(topBottom = 0.px)
          .fontFamily(FONT_FAMILY)
          .fontSize(if (breakpoint > Breakpoint.LG) 45.px else 20.px)
          .fontWeight(FontWeight.Normal)
          .color(Theme.Primary.rgb)
          .toAttrs()
      ) {
        Text("Hello, I'm")
      }
      P(
        attrs = Modifier
          .margin(top = 20.px, bottom = 0.px)
          .fontFamily(FONT_FAMILY)
          .fontSize(if (breakpoint > Breakpoint.LG) 68.px else 40.px)
          .fontWeight(FontWeight.Bolder)
          .color(Theme.Secondary.rgb)
          .toAttrs()
      ) {
        Text("Jonathan Smith")
      }
      P(
        attrs = Modifier
          .margin(top = 10.px, bottom = 5.px)
          .fontFamily(FONT_FAMILY)
          .fontSize(20.px)
          .fontWeight(FontWeight.Bold)
          .color(Theme.Secondary.rgb)
          .toAttrs()
      ) {
        Text("Mobile & Web Developer/Designer")
      }
      P(
        attrs = Modifier
          .margin(bottom = 25.px)
          .maxWidth(400.px)
          .fontFamily(FONT_FAMILY)
          .fontSize(15.px)
          .fontStyle(FontStyle.Italic)
          .fontWeight(FontWeight.Normal)
          .color(Theme.Secondary.rgb)
          .toAttrs()
      ) {
        Text(Constants.LOREM_IPSUM_SHORTEST)
      }
      Button(
        attrs = MainButtonStyle.toModifier()
          .height(40.px)
          .border(width = 0.px)
          .borderRadius(5.px)
          .background(Theme.Primary.rgb)
          .color(Colors.White)
          .cursor(Cursor.Pointer)
          .toAttrs()
      ) {
        Link(
          modifier = Modifier.color(Colors.White).textDecorationLine(TextDecorationLine.None),
          path = Section.Contact.path
        ) {
          Text("Hire Me")
        }
      }
    }
  }
}

@Composable
fun MainImage() {
  Column(
    modifier = Modifier
      .fillMaxSize(80.percent)
      .fillMaxHeight(),
    verticalArrangement = Arrangement.Bottom
  ) {
    Image(
      modifier = MainImageStyle.toModifier().fillMaxWidth(),
      src = Res.Image.mainImage,
      desc = "Main Image"
    )
  }
}