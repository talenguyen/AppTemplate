package vn.tiki.appid.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import com.tiki.appid.common.R;

public class ForegroundLinearLayout extends LinearLayoutCompat {

  private final Rect selfBounds = new Rect();
  private final Rect overlayBounds = new Rect();
  protected boolean foregroundInPadding = true;
  boolean foregroundBoundsChanged = false;
  private Drawable foreground;
  private int foregroundGravity = Gravity.FILL;

  public ForegroundLinearLayout(Context context) {
    this(context, null);
  }

  public ForegroundLinearLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ForegroundLinearLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ForegroundLinearLayout,
        defStyle, 0);

    foregroundGravity = a.getInt(
        R.styleable.ForegroundLinearLayout_android_foregroundGravity, foregroundGravity);

    final Drawable d = a.getDrawable(R.styleable.ForegroundLinearLayout_android_foreground);
    if (d != null) {
      setForeground(d);
    }

    foregroundInPadding = a.getBoolean(
        R.styleable.ForegroundLinearLayout_foregroundInsidePadding, true);

    a.recycle();
  }

  /**
   * Describes how the foreground is positioned.
   *
   * @return foreground gravity.
   * @see #setForegroundGravity(int)
   */
  public int getForegroundGravity() {
    return foregroundGravity;
  }

  /**
   * Describes how the foreground is positioned. Defaults to START and TOP.
   *
   * @param foregroundGravity See {@link android.view.Gravity}
   * @see #getForegroundGravity()
   */
  public void setForegroundGravity(int foregroundGravity) {
    if (this.foregroundGravity != foregroundGravity) {
      if ((foregroundGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) {
        foregroundGravity |= Gravity.START;
      }

      if ((foregroundGravity & Gravity.VERTICAL_GRAVITY_MASK) == 0) {
        foregroundGravity |= Gravity.TOP;
      }

      this.foregroundGravity = foregroundGravity;

      if (this.foregroundGravity == Gravity.FILL && foreground != null) {
        Rect padding = new Rect();
        foreground.getPadding(padding);
      }

      requestLayout();
    }
  }

  @Override
  protected boolean verifyDrawable(Drawable who) {
    return super.verifyDrawable(who) || (who == foreground);
  }

  @Override
  public void jumpDrawablesToCurrentState() {
    super.jumpDrawablesToCurrentState();
    if (foreground != null) {
      foreground.jumpToCurrentState();
    }
  }

  @Override
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    if (foreground != null && foreground.isStateful()) {
      foreground.setState(getDrawableState());
    }
  }

  /**
   * Returns the drawable used as the foreground of this FrameLayout. The
   * foreground drawable, if non-null, is always drawn on top of the children.
   *
   * @return A Drawable or null if no foreground was set.
   */
  public Drawable getForeground() {
    return foreground;
  }

  /**
   * Supply a Drawable that is to be rendered on top of all of the child
   * views in the frame layout.  Any padding in the Drawable will be taken
   * into account by ensuring that the children are inset to be placed
   * inside of the padding area.
   *
   * @param drawable The Drawable to be drawn on top of the children.
   */
  public void setForeground(Drawable drawable) {
    if (foreground != drawable) {
      if (foreground != null) {
        foreground.setCallback(null);
        unscheduleDrawable(foreground);
      }

      foreground = drawable;

      if (drawable != null) {
        setWillNotDraw(false);
        drawable.setCallback(this);
        if (drawable.isStateful()) {
          drawable.setState(getDrawableState());
        }
        if (foregroundGravity == Gravity.FILL) {
          Rect padding = new Rect();
          drawable.getPadding(padding);
        }
      } else {
        setWillNotDraw(true);
      }
      requestLayout();
      invalidate();
    }
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    foregroundBoundsChanged |= changed;
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    foregroundBoundsChanged = true;
  }

  @Override
  public void draw(@NonNull Canvas canvas) {
    super.draw(canvas);

    if (foreground != null) {
      final Drawable foreground = this.foreground;

      if (foregroundBoundsChanged) {
        foregroundBoundsChanged = false;
        final Rect selfBounds = this.selfBounds;
        final Rect overlayBounds = this.overlayBounds;

        final int w = getRight() - getLeft();
        final int h = getBottom() - getTop();

        if (foregroundInPadding) {
          selfBounds.set(0, 0, w, h);
        } else {
          selfBounds.set(getPaddingLeft(), getPaddingTop(),
              w - getPaddingRight(), h - getPaddingBottom());
        }

        Gravity.apply(foregroundGravity, foreground.getIntrinsicWidth(),
            foreground.getIntrinsicHeight(), selfBounds, overlayBounds);
        foreground.setBounds(overlayBounds);
      }

      foreground.draw(canvas);
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override
  public void drawableHotspotChanged(float x, float y) {
    super.drawableHotspotChanged(x, y);
    if (foreground != null) {
      foreground.setHotspot(x, y);
    }
  }
}