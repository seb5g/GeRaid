package ihm;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.BlockView;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;


public class LargeHTMLEditorKit extends HTMLEditorKit
{
  /**
   * 
   */
  private static final long serialVersionUID = -6320003968909219063L;
  ViewFactory factory = new MyViewFactory();
  private Double zoomFactor;
  
  public LargeHTMLEditorKit(Double zoomFactor)
  {
    this.zoomFactor = zoomFactor;
  }

  public double getZoomFactor() 
  {
      return zoomFactor;
  }
  
  public void setZoomFactor(Double zoom)
  {
    zoomFactor = zoom;
  }

  @Override
  public ViewFactory getViewFactory() 
  {
      return factory;
  }

  class MyViewFactory extends HTMLFactory 
  {
      @Override
      public View create(Element elem) 
      {
          AttributeSet attrs = elem.getAttributes();
          Object elementName = attrs.getAttribute(AbstractDocument.ElementNameAttribute);
          Object o = (elementName != null) ? null : attrs.getAttribute(StyleConstants.NameAttribute);
          if (o instanceof HTML.Tag) 
          {
              HTML.Tag kind = (HTML.Tag) o;
              if (kind == HTML.Tag.HTML) 
              {
                  return new HTMLBlockView(elem);
              }
              else if (kind == HTML.Tag.IMPLIED) 
              {
                  String ws = (String) elem.getAttributes().getAttribute(CSS.Attribute.WHITE_SPACE);
                  if ((ws != null) && ws.equals("pre")) 
                  {
                      return super.create(elem);
                  }
                  return new HTMLParagraphView(elem);
              } else if ((kind == HTML.Tag.P) ||
                      (kind == HTML.Tag.H1) ||
                      (kind == HTML.Tag.H2) ||
                      (kind == HTML.Tag.H3) ||
                      (kind == HTML.Tag.H4) ||
                      (kind == HTML.Tag.H5) ||
                      (kind == HTML.Tag.H6) ||
                      (kind == HTML.Tag.DT)) 
              {
                  // paragraph
                  return new HTMLParagraphView(elem);
              }
          }
          return super.create(elem);
      }

  }


  private class HTMLBlockView extends BlockView 
  {

      public HTMLBlockView(Element elem) 
      {
          super(elem,  View.Y_AXIS);
      }

      @Override
      protected void layout(int width, int height) 
      {
          if (width<Integer.MAX_VALUE) 
          {
              super.layout(new Double(width / getZoomFactor()).intValue(),
                       new Double(height *
                                  getZoomFactor()).intValue());
          }
      }

      public double getZoomFactor() 
      {
          return zoomFactor;
      }

      @Override
      public void paint(Graphics g, Shape allocation) 
      {
          Graphics2D g2d = (Graphics2D) g;
          double zoomFactor = getZoomFactor();
          AffineTransform old = g2d.getTransform();
          g2d.scale(zoomFactor, zoomFactor);
          super.paint(g2d, allocation);
          g2d.setTransform(old);
      }

      @Override
      public float getMinimumSpan(int axis) 
      {
          float f = super.getMinimumSpan(axis);
          f *= getZoomFactor();
          return f;
      }

      @Override
      public float getMaximumSpan(int axis) 
      {
          float f = super.getMaximumSpan(axis);
          f *= getZoomFactor();
          return f;
      }

      @Override
      public float getPreferredSpan(int axis) 
      {
          float f = super.getPreferredSpan(axis);
          f *= getZoomFactor();
          return f;
      }

      @Override
      public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException 
      {
          double zoomFactor = getZoomFactor();
          Rectangle alloc;
          alloc = a.getBounds();
          Shape s = super.modelToView(pos, alloc, b);
          alloc = s.getBounds();
          alloc.x *= zoomFactor;
          alloc.y *= zoomFactor;
          alloc.width *= zoomFactor;
          alloc.height *= zoomFactor;

          return alloc;
      }

      @Override
      public int viewToModel(float x, float y, Shape a,
                             Position.Bias[] bias) 
      {
          double zoomFactor = getZoomFactor();
          Rectangle alloc = a.getBounds();
          x /= zoomFactor;
          y /= zoomFactor;
          alloc.x /= zoomFactor;
          alloc.y /= zoomFactor;
          alloc.width /= zoomFactor;
          alloc.height /= zoomFactor;

          return super.viewToModel(x, y, alloc, bias);
      }

  }

}
