/*
 * #%L
 * Over-the-air deployment webapp
 * %%
 * Copyright (C) 2012 SAP AG
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.sap.prd.mobile.ios.ota.webapp;

import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QREncoder
{

  private static final BarcodeFormat DEFAULT_BARCODE_FORMAT = BarcodeFormat.QR_CODE;
  private static final String DEFAULT_IMAGE_FORMAT = "PNG";
  private static final int DEFAULT_WIDTH = 400;
  private static final int DEFAULT_HEIGHT = 400;
  private static final MatrixToImageConfig DEFAULT_MATRIX_CONFIG = new MatrixToImageConfig(0xFF000000, 0x00FFFFFF);

  public static void encode(String contents, OutputStream stream) throws IOException, WriterException
  {
    encode(contents, stream, DEFAULT_MATRIX_CONFIG, null);
  }

  public static void encode(String contents, OutputStream stream, MatrixToImageConfig config, Dimension dimension)
        throws IOException, WriterException
  {
    MultiFormatWriter barcodeWriter = new MultiFormatWriter();
    BitMatrix matrix = barcodeWriter.encode(contents, DEFAULT_BARCODE_FORMAT,
          (dimension == null || dimension.width <= 0) ? DEFAULT_WIDTH : dimension.width,
          (dimension == null || dimension.height <= 0) ? DEFAULT_HEIGHT : dimension.height);
    MatrixToImageWriter.writeToStream(matrix, DEFAULT_IMAGE_FORMAT, stream,
          config != null ? config : DEFAULT_MATRIX_CONFIG);
  }
}
