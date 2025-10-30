package plugin.google.maps;

import android.graphics.Bitmap;
import androidx.collection.LruCache;

public class BitmapCache extends LruCache<String, Bitmap> {

  public BitmapCache(int maxSize) {
    super(maxSize);
  }

  @Override
  protected int sizeOf(String key, Bitmap bitmap) {
    // The cache size will be measured in kilobytes rather than
    // number of items.
    return bitmap.getByteCount() / 1024;
  }

  @Override
  protected void entryRemoved(boolean evicted, String key, Bitmap oldBitmap, Bitmap newBitmap) {
    // Don't recycle bitmaps here - they may still be in use by markers, overlays, etc.
    // Modern Android (3.0+) stores bitmaps in the heap, so the garbage collector
    // will automatically reclaim memory when the bitmap is no longer referenced.
    // Manual recycling can cause "Can't copy a recycled bitmap" crashes if the bitmap
    // is still being used elsewhere.
  }
}
