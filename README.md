# JAI-TEST
Java Advanced Imaging - some tests that I did in the past - 2005 - 2010


https://www.oracle.com/java/technologies/advanced-imaging-api.html


Image I/O in Java Advanced Imaging
Java Advanced Imaging API
Download
FAQ
Overview
An important adjunct to the Java Advanced Imaging (JAI) API is a set of image encoder/decoder (codec) classes, which have been packaged with JAI since the release of JAI 1.0. The encoders and decoders for several popular image storage formats have been implemented. The image formats supported by these ancillary codec classes are: BMP, GIF (decoder only), FlashPix (decoder only), JPEG, PNG, PNM, TIFF, and WBMP.

However, the actual codec classes are not a committed part of JAI, and they are released under the com.sun.media.jai.codec and com.sun.media.jai.codecimpl packages. This means the codec classes might change in the future at the discretion of the JAI developers. Software developers should take this into consideration when writing any code directly against these classes.

The preferred method for reading an image file of any format into a RenderedImage is:

Copy
Copied to ClipboardError: Could not Copy
String filename = "// path and name of the file to be read,
                          that is on an accessible filesystem //";

    RenderedImage image = JAI.create("fileload", filename);

    URL url = "// URL of the remote image to be read //";

    RenderedImage image = JAI.create("url", url);
String filename = "// path and name of the file to be read,
                          that is on an accessible filesystem //";

    RenderedImage image = JAI.create("fileload", filename);

    URL url = "// URL of the remote image to be read //";

    RenderedImage image = JAI.create("url", url);
RenderedImage

Copy
Copied to ClipboardError: Could not Copy
RenderedImage image = "// the image to be stored //";
    String filename = "// path and name of the file to be writen //";
    String format = "// the format of the file //";

    RenderedOp op = JAI.create("filestore", image,
                               filename, format);
RenderedImage image = "// the image to be stored //";
    String filename = "// path and name of the file to be writen //";
    String format = "// the format of the file //";

    RenderedOp op = JAI.create("filestore", image,
                               filename, format);
Many questions have been posted to the JAI mailing lists regarding each specific file format, and below are answers to some commonly raised issues. In addition, please read the JAI FAQ page for more answers to Image I/O related questions.

BMP

The Windows 95 version of BMP is supported. The bit depth of the encoded output is determined by that of the source image.

FlashPix

The decoder is only partially implemented. No encoder is available at this time.

GIF

The decoder supports animated GIF files and GIF files with transparent background. Only the first frame of an animated GIF file may be loaded via JAI; subsequent frames must be obtained via direct use of the ancillary codec classes.

JPEG

The JPEG support is currently implemented on top of the unofficial J2SE classes in the com.sun.image.codec.jpeg package, which may not exist in all Java 2 environments.

PNG

The PNG encoder automatically determines the type of the image to be encoded (RGB, Grayscale, or Palette) based on the source image. The PNGEncodeParam setting has no effect on this.

PNM

PNM files may either have ASCII or raw (binary) data. The decoder automatically determines the data format and reads the data accordingly. By default the encoder stores the image data in raw format whenever possible. To request that the encoder store the data in ASCII format in JAI 1.0.2 or later, use the following encoder parameter argument:

Copy
Copied to ClipboardError: Could not Copy
RenderedImage image = "// the image to be stored //";
    String filename = "// path and name of the file to be written //";
    String format = "// the format of the file //";

    PNMEncodeParam param = new PNMEncodeParam();
    param.setRaw(false);

    RenderedOp op = JAI.create("filestore", image, filename, format, param);
RenderedImage image = "// the image to be stored //";
    String filename = "// path and name of the file to be written //";
    String format = "// the format of the file //";

    PNMEncodeParam param = new PNMEncodeParam();
    param.setRaw(false);

    RenderedOp op = JAI.create("filestore", image, filename, format, param);
TIFF

In addition to the baseline specification, the encoder and decoder support PackBits, modified Huffman and CCITT bilevel encodings (fax), JPEG-in-TIFF (per TIFF Technical Note #2), and DEFLATE compression schemes, can handle images with 16- and 32-bit integral samples and 32-bit floating point samples, and can read and write tiled images of all supported data types. The decoder in addition can decompress LZW-compressed imagery.

Additional features may be addressed in the future.

A single page of a multi-page TIFF file may loaded most easily by using the page parameter with the "TIFF" operator which is documented in the class comments of javax.media.jai.operator.TIFFDescriptor. A code sample is included here to show a means of loading a single page of a multi-page TIFF file using the ancillary codec classes directly.

WBMP

The WBMP codec reads and writes images in the Wireless Bitmap format described in chapter 6 and Appendix A of the Wireless Application Protocol (WAP) Wireless Application Environment Specification, Version 1.3, 29 March 2000. The WBMP type supported is WBMP Type 0: B/W, Uncompressed Bitmap. There are no limitations on the image dimensions.

The Java Image I/O API
Due to the many requests for a comprehensive image I/O package the Java Image I/O API was developed. The Java Image I/O API is part of the Java TM 2 Platform, Standard Edition, version 1.4 (J2SE1.4).

The Future of Image I/O in JAI
A package set called JAI-Image I/O Tools has been released and is available via the JAI home page. The package set includes image reader and writer plug-ins for the Java Image I/O API for numerous formats, image streams which use the Java New I/O API, and JAI operations for reading and writing images using the Java Image I/O API.

In a future JAI release, the image I/O-related operators in JAI-Image I/O Tools will be propagated to JAI. It has not been definitively determined as yet, but it is likely that when the new I/O operators have been added to JAI the old operations will be deprecated.

The classes currently in the com.sun.media.jai.codec and com.sun.media.jai.codecimpl packages will most likely be removed concurrent with a JAI release subsequent to that in which the Java Image I/O API-based operators become available. However, Sun is making publicly available the source code of the com.sun.media.jai.codec and com.sun.media.jai.codecimpl classes so that developers who have written code based on them will still be able to use them. Please note that no technical support may be provided for these classes once they have been superseded by the Java Image I/O API.