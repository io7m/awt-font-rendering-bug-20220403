/*
 * Copyright © 2022 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.bugs;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static java.awt.image.BufferedImage.TYPE_4BYTE_ABGR_PRE;

public final class Main
{
  private Main()
  {
    throw new IllegalStateException();
  }

  public static void main(
    final String[] args)
    throws Exception
  {
    final var font = loadFont();

    final var image =
      new BufferedImage(512, 512, TYPE_4BYTE_ABGR_PRE);

    final var graphics = image.createGraphics();
    graphics.setColor(Color.DARK_GRAY);
    graphics.fillRect(0, 0, 512, 512);
    graphics.setFont(font);
    graphics.setPaint(Color.WHITE);
    graphics.drawString("ひらがな", 64, 64);

    ImageIO.write(image, "PNG", new File("output.png"));
  }

  private static Font loadFont()
    throws IOException, FontFormatException
  {
    try (var stream =
           Main.class.getResourceAsStream("NotoSans-Regular.ttf")) {
      Objects.requireNonNull(stream, "stream");
      final var baseFont = Font.createFont(Font.TRUETYPE_FONT, stream);
      return baseFont.deriveFont(24.0f);
    }
  }
}